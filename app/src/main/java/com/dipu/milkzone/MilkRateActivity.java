package com.dipu.milkzone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MilkRateActivity extends AppCompatActivity implements View.OnClickListener {
    EditText rateid;
    Button savebutton, cancelbtn, showbtn;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_rate);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        this.setTitle("  Milk Rate");

        rateid = findViewById(R.id.rateedittextid);
        savebutton = findViewById(R.id.btnrateid);
        cancelbtn = findViewById(R.id.btnRateCancelid);
        showbtn = findViewById(R.id.btnRateshowid);

        savebutton.setOnClickListener(this);
        cancelbtn.setOnClickListener(this);
        showbtn.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("milk_rate");


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnrateid:
                saveRate();
                break;
            case R.id.btnRateCancelid:
                clear();
                break;
            case R.id.btnRateshowid:
                Intent intent = new Intent(MilkRateActivity.this, Milk_Rate_ListActivity.class);
                startActivity(intent);
                break;


        }

    }

    public void saveRate() {
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        String rate_date = df.format(date);
        String rate = rateid.getText().toString().trim();
        String id = databaseReference.push().getKey();
        if (TextUtils.isEmpty(rate)) {

            rateid.setError("Please type milk rate");
            rateid.requestFocus();
        } else {
            RateModel rateModel = new RateModel(id, rate, rate_date);
            assert id != null;
            databaseReference.child(id).setValue(rateModel);
            clear();
            Toast.makeText(getApplicationContext(), "Data Save successful", Toast.LENGTH_LONG).show();
        }
    }

    public void clear() {

        rateid.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
