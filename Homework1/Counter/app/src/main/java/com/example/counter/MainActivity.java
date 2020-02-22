package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = 0;

        TextView counterText = findViewById(R.id.counter_text);
        counterText.setText(String.valueOf(counter));
        counterText.setTextColor(getResources().getColor(R.color.colorAccent, null));
        counterText.setTextSize(64);

        Button button = findViewById(R.id.tap_button);
        button.setTextSize(48);
    }

    public void onTap(View view) {
        counter++;

        TextView counterText = findViewById(R.id.counter_text);
        counterText.setText(String.valueOf(counter));
    }
}
