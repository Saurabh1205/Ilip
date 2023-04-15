package com.example.ilip.activities.dashboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ilip.activities.attendance.AttendanceActivity;
import com.example.ilip.activities.dashboard.notification.AlertFragment;
import com.example.ilip.activities.dashboard.notification.InfoFragment;
import com.example.ilip.activities.dashboard.notification.SuccessFragment;
import com.example.ilip.activities.dashboard.notification.WarningFragment;
import com.example.ilip.activities.dashboard.otherLinks.OtherLinksActivity;
import com.example.ilip.activities.e_Learning.ElearningActivity;
import com.example.ilip.activities.fees.FeesActivity;
import com.example.ilip.activities.fees.payable.AbstractForPayable;
import com.example.ilip.activities.fees.payable.ModelForPayable;
import com.example.ilip.activities.schedule.ScheduleActivity;
import com.example.ilip.activities.SplashScreenActivity;
import com.example.ilip.activities.syllabus.MySyllabusActivity;
import com.example.ilip.activities.UserProfileScreenActivity;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.all_model.DashboardCourseAttendanceModel;
import com.example.ilip.common.ApiUniversalyCall;
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.common.PreferManager;
import com.example.ilip.R;
import com.fenchtose.tooltip.Tooltip;
import com.fenchtose.tooltip.TooltipAnimation;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.dialogplus.DialogPlus;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HomeScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    PreferManager preferManager;
    CommonModel commonModel;
    RequestQueue requestQueue;
    ConstantAPIsClass constantAPIsClass;
    ProgressDialog pd;
    TextView nav_studentId, nav_username, nav_user_email, nav_user_collage, nav_user_branch;
    Button nav_editColor;
    TextView home_screenRefresh, totalPresent, totalLectures, totalRatio;
    TextView refreshData;
    ImageView userProfile;
    DrawerLayout drawer;
    Menu menu;
    String userTypeSymbol;
    CircularProgressIndicator circularProgressIndicator;
    LinearProgressIndicator linearProgressIndicator;
    RecyclerView recyclerView;
    ArrayList<DashboardCourseAttendanceModel> arrayList;
    DashboardCourseAttendanceAdaptor mAdapter;
    BarChart chart2;
    BarChart chart1;
    TextView subjectInfo, sem_wise_Info;
    TextView linearProgressPercentage;
    //int count=0;
//    ArrayList<String> infoForSyllabus;
    SimpleAdapter infoForSyllabus;

    String infoForGradePoint;
    ApiUniversalyCall apiUniversalyCall;
    JSONObject jsonObject;
    private Handler mHandler;

    private int tooltipColor;
    private int tooltipSize;
    private int tooltipPadding;

    private int tipSizeSmall;
    private int tipSizeRegular;
    private int tipRadius;
    private Boolean TooltipOpen = false;
    String PassValue, TotalCount;
    Tooltip customTooltip;
    RelativeLayout linear_layout;
    LinearLayout headerBg;
    Button logoutBtn, resetBtn;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    public static final int NOTIFICATION_ID = 100;
    Context context;
    PackageManager packageManager;
    PieChart pieChart;
    int rotationAngle = 0;
    RecyclerView recyclerView1;
    TextView ApplicableFees, Received_Fees, BalanceFees, Concession;
    ModelForPayable modelforLog;
    Button ShowLayout;
    LinearLayout showLayoutdata;
    ScrollView ScrollLayout;
    RecyclerView graphChartHorizontal;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this.getApplicationContext();
//        askNotificationPermission();
        // openWhatsApp();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        commonModel = (CommonModel) extras.get("CommonModel");
        Log.e("CommonModel", "model data" + commonModel);
        if (commonModel.getLoginType().equals("Student")) {
            setTheme(R.style.StudentTheme);
        } else {
            setTheme(R.style.ParentTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        apiUniversalyCall = new ApiUniversalyCall(this,jsonObject);
        constantAPIsClass = new ConstantAPIsClass();
        requestQueue = Volley.newRequestQueue(this);
        preferManager = new PreferManager(this);
        pd = new ProgressDialog(this,R.style.MyTheme);
        pd.setProgressStyle(android.R.style.Widget_Material_ProgressBar_Large);
        //pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ScrollLayout = findViewById(R.id.ScrollLayout);
        graphChartHorizontal = findViewById(R.id.graphHorizontal);
        chart1 = findViewById(R.id.chart1);
        chart2 = findViewById(R.id.chart2);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        headerBg = header.findViewById(R.id.headerBg);
        nav_editColor = header.findViewById(R.id.editColor);
        nav_editColor.setOnClickListener(this);
        nav_studentId = header.findViewById(R.id.header_studentId);
        nav_username = header.findViewById(R.id.userName);
        nav_user_email = header.findViewById(R.id.userEmail);
        nav_user_collage = header.findViewById(R.id.userCollage);
        nav_user_branch = header.findViewById(R.id.userBranch);

        NavigationView navigationViewFooter = findViewById(R.id.nav_view_footer);
        View footer = navigationViewFooter.getHeaderView(0);
        logoutBtn = findViewById(R.id.logout);
        resetBtn = findViewById(R.id.reset);
        logoutBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        if (commonModel.getLoginType().equals("Student")) {
            userTypeSymbol = "S";
            headerBg.setBackground(getDrawable(R.drawable.nav_layout_curvshape_student));
        } else {
            userTypeSymbol = "P";
            headerBg.setBackground(getDrawable(R.drawable.nav_layout_curvshape_parent));
        }
        userProfile = header.findViewById(R.id.userProfile);
        home_screenRefresh = findViewById(R.id.home_screen_Refresh);
        home_screenRefresh.setOnClickListener(this);
        totalPresent = findViewById(R.id.totalPresent);
        totalLectures = findViewById(R.id.totalLectures);
        circularProgressIndicator = findViewById(R.id.progress_bar);
        circularProgressIndicator.setIndicatorColor(getColor(R.color.dashColorRed));
        totalRatio = findViewById(R.id.attendance_Percent);
        recyclerView = findViewById(R.id.subjectList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        linearProgressIndicator = findViewById(R.id.progress_bar2);
        linearProgressPercentage = findViewById(R.id.linearProgressText);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
//        toolbar.setOnClickListener(view -> recreate());
        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        menu = navigationView.getMenu();
        navigationView.setCheckedItem(0);
        linear_layout = findViewById(R.id.linear_layout);


//        FragmentManager fragmentManager = getSupportFragmentManager();
//        ImportFragment fragment = new ImportFragment();
//        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();

        //ProfileDetailAPICall(preferManager.getuserId());

//        int colorred=getColor(R.color.dashColorRed);;
        subjectInfo = findViewById(R.id.subjectInfo);
        sem_wise_Info = findViewById(R.id.sem_wiseInfo);
        subjectInfo.setOnClickListener(this);
        sem_wise_Info.setOnClickListener(this);

        StudentDetailApiCall(preferManager.getuserId());
        ProfileImageAPICall(preferManager.getuserId(), preferManager.getUserType());

        pieChart = findViewById(R.id.Pie_chart1);
        recyclerView1 = findViewById(R.id.headerRecycler);
        ApplicableFees = findViewById(R.id.Applicable_Fees);
        Received_Fees = findViewById(R.id.ReceivedAmount);
        BalanceFees = findViewById(R.id.balance);
        Concession = findViewById(R.id.Concession);
        ShowLayout = findViewById(R.id.showLayout);
        ShowLayout.setOnClickListener(this);
        showLayoutdata = findViewById(R.id.moreView);
    }

    public void openWhatsApp() {
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//        sendIntent.setType("text/plain");
//        sendIntent.setPackage("com.whatsapp");
//        startActivity(sendIntent);
        try {
            String mobile = "918007180374";
            String msg = "Its Working";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + mobile + "&text=" + msg)));
        } catch (Exception e) {
            //whatsapp app not install
            Log.e("Error =", "is==" + e);
        }
    }

    public void setColorAsPerCondition(int count) {
        if (count < 75) {
            int colorRed = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                colorRed = getColor(R.color.dashColorRed);
            }
            circularProgressIndicator.setIndicatorColor(colorRed);
            totalPresent.setTextColor(colorRed);
        } else if (count > 85) {
            int colorGreen = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                colorGreen = getColor(R.color.dashColorGreen);
            }
            circularProgressIndicator.setIndicatorColor(colorGreen);
            totalPresent.setTextColor(colorGreen);
        } else {
            int colorOrange = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                colorOrange = getColor(R.color.dashColorOrange);
            }
            circularProgressIndicator.setIndicatorColor(colorOrange);
            totalPresent.setTextColor(colorOrange);
        }
    }

    public void FirstAPiCallToRefresh(String sessionId, String semesterType, String semisterMstId, String studentId) {
        pd.show();
        String url = getString(R.string.URL_WEB) + ConstantAPIsClass.DashboardRefreshFirstAPICall;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_SESSION_ID", sessionId);
        postParam.put("PI_SEMESTER_TYPE", semesterType);
        postParam.put("PI_SEMESTER_MST_ID", semisterMstId);
        postParam.put("PI_STUDENT_ID", studentId);
        Log.e("FirstAPI Call is =", "param value" + url + postParam);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    pd.hide();
                    Log.e("Refresh", "FirstAPI Call==" + response);
                    String message = response.getString("MESSAGE");
                    if (response.get("STATUS").equals("TRUE")) {
                        StudentDetailApiCall(preferManager.getuserId());
                        ProfileImageAPICall(preferManager.getuserId(), preferManager.getUserType());
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                pd.hide();
            }
        }, error -> pd.hide()) {
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
            pd.hide();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    public void StudentDetailApiCall(String userId) {
        pd.show();
        String url = getString(R.string.URL_WEB) + constantAPIsClass.StudentDetail;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("User_Id", userId);
        postParam.put("User_Type", commonModel.getLoginType());
        Log.e("Student Details =", "param value" + url + new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    pd.hide();
                    Log.e("Student data", "StudentDetails==" + response);
                    // String message = response.getString("MESSAGE");
                    if (response.get("STATUS").equals("TRUE")) {
                        JSONArray jsonArrayPersonal_Dtls = response.getJSONArray("Personal_Details");
                        JSONArray jsonArrayStudentData = response.getJSONArray("data");
                        Log.e("Personal_Dtls", "Array data=" + jsonArrayPersonal_Dtls);
                        Log.e("Contact_Dtls", "Array dataContact_Dtls=" + jsonArrayStudentData);
                        if (jsonArrayPersonal_Dtls != null) {
                            for (int i = 0; i < jsonArrayPersonal_Dtls.length(); i++) {
                                try {
                                    JSONObject jObj = (JSONObject) jsonArrayPersonal_Dtls.get(i);
                                    nav_studentId.setText(userTypeSymbol + "" + jObj.getString("STUDENT_ID"));
                                    nav_username.setText(jObj.getString("STUD_FULL_NAME"));
                                    nav_user_email.setText(jObj.getString("STUDENT_EMAIL_ID"));
                                    preferManager.setUsername(nav_username.getText().toString());
                                    preferManager.setMobileNo(jObj.getString("STUDENT_MOBILE_NO"));
                                    preferManager.setEmailId(jObj.getString("STUDENT_EMAIL_ID"));
                                } catch (Exception e) {

                                }
                            }
                        }
                        if (jsonArrayStudentData != null) {
                            for (int i = 0; i < jsonArrayStudentData.length(); i++) {
                                try {
                                    JSONObject jObj = (JSONObject) jsonArrayStudentData.get(i);
                                    nav_user_collage.setText(jObj.getString("BRANCH_STANDARD_CODE"));
                                    nav_user_branch.setText(jObj.getString("BRANCH_SEMESTER_NAME"));

                                    commonModel.setBRANCH_STANDARD_ID(jObj.getString("BRANCH_STANDARD_ID"));
                                    commonModel.setACAD_SESSION_ID(jObj.getString("ACAD_SESSION_ID"));
                                    commonModel.setBRANCH_STANDARD_GRP_ID(jObj.getString("BRANCH_STANDARD_GRP_ID"));
                                    commonModel.setBRANCH_STANDARD_GROUP_CODE(jObj.getString("BRANCH_STANDARD_CODE"));
                                    commonModel.setMAIN_SEMESTER_MST_ID(jObj.getString("MAIN_SEMESTER_MST_ID"));
                                    commonModel.setSTUDENT_CODE(jObj.getString("STUDENT_CODE"));
                                    commonModel.setSTUDENT_ID(jObj.getString("STUDENT_ID"));
                                    commonModel.setCENTRE_CODE(jObj.getString("CENTRE_CODE"));
                                    commonModel.setCENTRE_GROUP_CODE(jObj.getString("CENTRE_GROUP_CODE"));
                                    commonModel.setACC_BALSHEET_MST_ID(jObj.getString("ACC_BALSHEET_MST_ID"));
                                    DashboardMenuAPICall(commonModel.getLoginType());
//                                        jsonObject=apiUniversalyCall.getAttendanceStatus(commonModel.getSTUDENT_ID(),
//                                                commonModel.getACAD_SESSION_ID(),commonModel.getMAIN_SEMESTER_MST_ID(),commonModel.getBRANCH_STANDARD_GRP_ID());
                                    //String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                                    //Log.e("Call", "Device ID ==" + deviceId);
                                    getAttendanceStatus();
                                    getSyllabusStatus();
                                    SemWiseAPICall();
                                    getStudentFeeDtl();
                                    // chart1.resetZoom();
                                    chart1.resetViewPortOffsets();
                                } catch (Exception e) {

                                }
                            }
                        }
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                pd.hide();
            }
        }, error -> pd.hide()) {
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
            pd.hide();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void DashboardMenuAPICall(String userType) {
        pd.show();
        String url = getString(R.string.URL_WEB) + constantAPIsClass.DashboardMenuAPI;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("Module_Code", "STUAPPANRD");
        postParam.put("CENTER_CODE", commonModel.getCENTRE_CODE());
        postParam.put("User_Type", userType.toUpperCase(Locale.ROOT));
        Log.e("Menu List", "URl" + url + "  Params===" + postParam);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    pd.hide();
                    menu.clear();
                    Log.e("Menu data", "Data==" + response);
                    String message = response.getString("MESSAGE");
                    if (response.get("STATUS").equals("TRUE")) {
                        JSONArray jsonArray = response.getJSONArray("MenuCodeList");
                        Log.e("MenuCodeList", "Array Menu=" + jsonArray);
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject jObj = (JSONObject) jsonArray.get(i);
                                    if (jObj.getString("MNMENU").equals("M")) {

                                        menu.add(1, menu.size(), menu.size(), jObj.getString("MENU_NAME"));
                                    }
                                } catch (Exception e) {
                                    Log.e("Exception", "data is=" + e);
                                }
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                pd.hide();
            }
        }, error -> pd.hide()) {
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
                pd.hide();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void ProfileImageAPICall(String userId, String userType) {
        pd.show();
        String url = getString(R.string.URL_WEB) + constantAPIsClass.ProfileImageAPI;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PERSON_ID", userId);
        postParam.put("PERSON_TYPE", "S");
        postParam.put("IMAGE_TYPE", "P");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    pd.hide();
                    Log.e("ProfileImage", "urlData" + response.getString("ImageUrl"));
                    if (response.get("STATUS").equals("TRUE")) {
                        try {
                            Picasso.get()
                                    .load(response.getString("ImageUrl"))
                                    .into(userProfile);
                            commonModel.setProfileImageURL(response.getString("ImageUrl"));
                        } catch (Exception e) {
                            Log.e("Error image", "is==" + e);
                        }
                        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                pd.hide();
            }
        }, error -> pd.hide()) {
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
                pd.hide();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void getAttendanceStatus() {
        pd.show();
        String url = getString(R.string.URL_WEB) + constantAPIsClass.studentAttendanceStatus;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_STUDENT_ID", commonModel.getSTUDENT_ID());
        postParam.put("PI_SESSION_ID", commonModel.getACAD_SESSION_ID());
        postParam.put("PI_SEMESTER_MST_ID", commonModel.getMAIN_SEMESTER_MST_ID());
        postParam.put("PI_BRANCH_STANDARD_GRP_ID", commonModel.getBRANCH_STANDARD_GRP_ID());
        Log.e("Attendance URL",url+" == "+new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    pd.hide();
                    Log.e("Attendance Status", "url==" + response);
                    preferManager.setJsonDataForAttendanceAPI(response.toString());
                    if (response.get("STATUS").equals("TRUE")) {
                        JSONArray jsonArray = response.getJSONArray("data");
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject jObj = (JSONObject) jsonArray.get(i);
                                    home_screenRefresh.setText(jObj.getString("ASONDT"));
                                    totalPresent.setText(jObj.getString("TOTPRD_PRESENT"));
                                    totalLectures.setText(jObj.getString("TOTPRD_APPLICABLE"));
                                    double dblVal = Double.parseDouble(jObj.getString("STUD_INITIAL"));
                                    int data = (int) Math.abs(dblVal);
                                    totalRatio.setText(data + "%");
                                    circularProgressIndicator.setProgress(data);
//                                        setColorAsPerCondition(data);
                                    circularProgressIndicator.setIndicatorColor(Color.GREEN);
                                    totalPresent.setTextColor(Color.GREEN);
                                    if (data < 75) {
                                        circularProgressIndicator.setIndicatorColor(getColor(R.color.dashColorRed));
                                        totalPresent.setTextColor(getColor(R.color.dashColorRed));
                                    } else if (data > 85) {
                                        circularProgressIndicator.setIndicatorColor(getColor(R.color.dashColorGreen));
                                        totalPresent.setTextColor(getColor(R.color.dashColorGreen));
                                    } else {
                                        circularProgressIndicator.setIndicatorColor(getColor(R.color.dashColorOrange));
                                        totalPresent.setTextColor(getColor(R.color.dashColorOrange));
                                    }
                                } catch (Exception e) {
                                    Log.e("Exception", "data is=" + e);
                                }
                            }
                        }

                        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                pd.hide();
            }
        }, error -> pd.hide()) {
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
                pd.hide();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void getSyllabusStatus() {
        pd.hide();
        arrayList = new ArrayList<>();
        ArrayList ImageList = new ArrayList();
        String url = getString(R.string.URL_WEB) + constantAPIsClass.studentSyllabusStatus;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_STUDENT_ID", commonModel.getSTUDENT_ID());
        postParam.put("PI_SESSION_ID", commonModel.getACAD_SESSION_ID());
        postParam.put("PI_SEMESTER_MST_ID", commonModel.getMAIN_SEMESTER_MST_ID());
        postParam.put("PI_BRANCH_STANDARD_GRP_ID", commonModel.getBRANCH_STANDARD_GRP_ID());
        postParam.put("PI_CENTRE_CODE", commonModel.getCENTRE_CODE());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    pd.hide();
                    preferManager.setJsonDataForSyllabusAPI(response.toString());
                    Log.e("Subject Status", "url==" + response);
                    if (response.get("STATUS").equals("TRUE")) {
                        ArrayList<String> xAxis = new ArrayList<>();
                        ArrayList<BarEntry> valueSet = new ArrayList();
                        List<Map<String, String>> list = new ArrayList<>();
                        //  Collections.reverse(list);
                        Map<String, String> map;

                        //  infoForSyllabus = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1);
                        JSONArray jsonArray = response.getJSONArray("data");
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject jObj = (JSONObject) jsonArray.get(i);
                                    DashboardCourseAttendanceModel modeldata = new DashboardCourseAttendanceModel();
                                    modeldata.setCourseTitle(jObj.getString("SUBJECT_DESCRIPTION"));
                                    modeldata.setCourseType(jObj.getString("SUBJ_TYPE_DESC"));
                                    modeldata.setCourseFacultyName(jObj.getString("EMP_NAME"));
                                    modeldata.setCourseRatio1(jObj.getString("TOTPRD_PRESENT"));
                                    modeldata.setCourseRatio2(jObj.getString("TOTPRD_APPLICABLE"));
                                    modeldata.setCourseUniversityCode(jObj.getString("UNIVERSITY_CODE") + "-" + jObj.getString("SUBJ_TYPE_SHORT_CODE"));
                                    modeldata.setBackGroundImage(getResources().getIdentifier("index" + i, "drawable", getPackageName()));
                                    arrayList.add(modeldata);
                                    ImageList.add(getResources().getIdentifier("index" + i, "drawable", getPackageName()));
                                    String data = "0";
                                    if (jObj.getString("SUBJ_TOTPRD_PERCTG").equals("-")) {
                                        data = "0";
                                    } else {
                                        data = jObj.getString("SUBJ_TOTPRD_PERCTG");
                                    }
                                    modeldata.setCoursePercentage(data);
                                    float value = (float) Double.parseDouble(data);

                                    int aprxValue = (int) Math.abs(value);
                                    Log.e("data", "converted" + aprxValue);
                                    int linearIndicator = 0;
                                    if (jObj.getString("SYLLABUS_PERCTG_TOT").equals("-")) {
                                        linearIndicator = Math.abs(0);
                                        linearProgressIndicator.setProgress(linearIndicator);
                                        linearProgressPercentage.setText(jObj.getString("SYLLABUS_PERCTG_TOT") + "%");
                                    } else {
                                        linearIndicator = Math.abs(Integer.parseInt(jObj.getString("SYLLABUS_PERCTG_TOT")));
                                        linearProgressIndicator.setProgress(linearIndicator);
                                        linearProgressPercentage.setText(linearIndicator + "%");
                                        Log.e("data", "LinearProgress bar" + linearProgressPercentage.getText());
                                    }
                                    BarEntry v1e = new BarEntry(i, aprxValue);

                                    valueSet.add(v1e);

                                    xAxis.add(jObj.getString("UNIVERSITY_CODE") + "-" + jObj.getString("SUBJ_TYPE_SHORT_CODE"));
//                                        BarDataSet barDataSet1 = new BarDataSet(valueSet, "Subject's");
//                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                            if(commonModel.getLoginType().equals("Student")) {
//                                                barDataSet1.setColor(getColor(R.color.theme_color_student));
//                                            }else{
//                                                barDataSet1.setColor(getColor(R.color.theme_color_parent));
//                                            }
//                                        }
//                                        BarData data1 = new BarData(barDataSet1);
//                                        data1.setBarWidth(0.8f);

//                                        chart1.setData(data1);
                                    // chart1.animateXY(2000, 2000);

//                                        XAxis bottomAxis = chart1.getXAxis();
//                                        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//                                        bottomAxis.setDrawAxisLine(true);
//                                        bottomAxis.setDrawGridLines(false);
//                                        bottomAxis.setCenterAxisLabels(false);
//                                        YAxis left = chart1.getAxisLeft();
//                                        left.setAxisMinValue(0);
//                                        left.setAxisMaxValue(100);
//                                        YAxis right = chart1.getAxisRight();
//                                        right.setAxisMinValue(0);
//                                        right.setAxisMaxValue(100);
                                    chart1.getDescription().setText("");
                                    //chart1.setDrawValueAboveBar(false);
                                    //  chart1.setDescription("");

                                    //chart1.setTouchEnabled(false);
                                    //chart1.resetZoom();
                                    //chart1.resetViewPortOffsets();
//                                        chart1.setVisibleXRangeMaximum(5); // allow 5 values to be displayed
//                                        chart1.moveViewToX(1);// set the left edge of the chart to x-index 1
                                    //  chart1.invalidate();
                                    //chart1.setFitBars(true);


//                                        infoForSyllabus.add(jObj.getString("UNIVERSITY_CODE")+" "+jObj.getString("SUBJ_TYPE_SHORT_CODE")+
//                                        " : "+jObj.getString("SUBJECT_DESCRIPTION"));


                                    map = new HashMap<>();
                                    map.put("Subject Code", jObj.getString("UNIVERSITY_CODE") + " (" + jObj.getString("SUBJ_TYPE_SHORT_CODE") + ")");
                                    map.put("In Details", jObj.getString("SUBJECT_DESCRIPTION"));
                                    list.add(map);

                                } catch (Exception e) {
                                    Log.e("Exception", "data is=" + e);
                                    pd.hide();
                                }
                            }
                            //  chart1.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxis));
//                                XAxis xl = chart1.getXAxis();
//                                xl.setValueFormatter(new IAxisValueFormatter() {
//                                    @Override
//                                    public String getFormattedValue(float value, AxisBase axis) {
//                                        // Print.e(value);
//                                        return xAxis.get((int) value);
//                                    }
//
//                                });

//                                list.notifyAll();

                            // PREPARING THE ARRAY LIST OF BAR ENTRIES
//                                ArrayList<BarEntry> barEntries = new ArrayList<>();
//                                barEntries.add(new BarEntry(1f, 70));
//                                barEntries.add(new BarEntry(2f, 80));
//                                barEntries.add(new BarEntry(3f, 90));
//                                barEntries.add(new BarEntry(4f, 20));
//                                barEntries.add(new BarEntry(5f, 0));
//                                barEntries.add(new BarEntry(6f, 10));
//                                barEntries.add(new BarEntry(7f, 5));
//                                barEntries.add(new BarEntry(8f, 100));
//                                barEntries.add(new BarEntry(9f, 10));
//                                barEntries.add(new BarEntry(10f, 50));
//                                barEntries.add(new BarEntry(11f, 80));
//                                barEntries.add(new BarEntry(12f, 90));
//                                barEntries.add(new BarEntry(13f, 40));
//                            }
// TO ADD THE VALUES IN X-AXIS
//                            ArrayList<String> xAxisName = new ArrayList<>();
//                            xAxisName.add("fsddfName 1");
//                            xAxisName.add("sdfsdName 2");
//                            xAxisName.add("dfsdName 3");
//                            xAxisName.add("dfsdfName 4");
//                            xAxisName.add("dsfsdName 5");
//                            xAxisName.add("dsfsdName 6");
//                            xAxisName.add("dfsdName 7");
//                            xAxisName.add("sdfsdName 8");
//                            xAxisName.add("dfsdfName 9");
//                            xAxisName.add("dfsdName 10");
//                            xAxisName.add("dsfdName 11");
//                            xAxisName.add("dfsdName 12");
//                            xAxisName.add("sdfsdfName 13");
                            barchart(chart1, valueSet, xAxis);
                             infoForSyllabus = new SimpleAdapter(getApplicationContext(), list, R.layout.item_row_layout,
                                    new String[]{"Subject Code", "In Details"}, new int[]{R.id.subjectCode, R.id.subDetails});
                            if (commonModel.getLoginType().equals("Parent")) {
                                ImageList.set(0, getResources().getIdentifier("index" + 6, "drawable", getPackageName()));
                            }
                        }
                        Adaptorforhorizontalgraph adaptorforhorizontalgraph = new Adaptorforhorizontalgraph(arrayList,getApplicationContext());
                        graphChartHorizontal.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        graphChartHorizontal.setAdapter(adaptorforhorizontalgraph);
                        //  chart1.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxis));

                        mAdapter = new DashboardCourseAttendanceAdaptor(arrayList, ImageList, getApplicationContext());
                        // recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(mAdapter);
                        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                pd.hide();
            }
        }, error -> pd.hide()) {
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
                pd.hide();
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

        barChart.setDrawGridBackground(true);
        BarDataSet barDataSet = new BarDataSet(arrayList, "Subject's");
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        //barDataSet.setColors();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (commonModel.getLoginType().equals("Student")) {
                barDataSet.setColor(getColor(R.color.theme_color_student));
            } else {
                barDataSet.setColor(getColor(R.color.theme_color_parent));
            }
        }
        barDataSet.setValueTextColor(Color.WHITE);
        BarData barData = new BarData(barDataSet);
        if(arrayList.size()==1){
            barData.setBarWidth(0.1f);
        }

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

    public void SemWiseAPICall() {
        pd.show();
        arrayList = new ArrayList<>();
        String url = getString(R.string.URL_WEB) + constantAPIsClass.sem_WiseAPI;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_STUDENT_ID", commonModel.getSTUDENT_ID());
        Log.e("Sem_wise API", url+"url=="+ new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    pd.hide();
                    Log.e("Sem_wise API", "url==" + response);
                    if (response.get("STATUS").equals("TRUE")) {
                        JSONArray jsonArray = response.getJSONArray("data");
                        ArrayList xAxis = new ArrayList<>();
                        ArrayList valueSet1 = new ArrayList();

                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject jObj = (JSONObject) jsonArray.get(i);
                                    BarEntry v1e1 = new BarEntry(i, (float) Double.parseDouble(jObj.getString("SGPA")));
                                    valueSet1.add(v1e1);

                                    xAxis.add(jObj.getString("SEMESTER_CODE") + "-" + jObj.getString("SEMESTER_SRNO"));
                                    BarDataSet barDataSet1 = new BarDataSet(valueSet1, "");
                                    //  Log.e("Value for y-Axis=","is=="+barDataSet1.getYValForXIndex(i));
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        if (commonModel.getLoginType().equals("Student")) {
                                            if (jObj.getString("STATUS_CODE").equals("FLD")) {
                                                barDataSet1.setColor(getColor(R.color.red));
                                            } else if (jObj.getString("STATUS_CODE").equals("WHLD") || jObj.getString("STATUS_CODE").equals("NA")) {
                                                barDataSet1.setColor(getColor(R.color.white));
                                            } else {
                                                barDataSet1.setColor(getColor(R.color.theme_color_student));
                                            }
                                        } else {
                                            if (jObj.getString("STATUS_CODE").equals("FLD")) {
                                                barDataSet1.setColor(getColor(R.color.red));
                                            } else if (jObj.getString("STATUS_CODE").equals("WHLD") || jObj.getString("STATUS_CODE").equals("NA")) {
                                                barDataSet1.setColor(getColor(R.color.white));
                                            } else {
                                                barDataSet1.setColor(getColor(R.color.theme_color_parent));
                                            }
                                        }
                                    }
                                    barDataSet1.setValueTextColor(Color.WHITE);
                                    BarData data2 = new BarData(barDataSet1);
                                    chart2.setData(data2);
                                    chart2.animateXY(2000, 2000);
                                    YAxis left = chart2.getAxisLeft();
                                    left.setAxisMaxValue(10);//dataset.getYMax()+2);
                                    left.setAxisMinValue(0);
//                                        XAxis bottomAxis = chart2.getXAxis();
//                                        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//                                        bottomAxis.setDrawAxisLine(true);
//                                        bottomAxis.setDrawGridLines(false);
                                    chart2.getDescription().setText("");
                                    chart2.setPinchZoom(false);
                                    chart2.getAxisRight().setEnabled(false);
                                    chart2.setClickable(false);
                                    chart2.setDrawValueAboveBar(false);
                                    chart2.setTouchEnabled(false);
                                    initBarChart(chart2);
                                    chart2.invalidate();
                                    chart2.setFitBars(true);

                                } catch (Exception e) {
                                    Log.e("Exception", "data is=" + e);
                                }
                            }
                            chart2.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxis));
//                                XAxis xl = chart2.getXAxis();
//                                xl.setValueFormatter(new IAxisValueFormatter() {
//                                    @Override
//                                    public String getFormattedValue(float value, AxisBase axis) {
//                                        return (String) xAxis.get((int) value);
//                                    }
//                                });
                        }
                        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                pd.hide();
            }
        }, error -> pd.hide()) {
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
                pd.hide();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void showPieChart(PieChart pieChart, int Received,  int Concession,int Balance) {

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        //initializing data
        Map<String, Integer> typeAmountMap = new HashMap<>();
        typeAmountMap.put("Received", Received);
        typeAmountMap.put("Concession/Write-off", Concession);
        typeAmountMap.put("Balance", Balance);

        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#358CED"));
        colors.add(Color.parseColor("#86BFFF"));
        colors.add(Color.parseColor("#E74260"));
//        colors.add(R.color.piechartGreen);
//        colors.add(R.color.piechartParot);
//        colors.add(R.color.piechartRed);
        //input data and fit data into pie chart entry
        for (String type : typeAmountMap.keySet()) {
            pieEntries.add(new PieEntry(typeAmountMap.get(type), type));
        }

        //collecting the entries with label name
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        //setting text size of the value
        pieDataSet.setValueTextSize(10f);

        //providing color list for coloring different entries
        Legend l = pieChart.getLegend(); // Customize the ledgends
        l.setEnabled(false);
//        l.setTextSize(0.01f);
//        l.setFormSize(0.01f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLinePart1OffsetPercentage(80.f);
        pieDataSet.setValueLinePart1Length(0.4f);
        pieDataSet.setValueLinePart2Length(0.5f);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
        //showing the value of the entries, default true if not set
        pieChart.setUsePercentValues(true);

        pieChart.getDescription().setEnabled(false);

        pieChart.setHighlightPerTapEnabled(true);

        pieData.setDrawValues(true);
        pieChart.setDrawSlicesUnderHole(true);
        pieChart.setDrawEntryLabels(false);

        pieChart.setContentDescription("");
        pieChart.setTouchEnabled(false);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
        // pieChart.setCenterTextTypeface();
//        pieChart.setEntryLabelColor(Color.WHITE);
//        pieChart.setEntryLabelTextSize(0.01f);
        //pieChart.setEntryLabelTypeface(typefaceHeading);
        pieChart.highlightValues(null);
        pieChart.invalidate();
        pieDataSet.setColors(colors);
    }

    private void chartData(PieChart pieChart, int Received,  int Concession,int Balance) {

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(Received, 0));
        entries.add(new PieEntry(Concession, 1));
        entries.add(new PieEntry(Balance, 2));


        final int[] piecolors = new int[]{
                    Color.parseColor("#6ACCCA"),
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
    public void getStudentFeeDtl() {
        pd.show();
        String url = getString(R.string.URL_WEB) + constantAPIsClass.StudentFeeDtl;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_PERSON_ID",commonModel.getSTUDENT_ID());
        postParam.put("str_CKKPARASTATUS", "Y");
        Log.e("Student Fees", url + "is==" + new JSONObject(postParam));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {

            try {
                if (response != null) {
                    Log.e("response ", "is==" + response);
                    pd.hide();
                    ArrayList<ModelForPayable> arrayList = new ArrayList();
                    arrayList.clear();

                    if (response.get("STATUS").equals("TRUE")) {
                        JSONArray jsonArray1 = response.getJSONArray("CurrencyWisePayable");
                        for (int i = 0; i <= jsonArray1.length(); i++) {
                            try {
                                JSONObject jObj = (JSONObject) jsonArray1.get(i);
                                ApplicableFees.setText(jObj.getString("CURRENCY_SYMBOL") + " " + jObj.getString("RECEIVABLE_TOTAL"));
                                Received_Fees.setText(jObj.getString("RECEIVED_TOTAL"));

                                if (jObj.getString("BALANCE_TOTAL").equals("0")) {
                                    BalanceFees.setText("-");
                                } else {
                                    BalanceFees.setText(jObj.getString("BALANCE_TOTAL"));
                                }

                                if (jObj.getString("CONSESSION_TOTAL").equals("0")) {
                                    Concession.setText("-");
                                } else {
                                    Concession.setText(jObj.getString("CONSESSION_TOTAL"));
                                }


                                int RECEIVED = (int) Math.abs(Float.parseFloat(jObj.getString("RECEIVED_TOTAL_PER")));
                                int BALANCE = (int) Math.abs(Double.parseDouble(jObj.getString("BALANCE_TOTAL_PER")));
                                int CONCESSION = (int) Math.abs(Double.parseDouble(jObj.getString("CONSESSION_TOTAL_PER")));

                                //  showPieChart(pieChart, Math.abs(RECEIVED), Math.abs(CONCESSION), Math.abs(BALANCE));
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
                                    arrayList.add(modelforLog);
                                }
                            } catch (Exception e) {
                                Log.e("asds", "asdfas" + e);
                            }
                        }
                        AbstractForPayable abstractForPayable = new AbstractForPayable(arrayList, context, modelforLog, "Home");
                        recyclerView1.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView1.setAdapter(abstractForPayable);
                        abstractForPayable.notifyDataSetChanged();
                        abstractForPayable.setHasStableIds(true);
                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                pd.hide();
            }


        }, error -> pd.hide()) {
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
                pd.hide();
            }
        });
        requestQueue.add(jsonObjectRequest);


    }

    private void showBarChart(BarChart barChart) {
        ArrayList<Double> valueList = new ArrayList<>();
        ArrayList<BarEntry> entries = new ArrayList<>();
        String title = "Title";

        //input data
        for (int i = 0; i < 6; i++) {
            valueList.add(i * 100.1);
        }

        //fit the data into a bar
        for (int i = 0; i < valueList.size(); i++) {
            BarEntry barEntry = new BarEntry(i, valueList.get(i).floatValue());
            entries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(entries, title);

        BarData data = new BarData(barDataSet);
        barChart.setData(data);
        barChart.invalidate();
    }

    private void initBarChart(BarChart barChart) {
        //hiding the grey background of the chart, default false if not set
        barChart.setDrawGridBackground(false);
        //remove the bar shadow, default false if not set
        barChart.setDrawBarShadow(false);
        //remove border of the chart, default false if not set
        barChart.setDrawBorders(false);

        //remove the description label text located at the lower right corner
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);

        //setting animation for y-axis, the bar will pop up from 0 to its value within the time we set
        barChart.animateY(1000);
        //setting animation for x-axis, the bar will pop up separately within the time we set
        barChart.animateX(1000);

        XAxis xAxis = barChart.getXAxis();
        //change the position of x-axis to the bottom
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //set the horizontal distance of the grid line
        xAxis.setGranularity(1f);
        //hiding the x-axis line, default true if not set
        xAxis.setDrawAxisLine(false);
        //hiding the vertical grid lines, default true if not set
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = barChart.getAxisLeft();
        //hiding the left y-axis line, default true if not set
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = barChart.getAxisRight();
        //hiding the right y-axis line, default true if not set
        rightAxis.setDrawAxisLine(false);

        Legend legend = barChart.getLegend();
        //setting the shape of the legend form to line, default square shape
        legend.setForm(Legend.LegendForm.LINE);
        //setting the text size of the legend
        legend.setTextSize(11f);
        //setting the alignment of legend toward the chart
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        //setting the stacking direction of legend
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //setting the location of legend outside the chart, default false if not set
        legend.setDrawInside(false);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new MaterialAlertDialogBuilder(HomeScreenActivity.this, R.style.AlertDialogTheme)
                    .setTitle("Alert")
                    .setMessage(getString(R.string.ExiApp))
//                    .setCancelable()
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        finish();
                        System.exit(0);
                    }).setNegativeButton("No", (dialog, which) -> dialog.dismiss()).show();
            // super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_Events) {
//            Toast.makeText(this, "Work in Progress", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), OtherLinksActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_Notifications) {
//            Toast.makeText(this, "Work in Progress", Toast.LENGTH_SHORT).show();
            //getNotificationDetails();
            notificationCount();
            // showCustomTooltip(findViewById(R.id.action_Notifications));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.e("Selected Menu  ", "is==" + item.getTitle());
//        Fragment fragment = null;
//        FragmentManager fragmentManager = getSupportFragmentManager();
        if (item.getTitle().equals("Dashboard")) {
            //fragment = new ImportFragment();
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
        }
        if (item.getTitle().equals("Attendance")) {
            Intent intent = new Intent(getApplicationContext(), AttendanceActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (item.getTitle().equals("Syllabus")) {
            Intent intent = new Intent(getApplicationContext(), MySyllabusActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (item.getTitle().equals("Schedule")) {
//            Toast.makeText(this, "Work in Progress", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (item.getTitle().equals("Fees")) {
            Toast.makeText(this, "Work in Progress", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), FeesActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (item.getTitle().equals("Profile")) {
            // fragment = new ProfileFragment();
            Intent intent = new Intent(getApplicationContext(), UserProfileScreenActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (item.getTitle().equals("E-Learning")) {
            Toast.makeText(this, "Work in Progress", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), ElearningActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (item.getTitle().equals("Logout")) {
            //System.exit(0);
            preferManager.setUserType(commonModel.getLoginType());
            Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
            startActivity(intent);
            finish();

        }
        //fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        int item = v.getId();
        if (item == R.id.home_screen_Refresh) {
            FirstAPiCallToRefresh(commonModel.getACAD_SESSION_ID(), "E", commonModel.getMAIN_SEMESTER_MST_ID(), preferManager.getuserId());
        }
        if (item == R.id.subjectInfo) {
            new MaterialAlertDialogBuilder(HomeScreenActivity.this, R.style.AlertDialogTheme)
                    .setTitle("Help")
                    .setAdapter(infoForSyllabus, null)
                    .show();
        }
        if (item == R.id.sem_wiseInfo) {
            View view = getLayoutInflater().inflate(R.layout.custom_dialog_chart, null);
            MaterialCardView cardColorPsd = view.findViewById(R.id.cardColor);
            if (commonModel.getLoginType().equals("Student")) {
                cardColorPsd.setCardBackgroundColor(getColor(R.color.theme_color_student));
            } else {
                cardColorPsd.setCardBackgroundColor(getColor(R.color.theme_color_parent));
            }
            new MaterialAlertDialogBuilder(HomeScreenActivity.this, R.style.AlertDialogTheme)
                    .setTitle("Help")
                    .setView(view)
                    .show();
        }
        if (item == R.id.editColor) {
            showCustomTooltip(v);
        }
        if (item == R.id.logout) {
            preferManager.setUserType(commonModel.getLoginType());
            Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
            startActivity(intent);
            finish();
        }
        if (item == R.id.reset) {
            drawer.addDrawerListener(toggle);
            new MaterialAlertDialogBuilder(HomeScreenActivity.this, R.style.AlertDialogTheme)
                    .setTitle("Alert")
                    .setMessage(getString(R.string.restApp))
//                    .setCancelable()
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        preferManager = new PreferManager(getApplicationContext(), "clear");
                        new CommonModel();
                        Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
                        startActivity(intent);
                        finish();
//                            System.exit(0);
                    }).setNegativeButton("No", (dialog, which) -> dialog.dismiss()).show();
        }
        if (item == R.id.showLayout) {
            ScrollLayout.postDelayed(() -> ScrollLayout.smoothScrollTo(0, ScrollLayout.getHeight()), 300);
            rotationAngle = rotationAngle == 0 ? 180 : 0;  //toggle
            //ShowLayout.animate().rotation(rotationAngle).setDuration(200).start();
            showLayoutdata.animate().setDuration(200);
            if (rotationAngle == 0) {
                showLayoutdata.setVisibility(View.GONE);
            } else {
                showLayoutdata.setVisibility(View.VISIBLE);
            }
        }
    }

    public void notificationCount() {
        pd.show();
        String url = getString(R.string.URL_WEB) + constantAPIsClass.getNotifications;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("PI_EMPLOYEE_ID", commonModel.getSTUDENT_ID());
        postParam.put("PI_EMPLOYEE_TYPE", userTypeSymbol);
        postParam.put("PI_WORK_DESIG_CODE", "");
        postParam.put("PI_WORK_DESG_MST_ID", "0");
        postParam.put("PI_DEPARTMENT_NUMBER", "0");
        postParam.put("PI_CENTER_CODE", commonModel.getCENTRE_CODE());
        postParam.put("PI_FILTER_VAL", commonModel.getBRANCH_STANDARD_ID());
        postParam.put("PI_SESSION_ID", commonModel.getACAD_SESSION_ID());
        postParam.put("PI_Semester_Type", "O");
        postParam.put("PI_Interface_from", "");
        Log.e("NotificationURL", url+" == " +new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            Log.e("data", "notification" + response);
            try {
                if (response.getString("STATUS").equals("TRUE")) {
                    pd.hide();
                    int Success = 0, Warning = 0, Info = 0, Alert = 0;
                    JSONObject jsonObject = response.getJSONObject("Notification");

                    if (!jsonObject.getString("WELLDONE").isEmpty()) {
                        Success = Integer.parseInt(jsonObject.getString("WELLDONE"));
                    }
                    if (!jsonObject.getString("WARNING").isEmpty()) {
                        Warning = Integer.parseInt(jsonObject.getString("WARNING"));
                    }
                    if (!jsonObject.getString("INFO").isEmpty()) {
                        Info = Integer.parseInt(jsonObject.getString("INFO"));
                    }
                    if (!jsonObject.getString("ALERT").isEmpty()) {
                        Alert = Integer.parseInt(jsonObject.getString("ALERT"));
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        getNotificationDetails(Success, Warning, Info, Alert);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
                pd.hide();
            }

        }, error -> pd.hide()) {
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
                pd.hide();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getNotificationDetails(int Success, int Warning, int Info, int Alert) {
        final DialogPlus inflate = DialogPlus.newDialog(this)
                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.activity_notifications))
                .setExpanded(true, WindowManager.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.TOP)
                .setCancelable(true)
                .create();
        Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.toolbar4Notification);
        toolbar.setTitle("");
        //setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> inflate.dismiss());

        ViewPager2 viewPager = (ViewPager2) inflate.findViewById(R.id.view_pager1);
        TabLayout tabLayout = (TabLayout) inflate.findViewById(R.id.tab_layout1);
        SuccessFragment successFragment = new SuccessFragment();
        WarningFragment warningFragment = new WarningFragment();
        InfoFragment infoFragment = new InfoFragment();
        AlertFragment alertFragment = new AlertFragment();
        tabLayout.addTab(tabLayout.newTab().setText("Success"));
        tabLayout.addTab(tabLayout.newTab().setText("Warning"));
        tabLayout.addTab(tabLayout.newTab().setText("Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Alert"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        //set the badge
        BadgeDrawable badgeSuccess = tabLayout.getTabAt(0).getOrCreateBadge();
        badgeSuccess.setVisible(true);
        badgeSuccess.setBackgroundColor(getColor(R.color.dashColorGreen));
        badgeSuccess.setNumber(Success);

        BadgeDrawable badgeWarning = tabLayout.getTabAt(1).getOrCreateBadge();
        badgeWarning.setBackgroundColor(getColor(R.color.dashColorOrange));
        badgeWarning.setVisible(true);
        badgeWarning.setNumber(Warning);

        BadgeDrawable badgeInfo = tabLayout.getTabAt(2).getOrCreateBadge();
        badgeInfo.setBackgroundColor(getColor(R.color.blue));
        badgeInfo.setVisible(true);
        badgeInfo.setNumber(Info);

        BadgeDrawable badgeAlert = tabLayout.getTabAt(3).getOrCreateBadge();
        badgeAlert.setBackgroundColor(getColor(R.color.dashColorRed));
        badgeAlert.setVisible(true);
        badgeAlert.setNumber(Alert);

        AttendanceActivity.ViewPagerAdapter viewPagerAdapter = new AttendanceActivity.ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPagerAdapter.addFragment(successFragment, "");
        viewPagerAdapter.addFragment(warningFragment, "");
        viewPagerAdapter.addFragment(infoFragment, "");
        viewPagerAdapter.addFragment(alertFragment, "");
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        Bundle bundle = new Bundle();
        bundle.putSerializable("CommonModel", commonModel);
        successFragment.setArguments(bundle);
        warningFragment.setArguments(bundle);
        infoFragment.setArguments(bundle);
        alertFragment.setArguments(bundle);

        inflate.show();
    }

    /////////Custom Tool Tip //////////////
    private void showCustomTooltip(View view) {
        View content = getLayoutInflater().inflate(R.layout.color_theme_nav_header_layout, null);
        mHandler = new Handler();
        Resources res = getResources();
        TooltipOpen = true;
        tooltipSize = res.getDimensionPixelOffset(R.dimen.one_dp);
        tooltipColor = ContextCompat.getColor(this, R.color.red);
        tooltipPadding = res.getDimensionPixelOffset(R.dimen.one_dp);
        tipSizeSmall = res.getDimensionPixelSize(R.dimen.one_dp);
        tipSizeRegular = res.getDimensionPixelSize(R.dimen.one_dp);


        customTooltip = new Tooltip.Builder(this)
                .anchor(view, Tooltip.BOTTOM)
                .animate(new TooltipAnimation(TooltipAnimation.SCALE_AND_FADE, 400))
                .autoAdjust(true)
                .withPadding(tooltipPadding)
                .content(content)
                .cancelable(true)
                .checkForPreDraw(true)
                .withTip(new Tooltip.Tip(tipSizeRegular, tipSizeRegular, tooltipColor, tipRadius))
                .into(navigationView)
                .debug(true)
                .show();
        /*mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TooltipOpen  =false;
                customTooltip.dismiss();
                TooltipOpen  =false;
            }

        }, 5000);*/

//        content.findViewById(R.id.lay_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TooltipOpen =false;
//                customTooltip.dismiss(true);
//            }
//        });


    }
}