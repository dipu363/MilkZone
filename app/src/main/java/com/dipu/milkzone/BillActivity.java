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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillActivity extends AppCompatActivity implements View.OnClickListener {
    TextView invoicedate, invoicediscount, dailyqtyid, ordertype, totalday, totalqty, rateperlitter, totalprice, disprice, afterdisprice;
    EditText confirmname, comfirmaddress, confirmmobile;
    Button btnconfirm, btnconfirmcencel;
    DatabaseReference dbOrdeconfirm, db_todayrate;
    FirebaseAuth mAuth;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);


        confirmname = findViewById(R.id.invoicenameid);
        comfirmaddress = findViewById(R.id.invoiceaddressid);
        confirmmobile = findViewById(R.id.invoicemobileid);
        btnconfirm = findViewById(R.id.btnconfirmorder);
        btnconfirmcencel = findViewById(R.id.btncancelconfirmorderid);
        invoicediscount = findViewById(R.id.invoicdiscountid);
        invoicedate = findViewById(R.id.billorderdateid);
        ordertype = findViewById(R.id.billordertypeid);
        dailyqtyid = findViewById(R.id.dailyqutyid);
        totalday = findViewById(R.id.totaldaysid);
        totalqty = findViewById(R.id.totalqtyid);
        rateperlitter = findViewById(R.id.invoicerateid);
        totalprice = findViewById(R.id.totalpriceid);
        disprice = findViewById(R.id.discountpricid);
        afterdisprice = findViewById(R.id.afterdiscountpriceid);

        dbOrdeconfirm = FirebaseDatabase.getInstance().getReference("customer_order");
        //set milk rate
        db_todayrate = FirebaseDatabase.getInstance().getReference("milk_rate");
        mAuth = FirebaseAuth.getInstance();
        bundle = getIntent().getExtras();
        btnconfirm.setOnClickListener(this);
        btnconfirmcencel.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        showinvoice();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnconfirmorder:
                // Toast.makeText(getApplicationContext(),"Order Confirmation Successful",Toast.LENGTH_LONG).show();
                chackvalidity();


                break;
            case R.id.btncancelconfirmorderid:
                Intent intent2 = new Intent(BillActivity.this, OrderActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                finish();
                break;
        }

    }

    public void showinvoice() {
        assert bundle != null;
        int days = bundle.getInt("days");
        double qty = bundle.getDouble("qty");
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") DateFormat formeter = new SimpleDateFormat("dd/MM/yyyy");
        Double milkrate = 50.0;
        Double discountrate = 2.0;
        Double netqty = days * qty;
        double subtotalprice = netqty * milkrate;
        double totaldiscount = (subtotalprice * 2) / 100;
        double afdistotalprice = subtotalprice - totaldiscount;

//SET VALUE IN INVOICE TEXT VIEW
        invoicedate.setText(formeter.format(date));
        ordertype.setText(bundle.getString("ordertype"));
        invoicediscount.setText(String.valueOf(discountrate));
        totalday.setText(String.valueOf(days));
        dailyqtyid.setText(String.valueOf(qty));
        rateperlitter.setText(String.valueOf(milkrate));
        totalqty.setText(String.valueOf(netqty));
        totalprice.setText(String.valueOf(subtotalprice));
        disprice.setText(String.valueOf(totaldiscount));
        afterdisprice.setText(String.valueOf(afdistotalprice));


    }

    public void chackvalidity() {
        String cusname = confirmname.getText().toString().trim();
        String address = comfirmaddress.getText().toString().trim();
        String mobile = confirmmobile.getText().toString().trim();

        if (TextUtils.isEmpty(cusname)) {
            confirmname.setError("Please type name ");
            confirmname.requestFocus();
        } else if (TextUtils.isEmpty(address)) {
            comfirmaddress.setError("Please type your Billing address");
            comfirmaddress.requestFocus();
        } else if (TextUtils.isEmpty(mobile)) {
            confirmmobile.setError("Please type your mobile number");
            confirmmobile.requestFocus();
        } else {
            orderSave();
            Intent intent1 = new Intent(BillActivity.this, UserHomeActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
            finish();
            Toast.makeText(getApplicationContext(), "Order Confirmation Successful.Thank You For order us", Toast.LENGTH_LONG).show();

        }

    }


    public void orderSave() {

        assert bundle != null;
        int days = bundle.getInt("days");
        double qty = bundle.getDouble("qty");
        String selectedday = bundle.getString("selecteddate");
        String ordertype = bundle.getString("ordertype");

        String cusname = confirmname.getText().toString().trim();
        String address = comfirmaddress.getText().toString().trim();
        String mobile = confirmmobile.getText().toString().trim();


        Date date = new Date();
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String orderdate = df.format(date);
        String orderStatus = "Ordered";
        Double milkrate = 50.0;
        Double discountrate = 2.0;
        Double netqty = days * qty;
        double subtotalprice = netqty * milkrate;
        double totaldiscount = (subtotalprice * 2) / 100;
        double afdistotalprice = subtotalprice - totaldiscount;
        String key = dbOrdeconfirm.push().getKey();
        String userid = mAuth.getCurrentUser().getUid();
        OrderModel orderModel = new OrderModel(key, userid, cusname, address, mobile, orderdate, ordertype, selectedday, days, qty, milkrate, discountrate, netqty, subtotalprice, totaldiscount, afdistotalprice, orderStatus);
        assert key != null;
        dbOrdeconfirm.child(key).setValue(orderModel);
        Toast.makeText(getApplicationContext(), "Order Confirmation Successful", Toast.LENGTH_LONG).show();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
