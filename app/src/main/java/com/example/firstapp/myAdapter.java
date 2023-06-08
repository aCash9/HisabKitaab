package com.example.firstapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class myAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<TransactionList> list;
    SelectListener listener;


    public myAdapter(Context context, List<TransactionList> list, SelectListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_views, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView1.setText("₹" + String.valueOf(list.get(position).getAmount()));
        holder.textView2.setText(list.get(position).getNote());
        holder.textView3.setText(list.get(position).getDate());
        holder.textView1.setBackgroundColor(Color.parseColor(list.get(position).getType().equals("add") ? "#C6EDC7" : "#E3A5A5" ));
        holder.textView2.setBackgroundColor(Color.parseColor(list.get(position).getType().equals("add") ? "#C6EDC7" : "#E3A5A5" ));
        holder.textView3.setBackgroundColor(Color.parseColor(list.get(position).getType().equals("add") ? "#C6EDC7" : "#E3A5A5" ));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
