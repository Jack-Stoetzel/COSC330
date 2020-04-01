package com.example.project01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class WordDisplay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_display);

        Intent intent = getIntent();
        int i = intent.getIntExtra("key", 0);

        String[] glossary = getResources().getStringArray(R.array.glossary);

        ImageView img = findViewById(R.id.image);
        TextView word = findViewById(R.id.word);
        TextView pro = findViewById(R.id.pronounce);
        TextView def = findViewById(R.id.definition);

        //img.setImageDrawable(R.drawable.glossary[i]);
        word.setText(glossary[i]);
        pro.setText(glossary[i + 1]);
        def.setText(glossary[i + 2]);
    }
}
