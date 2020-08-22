package com.dipu.milkzone;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Daily_production_Adapter extends ArrayAdapter<DailyProductionModel> {


    Activity context;
    List<DailyProductionModel> dailyproductionList;


    public Daily_production_Adapter(Activity context, List<DailyProductionModel> dailyproductionList) {
        super(context, R.layout.daily_pro_sample, dailyproductionList);
        this.context = context;
        this.dailyproductionList = dailyproductionList;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.daily_pro_sample, null, true);

        DailyProductionModel productionModel = dailyproductionList.get(position);

        TextView cowtitle = view.findViewById(R.id.dPro_list_titleid);
        TextView proDate = view.findViewById(R.id.dPro_Date_id);
        TextView moringQty = view.findViewById(R.id.dPro_MoringId);
        TextView eveningQty = view.findViewById(R.id.dPro_Evening_id);


        cowtitle.setText("Cow Title :" + productionModel.getPro_cowtitle());
        proDate.setText("Production Date :" + productionModel.getPro_date());
        moringQty.setText("Morning Qty(Lt) :" + productionModel.getMorning_Qty());
        eveningQty.setText("Evening Qty(Lt) :" + productionModel.getEvening_Qty());


        return view;


    }
}
