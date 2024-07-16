package com.example.travelvalda;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.models.AdapterChat;
import com.example.travelvalda.models.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    //View from xml
    Toolbar toolbar;
    RecyclerView recyclerView;
    ImageView profileIv;
    TextView nameTv, userStatusTv;
    EditText messageEt;
    ImageButton sendBtn;
    FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference usersDbRef;

    ValueEventListener seenListener;
    DatabaseReference userRefForSeen;
    List<Chat> chatList;
    AdapterChat adapterChat;

    String hisUid;
    String myUid;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        recyclerView = findViewById(R.id.chat_recycleView);
        nameTv = findViewById(R.id.nameTv);
        userStatusTv = findViewById(R.id.userStatusTv);
        messageEt = findViewById(R.id.messageEt);
        sendBtn = findViewById(R.id.sendBtn);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        hisUid = intent.getStringExtra("hisUid");

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        usersDbRef = firebaseDatabase.getReference("Users");

        Query userQuery = usersDbRef.orderByChild("uid").equalTo(hisUid);
        userQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    String name = ""+ ds.child("name").getValue();
                    nameTv.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEt.getText().toString().trim();
                if(TextUtils.isEmpty(message)){
                    Toast.makeText(ChatActivity.this, "Cannot send the empty message", Toast.LENGTH_SHORT).show();
                }else{
                    sendMessage(message);
                }
            }
        });

        readMessages();

        seenMessage();

    }

    private void seenMessage() {
        userRefForSeen = FirebaseDatabase.getInstance().getReference("Chats");
        seenListener = userRefForSeen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    Chat chat = ds.getValue(Chat.class);
                    if (chat != null) {
                        if (chat.getReceiver() != null && chat.getSender() != null) {
                            if (chat.getReceiver().equals(myUid) && chat.getSender().equals(hisUid)) {
                                HashMap<String, Object> hasSeenHashMap = new HashMap<>();
                                hasSeenHashMap.put("isSeen", true);
                                ds.getRef().updateChildren(hasSeenHashMap);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void readMessages() {
        chatList = new ArrayList<>();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Chats");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatList.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    Chat chat = ds.getValue(Chat.class);
                    if (chat != null) {
                        if (chat.getReceiver() != null && chat.getSender() != null) {
                            if ((chat.getReceiver().equals(myUid) && chat.getSender().equals(hisUid)) ||
                                    (chat.getReceiver().equals(hisUid) && chat.getSender().equals(myUid))) {
                                chatList.add(chat);
                            }
                        }
                    }
                }
                adapterChat = new AdapterChat(ChatActivity.this, chatList);
                adapterChat.notifyDataSetChanged();
                recyclerView.setAdapter(adapterChat);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMessage(String message){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        String timestamp = String.valueOf(System.currentTimeMillis());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", myUid);
        hashMap.put("receiver", hisUid);
        hashMap.put("message", message);
        hashMap.put("timestamp", timestamp);
        hashMap.put("isSeen", false);
        databaseReference.child("Chats").push().setValue(hashMap);

        messageEt.setText("");
    }

    private void checkUsersStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            myUid = user.getUid();
        }else{
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart(){
        checkUsersStatus();
        super.onStart();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (userRefForSeen != null && seenListener != null) {
            userRefForSeen.removeEventListener(seenListener);
        }
    }
}
