package com.example.ilip.activities.loginRegistrtionActivitys;

import static com.example.ilip.R.drawable.bg_for_student;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.ilip.activities.dashboard.HomeScreenActivity;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.common.OtpView;
import com.example.ilip.common.PreferManager;
import com.example.ilip.R;
import com.google.android.material.button.MaterialButton;

import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextView userName;
    Button registerHere,resetPin;
    MaterialButton loginAsStudBtn;
    OtpView editTextForPin;
    RequestQueue requestQueue;
    Context context;
    CommonModel commonModel;
    PreferManager preferManager;
    LinearLayout bg_img_set;
    ImageView loginLogo;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.StudentTheme);
        preferManager = new PreferManager(getApplicationContext());
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        commonModel = (CommonModel) extras.get("CommonModel");
        Log.e("CommonModel", "model data" + preferManager.getUserType()+"\n"+commonModel);
        if(!preferManager.getUserType().isEmpty()){
            commonModel.setLoginType(preferManager.getUserType());
            commonModel.setStudentLoginId(preferManager.getuserId());
            commonModel.setStudentMobileNo(preferManager.getMobileNo());
        }
        if(commonModel.getLoginType().equals("Student")){
            setTheme(R.style.StudentTheme);
        }
        if(commonModel.getLoginType().equals("Parent")){
            setTheme(R.style.ParentTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context = this;

        Log.e("CommonModel",preferManager.getUsername()+preferManager.getMobileNo()+"\nmodel data"+commonModel);
        requestQueue = Volley.newRequestQueue(this);
        bg_img_set = findViewById(R.id.LoginLayout);
        loginLogo = findViewById(R.id.loginLogo);

        editTextForPin = findViewById(R.id.edit_text4Pin);
        userName = findViewById(R.id.name);
        userName.setText(preferManager.getUsername());
       // forgetPin = findViewById(R.id.forgetBtn);
        resetPin = findViewById(R.id.resetBtn);
        loginAsStudBtn = findViewById(R.id.loginBtn);
        registerHere = findViewById(R.id.register_hereBtn);
       // forgetPin.setOnClickListener(this);
        resetPin.setOnClickListener(this);
        loginAsStudBtn.setOnClickListener(this);
        registerHere.setOnClickListener(this);
        executor = ContextCompat.getMainExecutor(this);
        if (commonModel.getLoginType().equals("Student")) {
            bg_img_set.setBackgroundResource(bg_for_student);
            loginLogo.setImageResource(R.drawable.loginlogo_student);
            loginAsStudBtn.setText("Login As Student");
            loginAsStudBtn.setBackgroundColor(Color.parseColor("#5A9CC8"));
            editTextForPin.setLineColor(getColor(R.color.otp_item_state));
        }
        if(commonModel.getLoginType().equals("Parent")){
            bg_img_set.setBackgroundResource(R.drawable.bg_for_parent);
            loginLogo.setImageResource(R.drawable.loginlogo_parent);
            loginAsStudBtn.setText("Login As Parent");
            loginAsStudBtn.setBackgroundColor(Color.parseColor("#55A4A2"));
            editTextForPin.setLineColor(getColor(R.color.parent_otp_theme));
        }

        biometricPrompt = new BiometricPrompt(this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
//                Toast.makeText(context, "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //Toast.makeText(context, "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CommonModel", commonModel);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });



        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Please Unlock Your App/Phone Security")
                .setSubtitle("Screen lock Pattern,PIN,Password or Fingerprint")
                .setDescription("Unlock SASHA")
                .setNegativeButtonText("4-Digit PIN for Login")
                .build();
        biometricPrompt.authenticate(promptInfo);
        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
//        Button biometricLoginButton = findViewById(R.id.biometric_login);
//        biometricLoginButton.setOnClickListener(view -> {
//
//        });
    }

    public void match4pin(String str){
        if(editTextForPin.getText().toString().isEmpty()){
           editTextForPin.setError(getString(R.string.error_field_required));
           editTextForPin.requestFocus();
           return;
        }
        if (preferManager.getPin().equals(editTextForPin.getText().toString())) {
            Intent intent = new Intent(this, HomeScreenActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }else{
            editTextForPin.setError(getString(R.string.enterCorrectPIN));
            editTextForPin.requestFocus();
            return;
        }
    }
    @Override
    public void onClick(View view) {
//        if(view.getId()== R.id.forgetBtn) {
//            registrationPageCall();
//        }
        if(view.getId()== R.id.resetBtn) {
            resetPinPageCall();
        }
        if(view.getId()== R.id.loginBtn) {
            //getOtpFromMessage("1234");
           // loginUserApi_call();
            match4pin(editTextForPin.getText().toString());
        }
        if(view.getId()== R.id.register_hereBtn) {
            registrationPageCall();
        }
    }

//    public void loginUserApi_call(){
//     String forPinData="";
//        //forPinData = editTextForPin.getText().toString();
//        if(TextUtils.isEmpty(forPinData)){
//            //editTextForPin.setError("This Filed Should Not Be Empty");
//            //editTextForPin.requestFocus();
//            return;
//        }
//        String url = getString(R.string.URL_WEB) +"/";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.e("jsonObject", "" + response);
//                try {
//                    if (response != null) {
//                        if (response.get("status").equals("SUCCESS")) {
//                            Toast.makeText(getApplicationContext(),"Successfully Call Api",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 50000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 50000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }

    public void registrationPageCall(){
            Intent intent = new Intent(this, RegistrationActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
    }

    public void resetPinPageCall(){
        commonModel.setScreenCallFrom("ForgetPin");
        Intent intent = new Intent(this, ForPinPasswordActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CommonModel", commonModel);
        intent.putExtras(bundle);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        LoginActivity.this.finish();
//        System.exit(0);
    }
}