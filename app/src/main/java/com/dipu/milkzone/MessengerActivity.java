package com.dipu.milkzone;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

public class MessengerActivity extends AppCompatActivity {

    ImageView profileimage;
    TextView username;
    FirebaseUser fUser;
    DatabaseReference reference;
    ImageButton btnVideoCall;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG).show();

        }
/*
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.messagetoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        btnVideoCall = findViewById(R.id.btnvideocall);
        btnVideoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MessengerActivity.this, "this is call", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MessengerActivity.this, VideoCallActivity.class);
                startActivity(intent);
            }
        });

        profileimage = findViewById(R.id.messageUserPhoto);
        username = findViewById(R.id.messageusertext);
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        username.setText(fUser.getDisplayName());
        Glide.with(MessengerActivity.this).load(fUser.getPhotoUrl()).into(profileimage);


    }


}
