package com.example.ilip.activities;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilip.activities.loginRegistrtionActivitys.LoginActivity;
import com.example.ilip.activities.loginRegistrtionActivitys.RegistrationActivity;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.common.PreferManager;
import com.example.ilip.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashScreenActivity extends AppCompatActivity {
    Context context;
    CommonModel commonModel;
    PreferManager preferManager;
    ImageView logo;
    TextView AppName;
    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context = this;
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(task -> {
//                    if (!task.isSuccessful()) {
//                        Log.e(TAG, "Fetching FCM registration token failed", task.getException());
//                        return;
//                    }
//
//                    // Get new FCM registration token
//                    String token = task.getResult();
//                    Log.e(TAG, "Token Id ==" + token);
//                    preferManager.setTokenId(token);
//                });
//        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        try {
            String installer = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            Log.e("installed from", "which=" + installer);
//            String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//            Log.e("Device Id", "which=" + androidId);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        commonModel = new CommonModel();
        preferManager = new PreferManager(this);
       // commonModel.setLoginType(preferManager.getUserLogin_Type());
        Log.e("Pref","data is =="+preferManager.getUserType()+preferManager.getUsername()+preferManager.getMobileNo());
        logo = findViewById(R.id.LogoImageView2);
        //AppName = findViewById(R.id.welcome);
        //preferManager.setISNOCONNECTION(true);
//        if (!preferManager.isISCompleteLogin()) {
//            preferManager.setFirstTimeLaunch(false);
//            if (checkConnection()) {
//                Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
//                startActivity(intent);
//            } else {
//                launchNoConnectionScreen();
//            }
//        }else {
//            if (checkConnection()) {
//                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
//                startActivity(intent);
//            } else {
//                launchNoConnectionScreen();
//            }
//        }

//        logo.setAnimation(topAnimation);
//        AppName.setAnimation(bottomAnimation);

        new Handler().postDelayed(() -> {
            if (!preferManager.isFirstTimeLogin()) {
            preferManager.setFirstTimeLaunch(false);
            if (checkConnection()) {
                launchLoginScreen();
            } else {
                launchNoConnectionScreen();
            }
        }else {
            if (checkConnection()) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CommonModel", commonModel);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            } else {
                launchNoConnectionScreen();
            }
        }
//            if (checkConnection()) {
//                launchLoginScreen();
//            } else {
//                launchNoConnectionScreen();
//            }
        }, 5000);
//        Uri uri = getIntent().getData();
//        if (uri != null) {
//            List<String> param = uri.getPathSegments();
//            String id = param.get(param.size() - 1);
//           // Toasty.info(getApplicationContext(), "data OF Deep Link is =" + id, Toast.LENGTH_LONG).show();
//        }
    }
    private void launchNoConnectionScreen() {
        Intent intent = new Intent(this, NoConnectionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CommonModel", commonModel);
        intent.putExtras(bundle);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private Boolean checkConnection() {
        boolean connected;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        return connected;
    }

    private void launchLoginScreen() {
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CommonModel", commonModel);
        i.putExtras(bundle);
        startActivity(i);
        finish();
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
        finish();
        System.exit(0);
    }
}