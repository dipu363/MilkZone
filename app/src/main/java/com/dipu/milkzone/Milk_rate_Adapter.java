package com.dipu.milkzone;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Milk_rate_Adapter extends ArrayAdapter<RateModel> {

    Activity context;
    List<RateModel> dailymilkratelist;

    public Milk_rate_Adapter(Activity context, List<RateModel> dailymilkratelist) {
        super(context, R.layout.milk_rate_semple, dailymilkratelist);
        this.context = context;
        this.dailymilkratelist = dailymilkratelist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.milk_rate_semple, null, true);
        RateModel rateModel = dailymilkratelist.get(position);

        TextView ratedate = view.findViewById(R.id.milklist_dateid);
        TextView dailyrate = view.findViewById(R.id.milklist_rateid);

        ratedate.setText("Rate Updated date :" + rateModel.getCurent_date());
        dailyrate.setText("Rate(Ltr) :" + "  " + rateModel.getRate() + " Tk");


        return view;
    }
}
