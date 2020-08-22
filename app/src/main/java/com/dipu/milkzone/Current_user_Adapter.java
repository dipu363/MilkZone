package com.dipu.milkzone;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Current_user_Adapter extends ArrayAdapter<Current_User> {
    private Activity context;
    private List<Current_User> currentuserlist;

    public Current_user_Adapter(Activity context, List<Current_User> currentuserlist) {
        super(context, R.layout.current_user_sample, currentuserlist);
        this.context = context;
        this.currentuserlist = currentuserlist;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.current_user_sample, null, true);
        Current_User current_user = currentuserlist.get(position);
        TextView currntemail = view.findViewById(R.id.currenuseremailtextid);
        TextView currnteToken = view.findViewById(R.id.currentusertokentextid);

        currntemail.setText("Email : " + current_user.getEmail());
        currnteToken.setText("Device Token : " + current_user.getToken());


        return view;
    }
}
