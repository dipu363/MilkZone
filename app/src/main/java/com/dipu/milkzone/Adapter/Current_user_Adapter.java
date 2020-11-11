package com.dipu.milkzone.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dipu.milkzone.Model.Current_UserModel;
import com.dipu.milkzone.R;

import java.util.List;

public class Current_user_Adapter extends ArrayAdapter<Current_UserModel> {
    private final Activity context;
    private final List<Current_UserModel> currentuserlist;

    public Current_user_Adapter(Activity context, List<Current_UserModel> currentuserlist) {
        super(context, R.layout.current_user_sample, currentuserlist);
        this.context = context;
        this.currentuserlist = currentuserlist;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater layoutInflater = context.getLayoutInflater();
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.current_user_sample, null, true);
        Current_UserModel current_userModel = currentuserlist.get(position);
        TextView currntemail = view.findViewById(R.id.currenuseremailtextid);
        TextView currnteToken = view.findViewById(R.id.currentusertokentextid);

        currntemail.setText("Email : " + current_userModel.getEmail());
        currnteToken.setText("Device Token : " + current_userModel.getToken());


        return view;
    }
}
