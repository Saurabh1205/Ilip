package com.example.ilip.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.example.ilip.activities.loginRegistrtionActivitys.LoginActivity;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.common.PreferManager;
import com.example.ilip.R;

public class NoConnectionActivity extends AppCompatActivity {
    TextView refresh;
    PreferManager prefManager;
    CommonModel commonModel;

    ProgressDialog spotsDialog;
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
        setContentView(R.layout.activity_no_connection);
        spotsDialog= new ProgressDialog(this, R.style.CustomProgressDialog);
        spotsDialog.setMessage("Loading data please wait...");
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        commonModel=(CommonModel)  extras.get("CommonModel");
        refresh= findViewById(R.id.refresh);
        final Animation animation = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setRepeatCount(-1);
        animation.setDuration(2000);
//        TextView textView = (TextView) findViewById(R.id.mywidget);
//        textView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.marquee) );


        commonModel=new CommonModel();
        prefManager= new PreferManager(this);
        refresh.setOnClickListener(v -> {
            spotsDialog.show();
            new Thread(() -> {
                try {
                    //  spotsDialog.dismiss();
                    while (true) {
                        Log.v("thread", "thread");
                        if (checkConnection()) {

                            prefManager.setISNOCONNECTION(false);
                            if (prefManager.isFirstTimeLaunch()) {
                                Log.v("", "this-----");
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                Bundle bundle = new Bundle();
                                //bundle.putSerializable("CommonModel", commonModel);
                                i.putExtras(bundle);

                                startActivity(i);
                                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                Log.v("threadClosed", "threadClosed");
                                spotsDialog.dismiss();
                                break;
                            }
                            if (!prefManager.isISCompleteLogin()) {

                                Intent i = new Intent(getApplicationContext(), SplashScreenActivity.class);
                                Bundle bundle = new Bundle();
                                //bundle.putSerializable("CommonModel", commonModel);
                                i.putExtras(bundle);
                                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                                spotsDialog.dismiss();
                                break;
                            }
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
        });

    }

    private Boolean checkConnection()
    {
        boolean connected=false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        return connected;

    }
    @Override
    public void onBackPressed() {
        Intent i= new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(i);
        finish();
        System.exit(0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( spotsDialog!=null && spotsDialog.isShowing() ){
            spotsDialog.cancel();
        }
    }

}