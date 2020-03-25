package com.example.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import com.google.android.gms.maps.GoogleMap;


public class MainActivity extends AppCompatActivity {

    private GoogleMap redMap;
    private Object View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayMap(android.view.View view)
    {
        Uri intentUri = Uri.parse("geo:34.055561, -117.182602");
        Intent RedMap = new Intent(Intent.ACTION_VIEW, intentUri);
        RedMap.setPackage("com.google.android.apps.maps");
        startActivity(RedMap);
    }

}
