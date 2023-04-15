package com.example.ilip.activities.syllabus.university;

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
import com.google.android.material.chip.Chip;
import com.sn.lib.NestedProgress;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniversityActivity extends AppCompatActivity {
    PreferManager preferManager;
    CommonModel commonModel;
    RequestQueue requestQueue;
    ConstantAPIsClass constantAPIsClass;
    TextView subjectTitle,subjectType,theory,practical,Tee,Cca;
    Chip credit,TeeHrs;
    RecyclerView recyclerView;
    ImageView universityNoData;
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
        setContentView(R.layout.activity_university);
        preferManager = new PreferManager(this);
        requestQueue = Volley.newRequestQueue(this);
        constantAPIsClass = new ConstantAPIsClass();
        nestedProgress = new NestedProgress(this);
        nestedProgress = findViewById(R.id.custom_Loader_University);
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        commonModel = (CommonModel) extras.get("CommonModel");
//        Log.e("CommonModel", "model data" + commonModel);

        Toolbar toolbar = findViewById(R.id.toolbarForUniversity);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        universityNoData = findViewById(R.id.universityNoData);
        subjectTitle = findViewById(R.id.subjectTitle);
        subjectType = findViewById(R.id.subjectType);
        theory  = findViewById(R.id.contactHrsTheory);
        practical = findViewById(R.id.contactHrsPractical);
        Tee = findViewById(R.id.weightageTee);
        Cca = findViewById(R.id.weightageCca);
        credit = findViewById(R.id.credit);
        TeeHrs = findViewById(R.id.TEEHrs);

        recyclerView = findViewById(R.id.listForUniversitySyllabus);

        UniversitySyllabus(commonModel.getSubjectApplicableNo(),commonModel.getSubjectGroupId(),commonModel.getACAD_SESSION_ID());
    }
    public void UniversitySyllabus(String ApplicableNo,String SubGroupId,String SessionId){
        nestedProgress.setVisibility(View.VISIBLE);
        String url = getString(R.string.URL_WEB)+constantAPIsClass.UniversitySyllabusApiCall;
        Map<String,String> param = new HashMap<>();
        param.put("PI_APPLICABLE_NUMBER",ApplicableNo);
        param.put("PI_SUBJECT_GROUP_ID",SubGroupId);
        param.put("PI_SESSION_ID",SessionId);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(param), response -> {
            Log.e("University", "Syllabus==" + response);
            try {
                String Msg = response.getString("MESSAGE");
                if (response.getString("STATUS").equals("TRUE")) {
                    nestedProgress.setVisibility(View.GONE);
                    universityNoData.setVisibility(View.GONE);
                    JSONArray jsonArray1 = response.getJSONArray("Hrs_Whtg");
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray1.get(i);
                        subjectTitle.setText(jsonObject.getString("SUBJECT_DESCRIPTION"));
                        subjectType.setText(jsonObject.getString("UNIVERSITY_CODE") + " (" + jsonObject.getString("SUBJECT_TYPE_DESCRIPTION") + ")");
                        if (jsonObject.getString("SUBJECT_TYPE_SHORT_NAME").equals("TH")) {
                            theory.setText(jsonObject.getString("TOTAL_NUMBER_OF_HOURS"));
                        } else {
                            practical.setText(jsonObject.getString("TOTAL_NUMBER_OF_HOURS"));
                        }
                        Tee.setText(jsonObject.getString("WEIGHTAGE_EXT"));
                        Cca.setText(jsonObject.getString("WEIGHTAGE_INT"));
                        credit.setText("Credit : " + jsonObject.getString("SUBJECT_CREDIT"));
                        TeeHrs.setText("TEE Hrs : " + jsonObject.getString("TOTAL_NUMBER_OF_HOURS"));
                    }
                    JSONArray jsonArray = response.getJSONArray("Syllabus_Dtls");
                    ArrayList<UniversitySyllabusModel> arrayList = new ArrayList<>();
                    List arrayList1 = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobj = (JSONObject) jsonArray.get(i);
                        UniversitySyllabusModel model = new UniversitySyllabusModel();
                        model.setSYLLABUS_DESCRIPTION(jobj.getString("SYLLABUS_DESCRIPTION"));
                        model.setWEIGHTAGE(jobj.getString("WEIGHTAGE"));
                        model.setTOPIC_NAME(jobj.getString("TOPIC_NAME"));
                        model.setSR_NO(jobj.getString("SR_NO"));
                        model.setTOPIC_DESCRIPTION(jobj.getString("TOPIC_DESCRIPTION"));
                        model.setWEIGHTAGE(jobj.getString("WEIGHTAGE"));
                        arrayList.add(model);
                    }

                    UniversityAdaptor universityAdaptor = new UniversityAdaptor(arrayList, arrayList1, getApplicationContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(universityAdaptor);
                    universityAdaptor.notifyDataSetChanged();
                } else {
                    nestedProgress.setVisibility(View.GONE);
                    universityNoData.setVisibility(View.VISIBLE);
                    Toast.makeText(UniversityActivity.this, Msg, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
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