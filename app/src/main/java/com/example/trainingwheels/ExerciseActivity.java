package com.example.trainingwheels;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class ExerciseActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        TextView exerciseName = findViewById(R.id.exerciseName);
        String exerciseString = getIntent().getStringExtra("exercise");
        exerciseName.setText(exerciseString);

        TextView repsText = findViewById(R.id.repsText);
        String repsString = getIntent().getStringExtra("reps");
        repsText.setText(repsString);

        TextView setsText = findViewById(R.id.setsText);
        String setsString = getIntent().getStringExtra("sets");
        setsText.setText(setsString);

        WebView WebView = (WebView) findViewById(R.id.webView);
        String linkString = getIntent().getStringExtra("link");

        WebView.loadUrl(linkString);
        WebView.getSettings().setJavaScriptEnabled(true);
        WebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        WebView.setWebViewClient(new WebViewClient());

    }

}