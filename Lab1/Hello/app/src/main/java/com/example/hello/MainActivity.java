package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView message = findViewById(R.id.text);
        message.setText(R.string.hello);
    }

    public void changeText(View view) {
        TextView message = findViewById(R.id.text);

        String temp = (String) message.getText();

        if(temp.equals("Hello")){
            message.setText(R.string.goodbye);
        }
        else if(temp.equals("Goodbye")){
            message.setText(R.string.hello);
        }
    }
}
