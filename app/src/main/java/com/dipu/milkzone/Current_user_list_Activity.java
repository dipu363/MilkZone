package com.dipu.milkzone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Current_user_list_Activity extends AppCompatActivity {

    DatabaseReference db_current_user;
    private ListView currentlistview;
    private List<Current_User> current_userlist;
    private Current_user_Adapter current_user_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_user_list_);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        this.setTitle("  Cow List");


        currentlistview = findViewById(R.id.current_userlistview_id);


        db_current_user = FirebaseDatabase.getInstance().getReference("current_user");
        current_userlist = new ArrayList<>();
        current_user_adapter = new Current_user_Adapter(Current_user_list_Activity.this, current_userlist);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {

        db_current_user.addValueEventListener(new ValueEventListener() {
                                                  @Override
                                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                      current_userlist.clear();
                                                      for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                                                          Current_User current_user = dataSnapshot1.getValue(Current_User.class);
                                                          current_userlist.add(current_user);
                                                      }
                                                      currentlistview.setAdapter(current_user_adapter);
                                                  }

                                                  @Override
                                                  public void onCancelled(@NonNull DatabaseError databaseError) {
                                                      databaseError.getMessage();

                                                  }
                                              }
        );
        super.onStart();
    }
}
