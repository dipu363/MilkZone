package com.dipu.milkzone.List;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.dipu.milkzone.Adapter.Cow_List_Adapter;
import com.dipu.milkzone.Model.CowDetailsModel;
import com.dipu.milkzone.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CowListActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    private ListView cowlistview;
    private List<CowDetailsModel> cow_list;
    private Cow_List_Adapter cow_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        this.setTitle("  Cow List");
        setContentView(R.layout.activity_cow_list);
        cowlistview = findViewById(R.id.cowlistviewid);


        databaseReference = FirebaseDatabase.getInstance().getReference("cow_details");
        cow_list = new ArrayList<>();
        cow_list_adapter = new Cow_List_Adapter(CowListActivity.this, cow_list);
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

        databaseReference.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        cow_list.clear();
                                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                                                            CowDetailsModel cowDetailsModel = dataSnapshot1.getValue(CowDetailsModel.class);
                                                            cow_list.add(cowDetailsModel);
                                                        }
                                                        cowlistview.setAdapter(cow_list_adapter);
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
