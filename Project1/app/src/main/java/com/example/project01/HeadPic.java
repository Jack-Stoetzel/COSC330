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

public class HeadPic extends Activity {

    private TextView textViewEar; //allows for control of TextViews
    private TextView textViewEye;
    private TextView textViewNose;
    private TextView textViewMouth;
    private TextView textViewNeck;

    private Button earButton; //allows to know when user presses body part
    private Button earButton2;
    private Button eyeButton;
    private Button noseButton;
    private Button mouthButton;
    private Button neckButton;

    private TextToSpeech speaker; //speaks out the body part

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.headpic_layout);

        initiateWidgets();
        initiateListeners();
        initiateSpeaker();
    }

    //initiates the widgets that will be used
    private void initiateWidgets()
    {
        textViewEar = findViewById(R.id.itextViewEar);
        textViewEye = findViewById(R.id.itextViewEye);
        textViewNose = findViewById(R.id.itextViewNose);
        textViewMouth = findViewById(R.id.itextViewMouth);
        textViewNeck = findViewById(R.id.itextViewNeck);

        earButton = findViewById(R.id.ibuttonEar);
        earButton2 = findViewById(R.id.ibuttonEar2);
        eyeButton = findViewById(R.id.ibuttonEye);
        noseButton = findViewById(R.id.ibuttonNose);
        mouthButton = findViewById(R.id.ibuttonMouth);
        neckButton = findViewById(R.id.ibuttonNeck);
    }

    //initiates all listeners
    @SuppressLint("ClickableViewAccessibility")
    private void initiateListeners()
    {
        earButton.setOnTouchListener(earListener);
        earButton2.setOnTouchListener(earListener);
        eyeButton.setOnTouchListener(eyeListener);
        noseButton.setOnTouchListener(noseListener);
        mouthButton.setOnTouchListener(mouthListener);
        neckButton.setOnTouchListener(neckListener);
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

    //listener for the ear buttons
    // will show the text for ear and say ear
    private View.OnTouchListener earListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewEar.setVisibility(View.VISIBLE);
                    speaker.speak("Ear", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewEar.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the eye buttons
    // will show the text for eye and say eye
    private View.OnTouchListener eyeListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewEye.setVisibility(View.VISIBLE);
                    speaker.speak("Eye", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewEye.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the nose buttons
    // will show the text for nose and say nose
    private View.OnTouchListener noseListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewNose.setVisibility(View.VISIBLE);
                    speaker.speak("Nose", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewNose.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the mouth buttons
    // will show the text for mouth and say mouth
    private View.OnTouchListener mouthListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewMouth.setVisibility(View.VISIBLE);
                    speaker.speak("Mouth", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewMouth.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the neck buttons
    // will show the text for neck and say neck
    private View.OnTouchListener neckListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewNeck.setVisibility(View.VISIBLE);
                    speaker.speak("Neck", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewNeck.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //button action sends user to body page
    public void nextBtn(View view)
    {
        Intent intent = new Intent(getApplicationContext(), BodyPic.class);
        startActivity(intent);
    }

    //DOES NOTHING
    //button action sends user to main page
    public void mainBtn(View view)
    {
        //Intent intent = new Intent(getApplicationContext(), .class);
        //startActivity(intent);
    }

    //button action sends user to body page
    public void prevBtn(View view)
    {
        Intent intent = new Intent(getApplicationContext(), BodyPic.class);
        startActivity(intent);
    }
}
