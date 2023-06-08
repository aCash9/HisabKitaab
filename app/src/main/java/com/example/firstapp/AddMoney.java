package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Date;
public class AddMoney extends AppCompatActivity {
    Button button;
    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ConstraintLayout constraintLayout;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        constraintLayout = findViewById(R.id.constraint);
        Intent intent = getIntent();
        String color_name = intent.getStringExtra(MainActivity.COLOR);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.amount);
        editText2 = findViewById(R.id.note);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting the date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = dateFormat.format(new Date());
                //getting the amount
                int amt = Integer.parseInt(editText.getText().toString());
                //getting the note
                String nt = editText2.getText().toString();
                if (nt.equals(""))  {
                    Log.d("mytag", "onClick: empty1");
                    editText2.setError("Field cannot be empty");
                } else if (editText.getText().toString().equals("")) {
                    editText.setError("Field cannot be empty");
                    Log.d("mytag", "onClick: empty2");
                } else {
                    Log.d("mytag", "onClick: empty3");

                    TransactionList item;
                        item = new TransactionList(amt, nt, currentDate, color_name);
                        dbHelper dbhelper = new dbHelper(AddMoney.this);
                        boolean b = dbhelper.addItem(item);
                }
                finish();
            }
        });

        if(color_name.equals("add"))
            constraintLayout.setBackgroundColor(Color.parseColor("#C6EDC7"));
        else if(color_name.equals("sub"))
            constraintLayout.setBackgroundColor(Color.parseColor("#E3A5A5"));
    }
}