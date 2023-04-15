package com.example.ilip.activities.loginRegistrtionActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ilip.all_model.CommonModel;
import com.example.ilip.common.OtpView;
import com.example.ilip.common.PreferManager;
import com.example.ilip.R;
import com.google.android.material.button.MaterialButton;

public class ResetPinActivity extends AppCompatActivity implements View.OnClickListener {
    OtpView editTextResetOldPin,editTextResetNewPin,editTextResetRetypePIN;
    Context context;
    CommonModel commonModel;
    PreferManager preferManager;
    MaterialButton submitBtn;
    LinearLayout layoutBackground;
    ImageView logoImg;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ParentTheme);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        commonModel=(CommonModel)  extras.get("CommonModel");
        Log.e("CommonModel","model data"+commonModel);
        if(commonModel.getLoginType().equals("Student")){
            setTheme(R.style.ParentTheme);
        }
        if(commonModel.getLoginType().equals("Parent")){
            setTheme(R.style.StudentTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context = this;
        preferManager = new PreferManager(this);
//        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar4ResetPin);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(view -> {
//            onBackPressed();
//        });
        layoutBackground = findViewById(R.id.layoutRest);
        logoImg = findViewById(R.id.resetLogo);
        editTextResetOldPin  = findViewById(R.id.ed_OdlPIN);
       // editTextResetOldPin.setText(preferManager.getPin());

        editTextResetNewPin = findViewById(R.id.ed_NewPINReset);
        editTextResetRetypePIN = findViewById(R.id.ed_reTypePinReset);
        submitBtn = findViewById(R.id.submitBtnReset);
        submitBtn.setOnClickListener(this);
        if(commonModel.getLoginType().equals("Student")){
            layoutBackground.setBackgroundResource(R.drawable.registration_student_bg);
            logoImg.setImageResource(R.drawable.sashalogo4x);
            editTextResetOldPin.setLineColor(getColor(R.color.otp_item_state));
            editTextResetNewPin.setLineColor(getColor(R.color.otp_item_state));
            editTextResetRetypePIN.setLineColor(getColor(R.color.otp_item_state));
            submitBtn.setBackgroundColor(getColor(R.color.theme_color_student));
        }else{
            layoutBackground.setBackgroundResource(R.drawable.registration_parent_bg);
            logoImg.setImageResource(R.drawable.iliplogo);
            editTextResetOldPin.setLineColor(getColor(R.color.parent_otp_theme));
            editTextResetNewPin.setLineColor(getColor(R.color.parent_otp_theme));
            editTextResetRetypePIN.setLineColor(getColor(R.color.parent_otp_theme));
            submitBtn.setBackgroundColor(getColor(R.color.theme_color_parent));
        }

        editTextResetNewPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pin = preferManager.getPin();
                if (pin != "" || pin != null) {
                    Log.e("length", "" + pin.length());
                    if (editTextResetNewPin.getText().length() == 4 ) {
                        if (pin.equals(editTextResetNewPin.getText().toString())) {
                            editTextResetNewPin.setError("Your new PIN have to be different !");
                            editTextResetNewPin.requestFocus();
                        }else{
                            editTextResetNewPin.clearFocus();
                            editTextResetRetypePIN.requestFocus();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextResetRetypePIN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pin = editTextResetRetypePIN.getText().toString();
                if (pin != "" || pin != null) {
                    Log.e("length", "" + pin.length());
                    if (4 == pin.length()) {
                        if (!pin.equals(editTextResetNewPin.getText().toString())) {
                            editTextResetRetypePIN.setError("Pin not match!");
                            editTextResetRetypePIN.requestFocus();
                        }else{
                            editTextResetNewPin.clearFocus();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        if(itemId == R.id.submitBtnReset){
            LoginPageCall();
        }
    }
    public void LoginPageCall(){
        if(TextUtils.isEmpty(editTextResetNewPin.getText().toString())){
            editTextResetNewPin.setError(getString(R.string.error_field_required));
            editTextResetNewPin.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(editTextResetRetypePIN.getText().toString())){
            editTextResetRetypePIN.setError(getString(R.string.error_field_required));
            editTextResetRetypePIN.requestFocus();
            return;
        }
        preferManager.setPin(editTextResetRetypePIN.getText().toString());
        Toast.makeText(context, R.string.Successfully, Toast.LENGTH_SHORT).show();
        //Snackbar.make(this,R.string.Successfully, Snackbar.LENGTH_LONG).show();
//        Intent intent = new Intent(this, LoginActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("CommonModel", commonModel);
//        intent.putExtras(bundle);
//        startActivity(intent);
//        finish();
        onBackPressed();
    }
}