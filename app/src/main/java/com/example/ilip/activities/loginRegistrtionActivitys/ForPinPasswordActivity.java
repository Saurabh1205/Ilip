package com.example.ilip.activities.loginRegistrtionActivitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.example.ilip.activities.dashboard.HomeScreenActivity;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.common.OtpView;
import com.example.ilip.common.PreferManager;

import com.example.ilip.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.orhanobut.dialogplus.DialogPlus;
import com.sn.lib.NestedProgress;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForPinPasswordActivity extends AppCompatActivity {
    CommonModel commonModel;
    View view;
    OtpView newPin, reTypePin, edOTP;
    Button nextBtn4pin;
    RequestQueue requestQueue;
    PreferManager preferManager;
    ConstantAPIsClass constantAPIsClass;
    ProgressDialog spotsDialog;
    LinearLayout layoutBackground;
    ImageView img;
    public int counter = 60;
    String Msg = "Registration done Successfully !";
    LoginActivity login = new LoginActivity();
    NestedProgress nestedProgress;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.StudentTheme);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        commonModel = (CommonModel) extras.get("CommonModel");
        Log.e("CommonModel", "model data" + commonModel);
        if(commonModel.getLoginType().equals("Student")){
            setTheme(R.style.StudentTheme);
        }
        if(commonModel.getLoginType().equals("Parent")){
            setTheme(R.style.ParentTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_pin_password);
        view = new View(this);
        spotsDialog = new ProgressDialog(this, R.style.CustomProgressDialog);
        spotsDialog.setMessage("Loading data please wait...");
        constantAPIsClass = new ConstantAPIsClass();
        img = findViewById(R.id.setPinLogo);
        layoutBackground = findViewById(R.id.layoutForgetPin);

        nestedProgress = new NestedProgress(this);
        nestedProgress= findViewById(R.id.custom_Loader_4pinPass);
        preferManager = new PreferManager(this);
        commonModel.setStudentMobileNo(preferManager.getMobileNo());
        requestQueue = Volley.newRequestQueue(this);
        newPin = findViewById(R.id.ed_newPin);
        reTypePin = findViewById(R.id.ed_reTypePin);
        nextBtn4pin = findViewById(R.id.next4PinBtn);
        if(commonModel.getLoginType().equals("Student")){
            layoutBackground.setBackgroundResource(R.drawable.registration_student_bg);
            img.setImageResource(R.drawable.sashalogo4x);
            newPin.setLineColor(getColor(R.color.otp_item_state));
            reTypePin.setLineColor(getColor(R.color.otp_item_state));
        }else{
            layoutBackground.setBackgroundResource(R.drawable.registration_parent_bg);
            img.setImageResource(R.drawable.iliplogo);
            newPin.setLineColor(getColor(R.color.parent_otp_theme));
            reTypePin.setLineColor(getColor(R.color.parent_otp_theme));
        }
        OTPLayoutView(this,commonModel.getStudentMobileNo());
        if(commonModel.getScreenCallFrom().equals("ForgetPin")) {
            CallResendOTP(commonModel.getStudentLoginId(), commonModel.getStudentMobileNo());
            Msg = "Your 4 Pin Password change Successfully";
        }
        newPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (newPin.getText().length() == 4 ) {
                    {
                        newPin.clearFocus();
                        reTypePin.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        reTypePin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pin = reTypePin.getText().toString();
                if (pin != "" || pin != null) {
                    Log.e("length", "" + pin.length());
                    if (4 == pin.length()) {
                        if (!pin.equals(newPin.getText().toString())) {
                            reTypePin.setError("Pin not match!");
                            reTypePin.requestFocus();
                        }
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nextBtn4pin.setOnClickListener(v -> getCompare4Pin(newPin.getText().toString(), reTypePin.getText().toString()));
    }

    public void getCompare4Pin(String newpin, String retypePin) {
        if (TextUtils.isEmpty(newpin)) {
            newPin.setError(getString(R.string.error_field_required));
            newPin.requestFocus();
            return;
        }else{
            newPin.setEnabled(false);
        }
        if (TextUtils.isEmpty(retypePin)) {
            reTypePin.setError(getString(R.string.error_field_required));
            reTypePin.requestFocus();
            return;
        }

        if (newpin.equals(retypePin)) {
           // OTPLayoutView(ForPinPasswordActivity.this);
            getCallLoginScreen(Msg);
        }else{

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void OTPLayoutView(Context context,String Mobile) {
      // nestedProgress.setVisibility(View.VISIBLE);
        final DialogPlus inflate = DialogPlus.newDialog(context)
                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.popup_otp_layout))
                .setExpanded(false, WindowManager.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.TOP)
                .setCancelable(false)
                .create();
        ImageView imageView = (ImageView) inflate.findViewById(R.id.img_Otp);
        LinearLayout layoutBg = (LinearLayout) inflate.findViewById(R.id.layoutOtp);
        TextView verifymob = (TextView) inflate.findViewById(R.id.verifyMsg);

        String mobNo = "######"+Mobile.substring(Mobile.length()-4) ;
        Log.e("Data","Mobile"+mobNo);
        verifymob.setText("Verification OTP sent to "+mobNo+" No.");
        edOTP = (OtpView) inflate.findViewById(R.id.ED_OTP_SBI);
        if(commonModel.getLoginType().equals("Student")){
           // layoutBg.setBackgroundResource(R.drawable.registration_student_bg);
            edOTP.setLineColor(getColor(R.color.otp_item_state));
            imageView.setImageResource(R.drawable.sashalogo4x);
        }else{
           // layoutBg.setBackgroundResource(R.drawable.registration_parent_bg);
            edOTP.setLineColor(getColor(R.color.parent_otp_theme));
            imageView.setImageResource(R.drawable.iliplogo);
        }

        edOTP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                InputMethodManager imm=null;
                String pin = edOTP.getText().toString();
                if (pin != "" || pin != null) {
                    Log.e("length", "" + pin.length());
                    if (6 == pin.length()) {
                        if (commonModel.getOTP_Pin().equals(pin)) {
                            imm = (InputMethodManager) getApplicationContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(edOTP.getApplicationWindowToken(), 0);
                           inflate.dismiss();
                        } else {
                            spotsDialog.hide();
                            edOTP.setError("Entered OTP is wrong !");
                            edOTP.requestFocus();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edOTP.setOnEditorActionListener((textView, action, keyEvent) -> {
            InputMethodManager imm = null;
            if (action == EditorInfo.IME_ACTION_DONE) {
                imm = (InputMethodManager) getApplicationContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edOTP.getApplicationWindowToken(), 0);
                return true;
            }
            return false;
        });
        Button submitBtn = (Button) inflate.findViewById(R.id.SubmitBtn);
        Button resnedBtn = (Button) inflate.findViewById(R.id.resendOtpbtn);
        submitBtn.setOnClickListener(view -> {
            spotsDialog.show();
            if (commonModel.getOTP_Pin().equals(edOTP.getText().toString())) {

                inflate.dismiss();

            } else {
                spotsDialog.hide();
                edOTP.setError("Entered OTP is wrong !");
                edOTP.requestFocus();
            }
        });
        resnedBtn.setOnClickListener(view -> CallResendOTP(commonModel.getStudentLoginId(), "918421480223"));
        inflate.show();
    }

    private void getCallLoginScreen(String Message) {
        new MaterialAlertDialogBuilder(ForPinPasswordActivity.this, R.style.AlertDialogTheme)
                .setTitle("Alert")
                .setMessage(Message)
                .setCancelable(false)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    preferManager.setPin(reTypePin.getText().toString());
                    preferManager.setFirstTimeLogin(false);
                    preferManager.setuserId(commonModel.getStudentLoginId());
                    preferManager.setUserType(commonModel.getLoginType());
                    preferManager.setMobileNo(commonModel.getStudentMobileNo());
                    Intent intent;
                    if(commonModel.getScreenCallFrom().equals("ForgetPin")) {
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                    }else{
                        intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("CommonModel", commonModel);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                    login.finish();
                    dialogInterface.dismiss();
                }).show();
    }

    public void CallResendOTP(String empid, String mobileno) {
    nestedProgress.setVisibility(View.VISIBLE);
        String url = getString(R.string.URL_WEB) + ConstantAPIsClass.ResendOTP;
        Map<String, String> postParam = new HashMap<>();
        postParam.put("empid",empid);
        postParam.put("mobileno", mobileno);
        postParam.put("CENTER_CODE", commonModel.getCENTRE_CODE());
        JSONObject jsonObject = new JSONObject(postParam);
        Log.e("COnverted Data is", url+"" + jsonObject);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            Log.e("jsonObject", "" + response);
            try {
                if (response != null) {
                    nestedProgress.setVisibility(View.GONE);
                    String message = response.getString("MESSAGE");
                    if (response.get("STATUS").equals("TRUE")) {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        commonModel.setOTP_Pin(response.getString("OTP"));
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

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
