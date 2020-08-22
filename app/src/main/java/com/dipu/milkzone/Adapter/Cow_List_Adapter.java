package com.dipu.milkzone.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dipu.milkzone.Model.CowDetailsModel;
import com.dipu.milkzone.R;

import java.util.List;

public class Cow_List_Adapter extends ArrayAdapter<CowDetailsModel> {
    private Activity context;
    private List<CowDetailsModel> cowDetailsModelList;

    public Cow_List_Adapter(Activity context, List<CowDetailsModel> cowDetailsModelList) {
        super(context, R.layout.sample_layout, cowDetailsModelList);
        this.context = context;
        this.cowDetailsModelList = cowDetailsModelList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout, null, true);
        CowDetailsModel cowDetailsModel = cowDetailsModelList.get(position);

        TextView title = view.findViewById(R.id.cowlist_titleid);
        TextView weight = view.findViewById(R.id.cowlist_Weightid);
        TextView avgproduction = view.findViewById(R.id.cowlist_avgproductionid);
        TextView remarks = view.findViewById(R.id.cowlist_remarksid);
        TextView status = view.findViewById(R.id.cowlist_statusid);

        title.setText("Cow Title :" + cowDetailsModel.getName());
        weight.setText("Cow Weight :" + cowDetailsModel.getWeight());
        avgproduction.setText("Avg.Production :" + (int) cowDetailsModel.getAvg_production());
        remarks.setText("Remarks :" + cowDetailsModel.getRemarks());


        return view;
    }
}
