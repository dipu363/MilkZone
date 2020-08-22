package com.dipu.milkzone;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dipu.milkzone.Fragment.AdminHomeFragment;
import com.dipu.milkzone.Fragment.AdminProfileFragment;
import com.dipu.milkzone.Fragment.UsersFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AdminPanelActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    CardView usercard, currentUserCard, OrderCard, ProductionCard;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    DatabaseReference db_milkrate;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        drawerLayout = findViewById(R.id.drawar_layoutAdmin_id);
        ActionBar actionBar = getSupportActionBar();

        NavigationView navigationView = findViewById(R.id.admin_navigationviewId);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Home");
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AdminHomeFragment()).commit();
        actionBar.setIcon(R.drawable.logomilkzone);

        db_milkrate = FirebaseDatabase.getInstance().getReference("milk_rate");

        usercard = findViewById(R.id.cardviewadmin_Optino1);
        currentUserCard = findViewById(R.id.cardviewadmin_Optino2);
        OrderCard = findViewById(R.id.cardviewadmin_Optino3);
        ProductionCard = findViewById(R.id.cardviewadmin_Optino4);
        usercard.setOnClickListener(this);
        currentUserCard.setOnClickListener(this);
        OrderCard.setOnClickListener(this);
        ProductionCard.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.cardviewadmin_Optino1:
                Intent intent1 = new Intent(AdminPanelActivity.this, Milk_Rate_ListActivity.class);
                startActivity(intent1);
                break;
            case R.id.cardviewadmin_Optino2:
                Intent intent2 = new Intent(AdminPanelActivity.this, Current_user_list_Activity.class);
                startActivity(intent2);
                break;
            case R.id.cardviewadmin_Optino3:
                Intent intent3 = new Intent(AdminPanelActivity.this, OrderListActivity.class);
                startActivity(intent3);
                break;
            case R.id.cardviewadmin_Optino4:
                Intent intent4 = new Intent(AdminPanelActivity.this, MessengerActivity.class);
                startActivity(intent4);
                break;
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.nav_home) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Home");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new AdminHomeFragment()).commit();
        } else if (menuItem.getItemId() == R.id.nav_profile) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new AdminProfileFragment()).commit();

        } else if (menuItem.getItemId() == R.id.manucowinformation) {
            Intent intent = new Intent(this, CowDetailsActivity.class);
            startActivity(intent);

        } else if (menuItem.getItemId() == R.id.Dailyproductionid) {
            Intent intent = new Intent(this, DailyProdutionActivity.class);
            startActivity(intent);

        } else if (menuItem.getItemId() == R.id.manuadd_rateid) {
            db_milkrate.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    int count = (int) dataSnapshot.getChildrenCount();
                    if (count >= 1) {
                        Intent intent = new Intent(AdminPanelActivity.this, Milk_Rate_ListActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(AdminPanelActivity.this, MilkRateActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else if (menuItem.getItemId() == R.id.addDiscountid) {
            Intent intent = new Intent(this, DiscountActivity.class);
            startActivity(intent);

        } else if (menuItem.getItemId() == R.id.orderlistitemid) {
            Intent intent = new Intent(this, OrderListActivity.class);
            startActivity(intent);

        } else if (menuItem.getItemId() == R.id.useritemid) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Users List");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new UsersFragment()).commit();

        } else if (menuItem.getItemId() == R.id.currentuseritemid) {
            Intent intent = new Intent(this, Current_user_list_Activity.class);
            startActivity(intent);

        } else if (menuItem.getItemId() == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(AdminPanelActivity.this, LoginAs.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Successfully Sign out", Toast.LENGTH_LONG).show();
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }


}