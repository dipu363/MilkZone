package com.dipu.milkzone.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.dipu.milkzone.R;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CalendarActivity extends AppCompatActivity {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    Button btnCalender;
    private CalendarPickerView calendarPickerView;

    public static long getDaysBetweenDates(String start, String end) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        Date startDate, endDate;
        long numberOfDays = 0;
        try {
            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
            numberOfDays = getUnitBetweenDates(startDate, endDate, TimeUnit.DAYS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return numberOfDays;
    }

    private static long getUnitBetweenDates(Date startDate, Date endDate, TimeUnit unit) {
        long timeDiff = endDate.getTime() - startDate.getTime();
        return unit.convert(timeDiff, TimeUnit.MILLISECONDS);
    }

    //or use "M/d/yyyy"

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calendar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        this.setTitle("   Select Order Date");


        calendarPickerView = findViewById(R.id.calendar_view);
        btnCalender = findViewById(R.id.btn_calendar_dates);


//get currentdate
        //Calendar nextmonth = Calendar.getInstance();
        Calendar nextyear = Calendar.getInstance();
        // nextmonth.add(Calendar.MONTH, 1);
        nextyear.add(Calendar.YEAR, 1);
        Date today = new Date();

//add one year to calendar from todays date
        calendarPickerView.init(today, nextyear.getTime()).inMode(CalendarPickerView.SelectionMode.SINGLE);


//action while clicking on a date
        calendarPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
                String strDate = formatter.format(date);
                // String formetdate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                Toast.makeText(getApplicationContext(), "Selected Date is : " + strDate, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDateUnselected(Date date) {
                SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
                String strDate = formatter.format(date);
                //String formetdate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                Toast.makeText(getApplicationContext(), "UnSelected Date is : " + strDate, Toast.LENGTH_SHORT).show();
            }
        });


        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendselecteddateOrderActivity();
            }
        });

    }

    public void sendselecteddateOrderActivity() {
        String selecteddate;
        ArrayList<String> dailydateary = new ArrayList<String>();

        for (int i = 0; i < calendarPickerView.getSelectedDates().size(); i++) {

            //her datearray.add(selecteddate);e you can fetch all dates
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


            selecteddate = formatter.format(calendarPickerView.getSelectedDates().get(i));
            dailydateary.add(selecteddate);
            int dates = dailydateary.size();

            Intent intent = new Intent(CalendarActivity.this, OrderActivity.class);
            intent.putExtra("showdate", dailydateary);
            intent.putExtra("date", dates);
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
