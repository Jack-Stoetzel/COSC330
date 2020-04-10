package com.example.project01;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;

public class WordSearchActivity extends Activity {

    private TableLayout table; //allows us to set up the listener on every box
    private TextView textViewAppointment; //allows to change the TextViews background
    private TextView textViewBreathe;
    private TextView textViewCheckup;
    private TextView textViewCopay;
    private TextView textViewCough;
    private TextView textViewEmergency;
    private TextView textViewInsurance;
    private TextView textViewInterpreter;
    private TextView textViewPatient;
    private TextView iWin; //allows us to choose when to display that they won
    private SoundPool soundPool; //to hold the sounds

    //hash maps
    private HashMap<Integer, Integer> map = new HashMap<>(); //holds whether they have found a value
    private HashMap<Integer, Integer> mapSelected = new HashMap<>(); //holds which boxes they have selected

    //where every correct box is that needs to be selected
    //appointment=0,breathe=1,checkup=2,copay=3,cough=4,emergency=5,insurance=6,interpreter=7
    private int[][] correctBoxes = {
            {R.id.itextView11_4, R.id.itextView11_5, R.id.itextView11_6, R.id.itextView11_7, R.id.itextView11_8,
                    R.id.itextView11_9, R.id.itextView11_10, R.id.itextView11_11, R.id.itextView11_12,
                    R.id.itextView11_13, R.id.itextView11_14},
            {R.id.itextView3_2, R.id.itextView4_2, R.id.itextView5_2, R.id.itextView6_2, R.id.itextView7_2,
                    R.id.itextView8_2, R.id.itextView9_2},
            {R.id.itextView8_4, R.id.itextView8_5, R.id.itextView8_6, R.id.itextView8_7, R.id.itextView8_8,
                    R.id.itextView8_9, R.id.itextView8_10},
            {R.id.itextView10_6, R.id.itextView10_7, R.id.itextView10_8, R.id.itextView10_9, R.id.itextView10_10},
            {R.id.itextView4_5, R.id.itextView5_5, R.id.itextView6_5, R.id.itextView7_5, R.id.itextView8_5},
            {R.id.itextView5_13, R.id.itextView6_13, R.id.itextView7_13, R.id.itextView8_13, R.id.itextView9_13,
                    R.id.itextView10_13, R.id.itextView11_13, R.id.itextView12_13, R.id.itextView13_13},
            {R.id.itextView5_9, R.id.itextView6_9, R.id.itextView7_9, R.id.itextView8_9, R.id.itextView9_9,
                    R.id.itextView10_9, R.id.itextView11_9, R.id.itextView12_9, R.id.itextView13_9},
            {R.id.itextView2_12, R.id.itextView3_12, R.id.itextView4_12, R.id.itextView5_12, R.id.itextView6_12,
                    R.id.itextView7_12, R.id.itextView8_12, R.id.itextView9_12, R.id.itextView10_12,
                    R.id.itextView11_12, R.id.itextView12_12},
            {R.id.itextView5_14, R.id.itextView6_14, R.id.itextView7_14, R.id.itextView8_14, R.id.itextView9_14,
                    R.id.itextView10_14, R.id.itextView11_14}};

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordsearch_puzzle);

        configureSounds();
        initiateWidgets();
        initiateListeners();
        initiateMap();
    }

    //sets every correct answer to 0 as they have not been found yet
    public void initiateMap() {
        map.put(R.id.itextViewAppointment, 0);
        map.put(R.id.itextViewBreathe, 0);
        map.put(R.id.itextViewCheckup, 0);
        map.put(R.id.itextViewCopay, 0);
        map.put(R.id.itextViewCough, 0);
        map.put(R.id.itextViewEmergency, 0);
        map.put(R.id.itextViewInsurance, 0);
        map.put(R.id.itextViewInterpreter, 0);
        map.put(R.id.itextViewPatient, 0);
    }

    //sets up the SoundPool that will be used
    private void configureSounds() {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundPool.load(this, R.raw.icorrectsound, 1);
        soundPool.load(this, R.raw.ivictorysound, 1);
    }

    //initiates the widgets that will be used
    private void initiateWidgets() {

        table = findViewById(R.id.itableLayout1);
        iWin = findViewById(R.id.iWin);

        textViewAppointment = findViewById(R.id.itextViewAppointment);
        textViewBreathe = findViewById(R.id.itextViewBreathe);
        textViewCheckup = findViewById(R.id.itextViewCheckup);
        textViewCopay = findViewById(R.id.itextViewCopay);
        textViewCough = findViewById(R.id.itextViewCough);
        textViewEmergency = findViewById(R.id.itextViewEmergency);
        textViewInsurance = findViewById(R.id.itextViewInsurance);
        textViewInterpreter = findViewById(R.id.itextViewInterpreter);
        textViewPatient = findViewById(R.id.itextViewPatient);
    }

    //initiates all TextView boxes with a listener
    public void initiateListeners()
    {
        for(int i = 0; i < 13; i++)
        {
            for(int j = 0; j < 15; j++)
            {
                (((TableRow)table.getChildAt(i)).getChildAt(j)).setOnClickListener(boxNumberListener);
            }
        }
    }

    //resets the current game to the original state
    public void resetBtn(View view) {
        setContentView(R.layout.wordsearch_puzzle);

        configureSounds();
        initiateWidgets();
        initiateListeners();
        initiateMap();
    }

    //button action sends user back to crossword page
    public void toCrossWordBtn(View view) {
        Intent intent = new Intent(getApplicationContext(), com.example.project01.CrosswordActivity.class);
        startActivity(intent);
    }

    //clear selected keys because they click the button "clear selected"
    public void clearSelection(View view)
    {
        Integer[] selectKeys = mapSelected.keySet().toArray(new Integer[mapSelected.size()]);
        for (Integer selectKey : selectKeys) {
            findViewById(selectKey).setBackgroundDrawable(getResources().getDrawable(R.drawable.iborder2));
            mapSelected.remove(selectKey);
        }
        fillInColors();
    }

    //listener meant for the boxes in the TableLayout
    // adds the selected box to the selected hash, checks if selected values are correct, then fills in colors
    private View.OnClickListener boxNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            mapSelected.put(v.getId(), 1);
            checkSelectedCorrect();
            fillInColors();
            fillInSelected();
        }
    };

    //checks keys and returns if they are all the same,
    // if there are not the same amount of key in both returns false immediately
    private boolean checkKeys(int[] keys)
    {
        if(keys.length != mapSelected.size())
        {
            return false;
        }
        for (int key : keys) {
            if (!mapSelected.containsKey(key)) {
                return false;
            }
        }
        clearSelectionCorrect();
        return true;
    }

    //clear selected keys because they were correct
    public void clearSelectionCorrect()
    {
        Integer[] selectKeys = mapSelected.keySet().toArray(new Integer[mapSelected.size()]);
        for (Integer selectKey : selectKeys) {
            mapSelected.remove(selectKey);
        }
    }

    //sets whether the correct answer was inputted, so that fillInColors() works
    private void checkSelectedCorrect() {
        if (map.get(R.id.itextViewAppointment) != 1 && checkKeys(correctBoxes[0])){
            map.put(R.id.itextViewAppointment, 1);
            soundPool.play(1, 1, 1, 1, 0, 1.0f);
        }
        else if (map.get(R.id.itextViewBreathe) != 1 && checkKeys(correctBoxes[1])) {
            map.put(R.id.itextViewBreathe, 1);
            soundPool.play(1, 1, 1, 1, 0, 1.0f);
        }
        else if (map.get(R.id.itextViewCheckup) != 1 && checkKeys(correctBoxes[2])) {
            map.put(R.id.itextViewCheckup, 1);
            soundPool.play(1, 1, 1, 1, 0, 1.0f);
        }
        else if (map.get(R.id.itextViewCopay) != 1 && checkKeys(correctBoxes[3])) {
            map.put(R.id.itextViewCopay, 1);
            soundPool.play(1, 1, 1, 1, 0, 1.0f);
        }
        else if (map.get(R.id.itextViewCough) != 1 && checkKeys(correctBoxes[4])) {
            map.put(R.id.itextViewCough, 1);
            soundPool.play(1, 1, 1, 1, 0, 1.0f);
        }
        else if (map.get(R.id.itextViewEmergency) != 1 && checkKeys(correctBoxes[5])) {
            map.put(R.id.itextViewEmergency, 1);
            soundPool.play(1, 1, 1, 1, 0, 1.0f);
        }
        else if (map.get(R.id.itextViewInsurance) != 1 && checkKeys(correctBoxes[6])) {
            map.put(R.id.itextViewInsurance, 1);
            soundPool.play(1, 1, 1, 1, 0, 1.0f);
        }
        else if (map.get(R.id.itextViewInterpreter) != 1 && checkKeys(correctBoxes[7])) {
            map.put(R.id.itextViewInterpreter, 1);
            soundPool.play(1, 1, 1, 1, 0, 1.0f);
        }
        else if(map.get(R.id.itextViewPatient) != 1 && checkKeys(correctBoxes[8]))
        {
            map.put(R.id.itextViewPatient, 1);
            soundPool.play(1, 1, 1, 1, 0, 1.0f);
        }
        hasWon();
    }

    //fills in the selected boxes
    private void fillInSelected()
    {
        Integer[] selectKeys = mapSelected.keySet().toArray(new Integer[mapSelected.size()]);
        for (Integer selectKey : selectKeys) {
            findViewById(selectKey).setBackgroundDrawable(getResources().getDrawable(R.color.iSelected));
        }
    }

    //fills in the colors for each box
    private void fillInColors() {
        if(map.get(R.id.itextViewAppointment) == 1)
        {
            textViewAppointment.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            findViewById(R.id.itextView11_4).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //A
            findViewById(R.id.itextView11_5).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //P
            findViewById(R.id.itextView11_6).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //P
            findViewById(R.id.itextView11_7).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //O
            findViewById(R.id.itextView11_8).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //I
            findViewById(R.id.itextView11_9).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //N
            findViewById(R.id.itextView11_10).setBackgroundColor(getResources().getColor(R.color.iCorrect));//T
            findViewById(R.id.itextView11_11).setBackgroundColor(getResources().getColor(R.color.iCorrect));//M
            findViewById(R.id.itextView11_12).setBackgroundColor(getResources().getColor(R.color.iCorrect));//E
            findViewById(R.id.itextView11_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));//N
            findViewById(R.id.itextView11_14).setBackgroundColor(getResources().getColor(R.color.iCorrect));//T
        }
        if (map.get(R.id.itextViewBreathe) == 1) {
            textViewBreathe.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            findViewById(R.id.itextView3_2).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //B
            findViewById(R.id.itextView4_2).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //R
            findViewById(R.id.itextView5_2).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //E
            findViewById(R.id.itextView6_2).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //A
            findViewById(R.id.itextView7_2).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //T
            findViewById(R.id.itextView8_2).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //H
            findViewById(R.id.itextView9_2).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //E
        }
        if (map.get(R.id.itextViewCheckup) == 1) {
            textViewCheckup.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            findViewById(R.id.itextView8_4).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //C
            findViewById(R.id.itextView8_5).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //H
            findViewById(R.id.itextView8_6).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //E
            findViewById(R.id.itextView8_7).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //C
            findViewById(R.id.itextView8_8).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //K
            findViewById(R.id.itextView8_9).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //U
            findViewById(R.id.itextView8_10).setBackgroundColor(getResources().getColor(R.color.iCorrect));//P
        }
        if (map.get(R.id.itextViewCopay) == 1) {
            textViewCopay.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            findViewById(R.id.itextView10_6).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //C
            findViewById(R.id.itextView10_7).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //O
            findViewById(R.id.itextView10_8).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //P
            findViewById(R.id.itextView10_9).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //A
            findViewById(R.id.itextView10_10).setBackgroundColor(getResources().getColor(R.color.iCorrect));//Y
        }
        if (map.get(R.id.itextViewCough) == 1) {
            textViewCough.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            findViewById(R.id.itextView4_5).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //C
            findViewById(R.id.itextView5_5).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //O
            findViewById(R.id.itextView6_5).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //U
            findViewById(R.id.itextView7_5).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //G
            findViewById(R.id.itextView8_5).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //H
        }
        if (map.get(R.id.itextViewEmergency) == 1) {
            textViewEmergency.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            findViewById(R.id.itextView5_13).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //E
            findViewById(R.id.itextView6_13).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //M
            findViewById(R.id.itextView7_13).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //E
            findViewById(R.id.itextView8_13).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //R
            findViewById(R.id.itextView9_13).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //G
            findViewById(R.id.itextView10_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));//E
            findViewById(R.id.itextView11_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));//N
            findViewById(R.id.itextView12_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));//C
            findViewById(R.id.itextView13_13).setBackgroundColor(getResources().getColor(R.color.iCorrect));//Y
        }
        if (map.get(R.id.itextViewInsurance) == 1) {
            textViewInsurance.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            findViewById(R.id.itextView5_9).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //I
            findViewById(R.id.itextView6_9).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //N
            findViewById(R.id.itextView7_9).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //S
            findViewById(R.id.itextView8_9).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //U
            findViewById(R.id.itextView9_9).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //R
            findViewById(R.id.itextView10_9).setBackgroundColor(getResources().getColor(R.color.iCorrect));//A
            findViewById(R.id.itextView11_9).setBackgroundColor(getResources().getColor(R.color.iCorrect));//N
            findViewById(R.id.itextView12_9).setBackgroundColor(getResources().getColor(R.color.iCorrect));//C
            findViewById(R.id.itextView13_9).setBackgroundColor(getResources().getColor(R.color.iCorrect));//E
        }
        if (map.get(R.id.itextViewInterpreter) == 1) {
            textViewInterpreter.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            findViewById(R.id.itextView2_12).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //I
            findViewById(R.id.itextView3_12).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //N
            findViewById(R.id.itextView4_12).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //T
            findViewById(R.id.itextView5_12).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //E
            findViewById(R.id.itextView6_12).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //R
            findViewById(R.id.itextView7_12).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //P
            findViewById(R.id.itextView8_12).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //R
            findViewById(R.id.itextView9_12).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //E
            findViewById(R.id.itextView10_12).setBackgroundColor(getResources().getColor(R.color.iCorrect));//T
            findViewById(R.id.itextView11_12).setBackgroundColor(getResources().getColor(R.color.iCorrect));//E
            findViewById(R.id.itextView12_12).setBackgroundColor(getResources().getColor(R.color.iCorrect));//R
        }
        if (map.get(R.id.itextViewPatient) == 1) {
            textViewPatient.setBackgroundColor(getResources().getColor(R.color.iCorrect));
            findViewById(R.id.itextView5_14).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //P
            findViewById(R.id.itextView6_14).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //A
            findViewById(R.id.itextView7_14).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //T
            findViewById(R.id.itextView8_14).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //I
            findViewById(R.id.itextView9_14).setBackgroundColor(getResources().getColor(R.color.iCorrect)); //E
            findViewById(R.id.itextView10_14).setBackgroundColor(getResources().getColor(R.color.iCorrect));//N
            findViewById(R.id.itextView11_14).setBackgroundColor(getResources().getColor(R.color.iCorrect));//T
        }
    }

    //checks if they have won yet
    private void hasWon()
    {
        if(map.get(R.id.itextViewAppointment) == 1 && map.get(R.id.itextViewBreathe) == 1 &&
                map.get(R.id.itextViewCheckup) == 1 &&map.get(R.id.itextViewCopay) == 1 &&
                map.get(R.id.itextViewCough) == 1 && map.get(R.id.itextViewEmergency) == 1 &&
                map.get(R.id.itextViewInsurance) == 1 && map.get(R.id.itextViewInterpreter) == 1 &&
                map.get(R.id.itextViewPatient) == 1 && iWin.getVisibility() != View.VISIBLE)
        {
            iWin.setVisibility(View.VISIBLE);
            soundPool.play(2,1,1,1,0,1.0f);
            fillInColors();
        }
    }

}