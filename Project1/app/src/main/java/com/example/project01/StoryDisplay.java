package com.example.project01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class StoryDisplay extends AppCompatActivity {
    int pos;
    String[] story;
    int[] images;
    TextView text;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_display);

        Intent intent = getIntent();
        int index = intent.getIntExtra("key", 0);

        image = findViewById(R.id.image);
        text = findViewById(R.id.text);

        if(index == 0) {
            story = getResources().getStringArray(R.array.maria_story);
            images = new int[]{R.drawable.maria_01, R.drawable.maria_02, R.drawable.maria_03, R.drawable.maria_04,
                    R.drawable.maria_05, R.drawable.maria_06, R.drawable.maria_07, R.drawable.maria_08,
                    R.drawable.maria_09, R.drawable.maria_10, R.drawable.maria_11, R.drawable.maria_12,
                    R.drawable.maria_13, R.drawable.maria_14};
        }
        else {
            story = getResources().getStringArray(R.array.tuma_story);
            images = new int[]{R.drawable.tuma_01, R.drawable.tuma_02, R.drawable.tuma_03, R.drawable.tuma_04,
                    R.drawable.tuma_05, R.drawable.tuma_06, R.drawable.tuma_07, R.drawable.tuma_08,
                    R.drawable.tuma_09};
        }

        pos = 0;

        text.setText(story[pos]);
        image.setImageResource(images[pos]);
    }

    public void backClick(View v){
        if (pos == 0) {
            pos = story.length;
        }
        pos--;

        text.setText(story[pos]);
        image.setImageResource(images[pos]);
    }

    public void nextClick(View v) {
        if(pos == story.length - 1) {
            pos = -1;
        }
        pos++;

        text.setText(story[pos]);
        image.setImageResource(images[pos]);
    }
}
