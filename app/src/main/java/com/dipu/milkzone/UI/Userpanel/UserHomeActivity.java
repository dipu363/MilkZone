package com.dipu.milkzone.UI.Userpanel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dipu.milkzone.Auth.LogInActivity;
import com.dipu.milkzone.Fragment.UserHomeFragment;
import com.dipu.milkzone.Fragment.UserProfileFragment;
import com.dipu.milkzone.Model.Current_UserModel;
import com.dipu.milkzone.R;
import com.dipu.milkzone.UI.OrderActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class UserHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    FirebaseAuth mAuth;
    FirebaseUser currentuser;
    private CardView usercard, recentUserCard, OrderuserCard, logoutuserCard;
    private TextView useremail;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_home);

        drawerLayout = findViewById(R.id.drawar_layoutUser_id);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        this.setTitle("  Home");

        NavigationView navigationView = findViewById(R.id.usernavigationviewId);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new UserProfileFragment()).commit();


        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        //  Log.d("dip","Inhere");
                        if (task.isSuccessful()) {
                            token = task.getResult().getToken();
                            saveToken(token);
                        } else {


                        }
                    }
                });


        userprfileupdate();


    }

    @Override
    protected void onStart() {
        super.onStart();


/*        if (mAuth.getCurrentUser() == null){

// here handel user already login


            Intent intent = new Intent(UserHomeActivity.this,LoginAs.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }*/


    }

    private void saveToken(String token) {
        final String email = mAuth.getCurrentUser().getEmail();
        final String name = mAuth.getCurrentUser().getDisplayName();
        Current_UserModel current_userModel = new Current_UserModel(email, token);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("current_user");
        databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(current_userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                }
            }
        });
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.user_nav_home) {
            getSupportActionBar().setTitle("Home");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new UserHomeFragment()).commit();

        } else if (menuItem.getItemId() == R.id.user_nav_order) {
            Intent intent = new Intent(this, OrderActivity.class);
            startActivity(intent);

        } else if (menuItem.getItemId() == R.id.user_nav_profile) {
            getSupportActionBar().setTitle("Profile");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new UserProfileFragment()).commit();

        } else if (menuItem.getItemId() == R.id.user_nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(), "Successfully Sign out", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }

    public void userprfileupdate() {
        NavigationView navigationView = findViewById(R.id.usernavigationviewId);
        View headerview = navigationView.getHeaderView(0);
        TextView username = headerview.findViewById(R.id.nav_headernametext_id1);
        TextView useremail = headerview.findViewById(R.id.nav_headeremailtext_id2);
        ImageView userimage = headerview.findViewById(R.id.nav_user_photo);
        username.setText(currentuser.getDisplayName());
        useremail.setText(currentuser.getEmail());
        Glide.with(this).load(currentuser.getPhotoUrl()).into(userimage);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
