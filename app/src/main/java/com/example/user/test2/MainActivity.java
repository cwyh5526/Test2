package com.example.user.test2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, AdapterView.OnItemSelectedListener{

    public static final String TAG ="Main Activity";
    private static final int REQUEST_CAMERA=0;
    private boolean mLogShown;
    private View mLayout;
    private Button btn_start_measure;
    private Spinner spinner_measuring_unit;
    private EditText edit_measuring_value;

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

    public void startMeasure(View view){
        //Start Button 누르면 실행됨
        CharSequence measuring_value= edit_measuring_value.getText();
        CharSequence measuring_unit = spinner_measuring_unit.getSelectedItem().toString();
        CharSequence temp_text= "Start Measuring : " +measuring_value+" "+measuring_unit;
        Toast toast = Toast.makeText(MainActivity.this,temp_text,Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);


        // Start Measuring with 값, 단위,
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = findViewById(R.id.main_layout);

        edit_measuring_value = (EditText) findViewById(R.id.edit_measuring_value);
        spinner_measuring_unit = (Spinner) findViewById(R.id.sipnner_measuring_unit);
        btn_start_measure = (Button)findViewById(R.id.btn_start_measure);

        if(savedInstanceState==null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            CameraPreviewFragment fragment = new CameraPreviewFragment();
//            RuntimePermissionsFragment fragment = new RuntimePermissionsFragment();
            transaction.replace(R.id.camera_fragment, fragment);
            transaction.commit();
        }

    /*Spinner*/
        //Create an Array Adapter using the string array and a default spinner layout
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.measuring_unit,android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        spinner_measuring_unit.setAdapter(adapter);
    /*Button*/
        btn_start_measure = (Button) findViewById(R.id.btn_start_measure);
    }
    @Override
    public void onPause(){

        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        showCameraPreview();
    }

    @Override
    public  void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Spinner Item 선택 되었을 때.
        //선택된 단위를 받아서 넘겨줘야 함.
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // 아무것도 선택 안되었을 때.
    }


}
