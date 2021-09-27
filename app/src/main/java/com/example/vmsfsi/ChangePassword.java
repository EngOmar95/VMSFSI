package com.example.vmsfsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class ChangePassword extends AppCompatActivity {
        EditText Password ,Confirm_Password;
        Button save;
        Database_api database_api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        save =findViewById(R.id.save);
        Password =findViewById(R.id.Password);
        Confirm_Password =findViewById(R.id.Confirm_Password);
        database_api=new Database_api();
        final AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
       Intent intent=getIntent();

        String Emil =intent.getStringExtra("Emil");


        awesomeValidation.addValidation(this, R.id.Password, "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", R.string.Invalid_Password);
        awesomeValidation.addValidation(this, R.id.Confirm_Password, R.id.Password, R.string.Invalid_ConfPassword);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (awesomeValidation.validate()) {
                    Dailog dialogProgressBar = new Dailog();

                    dialogProgressBar.show(getSupportFragmentManager(), "");

                    database_api.restPassword(ChangePassword.this, Password.getText().toString(), Emil, "changePassword", dialogProgressBar);
                }

            }
        });
    }
}