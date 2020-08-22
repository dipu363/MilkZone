package com.dipu.milkzone;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiscountActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText discountupto, discountparsenteg;
    private Button btndisuptocalander, btnadd, btncancel;
    private DatePickerDialog datePickerDialog;
    private DatabaseReference databaseReference;
    private boolean coponstatus = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        this.setTitle("  Discount Rate");

        discountupto = findViewById(R.id.dis_valid_id);
        discountparsenteg = findViewById(R.id.dis_parcenteg_id);
        btndisuptocalander = findViewById(R.id.btn_dis_valid_date_id);
        btnadd = findViewById(R.id.btnDisaddid);
        btncancel = findViewById(R.id.btnDisCancelid);
        btndisuptocalander.setOnClickListener(this);
        btnadd.setOnClickListener(this);
        btncancel.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("discount_details");
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

        if (v.getId() == R.id.btn_dis_valid_date_id) {
            //  Toast.makeText(getApplicationContext(),"Data button is click",Toast.LENGTH_LONG).show();

            DatePicker datePicker = new DatePicker(this);
            int currentday = datePicker.getDayOfMonth();
            int currentmonth = (datePicker.getMonth()) + 1;
            int currentyear = datePicker.getYear();

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            discountupto.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                        }
                    }, currentyear, currentmonth, currentday);

            datePickerDialog.show();

        } else if (v.getId() == R.id.btnDisaddid) {


            //  Toast.makeText(getApplicationContext(),df.format(date),Toast.LENGTH_LONG).show();

            savedata();
            Toast.makeText(getApplicationContext(), "Data Save SuccessFull", Toast.LENGTH_LONG).show();
            clear();

        } else if (v.getId() == R.id.btnDisCancelid) {

            // Toast.makeText(getApplicationContext(),"Data button is click",Toast.LENGTH_LONG).show();
            clear();
        }


    }


    public void savedata() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        String startdate = df.format(date);
        String validupto = discountupto.getText().toString();
        double discontparcent = Double.parseDouble(discountparsenteg.getText().toString());
        boolean status = coponstatus;
        String key = databaseReference.push().getKey();
        DiscountCoponModel discountCoponModel = new DiscountCoponModel(startdate, validupto, discontparcent, status);

        databaseReference.child(key).setValue(discountCoponModel);
        Toast.makeText(getApplicationContext(), "Data Save successful", Toast.LENGTH_LONG).show();
        clear();


    }

    public void clear() {

        discountupto.setText("");
        discountparsenteg.setText("");
    }

    String currentdate() {
        DatePicker datePicker = new DatePicker(this);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(datePicker.getDayOfMonth()).append("/");
        stringBuilder.append(datePicker.getMonth() + 1).append("/");
        stringBuilder.append(datePicker.getYear());
        return stringBuilder.toString();
    }
}
