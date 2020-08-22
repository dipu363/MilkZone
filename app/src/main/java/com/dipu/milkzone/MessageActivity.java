package com.dipu.milkzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dipu.milkzone.Model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseUser fuser;
    DatabaseReference reference;
    Intent intent;
    EditText sendmessege;
    List<Chat> mChat;
    RecyclerView chatrecycleview;
    private ImageView profileimage;
    private TextView username;
    private ImageButton btnaudio, btnvideo, btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
  /*      this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try{
           this.getSupportActionBar().hide();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();

        }*/


        Toolbar toolbar = findViewById(R.id.messagetoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profileimage = findViewById(R.id.messageUserPhoto);
        username = findViewById(R.id.messageusertext);
        btnaudio = findViewById(R.id.btnaudiocall);
        btnvideo = findViewById(R.id.btnvideocall);
        btn_send = findViewById(R.id.btnsendmessege);
        sendmessege = findViewById(R.id.messagetextid);

        //for show message
        chatrecycleview = findViewById(R.id.messagerecycalviewid);
        chatrecycleview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        chatrecycleview.setLayoutManager(linearLayoutManager);


        //for send message


        intent = getIntent();
        final String userid = intent.getStringExtra("userid");
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messeges = sendmessege.getText().toString().trim();
                if (!messeges.equals("")) {
                    sendMessages(fuser.getUid(), userid, messeges);
                    sendmessege.setText("");

                } else {

                    Toast.makeText(getApplicationContext(), "You can't send empty message", Toast.LENGTH_LONG).show();
                }


            }
        });


        reference = FirebaseDatabase.getInstance().getReference("UsersInfo").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                username.setText(userModel.getUname());
                Glide.with(MessageActivity.this).load(userModel.getImageID()).into(profileimage);

                redMessage(fuser.getUid(), userid, userModel.getImageID());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void redMessage(final String uid, final String userid, final String imageID) {
        mChat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mChat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals(uid) && chat.getSender().equals(userid) ||
                            chat.getReceiver().equals(userid) && chat.getSender().equals(uid)) {

                        mChat.add(chat);
                    }
                       /* messageAdapter= new MessageAdapter(MessageActivity.this,mChat,imageID);
                        chatrecycleview .setAdapter(messageAdapter);*/


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void sendMessages(String sender, String receiver, String messages) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("messages", messages);
        reference.child("chats").push().setValue(hashMap);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnvideocall:
                Toast.makeText(this, "this is click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MessageActivity.this, VideoCallActivity.class);
                startActivity(intent);
                break;
        }
    }
}
