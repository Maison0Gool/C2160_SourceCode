package com.example.playactivity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Window;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

/*
Entry Point to application
 */
public class MainActivity extends AppCompatActivity {

    private Game game;
    private MediaPlayer bcgmusic;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.Java", "onCreate()");
        super.onCreate(savedInstanceState);
        //Set Full Screen
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        //Set Content View to game
        game = new Game(this);
        setContentView(game);






    }



    @Override
    protected void onStart() {
        Log.d("MainActivity.Java", "onStart()");
        super.onStart();
        // Start background music playback when the activity is in the foreground
        bcgmusic = MediaPlayer.create(this, R.raw.dramaticmusic);
        bcgmusic.setLooping(true);
        // Set the volume to maximum
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
        }

        bcgmusic.start();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity.Java", "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity.Java", "onPause()");
        game.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity.Java", "onStop()");
        super.onStop();
        if (bcgmusic != null) {
            bcgmusic.stop();
            bcgmusic.release();
            bcgmusic = null;
        }


    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity.Java", "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Log.d("MainActivity.Java", "onBackPressed()");
        super.onBackPressed();
    }
}