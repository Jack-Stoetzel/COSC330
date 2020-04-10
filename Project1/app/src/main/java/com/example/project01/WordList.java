package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WordList extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        list = findViewById(R.id.word_list);
    }

    @Override
    protected void onResume() {
        String[] temp = getResources().getStringArray(R.array.glossary);
        int size = temp.length / 3;
        String[] words = new String[size];

        size = 0;
        for (int i = 0; i < temp.length; i++) {
            if (i % 3 == 0) {
                words[size] = temp[i];
                size++;
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);

        list.setAdapter(adapter);

        super.onResume();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), WordDisplay.class);
                intent.putExtra("key", i * 3);
                startActivity(intent);

            }
        });
    }
}
