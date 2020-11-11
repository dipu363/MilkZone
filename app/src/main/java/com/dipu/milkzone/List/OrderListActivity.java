package com.dipu.milkzone.List;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.dipu.milkzone.Adapter.OrderListAdapter;
import com.dipu.milkzone.Model.OrderModel;
import com.dipu.milkzone.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends AppCompatActivity {
    DatabaseReference db_Order;
    private List<OrderModel> order_list;
    private OrderListAdapter order_list_adapter;
    private ListView orderlistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);
        this.setTitle("  Order List");

        db_Order = FirebaseDatabase.getInstance().getReference("customer_order");
        orderlistView = findViewById(R.id.orderlistveiw_id);
        order_list = new ArrayList<>();
        order_list_adapter = new OrderListAdapter(OrderListActivity.this, order_list);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {

        db_Order.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                               order_list.clear();
                                               for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                                                   OrderModel orderModel = dataSnapshot1.getValue(OrderModel.class);
                                                   order_list.add(orderModel);
                                               }
                                               orderlistView.setAdapter(order_list_adapter);
                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {
                                               databaseError.getMessage();

                                           }
                                       }
        );
        super.onStart();
    }
}
