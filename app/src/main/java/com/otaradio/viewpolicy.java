package com.otaradio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class viewpolicy extends AppCompatActivity {
    private TextView fullscreentext;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpolicy);

        fullscreentext = findViewById(R.id.alltext);
        String text=getIntent().getStringExtra("text");
        fullscreentext.setText(text);


    }
}