package com.example.ilip.activities.syllabus.actionPlan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ilip.activities.syllabus.UniversitySyllabusModel;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.common.PreferManager;
import com.example.ilip.R;
import com.sn.lib.NestedProgress;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActionPlanActivity extends AppCompatActivity {
    PreferManager preferManager;
    CommonModel commonModel;
    RequestQueue requestQueue;
    ConstantAPIsClass constantAPIsClass;
    TextView subjectName,subjectType,facultyName;
    RecyclerView recyclerView;
    ImageView actionNoData;
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
        setContentView(R.layout.activity_action_plan);
        preferManager = new PreferManager(this);
        requestQueue = Volley.newRequestQueue(this);
        constantAPIsClass = new ConstantAPIsClass();
        nestedProgress = new NestedProgress(this);
        nestedProgress = findViewById(R.id.custom_Loader_Action_plan);
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        commonModel = (CommonModel) extras.get("CommonModel");
//        Log.e("CommonModel", "model data" + commonModel);

        Toolbar toolbar = findViewById(R.id.toolbarForActionPlan);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        actionNoData = findViewById(R.id.actionNoData);
        subjectName = findViewById(R.id.subjectTitle);
        subjectType = findViewById(R.id.subjectType);
        facultyName = findViewById(R.id.professorName);
        subjectName.setText(commonModel.getSelectedSubjectName());
        subjectType.setText(commonModel.getSubjectType());
        facultyName.setText(commonModel.getSubjectEmployeeName());
        recyclerView = findViewById(R.id.actionPlan_DataList);
        ActionPlan(commonModel.getSubjectGroupId(),commonModel.getSubjectDetailId(),commonModel.getSubjectApplicableNo(),commonModel.getSubjectBatchDtlId(),
                commonModel.getACAD_SESSION_ID(),commonModel.getSubjectEmployeeIds());
    }
    public void ActionPlan(String subjectGroupId,String subjectDetailId,String ApplicableNo,String StudentBatchDetailId,
                           String SessionId,String EmployeeId){
        nestedProgress.setVisibility(View.VISIBLE);
        String url = getString(R.string.URL_WEB)+constantAPIsClass.SubjectActionPlan;
        Map<String,String> postParam = new HashMap<>();
        postParam.put("PI_SUBJECT_GROUP_ID",subjectGroupId);
        postParam.put("PI_SUBJECT_DETAIL_ID",subjectDetailId);
        postParam.put("PI_APPLICABLE_NUMBER",ApplicableNo);
        postParam.put("PI_STU_BATCH_DET_ID",StudentBatchDetailId);
        postParam.put("PI_SESSION_ID",SessionId);
        postParam.put("PI_EMPLOYEE_STRING",EmployeeId);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                Log.e("Action paln", "Subject==" + response);
                ArrayList<UniversitySyllabusModel> arrayList = new ArrayList<>();
                String Msg = response.getString("MESSAGE");
                if (response.getString("STATUS").equals("TRUE")) {
                    nestedProgress.setVisibility(View.GONE);
                    actionNoData.setVisibility(View.GONE);
                    JSONArray jsonArray = response.getJSONArray("Data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        UniversitySyllabusModel model = new UniversitySyllabusModel();
                        model.setSR_NO(jsonObject.getString("SR_NO"));
                        model.setSYLLABUS_DESCRIPTION(jsonObject.getString("SYLLABUS_DESCRIPTION"));
                        model.setWEIGHTAGE(jsonObject.getString("WEIGHTAGE"));
                        model.setTOPIC_NAME(jsonObject.getString("TOPIC_NAME"));
                        model.setTOPIC_DESCRIPTION(jsonObject.getString("TOPIC_DESCRIPTION"));
                        model.setPLANED_START_DATE(jsonObject.getString("PLANED_START_DATE"));
                        model.setPLANED_END_DATE(jsonObject.getString("PLANED_END_DATE"));
                        model.setTOPIC_COVERED_DATE(jsonObject.getString("TOPIC_COVERED_DATE"));
                        arrayList.add(model);
                    }
                    ActionPlanAdaptor actionPlanAdaptor = new ActionPlanAdaptor(arrayList, getApplicationContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(actionPlanAdaptor);
                    actionPlanAdaptor.notifyDataSetChanged();
                } else {
                    Toast.makeText(ActionPlanActivity.this, Msg, Toast.LENGTH_SHORT).show();
                    nestedProgress.setVisibility(View.GONE);
                    actionNoData.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                nestedProgress.setVisibility(View.GONE);
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