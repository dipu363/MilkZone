package com.dipu.milkzone;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderListAdapter extends ArrayAdapter<OrderModel> {

    Activity context;
    List<OrderModel> orderlist;

    public OrderListAdapter(Activity context, List<OrderModel> orderlist) {
        super(context, R.layout.order_list_sample, orderlist);
        this.context = context;
        this.orderlist = orderlist;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.order_list_sample, null, true);
        OrderModel orderModel = orderlist.get(position);

        TextView user = view.findViewById(R.id.ordertextuseremailid);
        TextView orderdate = view.findViewById(R.id.ordertextorderdateid);
        TextView ordertype = view.findViewById(R.id.ordertextordertypeid);
        TextView ordersechdule = view.findViewById(R.id.ordertextsechduleid);
        TextView perdayqty = view.findViewById(R.id.ordertextperdayqtyid);
        TextView orderstatus = view.findViewById(R.id.ordertextorderstatus_id);

        user.setText("User : " + orderModel.getName());
        orderdate.setText("Order Date : " + orderModel.getOrderdate());
        ordertype.setText("Order Type : " + orderModel.getOrdertype());
        ordersechdule.setText("Sehcdul Date : " + orderModel.getScheduleDay());
        perdayqty.setText("Daily Qty(Ltr) : " + orderModel.getDailyqnty());
        orderstatus.setText("Status : " + orderModel.getOrderStatus());


        return view;
    }
}
