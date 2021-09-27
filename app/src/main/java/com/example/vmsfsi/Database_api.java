package com.example.vmsfsi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

public class Database_api {


    JSONObject jsonObject;
    JSONArray jsonArray;
    StringRequest stringRequest;
    Intent intent;
    SharedPreferences sp;
    Dailog dailogShow = new Dailog();


    public void Insert_and_Update(final Context context, final String url, final info date, final String ID ,   Dailog progressBar) {


        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

                    jsonObject = new JSONObject(response);


                    if (jsonObject.getString("Result").equals("true")) {

                        if (ID != "") {
                            sp = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString("Baby_Name", date.BabyName);
                            edit.putString("Baby_Age", date.Baby_Age);
                            edit.putString("Baby_image", date.Baby_image);
                            edit.apply();
                            progressBar.dismiss();
                            dailogShow.dialog("Medified Successfully", context,null );

                         //   Toast.makeText(context, "Medified Successfully", Toast.LENGTH_SHORT).show();
                            edit.apply();
                        } else {
                            intent =new Intent(context, login.class);

                          //  Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                            dailogShow.dialog("Success", context,intent );
                            progressBar.dismiss();
                            //   context.startActivity(intent);

                        }
                    }
                } catch (JSONException e) {

                    dailogShow.dialog("An error occurred ..try again", context,null );
                    progressBar.dismiss();
                  //  Toast.makeText(context, "An error occurred ..try again", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               dailogShow. dialog("An error occurred ..try again", context,null );
                progressBar.dismiss();
              //  Toast.makeText(context, "An error occurred ..try again", Toast.LENGTH_SHORT).show();
            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                //K              //Value
                params.put(date.getFull_Name(), date.Full_Name);
                params.put(date.getUser_Name(), date.User_Name);
                params.put(date.getPassword(), date.Password);
                params.put(date.getEmail(), date.Email);

                if (ID != "") {


                    params.put(date.getBaby_image(), date.Baby_image);

                    params.put(date.getBabyName(), date.BabyName);
                    params.put(date.getBaby_Age(), date.Baby_Age);
                    params.put("id", ID);
                }


                return params;
            }

        };
        Volley.newRequestQueue(context).add(stringRequest);


    }


    public void login(final Context context, final String UserName, final String Password ,  Dailog progressBar) {


        stringRequest = new StringRequest(Request.Method.POST, "https://momlense.000webhostapp.com/login.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

                    jsonObject = new JSONObject(response);

                    jsonArray = jsonObject.getJSONArray("Result");


                    sp = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor edit = sp.edit();


                    if (jsonArray.length() > 0) {
                        Database_api.this.jsonObject = jsonArray.getJSONObject(0);
                        edit.putString("id", Database_api.this.jsonObject.getString("id"));
                        edit.putString("Full_Name", Database_api.this.jsonObject.getString("Full_Name"));
                        edit.putString("User_Name", Database_api.this.jsonObject.getString("User_Name"));
                        edit.putString("Password", Database_api.this.jsonObject.getString("Password"));
                        edit.putString("Email", Database_api.this.jsonObject.getString("Email"));
                        edit.putString("Baby_Name", Database_api.this.jsonObject.getString("Baby_Name"));
                        edit.putString("Baby_Age", Database_api.this.jsonObject.getString("Baby_Age"));
                        edit.putString("Baby_image", Database_api.this.jsonObject.getString("Baby_image"));
                        edit.putString("SESSION_ID", Database_api.this.jsonObject.getString("SESSION_ID"));
                        edit.putString("TOKEN", Database_api.this.jsonObject.getString("TOKEN"));


                        edit.apply();
                        Intent intent;
                        progressBar.dismiss();
                        intent = new Intent(context, main_page.class);


                        context.startActivity(intent);
                    } else {

                        dailogShow.dialog("User Name or Password is incorrect", context,null );
                        progressBar.dismiss();
                     //   Toast.makeText(context, "User Name or Password is incorrect", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                    dailogShow. dialog("An error occurred ..try again", context,null );
                    progressBar.dismiss();
                  //  Toast.makeText(context, "An error occurred ..try again", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dailogShow.dialog("An error occurred ..try again", context,null );
                progressBar.dismiss();
            //    Toast.makeText(context, "An error occurred ..try again", Toast.LENGTH_SHORT).show();
            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //k
                params.put("User_Name", UserName);
                params.put("Password", Password);

                return params;
            }

        };
        Volley.newRequestQueue(context).add(stringRequest);

    }
    

    public  void SendEmailCode (Context context ,String Code ,String Emil ,Dailog progressBar){



        stringRequest = new StringRequest(Request.Method.POST, "https://babymonitoring2002.000webhostapp.com/SendEmil.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

                    jsonObject = new JSONObject(response);


                    if(jsonObject.getString("Result").equals("true")){


                        Intent intent =new Intent(context,Validate_Code.class)   ;
                        intent.putExtra("code",Code)       ;
                        intent.putExtra("Emil",Emil)       ;

                        dailogShow.dialog("Send",context,intent);
                        progressBar.dismiss();


                    }
                    if(jsonObject.getString("Result").equals("false")){
                        dailogShow. dialog("Emil invalid ..try again", context,null );
                        progressBar.dismiss();
                    }
                } catch (JSONException e) {

                    dailogShow. dialog("An error occurred ..try agai1n", context,null );
                    progressBar.dismiss();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dailogShow. dialog("An error occurred ..try again", context,null );
                progressBar.dismiss();


            }
        }) {



            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Emil",Emil);
                params.put("Code", Code);




                return params;
            }

        };
        Volley.newRequestQueue(context).add(stringRequest);


    }

    public void restPassword(final Context context, String NewPassword, final String Emil , String flag,  Dailog progressBar) {


        stringRequest = new StringRequest(Request.Method.POST, "https://momlense.000webhostapp.com/RestPassword.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

                    jsonObject = new JSONObject(response);


                    if (jsonObject.getString("Result").equals("true")) {



                            progressBar.dismiss();
                            if(flag.equals("isThereEmail"))    {
                                Intent intent =new Intent(context,ChangePassword.class)     ;
                                intent.putExtra("Emil",Emil);
                                context.startActivity(intent);
                            }  else{
                                Intent intent =new Intent(context,login.class)     ;
                                dailogShow.dialog(" Successfully", context,intent );}



                    } else{
                        progressBar.dismiss();
                        if(flag.equals("isThereEmail"))    {
                            dailogShow.dialog(" Emil Invalid ", context,null );
                           
                        }  else{   dailogShow.dialog(" Error.. try again", context,null );}



                    }
                } catch (JSONException e) {

                    dailogShow.dialog("An error occurred ..try again", context,null );
                    progressBar.dismiss();
                    //  Toast.makeText(context, "An error occurred ..try again", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dailogShow. dialog("An error occurred ..try again", context,null );
                progressBar.dismiss();
                //  Toast.makeText(context, "An error occurred ..try again", Toast.LENGTH_SHORT).show();
            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                //K              //Value
                params.put("Emil", Emil);

                if(!flag.equals("isThereEmail")){params.put("password", NewPassword);}

                params.put("flag", flag);



               

                return params;
            }

        };
        Volley.newRequestQueue(context).add(stringRequest);


    }


}
