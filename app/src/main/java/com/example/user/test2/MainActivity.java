package com.example.user.test2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    public static final String TAG ="Main Activity";
    private static final int REQUEST_CAMERA=0;
    private boolean mLogShown;
    private View mLayout;


    public void showCamera (View view){
        Log.i(TAG,"Show camera button pressed. Checking permission.");
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            requestCameraPermission();
        }else{
            Log.i(TAG,"CAMERA permission has already been granted. Displaying camera preview.");
            showCameraPreview();
        }

    }

    private void requestCameraPermission(){
        Log.i(TAG,"CAMERA permission has NOT been granted. Requesting permission.");


        // Camera permission has not been granted yet. Request it directly.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                REQUEST_CAMERA);


    }

    private void showCameraPreview() {
        CameraPreviewFragment camera_preview_fragment = new CameraPreviewFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.camera_fragment, camera_preview_fragment)
                .addToBackStack("contacts")
                .commit();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = findViewById(R.id.main_layout);

        if(savedInstanceState==null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            CameraPreviewFragment fragment = new CameraPreviewFragment();
//            RuntimePermissionsFragment fragment = new RuntimePermissionsFragment();
            transaction.replace(R.id.camera_fragment, fragment);
            transaction.commit();
        }

    }
    public void onPause(){
        super.onPause();
    }

    public  void onDestroy()
    {
        super.onDestroy();
    }

}
