package com.example.ilip.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.common.PreferManager;
import com.example.ilip.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserProfileScreenActivity extends AppCompatActivity {
    PreferManager preferManager;
    CommonModel commonModel;
    RequestQueue requestQueue;
    ConstantAPIsClass constantAPIsClass;
    ProgressDialog spotsDialog;
    TextView titlebartext;
    TextView profile_studentId,profile_fullName,profile_faculty,profile_branch,profile_branchCode;
    TextView admission_Category,admission_Pattern,admission_Acad_Label,admission_Acad_Session,admission_University_EnrNo;
    TextView person_Gender,person_BloodGrp,person_AadharNo,person_DOB,person_IsHandicapped,person_Religion
            ,person_Cast,person_Category,person_Nationality;
    TextView contact_PersonMob,contact_PersonEmail;
    TextView contact_FatherMobile,contact_FatherEmail;
    TextView contact_MotherMobile,contact_MotherEmail;
    TextView contact_GuardianMobile,contact_GuardianEmail;
    TextView permanent_Address,correspondence_Address;

    ImageView profileImage;
    String userTypeSymbol="";
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
        setContentView(R.layout.activity_user_profile_screen);
//        LayoutInflater inflater = (LayoutInflater) this
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View contentView = inflater.inflate(R.layout.activity_user_profile_screen, null, false);
//        drawer.addView(contentView,0);
//        titlebartext = findViewById(R.id.titleBar);
//        titlebartext.setText("My Profile");
        constantAPIsClass = new ConstantAPIsClass();
        requestQueue = Volley.newRequestQueue(this);
        preferManager = new PreferManager(this);
        spotsDialog = new ProgressDialog(this, R.style.CustomProgressDialog);
        spotsDialog.setMessage("Loading data please wait...");
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        commonModel = (CommonModel) extras.get("CommonModel");
//        Log.e("CommonModel", "model data" + commonModel);
        Log.e("Local Storage Data=","User Type=="+preferManager.getUserType());
        if(commonModel.getLoginType().equals("Student")){
            userTypeSymbol="S";
        }else{
            userTypeSymbol="P";
        }
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar4Profile);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
        profileImage = findViewById(R.id.profileImage);
        Picasso.get()
                .load(commonModel.getProfileImageURL())
                .into(profileImage);
        profile_studentId = findViewById(R.id.profile_studentId);
        profile_fullName = findViewById(R.id.profile_fullName);
        profile_faculty = findViewById(R.id.profile_faculty);
        profile_branch = findViewById(R.id.profile_branch);
        profile_branchCode = findViewById(R.id.profile_branchCode);

        admission_Category = findViewById(R.id.Adm_Category);
        admission_Pattern = findViewById(R.id.Adm_Pattern);
        admission_Acad_Label = findViewById(R.id.Adm_Acad_Label);
        admission_Acad_Session = findViewById(R.id.Adm_Acad_Session);
        admission_University_EnrNo = findViewById(R.id.Adm_University_EnrNo);

        person_Gender = findViewById(R.id.Person_gender);
        person_BloodGrp = findViewById(R.id.Person_BloodGrp);
        person_AadharNo = findViewById(R.id.Person_AadharNo);
        person_DOB = findViewById(R.id.Person_DOB);
        person_IsHandicapped = findViewById(R.id.person_IsHandicapped);
        person_Religion = findViewById(R.id.Person_Religion);
        person_Cast = findViewById(R.id.Person_Cast);
        person_Category = findViewById(R.id.Person_Category);
        person_Nationality = findViewById(R.id.Person_Nationality);

        contact_PersonMob = findViewById(R.id.person_Mobile);
        contact_PersonEmail = findViewById(R.id.person_Email);
        contact_FatherMobile = findViewById(R.id.father_Mobile);
        contact_FatherEmail = findViewById(R.id.father_Email);
        contact_MotherMobile = findViewById(R.id.mother_Mobile);
        contact_MotherEmail = findViewById(R.id.mother_Email);
        contact_GuardianMobile = findViewById(R.id.guardian_Mobile);
        contact_GuardianEmail = findViewById(R.id.guardian_Email);
        permanent_Address = findViewById(R.id.Permanent_Address);
        correspondence_Address = findViewById(R.id.correspondence_Address);

//        profile_subBranch = findViewById(R.id.profile_subBranch);
        ProfileDetailAPICall(commonModel.getSTUDENT_ID(),commonModel.getBRANCH_STANDARD_GRP_ID());
    }


//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
    public void ProfileDetailAPICall(String studentId,String groupId){
        String url = getString(R.string.URL_WEB) + constantAPIsClass.ProfileDetailAPI;
        Map<String,String> postParam = new HashMap<>();
        postParam.put("PI_STUDENT_ID",studentId);
        postParam.put("PI_BRANCH_STANDARD_GRP_ID",groupId);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            try {
                if (response != null) {
                    Log.e("ProfileDetails", "Data" + response);
                    String message = response.getString("MESSAGE");
                    if (response.get("STATUS").equals("TRUE")) {
                        JSONArray jsonArrayPersonal_Dtls = response.getJSONArray("Personal_Dtls");
                        JSONArray jsonArrayContact_Dtls = response.getJSONArray("Contact_Dtls");
                        Log.e("Personal_Dtls", "Array data=" + jsonArrayPersonal_Dtls);
                        Log.e("Contact_Dtls", "Array dataContact_Dtls=" + jsonArrayContact_Dtls);
                        if (jsonArrayPersonal_Dtls != null) {
                            for (int i = 0; i < jsonArrayPersonal_Dtls.length(); i++) {
                                try {
                                    JSONObject jObj = (JSONObject) jsonArrayPersonal_Dtls.get(i);

                                    profile_studentId.setText(userTypeSymbol + "" + jObj.getString("STUDENT_ID"));
                                    profile_fullName.setText(jObj.getString("FULL_NAME"));
                                    profile_faculty.setText(jObj.getString("CENTRE_NAME"));
                                    profile_branch.setText(jObj.getString("BRANCH_STD_GROUP_DESCRIPTION"));
                                    profile_branchCode.setText(jObj.getString("SPECIALIZATION_DUAL"));
//
                                    admission_Category.setText(jObj.getString("ADMISSION_CATEGORY_CODE"));
                                    admission_Pattern.setText(jObj.getString("ADMISSION_PATTERN"));
                                    admission_Acad_Label.setText(jObj.getString("ADMIT_ACADEMIC_LABEL") + " : ");
                                    admission_Acad_Session.setText(jObj.getString("ADMIT_ACADEMIC_YEAR"));
                                    admission_University_EnrNo.setText(jObj.getString("ENROLLMENT_NUMBER"));
                                    person_Gender.setText(jObj.getString("GENDER"));
                                    person_BloodGrp.setText(jObj.getString("BLOODGROUP"));
                                    person_AadharNo.setText(jObj.getString("ADHAR_CARD_NO"));
                                    person_DOB.setText(jObj.getString("DATE_OF_BIRTH"));
                                    person_IsHandicapped.setText(jObj.getString("PHYSICALLY_HANDICAPPED"));
                                    person_Religion.setText(jObj.getString("RELIGION"));
                                    person_Cast.setText(jObj.getString("CASTE_DESCRIPTION"));
                                    person_Category.setText(jObj.getString("CATEGORY_CODE"));
                                    person_Nationality.setText(jObj.getString("NATIONALITY"));

                                } catch (Exception e) {

                                }
                            }
                        }
                        if (jsonArrayContact_Dtls != null) {
                            for (int i = 0; i < jsonArrayContact_Dtls.length(); i++) {
                                try {
                                    JSONObject jObj = (JSONObject) jsonArrayContact_Dtls.get(i);

                                    contact_PersonMob.setText(jObj.getString("STUDENT_MOBILE_NO"));
                                    contact_PersonEmail.setText(jObj.getString("STUDENT_EMAIL_ID"));
                                    contact_FatherMobile.setText(jObj.getString("FATHER_MOBILE_NO"));
                                    contact_FatherEmail.setText(jObj.getString("FATHER_EMAIL_ID"));
                                    contact_MotherMobile.setText(jObj.getString("MOTHER_MOBILE_NO"));
                                    contact_MotherEmail.setText(jObj.getString("MOTHER_EMAIL_ID"));
                                    contact_GuardianMobile.setText(jObj.getString("GUARDIAN_MOBILE_NO"));
                                    contact_GuardianEmail.setText(jObj.getString("GUARDIAN_EMAIL_ID"));
                                    permanent_Address.setText(jObj.getString("PLOT_NUMBER") + "," + jObj.getString("STUDENT_ADDRESS1")
                                            + "," + jObj.getString("STREET_NAME") + "," + jObj.getString("CITY") + "," + jObj.getString("STATE_DESCRIPTION")
                                            + "-" + jObj.getString("PINCODE"));
                                    correspondence_Address.setText(permanent_Address.getText().toString());
                                } catch (Exception e) {

                                }
                            }
                        }
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
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

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        return false;
//    }

}