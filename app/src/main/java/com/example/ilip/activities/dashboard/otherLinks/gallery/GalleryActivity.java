package com.example.ilip.activities.dashboard.otherLinks.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ilip.activities.dashboard.otherLinks.OtherLinkCommonModel;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GalleryActivity extends AppCompatActivity {
    CommonModel commonModel;
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    ImageView noDataFound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        commonModel = (CommonModel) extras.get("CommonModel");
        Log.e("CommonModel", "model data" + commonModel);
        Toolbar toolbar = findViewById(R.id.toolbar4Gallery);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        requestQueue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.galleryList);
        noDataFound = findViewById(R.id.noDataFound);
        getData();
    }

    public void getData() {
        String url = getString(R.string.URL_WEB) + ConstantAPIsClass.getDataForOtherLinks;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_CENTRE_CODE", "");
        postParam.put("PI_DEPARTMENT_NUMBER", "");
        postParam.put("PI_JOB_PROFILE_ID", "");
        postParam.put("PI_PERSON_ID", commonModel.getSTUDENT_ID());
        postParam.put("PI_PERSON_TYPE", "S");
        postParam.put("PI_BRANCH_STANDARD_ID", commonModel.getBRANCH_STANDARD_ID());
        postParam.put("PI_WORK_DESIG_CODE", "");
        postParam.put("PI_NOTICE_TYPE", "HOME");
        postParam.put("PI_NOTICE_SPAN", "Y");
        postParam.put("PI_NOTICE_PATTERN", "GALLERYBOX");
        Log.e("Gallery",url+"is=="+new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            Log.e("Response for", "Gallery" + response);
            try {
                ArrayList<OtherLinkCommonModel> arrayList = new ArrayList<>();

                String Msg = response.getString("MESSAGE");
                if (response.getString("STATUS").equals("TRUE")) {
                    JSONArray jsonArray = response.getJSONArray("data");
                    ArrayList<String> urls = new ArrayList();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                        OtherLinkCommonModel otherLinkCommonModel = new OtherLinkCommonModel();
                        otherLinkCommonModel.setJsonArrayData(jsonObject.getJSONArray("attachment_list"));
                        otherLinkCommonModel.setGalleryItemName(jsonObject.getString("GROUP_NAME"));
                        otherLinkCommonModel.setGalleryItemDate_time(jsonObject.getString("NOTICE_START_DATE") + " | " + jsonObject.getString("NOTICE_START_TIME"));

                        arrayList.add(otherLinkCommonModel);
                    }
                    AdaptorForGallery adaptorForGallery = new AdaptorForGallery(arrayList, getApplicationContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adaptorForGallery);
                    adaptorForGallery.notifyDataSetChanged();
                } else {
                    Toast.makeText(GalleryActivity.this, Msg, Toast.LENGTH_SHORT).show();
                    noDataFound.setVisibility(View.VISIBLE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> {

        }){
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

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}