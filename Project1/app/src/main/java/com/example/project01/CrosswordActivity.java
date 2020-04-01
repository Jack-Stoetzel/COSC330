package com.example.project01;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class CrosswordActivity extends AppCompatActivity {

    private Button across;
    private Button down;
    private TextView textViewDiabetes;
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
    private int selectedText = -1;
    private int selectedBox = -1;

    private ImageView acrossImg;
    private ImageView downImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crossword_puzzle);
        initiateWidgets();
        initiateListeners();
    }

    public void initiateWidgets() {
        across = (Button) findViewById(R.id.iButtonAcross);
        down = (Button) findViewById(R.id.iButtonDown);
        acrossImg = (ImageView) findViewById(R.id.iimageView1);
        downImg = (ImageView) findViewById(R.id.iimageView2);

        textViewDiabetes = (TextView) findViewById(R.id.itextViewDiabetes);
        textViewCalories = (TextView) findViewById(R.id.itextViewCalories);
        textViewSodium = (TextView) findViewById(R.id.itextViewSodium);
        textViewVitamins = (TextView) findViewById(R.id.itextViewVitamins);
        textViewFruit = (TextView) findViewById(R.id.itextViewFruit);
        textViewObese = (TextView) findViewById(R.id.itextViewObese);
        textViewExercise = (TextView) findViewById(R.id.itextViewExercise);
        textViewCarbohydrates = (TextView) findViewById(R.id.itextViewCarbohydrates);

        textViewNum1 = (TextView) findViewById(R.id.itextView2_3);
        textViewNum2 = (TextView) findViewById(R.id.itextView3_13);
        textViewNum3 = (TextView) findViewById(R.id.itextView4_11);
        textViewNum4 = (TextView) findViewById(R.id.itextView5_2);
        textViewNum5 = (TextView) findViewById(R.id.itextView5_6);
        textViewNum6 = (TextView) findViewById(R.id.itextView7_8);
        textViewNum7 = (TextView) findViewById(R.id.itextView8_10);
        textViewNum8 = (TextView) findViewById(R.id.itextView10_7);
    }

    public void initiateListeners()
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

    public void acrossBtn(View view) {
        downImg.setVisibility(View.INVISIBLE);
        acrossImg.setVisibility(View.VISIBLE);
        across.setBackgroundColor(getResources().getColor(R.color.iActive));
        down.setBackgroundColor(getResources().getColor(R.color.iNotActive));

    }

    public void downBtn(View view) {
        acrossImg.setVisibility(View.INVISIBLE);
        downImg.setVisibility(View.VISIBLE);
        down.setBackgroundColor(getResources().getColor(R.color.iActive));
        across.setBackgroundColor(getResources().getColor(R.color.iNotActive));
    }

    private View.OnClickListener textListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(selectedText != -1 && findViewById(selectedText).getSolidColor() != getResources().getColor(R.color.iCorrect))
            {
                findViewById(selectedText).setBackgroundColor(0x00000000);
            }
            v.setBackgroundColor(getResources().getColor(R.color.iSelected));
            selectedText=v.getId();

            if(selectedText != -1 && selectedBox != -1)
            {
                checkSelectedCorrect();
            }
        }
    };

    private View.OnClickListener boxNumberListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(selectedBox != -1 && findViewById(selectedBox).getSolidColor() != getResources().getColor(R.color.iCorrect))
            {
                findViewById(selectedBox).setBackgroundColor(0x00000000);
            }
            v.setBackgroundColor(getResources().getColor(R.color.iSelected));
            selectedBox=v.getId();
            if(selectedText != -1 && selectedBox != -1)
            {
                checkSelectedCorrect();
            }
        }
    };

    public void checkSelectedCorrect()
    {
        if(selectedText == textViewVitamins.getId() && selectedBox == textViewNum1.getId())
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
        else if(selectedText == textViewExercise.getId() && selectedBox == textViewNum2.getId())
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
        else if(selectedText == textViewCalories.getId() && selectedBox == textViewNum3.getId())
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
        else if(selectedText == textViewCarbohydrates.getId() && selectedBox == textViewNum4.getId())
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
        else if(selectedText == textViewObese.getId() && selectedBox == textViewNum5.getId())
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
        else if(selectedText == textViewSodium.getId() && selectedBox == textViewNum6.getId())
        {
            textViewCalories.setBackgroundColor(getResources().getColor(R.color.iCorrect));
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
        else if(selectedText == textViewFruit.getId() && selectedBox == textViewNum7.getId())
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
        else if(selectedText == textViewDiabetes.getId() && selectedBox == textViewNum8.getId())
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
}

