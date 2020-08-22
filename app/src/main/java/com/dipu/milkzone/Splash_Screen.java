package com.dipu.milkzone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class Splash_Screen extends AppCompatActivity {


    private final int SPLASH_TIME_OUT = 3000;
    public Context context;
    Handler mHandler;
    Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("sss", " in here: ");
                    Intent intent = new Intent(Splash_Screen.this, LoginAs.class);
                    startActivity(intent);
                    Log.d("sss", " in here: ");
                /*   AppSharePreference.getAdminID(context);


                    Log.d("sss", "adminid: " + AppSharePreference.getAdminID(context));
                    Log.d("sss", "userid: " + AppSharePreference.getUserID(context));

                    if (AppSharePreference.getAdminID(context).equals("") && AppSharePreference.getUserID(context).equals("")) {
                        startActivity(new Intent(Splash_Screen.this, LoginAs.class));
                    } *//*else if (AppSharePreference.getDoctorID(context).equals("") && !AppSharePreference.getPatientID(context).equals("")) {
                        startActivity(new Intent(Splash_Screen.this, UserHomeActivity.class));
                    } else if (!AppSharePreference.getDoctorID(context).equals("") && AppSharePreference.getPatientID(context).equals("")) {
                        startActivity(new Intent(Splash_Screen.this, MainActivity.class));

                    }*//*
                     */

                } catch (Exception e) {
                    Log.d("sss", "error: ");

                }

                finish();
            }
        };
        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, SPLASH_TIME_OUT);


    }

    @Override
    public void finish() {
        mHandler.removeCallbacks(mRunnable);
        super.finish();
    }

}
