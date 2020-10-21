package com.example.musicservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private static final String TAG = "MusicService";
    MediaPlayer player;

    //Bind로 서비스를 설정할거면 밑을 채워주면 된다.
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        player = MediaPlayer.create(this, R.raw.waiting_for_love);
        player.setLooping(false);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "MusicService가 종료되었습니다.", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy()");
        player.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "MusicService가 시작되었습니다.", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy()");
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
