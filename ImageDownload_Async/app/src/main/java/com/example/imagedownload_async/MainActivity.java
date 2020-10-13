package com.example.imagedownload_async;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
private String string_url = "";
private static Context context;
private static EditText etUrl = null;
private static ImageView iView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context = getApplicationContext();

        Button btnDownload = (Button) findViewById(R.id.btnDownload);
        etUrl = (EditText) findViewById(R.id.et_url);
        iView = (ImageView) findViewById(R.id.iv_image);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string_url = etUrl.getText().toString();
                new DownloadImageTask().execute(string_url);
            }
        });
    };

    public static Context getAppcontext()   {
        return MainActivity.context;
    }

    public static ImageView getiView(){
        return iView;
    }

    public static EditText getEtUrl()   {
        return etUrl;
    }
}