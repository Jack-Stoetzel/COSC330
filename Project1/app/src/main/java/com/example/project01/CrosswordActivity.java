package com.example.project01;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class CrosswordActivity extends AppCompatActivity {

    //to flip between the down and across hint
    private Button across;
    private Button down;

    private TextView textViewDiabetes; //allows to change the TextViews background and select them
    private TextView textViewCalories;
    private TextView textViewSodium;
    private TextView textViewVitamins;
    private TextView textViewFruit;
    private TextView textViewObese;
    private TextView textViewExercise;
    private TextView textViewCarbohydrates;
    private TextView textViewNum1;
    private TextView textViewNum2;
    private TextView textViewNum3;
    private TextView textViewNum4;
    private TextView textViewNum5;
    private TextView textViewNum6;
    private TextView textViewNum7;
    private TextView textViewNum8;
    private TextView iWin; //allows us to choose when to display that they won
    private SoundPool soundPool; //to hold the sounds

    //to keep track of the box and word they have selected
    private int selectedText = -1;
    private int selectedBox = -1;

    //hash map to allow us to check if they have correctly answered crossword
    private HashMap<Integer, Integer> map = new HashMap<>();

    private ImageView acrossImg; //holds the across hints
    private ImageView downImg; //holds the down hints

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crossword_puzzle);

        configureSounds();
        initiateWidgets();
        initiateListeners();
        initiateMap();
    }

    //sets every correct answer to 0 as they have not been answered yet
    public void initiateMap()
    {
        map.put(R.id.itextViewDiabetes, 0);
        map.put(R.id.itextViewCalories, 0);
        map.put(R.id.itextViewSodium, 0);
        map.put(R.id.itextViewVitamins, 0);
        map.put(R.id.itextViewFruit, 0);
        map.put(R.id.itextViewObese, 0);
        map.put(R.id.itextViewExercise, 0);
        map.put(R.id.itextViewCarbohydrates, 0);
    }

    //sets up the SoundPool that will be used
    private void configureSounds()
    {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundPool.load(this, R.raw.icorrectsound, 1);
        soundPool.load(this, R.raw.ivictorysound, 1);
    }

    //initiates the widgets that will be used
    private void initiateWidgets() {
        across = findViewById(R.id.iButtonAcross);
        down = findViewById(R.id.iButtonDown);
        acrossImg = findViewById(R.id.iimageView1);
        downImg = findViewById(R.id.iimageView2);
        iWin = findViewById(R.id.iWin);

        textViewDiabetes = findViewById(R.id.itextViewDiabetes);
        textViewCalories = findViewById(R.id.itextViewCalories);
        textViewSodium = findViewById(R.id.itextViewSodium);
        textViewVitamins = findViewById(R.id.itextViewVitamins);
        textViewFruit = findViewById(R.id.itextViewFruit);
        textViewObese = findViewById(R.id.itextViewObese);
        textViewExercise = findViewById(R.id.itextViewExercise);
        textViewCarbohydrates = findViewById(R.id.itextViewCarbohydrates);

        textViewNum1 = findViewById(R.id.itextView2_3);
        textViewNum2 = findViewById(R.id.itextView3_13);
        textViewNum3 = findViewById(R.id.itextView4_11);
        textViewNum4 = findViewById(R.id.itextView5_2);
        textViewNum5 = findViewById(R.id.itextView5_6);
        textViewNum6 = findViewById(R.id.itextView7_8);
        textViewNum7 = findViewById(R.id.itextView8_10);
        textViewNum8 = findViewById(R.id.itextView10_7);
    }

    //initiates all listeners for the TextViews
    private void initiateListeners()
    {
        textViewDiabetes.setOnClickListener(textListener);
        textViewCalories.setOnClickListener(textListener);
        textViewSodium.setOnClickListener(textListener);
        textViewVitamins.setOnClickListener(textListener);
        textViewFruit.setOnClickListener(textListener);
        textViewObese.setOnClickListener(textListener);
        textViewExercise.setOnClickListener(textListener);
        textViewCarbohydrates.setOnClickListener(textListener);

        textViewNum1.setOnClickListener(boxNumberListener);
        textViewNum2.setOnClickListener(boxNumberListener);
        textViewNum3.setOnClickListener(boxNumberListener);
        textViewNum4.setOnClickListener(boxNumberListener);
        textViewNum5.setOnClickListener(boxNumberListener);
        textViewNum6.setOnClickListener(boxNumberListener);
        textViewNum7.setOnClickListener(boxNumberListener);
        textViewNum8.setOnClickListener(boxNumberListener);
    }

    //button action when they select the across button
    public void acrossBtn(View view) {
        downImg.setVisibility(View.INVISIBLE);
        acrossImg.setVisibility(View.VISIBLE);
        across.setBackgroundColor(getResources().getColor(R.color.iActive));
        down.setBackgroundColor(getResources().getColor(R.color.iNotActive));

    }

    //button action when they select the down button
    public void downBtn(View view) {
        acrossImg.setVisibility(View.INVISIBLE);
        downImg.setVisibility(View.VISIBLE);
        down.setBackgroundColor(getResources().getColor(R.color.iActive));
        across.setBackgroundColor(getResources().getColor(R.color.iNotActive));
    }

    //resets the current game to the original state
    public void resetBtn(View view) {
        setContentView(R.layout.crossword_puzzle);

        configureSounds();
        initiateWidgets();
        initiateListeners();
        initiateMap();
    }

    //button action sends user back to word search page
    public void toWordSearchBtn(View view){
        Intent intent = new Intent(getApplicationContext(), WordSearchActivity.class);
        startActivity(intent);
    }

    //listener meant for text that gets selected at the bottom
    // set colors and changes the selectedText value to the correct TextView
    private View.OnClickListener textListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(selectedText != -1)
            {
                findViewById(selectedText).setBackgroundColor(0x00000000);
            }

            selectedText=v.getId();

            if(selectedText != -1 && selectedBox != -1)
            {
                checkSelectedCorrect();
            }

            fillInColors();
            v.setBackgroundColor(getResources().getColor(R.color.iSelected));
        }
    };

    //listener meant for boxes that gets selected in the crossword puzzle
    // set colors and changes the selectedText value to the correct TextView
    private View.OnClickListener boxNumberListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(selectedBox != -1)
            {
                findViewById(selectedBox).setBackgroundDrawable(getResources().getDrawable(R.drawable.iborder2));
            }

            selectedBox=v.getId();

            if(selectedText != -1 && selectedBox != -1)
            {
                checkSelectedCorrect();
            }

            fillInColors();
            v.setBackgroundColor(getResources().getColor(R.color.iSelected));
        }
    };

    //sets whether the correct answer was inputted, so that fillInColors() works
    private void checkSelectedCorrect()
    {
        if(map.get(R.id.itextViewVitamins) != 1 && selectedText == textViewVitamins.getId() && selectedBox == textViewNum1.getId())
        {
            map.put(R.id.itextViewVitamins, 1);
            soundPool.play(1,1,1,1,0,1.0f);
        }
        else if(map.get(R.id.itextViewExercise) != 1 && selectedText == textViewExercise.getId() && selectedBox == textViewNum2.getId())
        {
            map.put(R.id.itextViewExercise, 1);
            soundPool.play(1,1,1,1,0,1.0f);
        }
        else if(map.get(R.id.itextViewCalories) != 1 && selectedText == textViewCalories.getId() && selectedBox == textViewNum3.getId())
        {
            map.put(R.id.itextViewCalories, 1);
            soundPool.play(1,1,1,1,0,1.0f);
        }
        else if(map.get(R.id.itextViewCarbohydrates) != 1 && selectedText == textViewCarbohydrates.getId() && selectedBox == textViewNum4.getId())
        {
            map.put(R.id.itextViewCarbohydrates, 1);
            soundPool.play(1,1,1,1,0,1.0f);
        }
        else if(map.get(R.id.itextViewObese) != 1 && selectedText == textViewObese.getId() && selectedBox == textViewNum5.getId())
        {
            map.put(R.id.itextViewObese, 1);
            soundPool.play(1,1,1,1,0,1.0f);
        }
        else if(map.get(R.id.itextViewSodium) != 1 && selectedText == textViewSodium.getId() && selectedBox == textViewNum6.getId())
        {
            map.put(R.id.itextViewSodium, 1);
            soundPool.play(1,1,1,1,0,1.0f);
        }
        else if(map.get(R.id.itextViewFruit) != 1 && selectedText == textViewFruit.getId() && selectedBox == textViewNum7.getId())
        {
            map.put(R.id.itextViewFruit, 1);
            soundPool.play(1,1,1,1,0,1.0f);
        }
        else if(map.get(R.id.itextViewDiabetes) != 1 && selectedText == textViewDiabetes.getId() && selectedBox == textViewNum8.getId())
        {
            map.put(R.id.itextViewDiabetes, 1);
            soundPool.play(1,1,1,1,0,1.0f);
        }
        hasWon();
    }

    //fills in the colors for each box and their text value
    private void fillInColors()
    {
        if(map.get(R.id.itextViewVitamins) == 1)
        {
            textViewVitamins.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView2_3)).setText("V");
            findViewById(R.id.itextView2_3).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView3_3)).setText("I");
            findViewById(R.id.itextView3_3).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView4_3)).setText("T");
            findViewById(R.id.itextView4_3).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_3)).setText("A");
            findViewById(R.id.itextView5_3).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView6_3)).setText("M");
            findViewById(R.id.itextView6_3).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView7_3)).setText("I");
            findViewById(R.id.itextView7_3).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView8_3)).setText("N");
            findViewById(R.id.itextView8_3).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView9_3)).setText("S");
            findViewById(R.id.itextView9_3).setBackgroundColor(getResources().getColor(R.color.iCorrect));
        }
        if(map.get(R.id.itextViewExercise) == 1)
        {
            textViewExercise.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView3_13)).setText("E");
            findViewById(R.id.itextView3_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView4_13)).setText("X");
            findViewById(R.id.itextView4_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_13)).setText("E");
            findViewById(R.id.itextView5_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView6_13)).setText("R");
            findViewById(R.id.itextView6_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView7_13)).setText("C");
            findViewById(R.id.itextView7_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView8_13)).setText("I");
            findViewById(R.id.itextView8_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView9_13)).setText("S");
            findViewById(R.id.itextView9_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView10_13)).setText("E");
            findViewById(R.id.itextView10_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));
        }
        if(map.get(R.id.itextViewCalories) == 1)
        {
            textViewCalories.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView4_11)).setText("C");
            findViewById(R.id.itextView4_11).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_11)).setText("A");
            findViewById(R.id.itextView5_11).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView6_11)).setText("L");
            findViewById(R.id.itextView6_11).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView7_11)).setText("O");
            findViewById(R.id.itextView7_11).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView8_11)).setText("R");
            findViewById(R.id.itextView8_11).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView9_11)).setText("I");
            findViewById(R.id.itextView9_11).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView10_11)).setText("E");
            findViewById(R.id.itextView10_11).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView11_11)).setText("S");
            findViewById(R.id.itextView11_11).setBackgroundColor(getResources().getColor(R.color.iCorrect));
        }
        if(map.get(R.id.itextViewCarbohydrates) == 1)
        {
            textViewCarbohydrates.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_2)).setText("C");
            findViewById(R.id.itextView5_2).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_3)).setText("A");
            findViewById(R.id.itextView5_3).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_4)).setText("R");
            findViewById(R.id.itextView5_4).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_5)).setText("B");
            findViewById(R.id.itextView5_5).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_6)).setText("O");
            findViewById(R.id.itextView5_6).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_7)).setText("H");
            findViewById(R.id.itextView5_7).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_8)).setText("Y");
            findViewById(R.id.itextView5_8).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_9)).setText("D");
            findViewById(R.id.itextView5_9).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_10)).setText("R");
            findViewById(R.id.itextView5_10).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_11)).setText("A");
            findViewById(R.id.itextView5_11).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_12)).setText("T");
            findViewById(R.id.itextView5_12).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_13)).setText("E");
            findViewById(R.id.itextView5_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_14)).setText("S");
            findViewById(R.id.itextView5_14).setBackgroundColor(getResources().getColor(R.color.iCorrect));
        }
        if(map.get(R.id.itextViewObese) == 1)
        {
            textViewObese.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView5_6)).setText("O");
            findViewById(R.id.itextView5_6).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView6_6)).setText("B");
            findViewById(R.id.itextView6_6).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView7_6)).setText("E");
            findViewById(R.id.itextView7_6).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView8_6)).setText("S");
            findViewById(R.id.itextView8_6).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView9_6)).setText("E");
            findViewById(R.id.itextView9_6).setBackgroundColor(getResources().getColor(R.color.iCorrect));
        }
        if(map.get(R.id.itextViewSodium) == 1)
        {
            textViewSodium.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView7_8)).setText("S");
            findViewById(R.id.itextView7_8).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView8_8)).setText("O");
            findViewById(R.id.itextView8_8).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView9_8)).setText("D");
            findViewById(R.id.itextView9_8).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView10_8)).setText("I");
            findViewById(R.id.itextView10_8).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView11_8)).setText("U");
            findViewById(R.id.itextView11_8).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView12_8)).setText("M");
            findViewById(R.id.itextView12_8).setBackgroundColor(getResources().getColor(R.color.iCorrect));
        }
        if(map.get(R.id.itextViewFruit) == 1)
        {
            textViewFruit.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView8_10)).setText("F");
            findViewById(R.id.itextView8_10).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView8_11)).setText("R");
            findViewById(R.id.itextView8_11).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView8_12)).setText("U");
            findViewById(R.id.itextView8_12).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView8_13)).setText("I");
            findViewById(R.id.itextView8_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView8_14)).setText("T");
            findViewById(R.id.itextView8_14).setBackgroundColor(getResources().getColor(R.color.iCorrect));
        }
        if(map.get(R.id.itextViewDiabetes) == 1)
        {
            textViewDiabetes.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView10_7)).setText("D");
            findViewById(R.id.itextView10_7).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView10_8)).setText("I");
            findViewById(R.id.itextView10_8).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView10_9)).setText("A");
            findViewById(R.id.itextView10_9).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView10_10)).setText("B");
            findViewById(R.id.itextView10_10).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView10_11)).setText("E");
            findViewById(R.id.itextView10_11).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView10_12)).setText("T");
            findViewById(R.id.itextView10_12).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView10_13)).setText("E");
            findViewById(R.id.itextView10_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));
            ((TextView)findViewById(R.id.itextView10_14)).setText("S");
            findViewById(R.id.itextView10_14).setBackgroundColor(getResources().getColor(R.color.iCorrect));
        }
    }

    //checks if they have won yet
    private void hasWon()
    {
        if(map.get(R.id.itextViewVitamins) == 1 && map.get(R.id.itextViewExercise) == 1 &&
                map.get(R.id.itextViewCalories) == 1 &&map.get(R.id.itextViewCarbohydrates) == 1 &&
                map.get(R.id.itextViewObese) == 1 && map.get(R.id.itextViewSodium) == 1 &&
                map.get(R.id.itextViewFruit) == 1 && map.get(R.id.itextViewDiabetes) == 1 &&
                iWin.getVisibility() != View.VISIBLE)
        {
            iWin.setVisibility(View.VISIBLE);
            soundPool.play(2,1,1,1,0,1.0f);
        }
    }
}

