package com.example.ilip.activities.fees.receipts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
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
import com.orhanobut.dialogplus.DialogPlus;
import com.sn.lib.NestedProgress;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdaptorForReceipt extends RecyclerView.Adapter<AdaptorForReceipt.ViewHolderReceipts> {
ArrayList<ModelclassforReceipts> arrayList;
Context context;
ConstantAPIsClass constantAPIsClass;
RequestQueue requestQueue;
    ModelclassforReceipts modelclassforReceipts;
    NestedProgress nestedProgress1;
    CommonModel commonModel;
//    public static ArrayList<ModelclassforReceipts.ReceiptDtlBean> receiptDtlBeanArrayList;


    public AdaptorForReceipt(ArrayList<ModelclassforReceipts> arrayList, Context context, NestedProgress nestedProgress1, CommonModel commonModel) {
        this.arrayList = arrayList;
        this.context = context;
        this.nestedProgress1 = nestedProgress1;
        this.commonModel = commonModel;
    }

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public ViewHolderReceipts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_receipts,parent,false);
       constantAPIsClass = new ConstantAPIsClass();
       requestQueue = Volley.newRequestQueue(context);
        return new ViewHolderReceipts(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReceipts holder, int position) {
    holder.SrNo.setText(arrayList.get(position).getSrNo());
    holder.receiptNo.setText(arrayList.get(position).getReceiptNo());
    holder.receiptDate.setText(arrayList.get(position).getReceipt_Date());
    holder.receipt_Amount.setText(arrayList.get(position).getAmount());
    holder.receiptNo.setTag(position);
    holder.receiptNo.setOnClickListener(v -> getReceiptsDetails(holder.receiptNo.getText().toString()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolderReceipts extends RecyclerView.ViewHolder{
        TextView SrNo,receiptDate,receipt_Amount;
        Button receiptNo;
        public ViewHolderReceipts(@NonNull View itemView) {
            super(itemView);
            SrNo = itemView.findViewById(R.id.SrNo);
            receiptDate = itemView.findViewById(R.id.receiptDate);
            receiptNo = itemView.findViewById(R.id.receiptNo);
            receipt_Amount = itemView.findViewById(R.id.receiptAmount);
        }
    }
    public void getReceiptsDetails(String receipt_Id) {
        nestedProgress1.setVisibility(View.VISIBLE);
        String url = context.getString(R.string.URL_WEB) + constantAPIsClass.getStudentFeesReceipt;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_CENTRE_CODE",commonModel.getCENTRE_CODE());
        postParam.put("PI_COLLEGE_ID", "1");
        postParam.put("PI_SESSION_ID", commonModel.getACAD_SESSION_ID());
        postParam.put("PI_RCPT_ID",receipt_Id);
        postParam.put("PI_PERSON_TYPE", ""+commonModel.getLoginType().charAt(0));
        postParam.put("PI_PERSON_ID", commonModel.getSTUDENT_ID());
        postParam.put("PI_BALSHEET_MST_ID", commonModel.getACC_BALSHEET_MST_ID());
        postParam.put("PI_MACHINE","ABC");
        Log.e("Url",url+"data is="+new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                ArrayList<ModelforReceiptSublist> arrayList = new ArrayList();
                if (response != null) {
                    Log.e("response Receipts ", "is==" + response);
                    if (response.get("STATUS").equals("TRUE")) {
                        nestedProgress1.setVisibility(View.GONE);
                        JSONArray jsonArray = (JSONArray) response.get("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jObj = (JSONObject) jsonArray.get(i);
                            modelclassforReceipts = new ModelclassforReceipts();
                            modelclassforReceipts.setSrNo(String.valueOf(i + 1));
                            modelclassforReceipts.setRECEIPT_NO(jObj.getString("RECEIPT_NO"));
                            modelclassforReceipts.setRECEIPT_DATE(jObj.getString("RECEIPT_DATE"));
                            modelclassforReceipts.setBRANCH_DEPT(jObj.getString("BRANCH_DEPT"));
                            modelclassforReceipts.setACADEMIC_YEAR(jObj.getString("ACADEMIC_YEAR"));
                            modelclassforReceipts.setPERSON_NAME(jObj.getString("PERSON_NAME"));
                            modelclassforReceipts.setPERSON_ID(jObj.getString("PERSON_ID"));
                            modelclassforReceipts.setTOTAL_AMOUNT(jObj.getString("TOTAL_AMOUNT"));
                            modelclassforReceipts.setTOTAL_WORDS(jObj.getString("TOTAL_WORDS"));
                            modelclassforReceipts.setCURRENCY_NAME(jObj.getString("CURRENCY_NAME"));
                            modelclassforReceipts.setCURRENCY_SYMBOL(jObj.getString("CURRENCY_SYMBOL"));
                            modelclassforReceipts.setFEE_RECEIPT_CONC_REMARK(jObj.getString("FEE_RECEIPT_CONC_REMARK"));
                            modelclassforReceipts.setPAYEE_BANK_ACCT(jObj.getString("PAYEE_BANK_ACCT"));
                            modelclassforReceipts.setCHQ_D(jObj.getString("CHQ_DD"));
                            JSONArray jsonArray1 = jObj.getJSONArray("Sub_Type_List");
                            for (int j = 0; j < jsonArray1.length(); j++) {
                                JSONObject jsonObject = (JSONObject) jsonArray1.get(j);
                                ModelforReceiptSublist modelforReceiptSublist = new ModelforReceiptSublist();
                                modelforReceiptSublist.setFees_SubType(jsonObject.getString("FEE_SUB_TYPE_DESC"));
                                modelforReceiptSublist.setFees_Amt(jsonObject.getString("AMT"));
                                modelforReceiptSublist.setPayMode(jsonObject.getString("PAY_SOURCE"));
                                arrayList.add(modelforReceiptSublist);
                            }
                        }
                        AdaptorforSublist adaptorforSublist = new AdaptorforSublist(arrayList, context);

//                        Gson gson = new Gson();
//                        modelclassforReceipts = gson.fromJson(String.valueOf(response), ModelclassforReceipts.class);
//
//                            receiptDtlBeanArrayList = (ArrayList<ModelclassforReceipts.ReceiptDtlBean>) modelclassforReceipts.getData();
//                           // Toast.makeText(context, receiptDtlBeanArrayList.get(0).getRECEIPT_NO(), Toast.LENGTH_LONG).show();
//                            Log.e("response ", "is==" + receiptDtlBeanArrayList.get(0).getSub_Type_List());
//                            JSONArray jsonArray = receiptDtlBeanArrayList.get(0).getSub_Type_List();
//                            for (int i =0;i<jsonArray.length();i++) {
//                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                                modelclassforReceipts.setFees_SubType(jsonObject.getString("FEE_SUB_TYPE_DESC"));
//                                modelclassforReceipts.setFees_Amt(jsonObject.getString("AMT"));
//                                arrayList.add(modelclassforReceipts);
//                            }


                        final DialogPlus inflate = DialogPlus.newDialog(context)
                                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.popup_for_receipt_dtl))
                                .setExpanded(true, WindowManager.LayoutParams.MATCH_PARENT)
                                .setGravity(Gravity.BOTTOM)
                                .setCancelable(true)
                                .create();
                        Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.toolbar4feesModel);
                        toolbar.setTitle("");

                        toolbar.setNavigationOnClickListener(view -> {
                            inflate.dismiss();
                        });
                        TextView currency = (TextView) inflate.findViewById(R.id.currency);
                        currency.setText("Paid Amounts(" + modelclassforReceipts.getCURRENCY_SYMBOL() + ")");
                        TextView Heading = (TextView) inflate.findViewById(R.id.TitleOfReceipt);
                        Heading.setText(modelclassforReceipts.getRECEIPT_NO() + "-(DT." + modelclassforReceipts.getRECEIPT_DATE() + ")");
                        TextView branch = (TextView) inflate.findViewById(R.id.branch);
                        TextView Academic_session = (TextView) inflate.findViewById(R.id.academic_session);
                        TextView StudentName = (TextView) inflate.findViewById(R.id.student_name);
                        TextView Student_id = (TextView) inflate.findViewById(R.id.student_Id);
                        TextView TotalWords = (TextView) inflate.findViewById(R.id.TotalWords);
                        TextView TotalAmount = (TextView) inflate.findViewById(R.id.TotalAmount);
                        ImageView paymentMode = (ImageView) inflate.findViewById(R.id.pay_mode);
                        TextView AccountDtl = (TextView) inflate.findViewById(R.id.AccountDtl);
                        TextView TotalAmt = (TextView) inflate.findViewById(R.id.TotalAmt);
                        TextView Remark = (TextView) inflate.findViewById(R.id.Remark);
                        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.subTypeDtl);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.setAdapter(adaptorforSublist);
                        adaptorforSublist.notifyDataSetChanged();


                        //adaptorforSublist.setHasStableIds(true);
                        branch.setText(modelclassforReceipts.getBRANCH_DEPT());
                        Academic_session.setText(modelclassforReceipts.getACADEMIC_YEAR());
                        StudentName.setText(modelclassforReceipts.getPERSON_NAME());
                        Student_id.setText("(" + modelclassforReceipts.getPERSON_ID() + ")");
                        TotalAmount.setText(modelclassforReceipts.getTOTAL_AMOUNT());
                        TotalWords.setText(modelclassforReceipts.getTOTAL_WORDS());
                        if (modelclassforReceipts.getCHQ_D().equals("ONLINE") || modelclassforReceipts.getCHQ_D().equals("BANK")) {
                            paymentMode.setImageResource(R.drawable.bank);
                        }
                        AccountDtl.setText(modelclassforReceipts.getCHQ_D());
                        TotalAmt.setText(modelclassforReceipts.getTOTAL_AMOUNT());
                        Remark.setText(modelclassforReceipts.getFEE_RECEIPT_CONC_REMARK());
                        inflate.show();
                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        nestedProgress1.setVisibility(View.GONE);
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                nestedProgress1.setVisibility(View.GONE);
            }

        }, error -> nestedProgress1.setVisibility(View.GONE)) {
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
