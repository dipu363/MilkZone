package com.dipu.milkzone.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dipu.milkzone.Model.UserModel;
import com.dipu.milkzone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    FirebaseAuth mAuth;
    FirebaseUser currentuser;
    private final Context mContext;
    private final List<UserModel> mUsers;

    public UserAdapter(Context mContext, List<UserModel> mUsers) {
        this.mUsers = mUsers;
        this.mContext = mContext;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.users_semple_item, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder viewHolder, final int i) {

        final UserModel user = mUsers.get(i);
        viewHolder.username.setText(user.getUname());
        Glide.with(mContext).load(user.getImageID()).into(viewHolder.profile_image);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageAdapter.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public ImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.user_photo);

        }
    }
}
