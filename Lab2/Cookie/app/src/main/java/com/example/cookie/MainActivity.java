package com.example.cookie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void feed(View view) {
        ImageView image = (ImageView) findViewById(R.id.imageView3);
        image.setVisibility(View.VISIBLE);
        Button button1 = (Button) findViewById(R.id.FeedingButton);
        button1.setText(R.string.done);

    }
}
