package com.example.ilip.activities.attendance.subjectWise;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.all_model.DashboardCourseAttendanceModel;
import com.example.ilip.common.ApiUniversalyCall;
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.common.PreferManager;
import com.example.ilip.R;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.sn.lib.NestedProgress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SubjectWiseFragment extends Fragment {
    CommonModel commonModel;
    RecyclerView recyclerView;
    PreferManager preferManager;
    ApiUniversalyCall apiUniversalyCall;
    ArrayList<DashboardCourseAttendanceModel> arrayList;
    TextView /*home_screenRefresh,*/totalPresent,totalLectures,totalRatio;
    Button home_screenRefresh;
    CircularProgressIndicator circularProgressIndicator;
    TextView branchCode,semesterCode,currentSession;
    RequestQueue requestQueue;
    ConstantAPIsClass constantAPIsClass;
    NestedProgress nestedProgress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferManager = new PreferManager(getContext());
        apiUniversalyCall = new ApiUniversalyCall(getContext());
        requestQueue = Volley.newRequestQueue(getContext());
        constantAPIsClass = new ConstantAPIsClass();
        nestedProgress = new NestedProgress(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_subject_wise, container, false);
        Bundle extras;
        extras = this.getArguments();
        if (extras != null) {
            commonModel = (CommonModel) extras.get("CommonModel");
        }
        Log.e("SubjectWise","Data"+commonModel);
        nestedProgress = view.findViewById(R.id.custom_Loader);
        branchCode = view.findViewById(R.id.sub_branchCode1);
        branchCode.setText(commonModel.getBRANCH_STANDARD_GROUP_CODE());
        semesterCode = view.findViewById(R.id.semCode1);
        semesterCode.setText(commonModel.getSemesterCode());
        currentSession = view.findViewById(R.id.currentSession1);
        currentSession.setText(commonModel.getCurrentSession());
        home_screenRefresh = view.findViewById(R.id.home_screen_Refresh);
        home_screenRefresh.setOnClickListener(v -> {
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.frm,new SubjectWiseFragment()).addToBackStack(null).commit();
            FirstAPiCallToRefresh(commonModel.getACAD_SESSION_ID(),"E",commonModel.getMAIN_SEMESTER_MST_ID(),preferManager.getuserId());
        });
        totalPresent = view.findViewById(R.id.totalPresent);
        totalLectures = view.findViewById(R.id.totalLectures);
        circularProgressIndicator = view.findViewById(R.id.progress_bar);
        totalRatio = view.findViewById(R.id.attendance_Percent);
        recyclerView = view.findViewById(R.id.subjectWiseList);


        FirstAPiCallToRefresh(commonModel.getACAD_SESSION_ID(),"E",commonModel.getMAIN_SEMESTER_MST_ID(),preferManager.getuserId());

        return view;
    }
    public void FirstAPiCallToRefresh(String sessionId,String semesterType,String semisterMstId,String studentId){
       nestedProgress.setVisibility(View.VISIBLE);
        String url = getString(R.string.URL_WEB) + ConstantAPIsClass.DashboardRefreshFirstAPICall;
        Map<String,String> postParam= new HashMap<>();
        postParam.put("PI_SESSION_ID",sessionId);
        postParam.put("PI_SEMESTER_TYPE",semesterType);
        postParam.put("PI_SEMESTER_MST_ID",semisterMstId);
        postParam.put("PI_STUDENT_ID",studentId);
        Log.e("SubjectWise URL",url+" == "+new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    Log.e("Refresh", "FirstAPI Call==" + response);
                    String message = response.getString("MESSAGE");
                    if (response.get("STATUS").equals("TRUE")) {
                        nestedProgress.setVisibility(View.GONE);
                        getAttendanceStatus(commonModel.getSTUDENT_ID(), commonModel.getACAD_SESSION_ID()
                                , commonModel.getMAIN_SEMESTER_MST_ID(), commonModel.getBRANCH_STANDARD_GRP_ID());
                        getSyllabusStatus();
                        // Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
            }
        }, error -> nestedProgress.setVisibility(View.GONE))
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                nestedProgress.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    public void getAttendanceStatus(String StudentId, String SessionId, String SemMstId, String BranchStdGrpId) {
        nestedProgress.setVisibility(View.VISIBLE);
        String url = getContext().getString(R.string.URL_WEB) + constantAPIsClass.studentAttendanceStatus;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_STUDENT_ID", StudentId);
        postParam.put("PI_SESSION_ID", SessionId);
        postParam.put("PI_SEMESTER_MST_ID", SemMstId);
        postParam.put("PI_BRANCH_STANDARD_GRP_ID", BranchStdGrpId);
        Log.e("Attendance URL",url+" == "+new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    Log.e("Attendance Status", "urlis =====" + response);
                    if (response.get("STATUS").equals("TRUE")) {
                        nestedProgress.setVisibility(View.GONE);
                        try {
                            Log.e("SubjectWise", "syllabusin Json" + response);
                            if (response.get("STATUS").equals("TRUE")) {
                                JSONArray jsonArray1 = response.getJSONArray("data");
                                if (jsonArray1 != null) {
                                    for (int i = 0; i < jsonArray1.length(); i++) {
                                        try {
                                            JSONObject jObj = (JSONObject) jsonArray1.get(i);
                                            home_screenRefresh.setText(jObj.getString("ASONDT"));
                                            totalPresent.setText(jObj.getString("TOTPRD_PRESENT"));
                                            totalLectures.setText(jObj.getString("TOTPRD_APPLICABLE"));
                                            double dblVal = Double.parseDouble(jObj.getString("STUD_INITIAL"));
                                            int digit = (int) Math.abs(dblVal);
                                            totalRatio.setText(digit + "%");
                                            circularProgressIndicator.setProgress(digit);
                                            if (digit < 75) {
                                                int colorRed = 0;
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    colorRed = getContext().getColor(R.color.dashColorRed);
                                                }
                                                totalPresent.setTextColor(colorRed);
                                                circularProgressIndicator.setIndicatorColor(colorRed);
                                                totalRatio.setTextColor(colorRed);
                                            } else if (digit > 85) {
                                                int colorGreen = 0;
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    colorGreen = getContext().getColor(R.color.dashColorGreen);
                                                }
                                                totalPresent.setTextColor(colorGreen);
                                                circularProgressIndicator.setIndicatorColor(colorGreen);
                                                totalRatio.setTextColor(colorGreen);
                                            } else {
                                                int colorOrange = 0;
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    colorOrange = getContext().getColor(R.color.dashColorOrange);
                                                }
                                                totalPresent.setTextColor(colorOrange);
                                                circularProgressIndicator.setIndicatorColor(colorOrange);
                                                totalRatio.setTextColor(colorOrange);
                                            }
                                        } catch (Exception e) {
                                            Log.e("Exception", "data is=" + e);
                                        }
                                    }

                                }

                                //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                nestedProgress.setVisibility(View.GONE);
            }


        }, error -> nestedProgress.setVisibility(View.GONE)) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                nestedProgress.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    public void getSyllabusStatus() {
        nestedProgress.setVisibility(View.VISIBLE);
        arrayList = new ArrayList<>();
        String url = getString(R.string.URL_WEB) + constantAPIsClass.studentSyllabusStatus;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_STUDENT_ID", commonModel.getSTUDENT_ID());
        postParam.put("PI_SESSION_ID", commonModel.getACAD_SESSION_ID());
        postParam.put("PI_SEMESTER_MST_ID", commonModel.getMAIN_SEMESTER_MST_ID());
        postParam.put("PI_BRANCH_STANDARD_GRP_ID", commonModel.getBRANCH_STANDARD_GRP_ID());
        postParam.put("PI_CENTRE_CODE", commonModel.getCENTRE_CODE());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            Log.e("SubjectWise", "syllabus" + response);

            if (response != null) {
                try {

                    arrayList = new ArrayList<>();
                    Log.e("SubjectWise", "syllabusin Json" + response);
                    if (response.getString("STATUS").equals("TRUE")) {
                        nestedProgress.setVisibility(View.GONE);
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jObj = (JSONObject) jsonArray.get(i);
                                DashboardCourseAttendanceModel modeldata = new DashboardCourseAttendanceModel();
                                modeldata.setCourseTitle(jObj.getString("SUBJECT_DESCRIPTION") + "(" + jObj.getString("SUBJECT_SHORT_DESCRIPTION") + ")");
                                modeldata.setCourseType(jObj.getString("SUBJ_TYPE_DESC"));
                                modeldata.setCourseRatio1(jObj.getString("TOTPRD_PRESENT"));
                                modeldata.setCourseRatio2(jObj.getString("TOTPRD_APPLICABLE"));
                                modeldata.setSubjectDetailsId(jObj.getString("SUBJECT_DETAIL_ID"));
                                modeldata.setSubjectApplicableNo(jObj.getString("APPLICABLE_NUMBER"));
                                modeldata.setSubjectEmployeeId(jObj.getString("EMPLOYEE_IDS"));
                                modeldata.setSubjectBatchDetailId(jObj.getString("STU_BATCH_DET_ID"));
                                String data1 = "0";
                                if (jObj.getString("SUBJ_TOTPRD_PERCTG").equals("-")) {
                                    data1 = "0";
                                } else {
                                    data1 = jObj.getString("SUBJ_TOTPRD_PERCTG");
                                }
                                modeldata.setCoursePercentage(data1);
                                arrayList.add(modeldata);
                            } catch (Exception e) {
                                nestedProgress.setVisibility(View.GONE);
                            }
                        }
                        SubjectwiseAdaptor subjectwiseAdaptor = new SubjectwiseAdaptor(arrayList, getContext(), commonModel);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(subjectwiseAdaptor);
                        subjectwiseAdaptor.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    nestedProgress.setVisibility(View.GONE);
                }
            }

        }, error -> nestedProgress.setVisibility(View.GONE)){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                nestedProgress.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


}