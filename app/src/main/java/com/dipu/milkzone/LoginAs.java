package com.dipu.milkzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginAs extends AppCompatActivity implements View.OnClickListener {
    Button adminbtnid, userbtnid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);

        adminbtnid = findViewById(R.id.adminbtn);
        userbtnid = findViewById(R.id.userbtn);
        adminbtnid.setOnClickListener(this);
        userbtnid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.adminbtn) {
            Intent intent = new Intent(LoginAs.this, AdminLoginActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.userbtn) {
            Intent intent = new Intent(LoginAs.this, LogInActivity.class);
            startActivity(intent);
        }
    }


}
