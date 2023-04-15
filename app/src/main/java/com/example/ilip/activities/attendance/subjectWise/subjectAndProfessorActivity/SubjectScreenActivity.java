package com.example.ilip.activities.attendance.subjectWise.subjectAndProfessorActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.R;
import com.sn.lib.NestedProgress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubjectScreenActivity extends AppCompatActivity {
    CommonModel commonModel;
    TextView expandLayoutBtn,daysAttend,daysOutOff,percent;
    LinearLayout Expandable_Cardview;
    int rotationAngle = 0;
    ConstantAPIsClass constantAPIsClass;
    RequestQueue requestQueue;
    RecyclerView recyclerView1,recyclerView2;
    TextView conductText,subjectName;
    NestedProgress np;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_screen);
        constantAPIsClass = new ConstantAPIsClass();
        requestQueue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        commonModel = (CommonModel) extras.get("CommonModel");
        Log.e("CommonModel", "model data" + commonModel);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarForSubjcet);
        toolbar.setTitle("");
        subjectName = toolbar.findViewById(R.id.subjectName);
        subjectName.setText(commonModel.getSelectedSubjectName());
        np = new NestedProgress(this);
        np = findViewById(R.id.custom_Loader);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        conductText = findViewById(R.id.conductText);
        conductText.setText(Html.fromHtml("PR::Present,<font color='#F05454'>AB::Absent</font>,S-LV::S-Leave"));
        expandLayoutBtn = findViewById(R.id.expanded_menu);
        daysAttend = findViewById(R.id.daysAttend);
        daysAttend.setText(commonModel.getDaysAttend());
        daysOutOff = findViewById(R.id.dayOutOff);
        daysOutOff.setText(commonModel.getDaysOutOff());
        percent = findViewById(R.id.percentage);
        percent.setText(commonModel.getAttendancePercent()+"%");
        double dblVal=Double.parseDouble(commonModel.getAttendancePercent());
        int data = (int) Math.abs(dblVal);
        if(data<75){
            int colorRed= 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                colorRed = getApplicationContext().getColor(R.color.dashColorRed);
            }
            daysAttend.setTextColor(colorRed);
            percent.setTextColor(colorRed);
        }else if(data>85){
            int colorGreen= 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                colorGreen = getApplicationContext().getColor(R.color.dashColorGreen);
            }
            daysAttend.setTextColor(colorGreen);
            percent.setTextColor(colorGreen);
        }else{
            int colorOrange= 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                colorOrange = getApplicationContext().getColor(R.color.dashColorOrange);
            }
            daysAttend.setTextColor(colorOrange);
            percent.setTextColor(colorOrange);
        }
        Expandable_Cardview = findViewById(R.id.expandLayout);
        recyclerView1 = findViewById(R.id.subjectTeacherList);
        recyclerView2 =findViewById(R.id.SubjectDetailsList);
        expandLayoutBtn.setOnClickListener(v -> {
            rotationAngle = rotationAngle == 0 ? 180 : 0;  //toggle
            expandLayoutBtn.animate().rotation(rotationAngle).setDuration(200).start();
            if (rotationAngle == 0) {
                Expandable_Cardview.setVisibility(View.GONE);
            } else {
                Expandable_Cardview.setVisibility(View.VISIBLE);
            }
        });
        getSubjectTeachersListData();
        SubjectDetailsList();
    }

    public void getSubjectTeachersListData(){
        np.setVisibility(View.VISIBLE);
        String url = getString(R.string.URL_WEB) + constantAPIsClass.SubjectEmployeeDetails;
        Map<String,String> postParam = new HashMap<>();
        postParam.put("PI_EMPLOYEE_STRING",commonModel.getSubjectEmployeeIds());
        ArrayList<SubjectProfessorListModel> arrayList = new ArrayList<>();
        Log.e("Subject Teacher URL",url+" == "+new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            Log.e("response for", "SubjectTeachersList" + response);
            try {
                String msg = response.getString("MESSAGE");
                if (response.get("STATUS").equals("TRUE")) {
                    np.setVisibility(View.GONE);
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        SubjectProfessorListModel subjectProfessorListModel = new SubjectProfessorListModel();
                        subjectProfessorListModel.setProfessorId(jsonObject.getString("EMPLOYEE_ID"));
                        subjectProfessorListModel.setProfessorEmailID(jsonObject.getString("MAIL_ADDRESS"));
                        subjectProfessorListModel.setProfessorMobile(jsonObject.getString("EMP_MOBILE_NO"));
                        subjectProfessorListModel.setProfessorName(jsonObject.getString("EMP_FN_MN_SHORT"));
                        subjectProfessorListModel.setProfessorDesignation(jsonObject.getString("SHORT_CODE"));
                        subjectProfessorListModel.setProfessorImageURL(jsonObject.getString("PHOTO_URL"));
                        arrayList.add(subjectProfessorListModel);
                    }
                    SubjectProfessorListDetailAdaptor subjectwiseAdaptor =
                            new SubjectProfessorListDetailAdaptor(arrayList, getApplicationContext());
                    recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
                    recyclerView1.setAdapter(subjectwiseAdaptor);
                }else{
                    np.setVisibility(View.GONE);
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                np.setVisibility(View.GONE);
            }

        }, error -> np.setVisibility(View.GONE)) {
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
                np.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
   public void SubjectDetailsList() {
       np.setVisibility(View.VISIBLE);
        String url = getString(R.string.URL_WEB) + constantAPIsClass.SubjectDetailsList;
        Map<String,String> postParam = new HashMap<>();
        postParam.put("PI_SESSION_ID",commonModel.getACAD_SESSION_ID());
        postParam.put("PI_SUBJECT_DETAIL_ID",commonModel.getSubjectDetailId());
        postParam.put("PI_APPLICABLE_NUMBER",commonModel.getSubjectApplicableNo());
        postParam.put("PI_STU_BATCH_DET_ID",commonModel.getSubjectBatchDtlId());
        postParam.put("PI_STUDENT_ID",commonModel.getSTUDENT_ID());

        Log.e("Params=","data=="+postParam);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                String msg = response.getString("MESSAGE");
                ArrayList<DashboardCourseAttendanceModel> arrayList = new ArrayList<>();
                Log.e("response for", "SubjectDetailsList" + response);
                if (response.getString("STATUS").equals("TRUE")) {
                    np.setVisibility(View.GONE);
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObj = (JSONObject) jsonArray.get(i);
                        DashboardCourseAttendanceModel modeldata = new DashboardCourseAttendanceModel();
                        modeldata.setTopicName(jObj.getString("TOPIC_NAME"));
                        modeldata.setTopicDescription(jObj.getString("TOPIC_DESCRIPTION"));
                        modeldata.setSyllabusDescription(jObj.getString("SYLLABUS_DESCRIPTION"));
                        modeldata.setDayWiseASON_date(jObj.getString("ATTENDANCE_DATE"));
                        modeldata.setDayWisePeriodTypeCode(" [ " + jObj.getString("PERIOD_TYPE_PRINT") + " ] ");
                        modeldata.setDayWiseTimeDuration(jObj.getString("PERIOD_FROM_TIME_PRINT") + " to " + jObj.getString("PERIOD_UPTO_TIME_PRINT"));
                        modeldata.setDayWiseAttendanceStatus(jObj.getString("STATUS"));
                        modeldata.setDayWiseAttendStatusSCode(jObj.getString("STATUS_PRINT"));
                        modeldata.setDayWiseEmpName(jObj.getString("EMP_FN_MN_LN_SHORT"));
                        if (modeldata.getDayWiseAttendStatusSCode().equals("PR") || modeldata.getDayWiseAttendStatusSCode().equals("AB") || modeldata.getDayWiseAttendStatusSCode().equals("S-LV")) {
                            modeldata.setDaywiseImageAttendStus(getResources().getIdentifier("attended", "drawable", getApplicationContext().getPackageName()));
                        } else if (modeldata.getDayWiseAttendStatusSCode().equals("NU") || modeldata.getDayWiseAttendStatusSCode().equals("NA") || modeldata.getDayWiseAttendStatusSCode().equals("")) {
                            modeldata.setDaywiseImageAttendStus(getResources().getIdentifier("yetnot_updated", "drawable", getApplicationContext().getPackageName()));
                        } else {
                            modeldata.setDaywiseImageAttendStus(getResources().getIdentifier("not_attended", "drawable", getApplicationContext().getPackageName()));
                        }
                        arrayList.add(modeldata);
                    }
                    SubjectListDetailAdaptor subjectListDetailAdaptor = new SubjectListDetailAdaptor(arrayList, getApplicationContext());
                    recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView2.setAdapter(subjectListDetailAdaptor);
                    subjectListDetailAdaptor.notifyDataSetChanged();
                }else{
                    np.setVisibility(View.GONE);
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                np.setVisibility(View.GONE);
            }
        }, error -> np.setVisibility(View.GONE)) {
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
                np.setVisibility(View.GONE);
            }
        });
       requestQueue.add(jsonObjectRequest);
    }
}