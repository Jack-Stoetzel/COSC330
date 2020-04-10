package com.example.project01;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class WordDisplay extends AppCompatActivity {
    TextToSpeech speaker;
    String[] glossary;
    int index;

    int[] images = {R.drawable.appointment, R.drawable.appointment, R.drawable.checkup, R.drawable.copay,
            R.drawable.emergency, R.drawable.fever, R.drawable.fiber, R.drawable.generic, R.drawable.headache,
            R.drawable.history, R.drawable.immune, R.drawable.insurance, R.drawable.labal, R.drawable.interpreter,
            R.drawable.medicine, R.drawable.mililiter, R.drawable.nutrient, R.drawable.nutrition, R.drawable.obesity,
            R.drawable.otc, R.drawable.pharmacy, R.drawable.pharmacist, R.drawable.rx, R.drawable.pcc,
            R.drawable.recipe, R.drawable.side, R.drawable.sodium, R.drawable.sore, R.drawable.symptom,
            R.drawable.tablespoon, R.drawable.teaspoon, R.drawable.vaccine, R.drawable.warning, R.drawable.vitamins};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_display);

        Intent intent = getIntent();
        index = intent.getIntExtra("key", 0);

        glossary = getResources().getStringArray(R.array.glossary);

        ImageView img = findViewById(R.id.image);
        TextView word = findViewById(R.id.word);
        TextView pro = findViewById(R.id.pronounce);
        TextView def = findViewById(R.id.definition);

        img.setImageResource(images[index / 3]);
        word.setText(glossary[index]);
        pro.setText(glossary[index + 1]);
        def.setText(glossary[index + 2]);

        speaker=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    speaker.setLanguage(Locale.CHINA);
                    // Locale:
                    // CANADA, CANADA_FRENCH, CHINA, FRANCE, GERMANY, ITALY, JAPAN, KOREA, PRC, ROOT(?), TAIWAN, UK, US
                }
            }
        });
    }

    public void onClick(View v) {
        String toSpeak = glossary[index];

        Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();

        speaker.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
}
