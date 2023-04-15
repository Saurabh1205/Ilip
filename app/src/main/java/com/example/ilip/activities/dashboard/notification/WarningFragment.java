package com.example.ilip.activities.dashboard.notification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WarningFragment extends Fragment {

CommonModel commonModel;
RequestQueue requestQueue;
ConstantAPIsClass constantAPIsClass;
RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    constantAPIsClass = new ConstantAPIsClass();
        Bundle extras;


        extras = this.getArguments();
        if (extras != null) {
            commonModel = (CommonModel) extras.get("CommonModel");
        }
        Log.e("FrameDayWise", "Data" + commonModel);
        String userTypeSymbol;
        if(commonModel.getLoginType().equals("Student")){
            userTypeSymbol="S";
        }else{
            userTypeSymbol="P";
        }
        notificationSummary(userTypeSymbol);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_warning, container, false);
        recyclerView = view.findViewById(R.id.warningList);
        return view;
    }
    public void notificationSummary(String usertype){
        requestQueue = Volley.newRequestQueue(getContext());
        String url = getString(R.string.URL_WEB)+ constantAPIsClass.getNotificationSummary;
        Map<String,String> postParam =  new HashMap<>();
        postParam.put("PI_EMPLOYEE_ID",commonModel.getSTUDENT_ID());
        postParam.put("PI_EMPLOYEE_TYPE",usertype);
        postParam.put("PI_WORK_DESIG_CODE","");
        postParam.put("PI_WORK_DESG_MST_ID","0");
        postParam.put("PI_DEPARTMENT_NUMBER","0");
        postParam.put("PI_CENTER_CODE",commonModel.getCENTRE_CODE());
        postParam.put("PI_FILTER_VAL",commonModel.getBRANCH_STANDARD_ID());
        postParam.put("PI_SESSION_ID",commonModel.getACAD_SESSION_ID());
        postParam.put("PI_Semester_Type","O");
        postParam.put("PI_Interface_from","");
        postParam.put("PI_JOB_CATEGORY","WARNING");
        Log.e("data is","=="+url+" "+new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            Log.e("data", "notification Summery" + response);
            try {
                ArrayList<CommonModel> list = new ArrayList<>();
                Log.e("data", "notification Summery" + response);
                if (response.getString("STATUS").equals("TRUE")) {
                    JSONArray jsonArray = response.getJSONArray("Notification_summary");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        commonModel.setNotificationSummary(jsonObject.getString("JOB_DESC"));
                        list.add(commonModel);
                    }
                    AdaptorClass adaptorClass = new AdaptorClass(list, getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adaptorClass.notifyDataSetChanged();
                    recyclerView.setAdapter(adaptorClass);
                }
            } catch (Exception e) {

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