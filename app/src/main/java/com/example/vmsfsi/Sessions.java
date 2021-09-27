package com.example.vmsfsi;

import android.content.SharedPreferences;

public class Sessions {

   // private  String API_KEY = "47172024";
   private  String API_KEY = "47172024";

    private   String SESSION_ID   ;
    private String TOKEN  ;


    public Sessions(String SESSION_ID, String TOKEN) {
        this.SESSION_ID = SESSION_ID;
        this.TOKEN = TOKEN;
        this.API_KEY= "47172024";
    }

    public  String getApiKey() {
        return API_KEY;
    }

    public String getSESSION_ID() {
        return SESSION_ID;
    }

    public void setSESSION_ID(String SESSION_ID) {
        this.SESSION_ID = SESSION_ID;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }
}
