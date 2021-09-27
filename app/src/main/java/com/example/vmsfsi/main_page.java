package com.example.vmsfsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_page extends AppCompatActivity {
    Button Parent;
    Button Monitoring_deceive;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Parent = findViewById(R.id.Parent);
        Monitoring_deceive = findViewById(R.id.Monitoring_deceive);


        Parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(main_page.this, menu.class);

                startActivity(intent);
            }
        });

        Monitoring_deceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(main_page.this, Monitoring_Device.class);

                startActivity(intent);
            }
        });
    }
}