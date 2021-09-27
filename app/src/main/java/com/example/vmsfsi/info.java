package com.example.vmsfsi;

public class info {

    String Full_Name;
    String  User_Name  ;
    String Password  ;
    String Email ;
    String BabyName;
    String Baby_Age;
    String Baby_image  ;


    public info(String full_Name, String user_Name, String password, String email, String babyName, String baby_Age, String baby_image) {
        Full_Name = full_Name;
        User_Name = user_Name;
        Password = password;
        Email = email;
        BabyName = babyName;
        Baby_Age = baby_Age;
        Baby_image = baby_image;
    }

    public info(String full_Name, String user_Name, String password, String email) {
        Full_Name = full_Name;
        User_Name = user_Name;
        Password = password;
        Email = email;
    }

    public String getFull_Name() {
        return "Full_Name";
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getUser_Name() {
        return "User_Name";
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getPassword() {
        return "Password";
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return "Email";
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBabyName() {
        return "Baby_Name";
    }

    public void setBabyName(String babyName) {
        BabyName = babyName;
    }

    public String getBaby_Age() {
        return "Baby_Age";
    }

    public void setBaby_Age(String baby_Age) {
        Baby_Age = baby_Age;
    }

    public String getBaby_image() {
        return "Baby_image";
    }

    public void setBaby_image(String baby_image) {
        Baby_image = baby_image;
    }
}
