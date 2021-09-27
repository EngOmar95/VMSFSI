package com.example.vmsfsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    Button login;

    Intent intent;
    EditText userName, passWord;
    TextView Registration , forget;
    Database_api database_api;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        Registration = findViewById(R.id.Registration);
        userName = findViewById(R.id.userName);
        passWord = findViewById(R.id.passWord);
        forget = findViewById(R.id.forget);
        database_api = new Database_api();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Dailog  dialogProgressBar =new Dailog();

                dialogProgressBar.show(getSupportFragmentManager(),"");

                database_api.login(login.this, userName.getText().toString(), passWord.getText().toString() ,dialogProgressBar);


            }
        });

        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(login.this, Register_page.class);

                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(login.this, Validation_Email.class);

                startActivity(intent);
            }
        });


    }


    @Override
    public void onBackPressed() {

    }
}