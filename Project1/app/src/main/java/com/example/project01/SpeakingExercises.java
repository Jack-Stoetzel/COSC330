package com.example.project01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class SpeakingExercises extends AppCompatActivity {

    private ListView list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking_exercise);

        list = findViewById(R.id.exercise_list);
    }

    @Override
    protected void onResume() {
        String[] items = getResources().getStringArray(R.array.exercise_List);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        list.setAdapter(adapter);

        super.onResume();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), SpeakingDisplay.class);
                intent.putExtra("key", i);
                startActivity(intent);

            }
        });
    }
}
