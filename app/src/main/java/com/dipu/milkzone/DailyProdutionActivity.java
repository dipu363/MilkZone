package com.dipu.milkzone;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DailyProdutionActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseReference databaseReference, databasecreat;
    private EditText pro_date, morningqty, eveningqty;
    private Spinner spinnerProperty;
    private Button btnAdd, btncancel, btnshowdailyproduction;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_prodution);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        this.setTitle("  Daily Production");
        pro_date = findViewById(R.id.pro_dateid);
        spinnerProperty = findViewById(R.id.spinnerProperty);


        morningqty = findViewById(R.id.pro_morningQtyid);
        eveningqty = findViewById(R.id.pro_EveningQtyid);
        btnAdd = findViewById(R.id.btnpro_addid);
        btncancel = findViewById(R.id.btnpro_Cancelid);
        btnshowdailyproduction = findViewById(R.id.btnshowdailyproductionid);
        pro_date.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btncancel.setOnClickListener(this);
        btnshowdailyproduction.setOnClickListener(this);


        databaseReference = FirebaseDatabase.getInstance().getReference("cow_details");
        databasecreat = FirebaseDatabase.getInstance().getReference("daily_production");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.pro_dateid) {


            DatePicker datePicker = new DatePicker(this);
            int currentday = datePicker.getDayOfMonth();
            int currentmonth = (datePicker.getMonth()) + 1;
            int currentyear = datePicker.getYear();

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            pro_date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                        }
                    }, currentyear, currentmonth, currentday);

            datePickerDialog.show();
        } else if (v.getId() == R.id.btnpro_addid) {
            //  Intent intent = new Intent(DailyProdutionActivity.this,TestActivity.class);
            // startActivity(intent);
            Toast.makeText(getApplicationContext(), "Data is Save Successful", Toast.LENGTH_LONG).show();

            savedata();
            clear();
        } else if (v.getId() == R.id.btnpro_Cancelid) {

            clear();

        } else if (v.getId() == R.id.btnshowdailyproductionid) {

            Intent intent = new Intent(DailyProdutionActivity.this, ProductionListActivity.class);
            startActivity(intent);
        }
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

                                                        //   Spinner spinnerProperty =findViewById(R.id.spinnerProperty);
                                                        ArrayAdapter<String> cownameadapter = new ArrayAdapter<String>(DailyProdutionActivity.this, android.R.layout.simple_spinner_item, cow_details_namelist);
                                                        cownameadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                        spinnerProperty.setAdapter(cownameadapter);

                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                }
        );
        super.onStart();
    }

    public void savedata() {
        String prodate = pro_date.getText().toString();
        String cowtitle = spinnerProperty.getSelectedItem().toString();
        int mQty = Integer.parseInt(morningqty.getText().toString());
        int eQty = Integer.parseInt(eveningqty.getText().toString());

        String key = databasecreat.push().getKey();

        Log.d("abc", "Inhere");
        DailyProductionModel dailyProductionModel = new DailyProductionModel(prodate, cowtitle, mQty, eQty);
        databasecreat.child(key).setValue(dailyProductionModel);
        Toast.makeText(getApplicationContext(), "Data Save successful", Toast.LENGTH_LONG).show();
        clear();


    }

    public void clear() {

        pro_date.setText("");
        spinnerProperty.setTag("kfjsk");
        morningqty.setText("");
        eveningqty.setText("");
    }

}
