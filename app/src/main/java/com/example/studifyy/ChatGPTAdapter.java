package com.example.studifyy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatGPTAdapter extends RecyclerView.Adapter<ChatGPTAdapter.myviewholder>{
    List<ChatGPTModalClass> list;
    public ChatGPTAdapter(List<ChatGPTModalClass> list) {
        this.list=list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.massagelayout,null);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        ChatGPTModalClass massage=list.get(position);
        if (massage.getSendby().equals(ChatGPTModalClass.send_by_me)){
            holder.left.setVisibility(View.GONE);
            holder.right.setVisibility(View.VISIBLE);
            holder.rightmsg.setText(massage.getMassage());
        }
        else {
            holder.left.setVisibility(View.VISIBLE);
            holder.right.setVisibility(View.GONE);
            holder.leftmsg.setText(massage.getMassage());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        LinearLayout left,right;
        TextView leftmsg,rightmsg;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            left=itemView.findViewById(R.id.leftmassagebox);
            right=itemView.findViewById(R.id.rightmassagebox);
            leftmsg=itemView.findViewById(R.id.lefttext);
            rightmsg=itemView.findViewById(R.id.righttext);

        }
    }
}
