package com.example.firstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transaction extends AppCompatActivity implements SelectListener{
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        dbHelper db = new dbHelper(this);
        List<TransactionList> list = db.getData();
        recyclerView = findViewById(R.id.list);
        Log.d("mytag", "onCreate: sending list for display");
        Collections.reverse(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new myAdapter(getApplicationContext(), list, this));
    }

    @Override
    public void onClick(TransactionList item) {
//        Toast.makeText(this, "click click", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder ad = new AlertDialog.Builder(Transaction.this);

        ad.setMessage("Are you sure you want to delete this transaction?");
        ad.setIcon(R.drawable.baseline_delete_24);
        ad.setTitle("Delete Transaction");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Transaction.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        ad.setNegativeButton("No", null);
        ad.show();
    }
}