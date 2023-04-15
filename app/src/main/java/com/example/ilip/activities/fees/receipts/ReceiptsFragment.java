package com.example.ilip.activities.fees.receipts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class ReceiptsFragment extends Fragment {
    ConstantAPIsClass constantAPIsClass;
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    CommonModel commonModel;
    // ProgressDialog progressDialog;
    NestedProgress nestedProgress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         constantAPIsClass  = new ConstantAPIsClass();
        requestQueue = Volley.newRequestQueue(getContext());
        nestedProgress = new NestedProgress(getContext());
                Bundle extras;
        extras = this.getArguments();
        if (extras != null) {
            commonModel = (CommonModel) extras.get("CommonModel");
        }
        Log.e("FrameFeesLog", "Data" + commonModel);
       // progressDialog = new ProgressDialog(getContext());
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_receipts, container, false);
        recyclerView = view.findViewById(R.id.receiptIdRecycler);
        nestedProgress = view.findViewById(R.id.custom_Loader_receipt);
        getReceiptsDetails();
        return view;
    }
    public void getReceiptsDetails() {
        //progressDialog.show();
        nestedProgress.setVisibility(View.VISIBLE);
        String url = getString(R.string.URL_WEB) + constantAPIsClass.getFeesReceipt;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_CENTRE_CODE",commonModel.getCENTRE_CODE());
        postParam.put("PI_BALSHEET_MST_ID", commonModel.getACC_BALSHEET_MST_ID());
        postParam.put("PI_SESSION_ID", commonModel.getACAD_SESSION_ID());
        postParam.put("PI_RCPT_DATE", "");
        postParam.put("PI_PERSON_TYPE", ""+commonModel.getLoginType().charAt(0));
        postParam.put("PI_PERSON_ID", commonModel.getSTUDENT_ID());
        postParam.put("PI_COUNT_OF_RCPT", "10");
        postParam.put("PI_FEE_SOURCE",commonModel.getLoginType().toUpperCase(Locale.ROOT));
        Log.e("receipt ", url+"data=="+ new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    Log.e("response ", "is==" + response);
                    nestedProgress.setVisibility(View.GONE);
                    //  progressDialog.hide();
                    ArrayList<ModelclassforReceipts> arrayList = new ArrayList();
                    // arrayList.clear();

                    if (response.get("STATUS").equals("TRUE")) {
//                          Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jObj = (JSONObject) jsonArray.get(i);
                            ModelclassforReceipts modelforreceipt = new ModelclassforReceipts();
                            modelforreceipt.setSrNo(String.valueOf(i + 1));
                            modelforreceipt.setReceiptNo(jObj.getString("RECEIPT_NO"));
                            modelforreceipt.setReceipt_Date(jObj.getString("RECEIPT_DATE"));
                            modelforreceipt.setAmount(jObj.getString("RECEIPT_AMOUNT"));
                            arrayList.add(modelforreceipt);

                        }
                        AdaptorForReceipt abstractForLog = new AdaptorForReceipt(arrayList, getContext(), nestedProgress, commonModel);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(abstractForLog);
                        abstractForLog.notifyDataSetChanged();
                        // abstractForLog.setHasStableIds(true);
                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        nestedProgress.setVisibility(View.GONE);
                        // progressDialog.hide();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                nestedProgress.setVisibility(View.GONE);
                // progressDialog.hide();
            }

        }, error -> {
            nestedProgress.setVisibility(View.GONE);
           // progressDialog.hide();
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
    }
}