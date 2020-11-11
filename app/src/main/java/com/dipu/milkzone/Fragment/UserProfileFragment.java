package com.dipu.milkzone.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dipu.milkzone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserProfileFragment extends Fragment {
    FirebaseUser fuser;
    DatabaseReference db_user;
    private ImageView userProfielimage;
    private TextView userfrofilename, useremail;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        userProfielimage = view.findViewById(R.id.profile_photo_user);
        userfrofilename = view.findViewById(R.id.profileusername);
        useremail = view.findViewById(R.id.profileuseremail);
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        db_user = FirebaseDatabase.getInstance().getReference("UsersInfo").child(fuser.getUid());
      /*  db_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                userfrofilename.setText(userModel.getUname());
                Glide.with(UserProfileFragment.this).load(userModel.getImageID()).into(userProfielimage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        userfrofilename.setText(fuser.getDisplayName());
        useremail.setText(fuser.getEmail());
        Glide.with(UserProfileFragment.this).load(fuser.getPhotoUrl()).into(userProfielimage);
        return view;
    }

    private void readUser() {


    }


}
