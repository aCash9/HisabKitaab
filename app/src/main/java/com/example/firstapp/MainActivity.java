package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button add;
    private Button sub;
    private Button history;
    public static final String COLOR = "com.example.firstapp.add.color";
    public static final String BALANCE = "com.example.firstapp.balance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.balance);
        dbHelper db = new dbHelper(this);
        textView.setText(db.getLastRowData());
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        history = findViewById(R.id.history);
        textView.setText(db.getLastRowData());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMoney.class);
                intent.putExtra(COLOR, "add");
                startActivity(intent);
                textView.setText(db.getLastRowData());
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMoney.class);
                intent.putExtra(COLOR, "sub");
                startActivity(intent);
                textView.setText(db.getLastRowData());
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Transaction.class);
                Log.d("mytag", "showList: success");
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        dbHelper db = new dbHelper(this);
        if(db.getLastRowData() == null)
            textView.setText("₹0");
        else
            textView.setText("₹" + db.getLastRowData());

    }

}