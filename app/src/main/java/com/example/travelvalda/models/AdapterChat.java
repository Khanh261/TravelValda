package com.example.travelvalda.models;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.MyHolder>{
    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;
    Context context;
    List<Chat> chatList;
    FirebaseUser fUser;

    public AdapterChat(Context context, List<Chat> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @androidx.annotation.NonNull
    @Override
    public MyHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT){
            View view = LayoutInflater.from(context).inflate(R.layout.row_chat_right, parent, false);
            return  new MyHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.row_chat_left, parent, false);
            return  new MyHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull MyHolder holder, int position) {
         String message = chatList.get(position).getMessage();
         String timeStamp = chatList.get(position).getTimestamp();

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(timeStamp));
        String dateTime = DateFormat.format("dd/MM/yyyy hh:mm aa", cal).toString();

        holder.messageTv.setText(message);
        holder.timeTv.setText(dateTime);

        if(position == chatList.size()-1){
            if(chatList.get(position).isSeen()){
                holder.isSeenTv.setText("Seen");
            }else{
                holder.isSeenTv.setText("Delivered");
            }
        }else{
            holder.isSeenTv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
    public int getItemViewType(int position){
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        if(chatList.get(position).getSender().equals(fUser.getUid())){
            return MSG_TYPE_RIGHT;
        }else{
            return MSG_TYPE_LEFT;
        }
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView messageTv;
        TextView timeTv;
        TextView isSeenTv;



        public MyHolder(@NonNull View itemView){
            super(itemView);

            messageTv = itemView.findViewById(R.id.messageTv);
            timeTv = itemView.findViewById(R.id.timeTv);
            isSeenTv = itemView.findViewById(R.id.isSeenTv);
        }
    }
}
