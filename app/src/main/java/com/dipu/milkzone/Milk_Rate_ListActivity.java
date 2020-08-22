package com.dipu.milkzone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Milk_Rate_ListActivity extends AppCompatActivity {
    ListView listView;
    DatabaseReference db_MilkRate;
    private List<RateModel> rate_list;
    private Milk_rate_Adapter milk_rate_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk__rate__list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        this.setTitle("Daily Milk Rate");
        listView = findViewById(R.id.milkRateListViwe_id);
        db_MilkRate = FirebaseDatabase.getInstance().getReference("milk_rate");
        rate_list = new ArrayList<>();
        milk_rate_adapter = new Milk_rate_Adapter(Milk_Rate_ListActivity.this, rate_list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RateModel rateModel = rate_list.get(position);

                showUpdateDialog(rateModel.getRate_id(), rateModel.getRate());

            }
        });

    }

    @Override
    protected void onStart() {

        db_MilkRate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rate_list.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    RateModel rateModel = dataSnapshot1.getValue(RateModel.class);
                    rate_list.add(rateModel);

                }
                listView.setAdapter(milk_rate_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }

    public void showUpdateDialog(final String rateid, String updaterate) {
        AlertDialog.Builder updatedialogbuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogview = inflater.inflate(R.layout.updatedialog, null);
        updatedialogbuilder.setView(dialogview);
        final EditText milkrate = dialogview.findViewById(R.id.updatemilkrate);
        Button btnupdate = dialogview.findViewById(R.id.btnupdaterate);
        Button btndelete = dialogview.findViewById(R.id.btedeleterate);
        Button btncencel = dialogview.findViewById(R.id.btnCencelid);
        milkrate.setText(updaterate);
        updatedialogbuilder.setTitle("Update Rate ID" + rateid);
        final AlertDialog alertDialog = updatedialogbuilder.create();
        alertDialog.show();
        btncencel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = new Date();
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                String rate_date = df.format(date);
                String nrate = milkrate.getText().toString().trim();
                updateMilkrate(rateid, nrate, rate_date);
                alertDialog.dismiss();


            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletemilkrate(rateid);
                alertDialog.dismiss();
            }
        });

    }

    private void deletemilkrate(String rateid) {
        DatabaseReference deleterate = FirebaseDatabase.getInstance().getReference("milk_rate").child(rateid);
        deleterate.removeValue();
        Toast.makeText(getApplicationContext(), "Rate Deleted", Toast.LENGTH_LONG).show();
    }

    private boolean updateMilkrate(String nrateid, String rate, String ndate) {


        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("milk_rate").child(nrateid);

        //updating artist
        RateModel rateModel = new RateModel(nrateid, rate, ndate);
        dR.setValue(rateModel);
        Toast.makeText(getApplicationContext(), "Rate Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
