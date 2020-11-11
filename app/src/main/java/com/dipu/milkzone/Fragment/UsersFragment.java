package com.dipu.milkzone.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dipu.milkzone.Adapter.UserAdapter;
import com.dipu.milkzone.Model.UserModel;
import com.dipu.milkzone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class UsersFragment extends Fragment {

    DatabaseReference db_user;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<UserModel> userModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users, container, false);


        recyclerView = view.findViewById(R.id.recycler_view_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userModelList = new ArrayList<>();
        readUser();

        return view;
    }

    private void readUser() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        db_user = FirebaseDatabase.getInstance().getReference("UsersInfo");

        db_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userModelList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    UserModel userModel = snapshot.getValue(UserModel.class);
                    assert userModel != null;
                    assert firebaseUser != null;
                    if (!userModel.getId().equals(firebaseUser.getUid())) {
                        userModelList.add(userModel);

                    }


                }

                userAdapter = new UserAdapter(getContext(), userModelList);
                recyclerView.setAdapter(userAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
