package com.example.project01;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class BodyPic extends Activity {

    private TextView textViewHead1;
    private TextView textViewHead2;
    private TextView textViewShoulder1;
    private TextView textViewShoulder2;
    private TextView textViewChest;
    private TextView textViewBack;
    private TextView textViewStomach;
    private TextView textViewArm1;
    private TextView textViewArm2;
    private TextView textViewHand1;
    private TextView textViewHand2;
    private TextView textViewFeet1;
    private TextView textViewFeet2;

    private Button headButton1;
    private Button headButton2;
    private Button shoulderButton1;
    private Button shoulderButton2;
    private Button chestButton;
    private Button backButton;
    private Button stomachButton;
    private Button armButton1;
    private Button armButton2;
    private Button armButton3;
    private Button armButton4;
    private Button handButton1;
    private Button handButton2;
    private Button handButton3;
    private Button handButton4;
    private Button feetButton1;
    private Button feetButton2;
    private Button feetButton3;
    private Button feetButton4;

    private TextToSpeech speaker; //speaks out the body part

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bodypic_layout);

        initiateWidgets();
        initiateListeners();
        initiateSpeaker();
    }

    //initiates the widgets that will be used
    private void initiateWidgets()
    {
        textViewHead1 = (TextView)findViewById(R.id.itextViewHead1);
        textViewHead2 = (TextView)findViewById(R.id.itextViewHead2);
        textViewShoulder1 = (TextView)findViewById(R.id.itextViewShoulder1);
        textViewShoulder2 = (TextView)findViewById(R.id.itextViewShoulder2);
        textViewChest = (TextView)findViewById(R.id.itextViewChest);
        textViewBack = (TextView)findViewById(R.id.itextViewBack);
        textViewStomach = (TextView)findViewById(R.id.itextViewStomach);
        textViewArm1 = (TextView)findViewById(R.id.itextViewArm1);
        textViewArm2 = (TextView)findViewById(R.id.itextViewArm2);
        textViewHand1 = (TextView)findViewById(R.id.itextViewHand1);
        textViewHand2 = (TextView)findViewById(R.id.itextViewHand2);
        textViewFeet1 = (TextView)findViewById(R.id.itextViewFeet1);
        textViewFeet2 = (TextView)findViewById(R.id.itextViewFeet2);

        headButton1 = (Button)findViewById(R.id.ibuttonHead1);
        headButton2 = (Button)findViewById(R.id.ibuttonHead2);
        shoulderButton1 = (Button)findViewById(R.id.ibuttonShoulder1);
        shoulderButton2 = (Button)findViewById(R.id.ibuttonShoulder2);
        chestButton = (Button)findViewById(R.id.ibuttonChest);
        backButton = (Button)findViewById(R.id.ibuttonBack);
        stomachButton = (Button)findViewById(R.id.ibuttonStomach);
        armButton1 = (Button)findViewById(R.id.ibuttonArm1);
        armButton2 = (Button)findViewById(R.id.ibuttonArm2);
        armButton3 = (Button)findViewById(R.id.ibuttonArm3);
        armButton4 = (Button)findViewById(R.id.ibuttonArm4);
        handButton1 = (Button)findViewById(R.id.ibuttonHand1);
        handButton2 = (Button)findViewById(R.id.ibuttonHand2);
        handButton3 = (Button)findViewById(R.id.ibuttonHand3);
        handButton4 = (Button)findViewById(R.id.ibuttonHand4);
        feetButton1 = (Button)findViewById(R.id.ibuttonFeet1);
        feetButton2 = (Button)findViewById(R.id.ibuttonFeet2);
        feetButton3 = (Button)findViewById(R.id.ibuttonFeet3);
        feetButton4 = (Button)findViewById(R.id.ibuttonFeet4);
    }

    //initiates all listeners
    @SuppressLint("ClickableViewAccessibility")
    private void initiateListeners()
    {
        headButton1.setOnTouchListener(headListener);
        headButton2.setOnTouchListener(headListener);
        shoulderButton1.setOnTouchListener(shoulderListener);
        shoulderButton2.setOnTouchListener(shoulderListener);
        chestButton.setOnTouchListener(chestListener);
        backButton.setOnTouchListener(backListener);
        stomachButton.setOnTouchListener(stomachListener);
        armButton1.setOnTouchListener(armListener);
        armButton2.setOnTouchListener(armListener);
        armButton3.setOnTouchListener(armListener);
        armButton4.setOnTouchListener(armListener);
        handButton1.setOnTouchListener(handListener);
        handButton2.setOnTouchListener(handListener);
        handButton3.setOnTouchListener(handListener);
        handButton4.setOnTouchListener(handListener);
        feetButton1.setOnTouchListener(feetListener);
        feetButton2.setOnTouchListener(feetListener);
        feetButton3.setOnTouchListener(feetListener);
        feetButton4.setOnTouchListener(feetListener);
    }

    //initiates the speaker that will read out the text
    private void initiateSpeaker()
    {
        speaker = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    speaker.setLanguage(Locale.US);
                }
            }
        });
    }

    //listener for the head buttons
    // will show the text for head and say head
    private View.OnTouchListener headListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewHead1.setVisibility(View.VISIBLE);
                    textViewHead2.setVisibility(View.VISIBLE);
                    speaker.speak("Head", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewHead1.setVisibility(View.INVISIBLE);
                    textViewHead2.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the shoulder buttons
    // will show the text for shoulder and say shoulder
    private View.OnTouchListener shoulderListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewShoulder1.setVisibility(View.VISIBLE);
                    textViewShoulder2.setVisibility(View.VISIBLE);
                    speaker.speak("Shoulder", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewShoulder1.setVisibility(View.INVISIBLE);
                    textViewShoulder2.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };
    //listener for the chest button
    // will show the text for chest and say chest
    private View.OnTouchListener chestListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewChest.setVisibility(View.VISIBLE);
                    speaker.speak("Chest", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewChest.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the stomach button
    // will show the text for stomach and say stomach
    private View.OnTouchListener stomachListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewStomach.setVisibility(View.VISIBLE);
                    speaker.speak("Stomach", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewStomach.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the back button
    // will show the text for back and say back
    private View.OnTouchListener backListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewBack.setVisibility(View.VISIBLE);
                    speaker.speak("Back", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewBack.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the arm buttons
    // will show the text for arm and say arm
    private View.OnTouchListener armListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewArm1.setVisibility(View.VISIBLE);
                    textViewArm2.setVisibility(View.VISIBLE);
                    speaker.speak("Arm", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewArm1.setVisibility(View.INVISIBLE);
                    textViewArm2.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the hand buttons
    // will show the text for hand and say hand
    private View.OnTouchListener handListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewHand1.setVisibility(View.VISIBLE);
                    textViewHand2.setVisibility(View.VISIBLE);
                    speaker.speak("Hand", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewHand1.setVisibility(View.INVISIBLE);
                    textViewHand2.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the feet buttons
    // will show the text for feet and say feet
    private View.OnTouchListener feetListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewFeet1.setVisibility(View.VISIBLE);
                    textViewFeet2.setVisibility(View.VISIBLE);
                    speaker.speak("Feet", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewFeet1.setVisibility(View.INVISIBLE);
                    textViewFeet2.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //button action sends user to label page
    public void nextBtn(View view)
    {
        Intent intent = new Intent(getApplicationContext(), LabelPic.class);
        startActivity(intent);
    }

    //DOES NOTHING
    //button action sends user to main page
    public void mainBtn(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MenuList.class);
        startActivity(intent);
    }

    //button action sends user to head page
    public void prevBtn(View view)
    {
        Intent intent = new Intent(getApplicationContext(), HeadPic.class);
        startActivity(intent);
    }
}
