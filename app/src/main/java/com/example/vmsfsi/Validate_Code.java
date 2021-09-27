package com.example.vmsfsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Validate_Code extends AppCompatActivity {
          Button validationCode;
          EditText validCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate__code);

        validationCode=findViewById(R.id.validationCode)    ;
        validCode=findViewById(R.id.validCode)    ;
       Intent intent=getIntent();
        String code =intent.getStringExtra("code");
        String Emil =intent.getStringExtra("Emil");
        Dailog dailogShow = new Dailog();
        validationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dailog  dialogProgressBar =new Dailog();

                dialogProgressBar.show(getSupportFragmentManager(),"");
                if(validCode.getText().toString().equals(code)){

                    Intent intent = new Intent(Validate_Code.this, ChangePassword.class);
                    intent.putExtra("Emil",Emil)  ;
                    dialogProgressBar.dismiss();
                    dailogShow.dialog("Done ",Validate_Code.this,intent);




                }else {
                    dialogProgressBar.dismiss();
                    dailogShow.dialog("Code Invalid",Validate_Code.this , null);
                }

            }



          
        });
    }
}