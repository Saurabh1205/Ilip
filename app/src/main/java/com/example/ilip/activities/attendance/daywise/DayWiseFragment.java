package com.example.ilip.activities.attendance.daywise;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DayWiseFragment extends Fragment implements View.OnClickListener {
    TextView selectedDate, calenderOpen, expandLayoutBtn;
    TextView branchCode,semesterCode,currentSession;
    Calendar myCalendar;
    String data;
    //ApiUniversalyCall apicall;
    JSONObject jsonObject;
    CommonModel commonModel;
    LinearLayout Expandable_Cardview;
    int rotationAngle = 0;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    ConstantAPIsClass constantAPIsClass;
    String selecteddate;
    DaywiseListAdapter mAdapter;
    Date minDate, maxDate;
    SimpleDateFormat dateFormat;
    LinearLayout noDataFoundLayout;
    TextView setTextId;
    NestedProgress np;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constantAPIsClass = new ConstantAPIsClass();
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        dateFormat = new SimpleDateFormat(myFormat);
        selecteddate = new SimpleDateFormat(myFormat, Locale.getDefault()).format(new Date());
        requestQueue = Volley.newRequestQueue(getContext());
        np = new NestedProgress(getContext());
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day_wise, container, false);
        Bundle extras;
        extras = this.getArguments();
        if (extras != null) {
            commonModel = (CommonModel) extras.get("CommonModel");
        }
        Log.e("FrameDayWise", "Data" + commonModel);
//        try {
//            minDate = dateFormat.parse(commonModel.getMinDate());
//            maxDate = dateFormat.parse(commonModel.getMaxDate());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = (view1, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(data, myCalendar);
        };
        calenderOpen = view.findViewById(R.id.calender);
        calenderOpen.setOnClickListener(v -> {
            int mYear = myCalendar.get(Calendar.YEAR);
            int mMonth = myCalendar.get(Calendar.MONTH);
            int mDay = myCalendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(), R.style.DialogTheme, date, mYear, mMonth, mDay);
            dateDialog.getDatePicker().setMinDate(minDate.getTime());
            dateDialog.getDatePicker().setMaxDate(maxDate.getTime());
            dateDialog.show();
        });
        setTextId =view.findViewById(R.id.setTextId);
        setTextId.setText(Html.fromHtml("PR::Present,<font color='#F05454'>AB::Absent</font>,S-LV::S-Leave"));
        noDataFoundLayout = view.findViewById(R.id.noDataFoundLayout);
        branchCode = view.findViewById(R.id.branchCode2);
        branchCode.setText(commonModel.getBRANCH_STANDARD_GROUP_CODE());
        semesterCode = view.findViewById(R.id.semCode2);
        currentSession = view.findViewById(R.id.currentSession2);
        selectedDate = view.findViewById(R.id.selectedDate);
        expandLayoutBtn = view.findViewById(R.id.expanded_menu);
        Expandable_Cardview = view.findViewById(R.id.expandLayout);
        expandLayoutBtn.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.dayWiseListdata);
        // recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        DateSpanForCalender(commonModel.getMAIN_SEMESTER_MST_ID(),commonModel.getBRANCH_STANDARD_ID());
        dayWiseAPICAll(commonModel.getSTUDENT_ID(),
                commonModel.getACAD_SESSION_ID(), commonModel.getMAIN_SEMESTER_MST_ID(), commonModel.getBRANCH_STANDARD_GRP_ID(), selecteddate);
        return view;

    }

    private void updateLabel(String date, Calendar calendar) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date = sdf.format(calendar.getTime());
        Toast.makeText(getContext(), "Selected date=" + date, Toast.LENGTH_SHORT).show();
        dayWiseAPICAll(commonModel.getSTUDENT_ID(),
                commonModel.getACAD_SESSION_ID(), commonModel.getMAIN_SEMESTER_MST_ID(), commonModel.getBRANCH_STANDARD_GRP_ID(), date);

    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        if (itemId == R.id.expanded_menu) {
            rotationAngle = rotationAngle == 0 ? 180 : 0;  //toggle
            expandLayoutBtn.animate().rotation(rotationAngle).setDuration(200).start();
            if (rotationAngle == 0) {
                Expandable_Cardview.setVisibility(View.GONE);
            } else {
                Expandable_Cardview.setVisibility(View.VISIBLE);
            }
        }
    }
    public void DateSpanForCalender(String SemesterMstId,String BranchStdId){
        np.setVisibility(View.VISIBLE);
        String url = getString(R.string.URL_WEB) + constantAPIsClass.calenderSpan;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_SEMESTER_MST_ID", SemesterMstId);
        postParam.put("PI_BRANCH_STANDARD_ID", BranchStdId);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            // jsonObject = response;
            try {
                if (response != null) {
                    np.setVisibility(View.GONE);
                    Log.e("Calender Start&End", "url date =====" + response);
                    if (response.get("STATUS").equals("TRUE")) {
//                          Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i <= jsonArray.length(); i++) {
                            try {
                                JSONObject jObj = (JSONObject) jsonArray.get(i);
                                semesterCode.setText(jObj.getString("SEMESTER_CODE") + "-" + jObj.getString("SEMESTER_SRNO"));
                                currentSession.setText(jObj.getString("SESSION_NAME"));
                                commonModel.setMinDate(jObj.getString("ATT_START_DATE"));
                                commonModel.setMaxDate(jObj.getString("ATT_END_DATE"));
                                minDate = dateFormat.parse(commonModel.getMinDate());
                                maxDate = dateFormat.parse(commonModel.getMaxDate());
                                commonModel.setSemesterCode(semesterCode.getText().toString());
                                commonModel.setCurrentSession(currentSession.getText().toString());
                            } catch (Exception e) {
                                np.setVisibility(View.GONE);
                            }
                        }

                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
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
    public void dayWiseAPICAll(String StudentId, String SessionId, String SemMstId, String BranchStdGrpId, String Date_is) {
        requestQueue = Volley.newRequestQueue(getContext());
        np.setVisibility(View.VISIBLE);
        String url = getString(R.string.URL_WEB) + constantAPIsClass.day_WiseAPI;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_STUDENT_ID", StudentId);
        postParam.put("PI_SESSION_ID", SessionId);
        postParam.put("PI_SEMESTER_MST_ID", SemMstId);
        postParam.put("PI_BRANCH_STANDARD_ID", BranchStdGrpId);
        postParam.put("PI_DATE", Date_is);
        Log.e("Daywise URL",url+" == "+new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    np.setVisibility(View.GONE);
                    Log.e("dayWise Status", "url =====" + response);
                    ArrayList<DashboardCourseAttendanceModel> arrayList = new ArrayList();
                    arrayList.clear();
                    selectedDate.setText(response.getString("Date"));
                    String message = response.getString("MESSAGE");
                    if (response.get("STATUS").equals("TRUE")) {
                        recyclerView.setVisibility(View.VISIBLE);
                        noDataFoundLayout.setBackgroundResource(R.color.white);
//                          Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        branchCode.setText(response.getString("BRANCH_STANDARD_GROUP_CODE"));
                        commonModel.setBRANCH_STANDARD_GROUP_CODE(branchCode.getText().toString());
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i <= jsonArray.length(); i++) {
                            try {
                                JSONObject jObj = (JSONObject) jsonArray.get(i);
                                DashboardCourseAttendanceModel modeldata = new DashboardCourseAttendanceModel();
                                modeldata.setDayWiseSubDtl(jObj.getString("SUBJECT_DESCRIPTION"));
                                modeldata.setDayWiseSubType("( " + jObj.getString("TYPE_DESCRIPTION") + " )");
                                modeldata.setDayWiseASON_date(jObj.getString("ASON_FORMAT2"));
                                modeldata.setDayWisePeriodTypeCode(" [ " + jObj.getString("PERIOD_TYPE_SHORT_CODE") + " ] ");
                                modeldata.setDayWiseTimeDuration(jObj.getString("PERIOD_FROM_TIME_PRINT") + " to " + jObj.getString("PERIOD_UPTO_TIME_PRINT"));
                                modeldata.setDayWiseAttendanceStatus(jObj.getString("ATT_STATUS"));
                                modeldata.setDayWiseAttendStatusSCode(jObj.getString("ATT_STATUS_SHORT_CODE"));
                                modeldata.setDayWiseEmpName(jObj.getString("EMPLOYEE_NAME"));
                                if (modeldata.getDayWiseAttendStatusSCode().equals("PR") || modeldata.getDayWiseAttendStatusSCode().equals("AB") || modeldata.getDayWiseAttendStatusSCode().equals("S-LV")) {
                                    modeldata.setDaywiseImageAttendStus(getResources().getIdentifier("attended", "drawable", getActivity().getPackageName()));
                                } else if (modeldata.getDayWiseAttendStatusSCode().equals("NU") || modeldata.getDayWiseAttendStatusSCode().equals("NA") || modeldata.getDayWiseAttendStatusSCode().equals("")) {
                                    modeldata.setDaywiseImageAttendStus(getResources().getIdentifier("yetnot_updated", "drawable", getActivity().getPackageName()));
                                } else {
                                    modeldata.setDaywiseImageAttendStus(getResources().getIdentifier("not_attended", "drawable", getActivity().getPackageName()));
                                }
                                arrayList.add(modeldata);
                            } catch (Exception e) {
                                Log.e("errro=", "errr===" + e);
                            }
                        }
                        mAdapter = new DaywiseListAdapter(arrayList, getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        mAdapter.setHasStableIds(true);
                    } else {
                        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                        noDataFoundLayout.setBackgroundResource(R.drawable.no_data_found);
                        recyclerView.setVisibility(View.GONE);
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
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