package com.example.project01;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class SpeakingDisplay extends AppCompatActivity {
    int pos;
    TextToSpeech speaker;
    String[] exercise;
    TextView dialogue;
    String[] speaking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking_display);

        Intent intent = getIntent();
        int index = intent.getIntExtra("key", 0);

        ImageView img = findViewById(R.id.image);
        dialogue = findViewById(R.id.dialogue);



        switch(index){
            case 0:
                exercise = getResources().getStringArray(R.array.exercise1);
                speaking = getResources().getStringArray(R.array.speaking1);
                img.setImageResource(R.drawable.tuma);
                break;
            case 1:
                exercise = getResources().getStringArray(R.array.exercise2);
                speaking = getResources().getStringArray(R.array.speaking2);
                img.setImageResource(R.drawable.back);
                break;
            case 2:
                exercise = getResources().getStringArray(R.array.exercise3);
                speaking = getResources().getStringArray(R.array.speaking3);
                img.setImageResource(R.drawable.chapter3);
                break;
            case 3:
                exercise = getResources().getStringArray(R.array.exercise4);
                speaking = getResources().getStringArray(R.array.speaking4);
                img.setImageResource(R.drawable.food);
                break;
        }

        dialogue.setText(exercise[0]);

        pos = -1;

        speaker=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    speaker.setLanguage(Locale.US);
                    // Locale:
                    // CANADA, CANADA_FRENCH, CHINA, FRANCE, GERMANY, ITALY, JAPAN, KOREA, PRC, ROOT(?), TAIWAN, UK, US
                }
                else {
                    System.out.println("ERROR!!! ERROR!!! ERROR!!!");
                }
            }
        });
    }

    public void backClick(View v) {
        if(pos > 0) {
            pos--;

            dialogue.setText("");
            for(int i = 0; i <= pos; i++)
            {
                dialogue.append(exercise[i] + "\n\n");
            }

            String toSpeak = speaking[pos];

            speaker.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void nextClick(View v) {
        if(pos + 1 == exercise.length || pos == -1) {
            pos = 0;
        }
        else {
            pos++;
        }

        dialogue.setText("");
        for(int i = 0; i <= pos; i++)
        {
            dialogue.append(exercise[i] + "\n\n");
        }

        String toSpeak = speaking[pos];

        speaker.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
}
