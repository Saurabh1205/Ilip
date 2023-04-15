package com.example.ilip.activities.fees.payable;

import android.graphics.Color;
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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PayableFragment extends Fragment {
    PieChart pieChart;
    ConstantAPIsClass constantAPIsClass;
    RequestQueue requestQueue;
    TextView ApplicableFees,Received_Fees,BalanceFees,Concession;
    RecyclerView recyclerView;
    CommonModel commonModel;
    ModelForPayable modelforLog;
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
        Log.e("FramePayable", "Data" + commonModel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payable, container, false);
        pieChart = view.findViewById(R.id.Pie_chart1);
        recyclerView = view.findViewById(R.id.headerRecycler);
        ApplicableFees = view.findViewById(R.id.Applicable_Fees);
        Received_Fees = view.findViewById(R.id.ReceivedAmount);
        BalanceFees = view.findViewById(R.id.balance);
        Concession = view.findViewById(R.id.Concession);
        getStudentFeeDtl();


        return  view;
    }
//    private void showPieChart(PieChart pieChart,int Received,int Balance,int Concession){
//
//        ArrayList<PieEntry> pieEntries = new ArrayList<>();
//
//        //initializing data
//        Map<String, Integer> typeAmountMap = new HashMap<>();
//        typeAmountMap.put("Received",Received);
//        typeAmountMap.put("Concession/Write-off",Concession);
//        typeAmountMap.put("Balance",Balance);
//
//        //initializing colors for the entries
//        ArrayList<Integer> colors = new ArrayList<>();
//        colors.add(Color.parseColor("#358CED"));
//        colors.add(Color.parseColor("#86BFFF"));
//        colors.add(Color.parseColor("#E74260"));
////        colors.add(R.color.piechartGreen);
////        colors.add(R.color.piechartParot);
////        colors.add(R.color.piechartRed);
//        //input data and fit data into pie chart entry
//        for(String type : typeAmountMap.keySet()){
//            pieEntries.add(new PieEntry(typeAmountMap.get(type), type));
//        }
//
//        //collecting the entries with label name
//        PieDataSet pieDataSet = new PieDataSet(pieEntries,"");
//        //setting text size of the value
//        pieDataSet.setValueTextSize(10f);
//        PieData pieData = new PieData(pieDataSet);
//        pieData.setValueTextColor(Color.WHITE);
//        //showing the value of the entries, default true if not set
//        pieChart.setUsePercentValues(true);
//
//        pieChart.getDescription().setEnabled(false);
//
//        pieChart.setHighlightPerTapEnabled(true);
//        Legend l = pieChart.getLegend(); // Customize the ledgends
//        l.setEnabled(false);
//        pieData.setDrawValues(true);
//        pieChart.setDrawEntryLabels(false);
//        pieChart.setContentDescription("");
//        pieChart.setTouchEnabled(false);
//        pieData.setValueTextColor(Color.WHITE);
//        pieChart.setData(pieData);
//        //pieChart.setEntryLabelTypeface(typefaceHeading);
//        pieChart.highlightValues(null);
//        pieChart.invalidate();
//        pieDataSet.setColors(colors);
//
//    }
    private void chartData(PieChart pieChart, int Received,  int Concession,int Balance) {

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(Received, 0));
        entries.add(new PieEntry(Concession, 1));
        entries.add(new PieEntry(Balance, 2));


        final int[] piecolors = new int[]{
                Color.parseColor("#358CED"),
                Color.parseColor("#86BFFF"),
                Color.parseColor("#E74260")};

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Borrowing");
        labels.add("Pending");
        PieDataSet dataset = new PieDataSet(entries,"");

        PieData data = new PieData(dataset);
        dataset.setColors(ColorTemplate.createColors(piecolors)); //
        Legend l = pieChart.getLegend(); // Customize the ledgends
        l.setEnabled(false);
        data.setValueTextColor(Color.WHITE);
//        pieChart.setDescription("abc");
        pieChart.setUsePercentValues(true);

        pieChart.getDescription().setEnabled(false);

        pieChart.setHighlightPerTapEnabled(true);

        data.setDrawValues(true);
        pieChart.setDrawSlicesUnderHole(true);
        pieChart.setDrawEntryLabels(false);

        pieChart.setContentDescription("");
        pieChart.setTouchEnabled(false);
        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }
    public void getStudentFeeDtl(){
        String url = getString(R.string.URL_WEB)+constantAPIsClass.StudentFeeDtl;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_PERSON_ID",commonModel.getSTUDENT_ID());/*"1032170092"*/
        postParam.put("str_CKKPARASTATUS","Y");
        Log.e("Student Fees", url +"is==" +new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {

            try {
                if (response != null) {
                    Log.e("response ", "is==" + response);

                    ArrayList<ModelForPayable> arrayList = new ArrayList();
                    arrayList.clear();

                    if (response.get("STATUS").equals("TRUE")) {
                        JSONArray jsonArray1 = response.getJSONArray("CurrencyWisePayable");
                        for (int i = 0; i <= jsonArray1.length(); i++) {
                            try {
                                JSONObject jObj = (JSONObject) jsonArray1.get(i);
                                ApplicableFees.setText(jObj.getString("CURRENCY_SYMBOL") + " " + jObj.getString("RECEIVABLE_TOTAL"));
                                Received_Fees.setText(jObj.getString("RECEIVED_TOTAL"));
                                BalanceFees.setText(jObj.getString("BALANCE_TOTAL"));
                                Concession.setText(jObj.getString("CONSESSION_TOTAL"));

                               /* BigDecimal number = new BigDecimal(jObj.getString("RECEIVED_TOTAL_PER"));

//                                  int myInt = number.intValue();
                                double myDouble = number.doubleValue();
                                float myFloat = number.floatValue();*/
                                int RECEIVED = (int) Math.abs(Float.parseFloat(jObj.getString("RECEIVED_TOTAL_PER")));
                                int BALANCE = (int) Math.abs(Double.parseDouble(jObj.getString("BALANCE_TOTAL_PER")));
                                int CONCESSION = (int) Math.abs(Double.parseDouble(jObj.getString("CONSESSION_TOTAL_PER")));
                                // float dblVal= (float) Double.parseDouble(response.getString("RECEIVED_TOTAL_PER"));
                                //  int data = (int) Math.abs(myDouble);

                                // Log.e("data is", myDouble + "==" + data);
//                                    Log.e("Received", "==" + Math.abs(Float.parseFloat(jObj.getString("RECEIVED_TOTAL_PER"))));
//                                    Log.e("data is ", "==" + RECEIVED + BALANCE + CONCESSION);
                                chartData(pieChart, Math.abs(RECEIVED), Math.abs(CONCESSION), Math.abs(BALANCE));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i <= jsonArray.length(); i++) {
                            try {
                                JSONObject jObj = (JSONObject) jsonArray.get(i);
                                modelforLog = new ModelForPayable();
                                modelforLog.setFee_head(jObj.getString("FEE_HEAD"));
                                modelforLog.setFee_Type(jObj.getString("FEE_TYPE"));

                                if (!jObj.getString("BALANCE_AMT_TOTAL").equals("0")) {
                                    modelforLog.setFee_value(jObj.getString("BALANCE_AMT_TOTAL"));

                                    if (jObj.getString("FEE_TYPE").equals("Structural Fee")) {
                                        modelforLog.setJsonArrayStructural(jObj.getJSONArray("Parent"));
                                    }
                                    if (jObj.getString("FEE_TYPE").equals("Examination")) {
                                        modelforLog.setJsonArrayExamination(jObj.getJSONArray("Parent"));
                                    }
                                    if (jObj.getString("FEE_TYPE").equals("Non-Structural Fee")) {
                                        modelforLog.setJsonArrayNonStructural(jObj.getJSONArray("Parent"));
                                    }
                                    arrayList.add(modelforLog);
                                }
                            } catch (Exception e) {
                                Log.e("asds", "asdfas" + e);
                            }
                        }
                        AbstractForPayable abstractForPayable = new AbstractForPayable(arrayList, getContext(), modelforLog, "");
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(abstractForPayable);
                        abstractForPayable.notifyDataSetChanged();
                        abstractForPayable.setHasStableIds(true);
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


    }
}