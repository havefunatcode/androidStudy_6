package com.example.imagedownload;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnDownload = (Button) findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText etUrl = (EditText) findViewById(R.id.et_url);
                final ImageView iView = (ImageView) findViewById(R.id.iv_image);
                Thread t = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            URL url = new URL(etUrl.getText().toString());
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.connect();
                            InputStream iStream = urlConnection.getInputStream();
                            final Bitmap bitmap = BitmapFactory.decodeStream(iStream);
                            iView.post(new Runnable() {
                                // Main Thread UI Change
                                @Override
                                public void run() {
                                    iView.setImageBitmap(bitmap);
                                }
                            });
                        } catch (Exception e)  {
                            Toast.makeText(getBaseContext(), "Image downloaded error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                );
                t.start();
            }
        });
    }
}