package com.example.imagedownload_async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView iView = MainActivity.getiView();

    @Override
    protected Bitmap doInBackground(String... urls) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            InputStream iStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(iStream);
        } catch(Exception e)    {
            Toast.makeText(MainActivity.getAppcontext(), "Image downloaded error", Toast.LENGTH_SHORT).show();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        iView.setImageBitmap(result);
    }
}
