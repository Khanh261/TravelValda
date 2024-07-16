package com.example.travelvalda.models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.ChatActivity;
import com.example.travelvalda.R;

import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyHoder> {

    Context context;
    List<Users> userList;

    public AdapterUsers(Context context, List<Users> userList) {
        this.context= context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_users, parent, false);

        return new MyHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHoder holder, int position) {

        String hisUID = userList.get(position).getUserId();
        String userName = userList.get(position).getUserName();
        String userEmail = userList.get(position).getEmail();

        holder.mNameTv.setText(userName);
        holder.mEmailTv.setText(userEmail);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("hisUid", hisUID);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyHoder extends  RecyclerView.ViewHolder{
        TextView mNameTv, mEmailTv;

        public MyHoder(@NonNull View itemView){
            super(itemView);

            mNameTv = itemView.findViewById(R.id.nameTv);
            mEmailTv = itemView.findViewById(R.id.emailTv);
        }
    }
}