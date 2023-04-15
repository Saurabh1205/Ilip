package com.example.ilip.activities.fees.logs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.R;
import com.sn.lib.NestedProgress;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class LogsFragment extends Fragment {
    ConstantAPIsClass constantAPIsClass;
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    CommonModel commonModel;
    NestedProgress nestedProgress;
    TextView text_color;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constantAPIsClass = new ConstantAPIsClass();
        requestQueue = Volley.newRequestQueue(getContext());
        Bundle extras;
        extras = this.getArguments();
        if (extras != null) {
            commonModel = (CommonModel) extras.get("CommonModel");
        }
        Log.e("FrameFeesLog", "Data" + commonModel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logs, container, false);
        recyclerView = view.findViewById(R.id.recycle_view_Logs);
        nestedProgress = view.findViewById(R.id.custom_Loader);
//        text_color = view.findViewById(R.id.Text_color);
//        String text = "<font color='#67A7EF'>Transaction No</font>"+"\nOrder No & Date";
//        text_color.setText(Html.fromHtml(text));
        getFeesLog();
        return view;
    }

    public void getFeesLog() {
       nestedProgress.setVisibility(View.VISIBLE);
        String url = getString(R.string.URL_WEB) + ConstantAPIsClass.getOnlineTransactionDtl;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_CENTRE_GROUP_CODE", commonModel.getCENTRE_GROUP_CODE());
        postParam.put("PI_CENTRE_CODE", commonModel.getCENTRE_CODE());
        postParam.put("PI_PERSON_TYPE", ""+commonModel.getLoginType().charAt(0));
        postParam.put("PI_PERSON_ID", commonModel.getSTUDENT_ID());
        Log.e("Transaction ",url+"Detail=="+new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    Log.e("response ", "is==" + response);

                    ArrayList<ModelforLog> arrayList = new ArrayList();
                    arrayList.clear();

                    if (response.get("STATUS").equals("TRUE")) {
                        nestedProgress.setVisibility(View.GONE);
//                          Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i <= jsonArray.length(); i++) {
                            try {
                                JSONObject jObj = (JSONObject) jsonArray.get(i);
                                ModelforLog modelforLog = new ModelforLog();
                                modelforLog.setTransaction_no(jObj.getString("TRN_REF_NO"));
                                modelforLog.setAmount(jObj.getString("Amount_Paid"));
                                modelforLog.setOrder_no(jObj.getString("ONLINE_PAYMENT_MST_ID"));
                                modelforLog.setBank_Reference_No(jObj.getString("BNK_REF_NO"));
                                String[] datetime = jObj.getString("TRN_DATE_TIME").split(" ");
                                modelforLog.setTransaction_Date(datetime[0]);
                                modelforLog.setTransaction_Time(datetime[1]);
                                modelforLog.setStatus(jObj.getString("TRANSACTION_STATUS"));
                                modelforLog.setImagePath(getResources().getIdentifier(jObj.getString("TRANSACTION_STATUS").toLowerCase(Locale.ROOT), "drawable", getContext().getPackageName()));
                                modelforLog.setReceipt_no(jObj.getString("RECEIPT_NO"));
                                arrayList.add(modelforLog);
                            } catch (Exception e) {
                                nestedProgress.setVisibility(View.GONE);
                            }
                        }
                        AbstractForLog abstractForLog = new AbstractForLog(arrayList, getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(abstractForLog);
                        abstractForLog.notifyDataSetChanged();
                        abstractForLog.setHasStableIds(true);
                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        nestedProgress.setVisibility(View.GONE);
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

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}