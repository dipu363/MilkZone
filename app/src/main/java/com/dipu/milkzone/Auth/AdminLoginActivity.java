package com.dipu.milkzone.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dipu.milkzone.R;
import com.dipu.milkzone.UI.Adminpanel.AdminPanelActivity;


public class AdminLoginActivity extends AppCompatActivity {
    private EditText adminemail, adminpasswordid;
    private Button adminlogin;
    private ProgressBar adminprograse;
    private final String email = "admin@gmail.com";
    private final String pass = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        adminemail = findViewById(R.id.admin_emailedittextid);
        adminpasswordid = findViewById(R.id.edtadmin_Password);
        adminprograse = findViewById(R.id.admin_login_progress);

        adminlogin = findViewById(R.id.btn_admin_login);
        adminprograse.setVisibility(View.INVISIBLE);


        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_admin_login) {

                    String emailid = adminemail.getText().toString().trim();
                    String password = adminpasswordid.getText().toString().trim();
                    if (TextUtils.isEmpty(emailid)) {
                        adminemail.setError("Please type valid email address");
                        adminemail.requestFocus();

                    } else if (TextUtils.isEmpty(password)) {

                        adminpasswordid.setError("Please type password");
                        adminpasswordid.requestFocus();
                    } else if (email.equals(emailid) && pass.equals(password)) {
                        adminprograse.setVisibility(View.VISIBLE);
                        adminlogin.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(AdminLoginActivity.this, AdminPanelActivity.class);
                        startActivity(intent);
                        adminemail.setText("");
                        adminpasswordid.setText("");
                        adminprograse.setVisibility(View.INVISIBLE);
                        adminlogin.setVisibility(View.VISIBLE);
                    } else {

                        Toast.makeText(getApplicationContext(), "LogIN Failed ! wrong email or password ", Toast.LENGTH_LONG).show();
                        adminprograse.setVisibility(View.INVISIBLE);
                        adminlogin.setVisibility(View.VISIBLE);
                    }


                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
