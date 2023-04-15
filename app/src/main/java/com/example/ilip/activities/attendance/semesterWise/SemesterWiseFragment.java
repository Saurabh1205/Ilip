package com.example.ilip.activities.attendance.semesterWise;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
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
import com.example.ilip.all_model.DashboardCourseAttendanceModel;
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SemesterWiseFragment extends Fragment {
    CommonModel commonModel;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    ArrayList<DashboardCourseAttendanceModel> arrayList;
    ConstantAPIsClass constantAPIsClass;
    BarChart chartSemWise;
    TextView branchCode,semesterCode,currentSession,LRD;
    LinearLayout bgTableTitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constantAPIsClass = new ConstantAPIsClass();
        requestQueue = Volley.newRequestQueue(getContext());
      //
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_semester_wise, container, false);
        Bundle extras;
        extras = this.getArguments();
        if (extras != null) {
            commonModel = (CommonModel) extras.get("CommonModel");
        }
        Log.e("SemesterWise","Data"+commonModel);
        bgTableTitle = view.findViewById(R.id.bgTableTitle);
        branchCode = view.findViewById(R.id.branchCode0);
        LRD = view.findViewById(R.id.LRD);
        branchCode.setText(commonModel.getBRANCH_STANDARD_GROUP_CODE());
        semesterCode = view.findViewById(R.id.semCode0);
        semesterCode.setText(commonModel.getSemesterCode());
        currentSession = view.findViewById(R.id.currentSession0);
        currentSession.setText(commonModel.getCurrentSession());
        recyclerView = view.findViewById(R.id.listForSemWise);
        chartSemWise = view.findViewById(R.id.chartSemWise);

            if (commonModel.getLoginType().equals("Student")) {
                bgTableTitle.setBackgroundColor(getContext().getColor(R.color.theme_color_student));

            } else {
                bgTableTitle.setBackgroundColor(getContext().getColor(R.color.theme_color_parent));
            }

        getSyllabusStatus();
        return view;
    }

    public void getSyllabusStatus(){
        arrayList=new ArrayList<>();
        String url  = getString(R.string.URL_WEB)+ constantAPIsClass.studentAttendanceStatus;
        Map<String,String> postParam = new HashMap<>();
        postParam.put("PI_STUDENT_ID",commonModel.getSTUDENT_ID());
        postParam.put("PI_SESSION_ID",commonModel.getACAD_SESSION_ID());
        postParam.put("PI_SEMESTER_MST_ID","0"/*commonModel.getMAIN_SEMESTER_MST_ID()*/);
        postParam.put("PI_BRANCH_STANDARD_GRP_ID",commonModel.getBRANCH_STANDARD_GRP_ID());
        Log.e("Semwise URL",url+" == "+new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    Log.e("Sem wise", "url==" + response);
                    if (response.get("STATUS").equals("TRUE")) {
                        ArrayList<String> xAxis = new ArrayList<>();
                        ArrayList valueSet = new ArrayList();
//                            infoForSyllabus = new ArrayList<String>();
                        JSONArray jsonArray = response.getJSONArray("data");
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject jObj = (JSONObject) jsonArray.get(i);
                                    DashboardCourseAttendanceModel modeldata = new DashboardCourseAttendanceModel();
                                    modeldata.setSemWiseStatistic(jObj.getString("ORG_SEMESTER_NAME"));

                                    String data = "0";
                                    if (jObj.getString("STUD_INITIAL").equals("-")) {
                                        data = "0";
                                    } else {
                                        data = jObj.getString("STUD_INITIAL");
                                    }
                                    float value = (float) Double.parseDouble(data);
                                    int aprxValue = (int) Math.abs(value);
                                    Log.e("data", "converted" + aprxValue);

                                    modeldata.setSemWiseAttendance(aprxValue + "%");
                                    LRD.setText(jObj.getString("ASONDT"));
                                    arrayList.add(modeldata);
                                    BarEntry v1e = new BarEntry(i, aprxValue);
                                    valueSet.add(v1e);

                                    xAxis.add(jObj.getString("SEMESTER_SHORT_NAME"));
//                                        BarDataSet barDataSet1 = new BarDataSet(valueSet, "Subject's");
//                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                            if(commonModel.getLoginType().equals("Student")) {
//                                                barDataSet1.setColor(getContext().getColor(R.color.theme_color_student));
//                                            }else{
//                                                barDataSet1.setColor(getContext().getColor(R.color.theme_color_parent));
//                                            }
//                                        }
//                                        barDataSet1.setValueTextColor(Color.WHITE);
//                                        BarData data1 = new BarData(barDataSet1);
//                                        chartSemWise.setData(data1);
//                                        chartSemWise.animateXY(2000, 2000);
//                                        XAxis bottomAxis = chartSemWise.getXAxis();
//                                        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//                                        bottomAxis.setDrawAxisLine(true);
//                                        bottomAxis.setDrawGridLines(false);
//                                        YAxis left = chartSemWise.getAxisLeft();
//                                        left.setAxisMinValue(0);
//                                        left.setAxisMaxValue(100);
//                                        YAxis right = chartSemWise.getAxisRight();
//                                        right.setAxisMinValue(0);
//                                        right.setAxisMaxValue(100);
//                                        chartSemWise.setDrawValueAboveBar(false);
//
//
//                                        chartSemWise.getDescription().setText("");
////                                        chartSemWise.setTouchEnabled(false);
//                                        chartSemWise.invalidate();
                                    barchart(chartSemWise, valueSet, xAxis);
                                } catch (Exception e) {
                                    Log.e("Exception", "data is=" + e);
                                }
                            }
//                                chartSemWise.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxis));
//                                XAxis xl = chartSemWise.getXAxis();
//                                xl.setValueFormatter(new IAxisValueFormatter() {
//                                    @Override
//                                    public String getFormattedValue(float value, AxisBase axis) {
//                                        // Print.e(value);
//                                        return xAxis.get((int) value);
//                                    }
//
//                                });
                        }
                        SemWiseAdapter mAdapter = new SemWiseAdapter(arrayList, getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(mAdapter);
                        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
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
    public void barchart(BarChart barChart, ArrayList<BarEntry> arrayList, final ArrayList<String> xAxisValues) {
        barChart.setDrawBarShadow(false);
        //barChart.setFitBars(true);
        barChart.setDrawValueAboveBar(false);
        //barChart.setMaxVisibleValueCount(25);
        barChart.setPinchZoom(true);
        barChart.getDescription().setText("");
        barChart.setDrawGridBackground(true);
        BarDataSet barDataSet = new BarDataSet(arrayList, "Subject's");
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //barDataSet.setColors();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (commonModel.getLoginType().equals("Student")) {
                barDataSet.setColor(getContext().getColor(R.color.theme_color_student));
            } else {
                barDataSet.setColor(getContext().getColor(R.color.theme_color_parent));
            }
        }
        barDataSet.setValueTextColor(Color.WHITE);
        BarData barData = new BarData(barDataSet);
        // barData.setBarWidth(0.9f);
        // barData.setValueTextSize(0f);

        barChart.setBackgroundColor(Color.TRANSPARENT); //set whatever color you prefer
        barChart.setDrawGridBackground(false);

        Legend l = barChart.getLegend(); // Customize the ledgends
        l.setTextSize(10f);
        l.setFormSize(10f);
//To set components of x axis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextSize(10f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        xAxis.setDrawGridLines(false);
        YAxis left = barChart.getAxisLeft();
        left.setAxisMinValue(0);
        left.setAxisMaxValue(100);
        YAxis right = barChart.getAxisRight();
        right.setAxisMinValue(0);
        right.setAxisMaxValue(100);
        barChart.setData(barData);

    }
}