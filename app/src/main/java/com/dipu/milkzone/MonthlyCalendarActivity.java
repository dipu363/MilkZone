package com.dipu.milkzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MonthlyCalendarActivity extends AppCompatActivity {
    private CalendarPickerView monthly_calendarView;
    private TextView monthlytextview;
    private Button btnmonthlycalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        this.setTitle("Select Order Date");
        setContentView(R.layout.activity_monthly_calendar);
        monthly_calendarView = findViewById(R.id.monthlycalendar_view);
        //  monthlytextview = findViewById(R.id.monthlycalendertextid);
        btnmonthlycalender = findViewById(R.id.btn_monthlycalendar_dates);


//get currentdate
        Calendar nextmonth = Calendar.getInstance();
        // Calendar nextyear = Calendar.getInstance();
        nextmonth.add(Calendar.MONTH, 1);
        //nextyear.add(Calendar.YEAR,1);

        Date today = new Date();

//add one year to calendar from todays date
        monthly_calendarView.init(today, nextmonth.getTime()).inMode(CalendarPickerView.SelectionMode.MULTIPLE);


//action while clicking on a date
        monthly_calendarView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//if selected date show a taxt view  follow this code below comment

           /*     String selecteddate;
                ArrayList<String> monthlydateary = new ArrayList<String>();

                for (int i = 0; i< monthly_calendarView.getSelectedDates().size();i++){

                    //her datearray.add(selecteddate);e you can fetch all dates


                    selecteddate = monthly_calendarView.getSelectedDates().get(i).toString();
                    monthlydateary.add(selecteddate);

                    monthlytextview .setText( monthlydateary.toString());
                }*/

                String formetdate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                Toast.makeText(getApplicationContext(), "Selected Date is : " + formetdate, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onDateUnselected(Date date) {
                String formetdate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                Toast.makeText(getApplicationContext(), "UnSelected Date is : " + formetdate, Toast.LENGTH_SHORT).show();
            }
        });


        btnmonthlycalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendselecteddateOrderActivity();
            }
        });
    }

    public void sendselecteddateOrderActivity() {
        Date selecteddate;
        Date datesize;
        ArrayList<String> monthlydateary = new ArrayList<String>();


        for (int i = 0; i < monthly_calendarView.getSelectedDates().size(); i++) {

            //her datearray.add(selecteddate);e you can fetch all dates
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


            selecteddate = monthly_calendarView.getSelectedDates().get(i);

            monthlydateary.add(formatter.format(selecteddate));
            int dates = monthlydateary.size();


            Intent intent = new Intent(MonthlyCalendarActivity.this, OrderActivity.class);
            intent.putExtra("date", dates);
            intent.putExtra("showdate", monthlydateary);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
