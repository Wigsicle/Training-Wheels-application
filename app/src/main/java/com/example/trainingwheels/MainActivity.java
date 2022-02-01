package com.example.trainingwheels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.sax);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you


        int SPLASH_SCREEN_TIME_OUT = 4000;
        new Handler().postDelayed(() -> {
            Intent i = new Intent(MainActivity.this, IndividualActivity.class);
            startActivity(i);
            finish();
        }, SPLASH_SCREEN_TIME_OUT);
    }
}