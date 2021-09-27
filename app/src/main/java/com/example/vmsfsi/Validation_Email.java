package com.example.vmsfsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.Random;

public class Validation_Email extends AppCompatActivity {
      EditText validEmil;
    Button Send;
    Database_api database_api;
    String Code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation__email);
            database_api=new Database_api();
        Send=findViewById(R.id.Send)          ;
        validEmil=findViewById(R.id.validEmil)          ;
        final AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.validEmil, Patterns.EMAIL_ADDRESS, R.string.Invalid_Emil);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()) {
                    Dailog dialogProgressBar = new Dailog();

                    dialogProgressBar.show(getSupportFragmentManager(), "");
                    //  Code =getGenerationNumber();
                    // database_api.SendEmailCode(Validation_Email.this,Code,validEmil.getText().toString(),dialogProgressBar);
                    database_api.restPassword(Validation_Email.this, null, validEmil.getText().toString(), "isThereEmail", dialogProgressBar);
                    // Intent intent = new Intent(Validation_Email.this, Validate_Code.class);
                }
               // startActivity(intent);
            }
        });
    }

    public  String getGenerationNumber(){
        Random random=new Random();
        int randomNumber=1000+random.nextInt(999);

        String Code=String.valueOf(randomNumber);
        return  Code;
    }
}