package com.example.ilip.activities.syllabus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.all_model.DashboardCourseAttendanceModel;
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.common.PreferManager;
import com.example.ilip.R;
import com.sn.lib.NestedProgress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySyllabusActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    CommonModel commonModel;
    ConstantAPIsClass constantAPIsClass;
    PreferManager preferManager;
    ArrayList<DashboardCourseAttendanceModel> arrayList;
    RecyclerView recyclerView;
    NestedProgress nestedProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        commonModel = (CommonModel) extras.get("CommonModel");
        Log.e("CommonModel", "model data" + commonModel);
        if(commonModel.getLoginType().equals("Student")){
            setTheme(R.style.StudentTheme);
        }else{
            setTheme(R.style.ParentTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_syllabus);
        preferManager = new PreferManager(this);
        requestQueue = Volley.newRequestQueue(this);
        constantAPIsClass = new ConstantAPIsClass();
        nestedProgress = new NestedProgress(this);
        nestedProgress = findViewById(R.id.custom_Loader_Syllabus);
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        commonModel = (CommonModel) extras.get("CommonModel");
//        Log.e("CommonModel", "model data" + commonModel);

        Toolbar toolbar = findViewById(R.id.toolbarForSyllabus);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        recyclerView = findViewById(R.id.mySyllabus);
        MyCourseArrayData();
    }
    public void MyCourseArrayData(){
        nestedProgress.setVisibility(View.VISIBLE);
        String url = getString(R.string.URL_WEB)+constantAPIsClass.getMyCourseApiCall;
        Map<String,String> postParm = new HashMap<>();
        postParm.put("PI_STUDENT_ID",commonModel.getSTUDENT_ID());
        postParm.put("PI_SESSION_ID",commonModel.getACAD_SESSION_ID());
        postParm.put("PI_SEMESTER_MST_ID",commonModel.getMAIN_SEMESTER_MST_ID());
        postParm.put("PI_CENTRE_CODE",commonModel.getCENTRE_CODE());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParm), response -> {
            Log.e("My Course", "syllabus" + response);

            if (response != null) {
                try {
                    nestedProgress.setVisibility(View.GONE);
                    arrayList = new ArrayList<>();
                    JSONArray jsonArray = response.getJSONArray("My_Course");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jObj = (JSONObject) jsonArray.get(i);
                            DashboardCourseAttendanceModel modeldata = new DashboardCourseAttendanceModel();
                            modeldata.setCourseTitle(jObj.getString("SUBJECT_DESCRIPTION"));
                            modeldata.setCourseType(jObj.getString("SUBJECT_TYPE_DESCRIPTION"));
                            modeldata.setCourseFacultyName(jObj.getString("EMP_NAME"));
                            modeldata.setCourseUniversityCode(jObj.getString("UNIVERSITY_CODE"));
                            modeldata.setCourseRatio1(jObj.getString("COVERED_TOPICS"));
                            modeldata.setCourseRatio2(jObj.getString("TOTAL_TOPICS"));
                            modeldata.setSubjectDetailsId(jObj.getString("SUBJECT_DETAIL_ID"));
                            modeldata.setSubjectGroupId(jObj.getString("SUBJECT_GROUP_ID"));
                            modeldata.setSubjectApplicableNo(jObj.getString("APPLICABLE_NUMBER"));
                            modeldata.setSubjectEmployeeId(jObj.getString("EMPLOYEE_ID"));
                            modeldata.setSubjectBatchDetailId(jObj.getString("STU_BATCH_DET_ID"));
                            modeldata.setCompulsory_Optional_Flag(jObj.getString("COMPULSORY_OPTIONAL_FLAG"));
                            String data1 = "0";
                            if (jObj.getString("SYLLABUS_PERCTG").equals("")) {
                                data1 = "0";
                            } else {
                                data1 = jObj.getString("SYLLABUS_PERCTG");
                            }
                            modeldata.setCoursePercentage(data1);
                            arrayList.add(modeldata);
                        } catch (Exception e) {
                            nestedProgress.setVisibility(View.GONE);
                        }
                    }
                    MySyllabusListAdaptor subjectAdaptor = new MySyllabusListAdaptor(arrayList, getApplicationContext(), constantAPIsClass, commonModel);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(subjectAdaptor);
                    subjectAdaptor.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    nestedProgress.setVisibility(View.GONE);
                }
            }
        }, error -> {

        }){

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