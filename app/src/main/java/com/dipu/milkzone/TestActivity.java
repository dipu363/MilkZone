package com.dipu.milkzone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        spinner = findViewById(R.id.spinnerProperty);

        databaseReference = FirebaseDatabase.getInstance().getReference("cow_details");

    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        // Is better to use a List, because you don't know the size
                                                        // of the iterator returned by dataSnapshot.getChildren() to
                                                        // initialize the array
                                                        final List<String> cow_details_namelist = new ArrayList<String>();

                                                        for (DataSnapshot cowtitle : dataSnapshot.getChildren()) {
                                                            String cowname = cowtitle.child("name").getValue(String.class);
                                                            if (cowname != null) {
                                                                cow_details_namelist.add(cowname);
                                                            }
                                                        }

                                                        Spinner spinnerProperty = findViewById(R.id.spinnerProperty);
                                                        ArrayAdapter<String> cownameadapter = new ArrayAdapter<String>(TestActivity.this, android.R.layout.simple_spinner_item, cow_details_namelist);
                                                        cownameadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                        spinnerProperty.setAdapter(cownameadapter);
                                                        spinnerProperty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                if (parent.getItemAtPosition(position).equals("Choose Cow Name")) {

                                                                    // do nothing
                                                                } else {
                                                                    String item = parent.getItemAtPosition(position).toString();
                                                                    Toast.makeText(getApplicationContext(), "Selected name :" + item, Toast.LENGTH_LONG).show();
                                                                }


                                                            }

                                                            @Override
                                                            public void onNothingSelected(AdapterView<?> parent) {

                                                            }
                                                        });
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                }
        );
        super.onStart();
    }
}
