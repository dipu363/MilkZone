package com.dipu.milkzone;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnorderconfirm, btnordercencel;
    TextView selecteddate;
    RadioGroup radioGroup;
    RadioButton SelectDailybtn, selectmonthlybnt;
    EditText qunty, coponid;
    String scheduleDay, ordertype = "";
    int countday;
    double qnty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);


        btnorderconfirm = findViewById(R.id.btnorderconfirmid);
        btnordercencel = findViewById(R.id.btnordercancelid);
        radioGroup = findViewById(R.id.radiogropeid);
        SelectDailybtn = findViewById(R.id.radiobtndailly);
        selectmonthlybnt = findViewById(R.id.radiobtnmonthly);
        qunty = findViewById(R.id.edt_qnty);
        selecteddate = findViewById(R.id.selectdatetaxtviewId);
        btnorderconfirm.setOnClickListener(this);
        btnordercencel.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        getsheduledate();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnorderconfirmid) {
            showInvoice();
        } else if (v.getId() == R.id.btnordercancelid) {
            ordenCancel();
        }

    }

    //get selected date from calender activity
    private void getsheduledate() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ArrayList<String> gatdatelist;
            gatdatelist = bundle.getStringArrayList("showdate");
            assert gatdatelist != null;
            selecteddate.setText(gatdatelist.toString());
            countday = bundle.getInt("date");
            if (countday > 1) {
                ordertype = "Monthly";
            } else {
                ordertype = "Daily";
            }

        }
    }

    public void showInvoice() {

        scheduleDay = selecteddate.getText().toString();
        String qty = qunty.getText().toString();
        if (TextUtils.isEmpty(scheduleDay)) {
            selecteddate.setError("please Select your order type and select date");

        } else if (TextUtils.isEmpty(qty)) {
            qunty.setError("please type quantity");
            qunty.requestFocus();
        } else {
            qnty = Double.parseDouble(qty);
            Intent intent = new Intent(OrderActivity.this, BillActivity.class);
            intent.putExtra("ordertype", ordertype);
            intent.putExtra("qty", qnty);
            intent.putExtra("days", countday);
            intent.putExtra("selecteddate", scheduleDay);
            startActivity(intent);


        }


    }

    public void ordenCancel() {
        selecteddate.setText("");
        qunty.setText("");
        coponid.setText("");
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radiobtndailly:
                if (checked)
                    dailycalendar();
                break;
            case R.id.radiobtnmonthly:
                if (checked)
                    Monthlycalendar();
                break;
        }
    }


    private void dailycalendar() {
        Intent intent = new Intent(OrderActivity.this, CalendarActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        // finish();
    }


    private void Monthlycalendar() {
        Intent intent = new Intent(OrderActivity.this, MonthlyCalendarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(OrderActivity.this, UserHomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
