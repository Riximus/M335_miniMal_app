package com.example.m335_minimal_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_newCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_newCanvas = findViewById(R.id.btn_newCanvas);

        ButtonNewCanvas();
    }

    private void ButtonNewCanvas (){
        btn_newCanvas.setOnClickListener(view -> {
            switchActivity();
        });
    }

    private void switchActivity(){
        Intent switchActivity = new Intent(this, PaintActivity.class);
        startActivity(switchActivity);
    }
}