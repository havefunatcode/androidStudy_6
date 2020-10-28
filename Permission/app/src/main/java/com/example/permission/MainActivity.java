package com.example.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    //permission variable
     private static final int PERMISSION_REQUEST_CAMERA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_open_camera).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showCameraPreview();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_REQUEST_CAMERA)    {
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)    {
                Toast.makeText(getApplicationContext(), "권한 획득1", Toast.LENGTH_LONG).show();
                startCamera();
            } else  {
                Toast.makeText(getApplicationContext(), "권한 획득 실패", Toast.LENGTH_LONG).show();
            }
        }
    }

    //Check Permission
    private void showCameraPreview() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)   {
            //Permission approval
            Toast.makeText(getApplicationContext(), "권한 획득2", Toast.LENGTH_LONG).show();
            startCamera();
        } else  {
            //Permission denied
            requestCameraPermission();
        }
    }

    //Logic for get approval to Camera
    private void requestCameraPermission()  {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))   {
            Toast.makeText(getApplicationContext(), "이 앱은 카메라 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            //ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},
            //        PERMISSION_REQUEST_CAMERA);
        } else  {
            //예전에 권한을 허가해 주지 않음.(권한이 현재 없는 상태)
            Toast.makeText(getApplicationContext(), "권한 없음", Toast.LENGTH_LONG).show();
            //ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},
            //       PERMISSION_REQUEST_CAMERA);
        }
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CAMERA);
    }

    //Camera Start
    private void startCamera()  {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }
}
