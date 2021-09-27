package com.example.vmsfsi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Baby_profile extends AppCompatActivity {


    Button edit_info, Save;
    TextView babyName, BabyAge;
    ImageView imageBaby;
    ImageButton GoToHome;
    Intent intent;
    Bitmap Image;
    int ConvertAge;
    SharedPreferences sp;
    Database_api database_api;
    String Baby_image, id, Full_Name, User_Name, Password, Email;
    String url ="https://momlense.000webhostapp.com/Update.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_profile);


        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        id = sp.getString("id", "0");
        Full_Name = sp.getString("Full_Name", "0");
        User_Name = sp.getString("User_Name", "0");
        Password = sp.getString("Password", "0");
        Email = sp.getString("Email", "0");
        Baby_image = sp.getString("Baby_image", "0");

        edit_info = findViewById(R.id.edit_info);
        Save = findViewById(R.id.Save);
        GoToHome = findViewById(R.id.GoToHome);
        babyName = findViewById(R.id.babyName);
        BabyAge = findViewById(R.id.BabyAge);
        imageBaby = findViewById(R.id.imageBaby);




        imageBaby.setImageBitmap(Image_ConvertTo_Bitmap(Baby_image));
        babyName.setText(sp.getString("Baby_Name", "0"));
        BabyAge.setText(sp.getString("Baby_Age", "0"));

        if (Baby_image.equals("null")) {

            imageBaby.setImageResource(R.drawable.child);
        }

        if (babyName.getText().toString().equals("null")) {
            babyName.setText(null);
            babyName.setHint("Enter Your Baby Name");

        }

        if (BabyAge.getText().toString().equals("null")) {
            BabyAge.setText(null);
            BabyAge.setHint("Enter Your Baby Age");

        }

        imageBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);

            }

        });


        GoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        edit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_info.setVisibility(View.GONE);
                Save.setVisibility(View.VISIBLE);
                babyName.setEnabled(true);
                BabyAge.setEnabled(true);

            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dailog  dialogProgressBar =new Dailog();

                dialogProgressBar.show(getSupportFragmentManager(),"");
                 ConvertAge =Integer.parseInt(BabyAge.getText().toString());

                edit_info.setVisibility(View.VISIBLE);
                Save.setVisibility(View.GONE);
                babyName.setEnabled(false);
                BabyAge.setEnabled(false);

                database_api = new Database_api();

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageBaby.getDrawable();
                Image = bitmapDrawable.getBitmap();
                String StringImage = Image_ConvertTo_String(Image);

                if(ConvertAge<=24) {
                    info Information = new info(Full_Name, User_Name, Password, Email, babyName.getText().toString(), BabyAge.getText().toString(), StringImage);

                    database_api.Insert_and_Update(Baby_profile.this, url, Information, id , dialogProgressBar);
                }  else {

                    Toast.makeText(Baby_profile.this, "Baby Age must be less than 25 monthes ", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {

                Image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                imageBaby.setImageBitmap(Image);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private String Image_ConvertTo_String(Bitmap image) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        return encodedImage;
    }



    public Bitmap Image_ConvertTo_Bitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


}
