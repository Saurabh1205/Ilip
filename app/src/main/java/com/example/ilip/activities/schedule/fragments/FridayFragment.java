package com.example.ilip.activities.schedule.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ilip.activities.schedule.AdaptorforExtraLectures;
import com.example.ilip.activities.schedule.ScheduleClassAdaptor;
import com.example.ilip.activities.schedule.ScheduleClassModel;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.orhanobut.dialogplus.DialogPlus;
import com.sn.lib.NestedProgress;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FridayFragment extends Fragment {
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    ConstantAPIsClass constantAPIsClass;
    CommonModel commonModel;
    ExtendedFloatingActionButton fab;
    ScheduleClassModel scheduleClassModel;
    ArrayList<ScheduleClassModel> arrayList,arrayList1;
    ImageView holidayImg;
    NestedProgress nestedProgress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constantAPIsClass = new ConstantAPIsClass();
        requestQueue = Volley.newRequestQueue(getContext());
        nestedProgress = new NestedProgress(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friday, container, false);
        Bundle extras;
        extras = this.getArguments();
        if (extras != null) {
            commonModel = (CommonModel) extras.get("CommonModel");
        }
        Log.e("FrameDayWise", "Data" + commonModel);
        recyclerView = view.findViewById(R.id.Friday);
        fab = view.findViewById(R.id.fab_Friday);
        holidayImg = view.findViewById(R.id.fri);
        nestedProgress = view.findViewById(R.id.custom_Loader);
        fab.hide();
        fab.setOnClickListener(v -> ExtraLectureLayoutView(v.getContext()));
        getScheduleDayWise();

        return view;
    }

    public void getScheduleDayWise() {
        nestedProgress.setVisibility(View.VISIBLE);
        String url = getString(R.string.URL_WEB) + constantAPIsClass.getScheduleDetails;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_STUDENT_ID", commonModel.getSTUDENT_ID());
        postParam.put("PI_SESSION_ID", commonModel.getACAD_SESSION_ID());
        postParam.put("PI_SEMESTER_MST_ID", commonModel.getMAIN_SEMESTER_MST_ID());
        postParam.put("PI_CENTRE_CODE", commonModel.getCENTRE_CODE());
        postParam.put("PI_WEEK_DAY_ID", "6");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            // jsonObject = response;
            try {
                if (response != null) {
                    nestedProgress.setVisibility(View.GONE);
                    Log.e("Friday", "url date =====" + response);
                    if (response.getString("OFF_STATUS").equals("Y")) {
                        holidayImg.setImageResource(R.drawable.holiday);
                        holidayImg.setVisibility(View.VISIBLE);
                    } else if (response.getString("DAY_SHEDULE_COUNT").equals("0")) {
                        holidayImg.setImageResource(R.drawable.no_data_found);
                        holidayImg.setVisibility(View.VISIBLE);
                    }
                    if (response.get("STATUS").equals("TRUE")) {

//                          Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        arrayList = new ArrayList<>();
                        arrayList1 = new ArrayList<>();
                        JSONArray jsonArray = response.getJSONArray("Day_Shedule");
                        JSONArray jsonArray1 = response.getJSONArray("Extra_Class");
                        for (int i = 0; i <= jsonArray.length(); i++) {
                            try {
                                JSONObject jObj = (JSONObject) jsonArray.get(i);
                                scheduleClassModel = new ScheduleClassModel();
                                scheduleClassModel.setTimeSlot(jObj.getString("TIME_SLOT"));
                                scheduleClassModel.setSubDescription(jObj.getString("SUBJECT_DESCRIPTION"));
                                scheduleClassModel.setUniversityCode(jObj.getString("UNIVERSITY_CODE"));
                                scheduleClassModel.setSubjectType(jObj.getString("SUBJECT_TYPE_DESCRIPTION"));
                                scheduleClassModel.setEmployeeNameShort(jObj.getString("EMP_FN_MN_LN_SHORT"));
                                scheduleClassModel.setSubjectBatchName(jObj.getString("SUB_BATCH_NAME"));
                                scheduleClassModel.setRoomCode(jObj.getString("ROOM_CODE"));
                                scheduleClassModel.setRecessDescription(jObj.getString("RECESS_DESC"));
                                scheduleClassModel.setRecessDuration(jObj.getString("RECESS_DURATION"));
                                scheduleClassModel.setWeekly_Off_Status(jObj.getString("WEEKLY_OFF_STATUS"));
                                arrayList.add(scheduleClassModel);
                            } catch (Exception e) {

                            }
                        }
                        ScheduleClassAdaptor scheduleClassAdaptor = new ScheduleClassAdaptor(arrayList, getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(scheduleClassAdaptor);
                        scheduleClassModel.setExtraLectureCount(response.getString("Extra_Lec_Count"));
                        scheduleClassModel.setDayScheduleCount(response.getString("DAY_SHEDULE_COUNT"));
                        scheduleClassModel.setOffStatus(response.getString("OFF_STATUS"));
                        Log.e("Extended Fab", "data===" + scheduleClassModel.getExtraLectureCount() + scheduleClassModel.getOffStatus() + scheduleClassModel.getDayScheduleCount());
                        if (Integer.parseInt(scheduleClassModel.getExtraLectureCount()) > 0) {
                            fab.show();
                            for (int i = 0; i < jsonArray1.length(); i++) {
                                try {
                                    JSONObject jObj = (JSONObject) jsonArray1.get(i);
                                    scheduleClassModel = new ScheduleClassModel();
                                    scheduleClassModel.setDate(jObj.getString("DATE1") + " (" + jObj.getString("DAY1") + ")");
                                    scheduleClassModel.setTimeSlot(jObj.getString("FROM_TIME") + " to " + jObj.getString("UPTO_TIME"));
                                    scheduleClassModel.setSubDescription(jObj.getString("SUBJECT_DESCRIPTION"));
                                    scheduleClassModel.setUniversityCode(jObj.getString("UNIVERSITY_CODE"));
                                    scheduleClassModel.setSubjectType(jObj.getString("SUBJECT_TYPE_DESCRIPTION"));
                                    scheduleClassModel.setEmployeeNameShort(jObj.getString("EMP_NAME") + " (" + jObj.getString("EMPLOYEE_ID") + ")");
                                    scheduleClassModel.setSubjectBatchName(jObj.getString("SUB_BATCH_NAME"));
                                    scheduleClassModel.setRoomCode(jObj.getString("ROOM_CODE"));
                                    arrayList1.add(scheduleClassModel);
                                } catch (Exception e) {
                                    Log.e("error", "errrrr==" + e);
                                }
                            }
                        }
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
    public void ExtraLectureLayoutView(Context context) {
        final DialogPlus inflate = DialogPlus.newDialog(context)
                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.popup_extra_lectures_layout))
                .setExpanded(true, WindowManager.LayoutParams.MATCH_PARENT)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(true)
                .create();
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.toolbar4ExtraLectures);
        toolbar.setTitle("");
        toolbar.setNavigationOnClickListener(view -> inflate.dismiss());
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.extraLecturesList);
        AdaptorforExtraLectures adaptor = new AdaptorforExtraLectures(arrayList1,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptor);
        inflate.show();
    }
}