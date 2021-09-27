package com.example.vmsfsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class menu extends AppCompatActivity {
          Button Baby_profile , Monitorung , about_us ;
          Intent intent;
          ImageButton Sign_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

         Baby_profile =findViewById(R.id.Baby_profile) ;
         Monitorung =findViewById(R.id.Monitorung) ;
         about_us=findViewById(R.id.about_us) ;
         Sign_out =findViewById(R.id.Sign_out) ;

        Baby_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(menu.this, Baby_profile.class);

                startActivity(intent);
            }
        });

        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(menu.this, About_us.class);

                startActivity(intent);
            }
        });

        Sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(menu.this, login.class);

                startActivity(intent);


                finish();
            }
        });
        Monitorung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(menu.this, Monitoring.class);

                startActivity(intent);
            }
        });




    }
}