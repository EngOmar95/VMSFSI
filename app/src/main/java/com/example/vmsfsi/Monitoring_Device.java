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
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class Monitoring_Device extends AppCompatActivity {

   // public static final String API_KEY = "47164624";
    Sessions sessions ;
 //  public static final String API_KEY = "47172024";
    SharedPreferences sp;
   // public  String SESSION_ID   ;
   // public String TOKEN  ;
 //   public static final String SESSION_ID = "2_MX40NzE2NDYyNH5-MTYxNjE0OTM2Mzk1Mn4rNTVibDVCOUtYUVVydEwvSE44YlhlRnp-fg";
   // public static final String TOKEN = "T1==cGFydG5lcl9pZD00NzE2NDYyNCZzaWc9OWIzMTRhZWI2ZDRiNzM3OWYyYTI2ZTUyZjYxZDA1NTE5NGJjN2Q2ZjpzZXNzaW9uX2lkPTJfTVg0ME56RTJORFl5Tkg1LU1UWXhOakUwT1RNMk16azFNbjRyTlRWaWJEVkNPVXRZVVZWeWRFd3ZTRTQ0WWxobFJucC1mZyZjcmVhdGVfdGltZT0xNjE2MTQ5MzgxJm5vbmNlPTAuNTAwNzYxMDI3NDg4MTY4OSZyb2xlPXB1Ymxpc2hlciZleHBpcmVfdGltZT0xNjE4NzQxMzgxJmluaXRpYWxfbGF5b3V0X2NsYXNzX2xpc3Q9";
    private static final int PERMISSIONS_REQUEST_CODE = 124;
    private Session session;

    private FrameLayout publisherViewContainer;
    private Publisher publisher;
          Dailog dialogProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring__device);
        publisherViewContainer = findViewById(R.id.publisher_container);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
          sessions =new Sessions(sp.getString("SESSION_ID", "0"),sp.getString("TOKEN", "0")) ;
       // SESSION_ID = sp.getString("SESSION_ID", "0");
        //TOKEN = sp.getString("TOKEN", "0");
        dialogProgressBar =new Dailog();

        dialogProgressBar.show(getSupportFragmentManager(),"");
        requestPermissions();
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


            publisher = new Publisher.Builder(Monitoring_Device.this).build();
            publisher.setPublisherListener(publisherListener);
            publisher.getRenderer().setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE, BaseVideoRenderer.STYLE_VIDEO_FILL);
                                dialogProgressBar.dismiss();
            publisherViewContainer.addView(publisher.getView());

          //  if (publisher.getView() instanceof GLSurfaceView) {
            //    ((GLSurfaceView) publisher.getView()).setZOrderOnTop(false);
            //}

            session.publish(publisher);
        }

        @Override
        public void onDisconnected(Session session) {
            Toast.makeText(Monitoring_Device.this, "onDisconnected session", Toast.LENGTH_SHORT).show();

        }


        @Override
        public void onStreamReceived(Session session, Stream stream) {
            Toast.makeText(Monitoring_Device.this, "onStreamReceived session", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onStreamDropped(Session session, Stream stream) {

        }


        @Override
        public void onError(Session session, OpentokError opentokError) {

        }
    };


    private PublisherKit.PublisherListener publisherListener = new PublisherKit.PublisherListener() {
        @Override
        public void onStreamCreated(PublisherKit publisherKit, Stream stream) {

        }

        @Override
        public void onStreamDestroyed(PublisherKit publisherKit, Stream stream) {

        }

        @Override
        public void onError(PublisherKit publisherKit, OpentokError opentokError) {

        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (session != null) {
            session.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (session != null) {
            session.onResume();
        }
    }


}