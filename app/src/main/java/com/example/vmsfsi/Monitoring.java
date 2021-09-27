package com.example.vmsfsi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.SharedPreferences;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.opentok.android.BaseVideoRenderer;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;
import com.opentok.android.SubscriberKit;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class Monitoring extends AppCompatActivity {
    private FrameLayout subscriberViewContainer;
    private Session session;
    Sessions sessions ;
    private Subscriber subscriber;
    SharedPreferences sp;
   // public static final String API_KEY = "47164624";
  //  public static final String API_KEY = "47172024";
   // public  String SESSION_ID   ;
    //public String TOKEN  ;
  //  public static final   String SESSION_ID  = "2_MX40NzE2NDYyNH5-MTYxNjE0OTM2Mzk1Mn4rNTVibDVCOUtYUVVydEwvSE44YlhlRnp-fg";
  //  public static final String TOKEN = "T1==cGFydG5lcl9pZD00NzE2NDYyNCZzaWc9OWIzMTRhZWI2ZDRiNzM3OWYyYTI2ZTUyZjYxZDA1NTE5NGJjN2Q2ZjpzZXNzaW9uX2lkPTJfTVg0ME56RTJORFl5Tkg1LU1UWXhOakUwT1RNMk16azFNbjRyTlRWaWJEVkNPVXRZVVZWeWRFd3ZTRTQ0WWxobFJucC1mZyZjcmVhdGVfdGltZT0xNjE2MTQ5MzgxJm5vbmNlPTAuNTAwNzYxMDI3NDg4MTY4OSZyb2xlPXB1Ymxpc2hlciZleHBpcmVfdGltZT0xNjE4NzQxMzgxJmluaXRpYWxfbGF5b3V0X2NsYXNzX2xpc3Q9";
    private static final int PERMISSIONS_REQUEST_CODE = 124;
    Dailog  dialogProgressBar;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        dialogProgressBar =new Dailog();

        dialogProgressBar.show(getSupportFragmentManager(),"");
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sessions =new Sessions(sp.getString("SESSION_ID", "0"),sp.getString("TOKEN", "0")) ;
       // SESSION_ID = sp.getString("SESSION_ID", "0");
        //TOKEN = sp.getString("TOKEN", "0");


        subscriberViewContainer = findViewById(R.id.subscriber_container);
        requestPermissions();
       // initializeSession(API_KEY, SESSION_ID,  TOKEN);
    }


    @AfterPermissionGranted(PERMISSIONS_REQUEST_CODE)
    private void requestPermissions() {
        String[] perms = { Manifest.permission.INTERNET, Manifest.permission.CAMERA };
        if (EasyPermissions.hasPermissions(this, perms)) {
            // initialize and connect to the session
            initializeSession(sessions.getApiKey(), sessions.getSESSION_ID(),  sessions.getTOKEN());

        } else {
            EasyPermissions.requestPermissions(this, "This app needs access to your camera and mic to make video calls", PERMISSIONS_REQUEST_CODE, perms);
        }
    }


    private void initializeSession(String apiKey, String sessionId, String token) {


        session = new Session.Builder(this, apiKey, sessionId).build();
        session.setSessionListener(sessionListener);
        session.connect(token);
    }


    private Session.SessionListener sessionListener = new Session.SessionListener() {
        @Override
        public void onConnected(Session session) {
         //   Toast.makeText(Monitoring.this, "1", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onDisconnected(Session session) {
         //   Toast.makeText(Monitoring.this, "2", Toast.LENGTH_SHORT).show();
        }


        @Override
        public void onStreamReceived(Session session, Stream stream) {



            if (subscriber == null) {
                subscriber = new Subscriber.Builder(Monitoring.this, stream).build();
                subscriber.getRenderer().setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE, BaseVideoRenderer.STYLE_VIDEO_FILL);
                subscriber.setSubscriberListener(subscriberListener);
                session.subscribe(subscriber);
                dialogProgressBar.dismiss();
                subscriberViewContainer.addView(subscriber.getView());
            }
        }

        @Override
        public void onStreamDropped(Session session, Stream stream) {

            if (subscriber != null) {
                subscriber = null;
                subscriberViewContainer.removeAllViews();
            }
        }


        @Override
        public void onError(Session session, OpentokError opentokError) {

        }
    };

    SubscriberKit.SubscriberListener subscriberListener = new SubscriberKit.SubscriberListener() {
        @Override
        public void onConnected(SubscriberKit subscriberKit) {

        }

        @Override
        public void onDisconnected(SubscriberKit subscriberKit) {

        }

        @Override
        public void onError(SubscriberKit subscriberKit, OpentokError opentokError) {

        }
    }    ;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}