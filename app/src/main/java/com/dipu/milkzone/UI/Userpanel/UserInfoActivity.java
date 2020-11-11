package com.dipu.milkzone.UI.Userpanel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dipu.milkzone.R;

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("User Info");
        setContentView(R.layout.activity_user_info);
    }
}
