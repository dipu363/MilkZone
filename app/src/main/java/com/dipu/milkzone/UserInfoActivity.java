package com.dipu.milkzone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("User Info");
        setContentView(R.layout.activity_user_info);
    }
}
