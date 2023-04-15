package com.example.ilip.activities.loginRegistrtionActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.snackbar.Snackbar;
import com.sn.lib.NestedProgress;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    MaterialButtonToggleGroup materialButtonToggleGroup;
    EditText ed_institutecode,ed_loginid,ed_mobilenum,ed_password;
    Button nextBtn;
    CommonModel commonModel;
    PreferManager preferManager;
    ConstantAPIsClass constantAPIsClass;
    RequestQueue requestQueue;
    LinearLayout registrationScreen;
    ImageView img;
    String userTypeInit="P";
    NestedProgress nestedProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context = this;
        requestQueue = Volley.newRequestQueue(this);
        constantAPIsClass = new ConstantAPIsClass();
        preferManager = new PreferManager(this);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        commonModel=(CommonModel)  extras.get("CommonModel");
        Log.e("CommonModel","model data"+commonModel);
        if(userTypeInit.equals("S")){
            commonModel.setLoginType("Student");
        }else{
            commonModel.setLoginType("Parent");
        }
        nestedProgress = new NestedProgress(this);
        nestedProgress = findViewById(R.id.custom_Loader_Regist);
        img = findViewById(R.id.regisLogo);
        registrationScreen = findViewById(R.id.registrationScreen);
        nextBtn = findViewById(R.id.nextBtn);
//        materialButtonToggleGroup = findViewById(R.id.materialtoogelBtn);
//        materialButtonToggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
//            if (isChecked) {
//                getCheckedButton();
//            }
//        });
        registrationScreen.setBackgroundResource(R.drawable.registration_parent_bg);
        nextBtn.setBackgroundColor(getColor(R.color.theme_color_parent));
        setTheme(R.style.ParentTheme);
        img.setImageResource(R.drawable.iliplogo);
        userTypeInit = "P";
        ed_institutecode = findViewById(R.id.instituteCode);
        ed_loginid = findViewById(R.id.loginId);

        ed_mobilenum = findViewById(R.id.phoneNumber);
        ed_password = findViewById(R.id.ed_password);

        nextBtn.setOnClickListener(this);
       // if(ed_institutecode.clearFocus())
        ed_institutecode.setOnFocusChangeListener((v, hasFocus) -> {
            if(ed_loginid.isFocused()){
                getClientCodeVerification(ed_institutecode.getText().toString());
            }
        });

        ed_institutecode.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                ed_institutecode.clearFocus();
                if(ed_institutecode.getText().toString().isEmpty()){
                    ed_institutecode.setError(getString(R.string.error_field_required));
                    ed_institutecode.requestFocus();
                    nestedProgress.setVisibility(View.GONE);
                    return false;
                }else{
                    if(ed_loginid.isFocusable()){
                        getClientCodeVerification(ed_institutecode.getText().toString());
                        ed_loginid.requestFocus();
                        return true;
                    }
                }
            }
            return false;
        });
        ed_loginid.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                ed_loginid.clearFocus();
                ed_mobilenum.requestFocus();
                return true;
            }
            return false;
        });
        ed_mobilenum.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                ed_mobilenum.clearFocus();
                ed_password.requestFocus();
                return true;
            }
            return false;
        });
        ed_password.setOnEditorActionListener((v, actionId, event) -> {
            InputMethodManager imm=null;
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                    imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ed_password.getApplicationWindowToken(), 0);
                    nextBtn.requestFocus();
                    return true;
            }
            return false;
        });

    }

    @Override
    public void onClick(View view) {
    if(view.getId()==R.id.nextBtn){
        forPinCreatePageCall();
    }
    }
    public void forPinCreatePageCall(){
        nestedProgress.setVisibility(View.VISIBLE);
        if(ed_institutecode.getText().toString().isEmpty()){
            ed_institutecode.setError(getString(R.string.error_field_required));
            ed_institutecode.requestFocus();
            nestedProgress.setVisibility(View.GONE);
            return;
        }
        if (ed_loginid.getText().toString().isEmpty()){
            ed_loginid.setError(getString(R.string.error_field_required));
            ed_loginid.requestFocus();
            nestedProgress.setVisibility(View.GONE);
            return;
        }

        if(ed_mobilenum.getText().toString().isEmpty()){
            ed_mobilenum.setError(getString(R.string.error_field_required));
            ed_mobilenum.requestFocus();
            nestedProgress.setVisibility(View.GONE);
            return;
        }
        if (ed_password.getText().toString().isEmpty()){
            ed_password.setError(getString(R.string.error_field_required));
            ed_password.requestFocus();
            nestedProgress.setVisibility(View.GONE);
            return;
        }
        if(!ed_loginid.getText().toString().contains(userTypeInit)){
//            ed_loginid.setError(getString(R.string.error_wrong_user_type));
//            ed_loginid.requestFocus();
            Snackbar.make(registrationScreen, getString(R.string.error_wrong_user_type), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            nestedProgress.setVisibility(View.GONE);
            return;
        }

        String url = getString(R.string.URL_WEB) + ConstantAPIsClass.RegistrationAPI;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("empid", ed_loginid.getText().toString());
        postParam.put("password", ed_password.getText().toString());
        postParam.put("Device_Id", preferManager.getTokenId());
        postParam.put("mobileno", ed_mobilenum.getText().toString());
        postParam.put("Module_Code", "STUAPPANRD");
        postParam.put("URN_Number", "");
        postParam.put("SMSAPI", "");
        postParam.put("CENTER_CODE", "");
        postParam.put("INST_MOD_CODE", ed_institutecode.getText().toString());
        postParam.put("APP_LICENSED", "SASHA_ANDROID");
        postParam.put("DATABASE",commonModel.getClientDB());

        JSONObject jsonObject = new JSONObject(postParam);
        Log.e("COnverted Data is", "" + jsonObject);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            Log.e("jsonObject", "" + response);
            try {

                if (response != null) {
                    nestedProgress.setVisibility(View.GONE);
                    String message = response.getString("MESSAGE");
                    if (response.get("STATUS").equals("TRUE")) {
                        commonModel.setOTP_Pin((String) response.get("OTP"));
                        commonModel.setStudentInstituteCode(ed_institutecode.getText().toString());
                        commonModel.setStudentLoginId((String) response.get("USERID"));
                        commonModel.setStudentMobileNo(ed_mobilenum.getText().toString());
                        commonModel.setStudentPassword(ed_password.getText().toString());
                        commonModel.setCENTRE_CODE(response.getString("CENTERCODE"));
                        Toast.makeText(getApplicationContext(), "Successfully Call Api", Toast.LENGTH_LONG).show();

                        preferManager.setMobileNo(ed_mobilenum.getText().toString());
                        Intent intent = new Intent(getApplicationContext(), ForPinPasswordActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("CommonModel", commonModel);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                nestedProgress.setVisibility(View.GONE);
            }
        }, error -> {
            Log.e("Exception", "Error=" + error);
            nestedProgress.setVisibility(View.GONE);
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
                nestedProgress.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    public void getClientCodeVerification(String Client_Code){
        String url = getString(R.string.URL_WEB) + ConstantAPIsClass.WebControllerClientCode;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("DATABASE","PMT");
        postParam.put("CLIENT_CODE",Client_Code);
        postParam.put("APP_LICENSED","SASHA_ANDROID");
        Log.e("Client clg Verification", url+"url"+new JSONObject(postParam));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            Log.e("jsonObject", "" + response);
            try {
                if (response != null) {
                    nestedProgress.setVisibility(View.GONE);
                    String message = response.getString("MESSAGE");
                    if (response.get("STATUS").equals("TRUE")) {
                        JSONArray jsonArray = response.getJSONArray("DATA");
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject jObj = (JSONObject) jsonArray.get(i);
                                    commonModel.setClientDB(jObj.getString("ORACLE_USER_NAME"));
                                    nextBtn.setEnabled(true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {
                        nextBtn.setEnabled(false);
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Exception", "Error=" + e);
                nestedProgress.setVisibility(View.GONE);
            }
        }, error -> {
            Log.e("Exception", "Error=" + error);
            nestedProgress.setVisibility(View.GONE);
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
                Log.e("Error == ","=="+error);
                nestedProgress.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getCheckedButton() {
        Log.e("data is ==","getCheckedButton(): ");
        int checkedButtonId = materialButtonToggleGroup.getCheckedButtonId();
        Log.e("data","getCheckedButton(): " + checkedButtonId);
        if (checkedButtonId != 0) {
            MaterialButton checkedButton = findViewById(checkedButtonId);
            String buttonText = checkedButton.getText().toString();
            Toast.makeText(context, buttonText, Toast.LENGTH_SHORT).show();
            commonModel.setLoginType(buttonText);

            if(buttonText.equals("Student")){
                registrationScreen.setBackgroundResource(R.drawable.registration_student_bg);
                nextBtn.setBackgroundColor(getColor(R.color.theme_color_student));
                setTheme(R.style.StudentTheme);
                img.setImageResource(R.drawable.sashalogo4x);
                userTypeInit = "S";
            }else{
                registrationScreen.setBackgroundResource(R.drawable.registration_parent_bg);
                nextBtn.setBackgroundColor(getColor(R.color.theme_color_parent));
                setTheme(R.style.ParentTheme);
                img.setImageResource(R.drawable.iliplogo);
                userTypeInit = "P";
            }
        }
    }
}