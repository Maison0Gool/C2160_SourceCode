package com.example.playactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;
import android.os.Bundle;
import android.view.WindowManager;

/*
Entry Point to application
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set Full Screen
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        //Set Content View to game
        setContentView(new Game(this));


    }
}
