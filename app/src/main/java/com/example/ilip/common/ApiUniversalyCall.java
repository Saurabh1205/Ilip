package com.example.ilip.common;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ilip.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public  class ApiUniversalyCall {
    RequestQueue requestQueue;
    ConstantAPIsClass constantAPIsClass = new ConstantAPIsClass();
    Context context;
    JSONObject jsonObject;
    String data;
    PreferManager preferManager;
    public ApiUniversalyCall(Context context) {
        this.context = context;
    }

    public void getAttendanceStatus(String StudentId, String SessionId, String SemMstId, String BranchStdGrpId) {
       requestQueue = Volley.newRequestQueue(context);
        preferManager = new PreferManager(context);
        String url = context.getString(R.string.URL_WEB) + constantAPIsClass.studentAttendanceStatus;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_STUDENT_ID", StudentId);
        postParam.put("PI_SESSION_ID", SessionId);
        postParam.put("PI_SEMESTER_MST_ID", SemMstId);
        postParam.put("PI_BRANCH_STANDARD_GRP_ID", BranchStdGrpId);
//        data = new String();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    Log.e("Attendance Status", "urlis =====" + response);
                    if (response.get("STATUS").equals("TRUE")) {
                        preferManager.setJsonDataForAttendanceAPI(response.toString());
//                          Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
            }


        }, error -> {

        }) {
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
//        return jsonObject;
    }


}
