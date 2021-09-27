package com.example.vmsfsi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class Register_page extends AppCompatActivity {

    EditText Full_Name, User_Name, Password, Confirm_Password, Email;
    Database_api database_api;
    Button Register;
    String url ="https://momlense.000webhostapp.com/insert.php" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        final AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        Full_Name = findViewById(R.id.Full_Name);
        User_Name = findViewById(R.id.User_Name);
        Password = findViewById(R.id.Password);
        Confirm_Password = findViewById(R.id.Confirm_Password);
        Email = findViewById(R.id.Email);
        Register = findViewById(R.id.Register);


        awesomeValidation.addValidation(this, R.id.Full_Name, RegexTemplate.NOT_EMPTY, R.string.Invalid);
        awesomeValidation.addValidation(this, R.id.User_Name, RegexTemplate.NOT_EMPTY, R.string.Invalid);
        awesomeValidation.addValidation(this, R.id.Password, "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", R.string.Invalid_Password);
        awesomeValidation.addValidation(this, R.id.Confirm_Password, R.id.Password, R.string.Invalid_ConfPassword);



        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                database_api = new Database_api();
                info Information = new info(Full_Name.getText().toString(), User_Name.getText().toString(),
                        Password.getText().toString(), Email.getText().toString());
                if (awesomeValidation.validate()) {
                    Dailog  dialogProgressBar =new Dailog();

                    dialogProgressBar.show(getSupportFragmentManager(),"");
                    database_api.Insert_and_Update(Register_page.this, url, Information, "" ,  dialogProgressBar);

                }
            }
        });

    }
}