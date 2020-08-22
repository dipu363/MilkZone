package com.dipu.milkzone;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    EditText useremail, userpasswordid;
    Button login;
    ImageView loginPhoto;
    private Intent HomeActivity;
    private ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        HomeActivity = new Intent(this, UserHomeActivity.class);

        login = findViewById(R.id.btn_login);
        Button signup = findViewById(R.id.btn_signup);
        loginProgress = findViewById(R.id.login_progress);
        loginPhoto = findViewById(R.id.login_photo);
        useremail = findViewById(R.id.emailedittextid);
        userpasswordid = findViewById(R.id.edtPassword);


        signup.setOnClickListener(this);
        login.setOnClickListener(this);
        loginPhoto.setOnClickListener(this);
        loginProgress.setVisibility(View.INVISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        //  Log.d("sssssss","Inhere");
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (user != null) {
            //user is already connected  so we need to redirect him to home page
            updateUI();

        }


    }

    private void updateUI() {
        startActivity(HomeActivity);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            String email = useremail.getText().toString();
            String password = userpasswordid.getText().toString();
            if (TextUtils.isEmpty(email)) {
                useremail.setError("Pless Type valid Email Address");
                useremail.requestFocus();

            } else if (TextUtils.isEmpty(password)) {
                userpasswordid.setError("Pless Type Password at least 6 character");
                userpasswordid.requestFocus();


            } else {
                loginProgress.setVisibility(View.VISIBLE);
                login.setVisibility(View.INVISIBLE);
                // firebaseAuth login
                firebaseAuth.signInWithEmailAndPassword(useremail.getText().toString(), userpasswordid.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginProgress.setVisibility(View.INVISIBLE);
                            login.setVisibility(View.VISIBLE);
                            Intent intent = new Intent(LogInActivity.this, UserHomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            clear();

                        } else {
                            Toast.makeText(LogInActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                            login.setVisibility(View.VISIBLE);
                            loginProgress.setVisibility(View.INVISIBLE);
                        }
                    }
                });


            }

        } else if (v.getId() == R.id.btn_signup) {
            Intent intent = new Intent(LogInActivity.this, SignUp.class);
            startActivity(intent);
        } else if (v.getId() == R.id.login_photo) {
            Intent intent = new Intent(LogInActivity.this, SignUp.class);
            startActivity(intent);
            finish();


        }

    }

    private void clear() {
        useremail.setText("");
        userpasswordid.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
