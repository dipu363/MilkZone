package com.dipu.milkzone.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dipu.milkzone.List.CowListActivity;
import com.dipu.milkzone.Model.CowDetailsModel;
import com.dipu.milkzone.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CowDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference databaseReference;
    CowDetailsModel cowDetailsModel;
    private EditText cowname, cowweight, cowremarks, avgmilkproduction;
    private Button cowaddbtn, cowcancelbtn, btnshow;
    private final boolean status = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        this.setTitle("  Cow Details");


        cowname = findViewById(R.id.cow_titleid);
        cowweight = findViewById(R.id.WightEditid);
        cowremarks = findViewById(R.id.cow_remarksid);
        avgmilkproduction = findViewById(R.id.cow_avgproductionid);
        cowaddbtn = findViewById(R.id.btnCowAddid);
        cowcancelbtn = findViewById(R.id.btnCowCancelid);
        btnshow = findViewById(R.id.btnCowdetailsshowid);

        btnshow.setOnClickListener(this);
        cowaddbtn.setOnClickListener(this);
        cowcancelbtn.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("cow_details");
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

        if (v.getId() == R.id.btnCowAddid) {


            addCow();
            Toast.makeText(getApplicationContext(), "Data save successfully", Toast.LENGTH_LONG).show();
        } else if (v.getId() == R.id.btnCowCancelid) {

            clear();
        } else if (v.getId() == R.id.btnCowdetailsshowid) {

            Intent intent = new Intent(CowDetailsActivity.this, CowListActivity.class);
            startActivity(intent);
        }

    }

    public void addCow() {

        Log.d("abc", "Inhere");
        String name = cowname.getText().toString();
        String weight = cowweight.getText().toString();
        String remarks = cowremarks.getText().toString();
        double avg_production = Double.parseDouble(avgmilkproduction.getText().toString());
        String key = databaseReference.push().getKey();

        Log.d("abc", "Inhere");

      /*  if(name.isEmpty()) {
            cowname.setError("Enter Cow Title");
            cowname.requestFocus();
            return;
        }
        if(weight.isEmpty()) {
            cowweight.setError("Enter Cow Weight");
            cowweight.requestFocus();
            return;
        }

        if(aavgproduction<1) {
            avgmilkproduction.setError("Enter Average milk production per day");
            avgmilkproduction.requestFocus();
            return;
        }*/


        Log.d("abc", "in here");

        cowDetailsModel = new CowDetailsModel(name, weight, remarks, status, avg_production);
        databaseReference.child(key).setValue(cowDetailsModel);
        Toast.makeText(getApplicationContext(), "Data Save successful", Toast.LENGTH_LONG).show();
        clear();


    }


    public void clear() {

        cowname.setText("");
        cowweight.setText("");
        cowremarks.setText("");
        avgmilkproduction.setText("");

    }
  /*  public void Chack_valid(){

        Log.d("abc","Inhere");
        String name = cowname.getText().toString();
        Log.d("abc","Inhere");
        String weight = cowweight.getText().toString();
        String remarks = cowremarks.getText().toString();
        double aavgproduction = Double.parseDouble(avgmilkproduction.getText().toString()) ;


        Log.d("abc",name);

        if(name.isEmpty()) {
            cowname.setError("Enter Cow Title");
            cowname.requestFocus();
            return;
        }
        if(weight.isEmpty()) {
            cowweight.setError("Enter Cow Weight");
            cowweight.requestFocus();
            return;
        }

        if(aavgproduction<1) {
            avgmilkproduction.setError("Enter Average milk production per day");
            avgmilkproduction.requestFocus();
            return;
        }





    }*/
}
