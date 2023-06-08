package com.example.firstapp;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    RelativeLayout layout;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textView1 = itemView.findViewById(R.id.amount);
        textView2 = itemView.findViewById(R.id.note);
        textView3 = itemView.findViewById(R.id.date);
        layout = itemView.findViewById(R.id.layout);
    }
}
