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

public class LabelPic extends Activity {

    private TextView textViewNameAddr; //allows for control of TextViews
    private TextView textViewPrescNum;
    private TextView textViewPatName;
    private TextView textViewDirections;
    private TextView textViewMedication;
    private TextView textViewQuantity;
    private TextView textViewRefills;
    private TextView textViewPrescName;
    private TextView textViewDateOfFill;
    private TextView textViewAuxLabel;

    private Button nameAddrButton; //allows to know when user presses body part
    private Button prescNumButton;
    private Button patNameButton;
    private Button directionsButton;
    private Button medicationButton;
    private Button quantityButton;
    private Button refillsButton;
    private Button prescNameButton;
    private Button dateOfFillButton;
    private Button auxLabelButton;

    private TextToSpeech speaker; //speaks out the body part

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.labelpic_layout);

        initiateWidgets();
        initiateListeners();
        initiateSpeaker();
    }

    //initiates the widgets that will be used
    private void initiateWidgets()
    {
        textViewNameAddr = (TextView)findViewById(R.id.itextViewNameAddr);
        textViewPrescNum = (TextView)findViewById(R.id.itextViewPrescNum);
        textViewPatName = (TextView)findViewById(R.id.itextViewPatName);
        textViewDirections = (TextView)findViewById(R.id.itextViewDirections);
        textViewMedication = (TextView)findViewById(R.id.itextViewMedication);
        textViewQuantity = (TextView)findViewById(R.id.itextViewQuantity);
        textViewRefills = (TextView)findViewById(R.id.itextViewRefills);
        textViewPrescName = (TextView)findViewById(R.id.itextViewPrescName);
        textViewDateOfFill = (TextView)findViewById(R.id.itextViewDateOfFill);
        textViewAuxLabel = (TextView)findViewById(R.id.itextViewAuxLabel);

        nameAddrButton = (Button)findViewById(R.id.ibuttonNameAddr);
        prescNumButton = (Button)findViewById(R.id.ibuttonPrescNum);
        patNameButton = (Button)findViewById(R.id.ibuttonPatName);
        directionsButton = (Button)findViewById(R.id.ibuttonDirections);
        medicationButton = (Button)findViewById(R.id.ibuttonMedication);
        quantityButton = (Button)findViewById(R.id.ibuttonQuantity);
        refillsButton = (Button)findViewById(R.id.ibuttonRefills);
        prescNameButton = (Button)findViewById(R.id.ibuttonPrescName);
        dateOfFillButton = (Button)findViewById(R.id.ibuttonDateOfFill);
        auxLabelButton = (Button)findViewById(R.id.ibuttonAuxLabel);
    }

    //initiates all listeners
    @SuppressLint("ClickableViewAccessibility")
    private void initiateListeners()
    {
        nameAddrButton.setOnTouchListener(nameAddrListener);
        prescNumButton.setOnTouchListener(prescNumListener);
        patNameButton.setOnTouchListener(patNameListener);
        directionsButton.setOnTouchListener(directionsListener);
        medicationButton.setOnTouchListener(medicationListener);
        quantityButton.setOnTouchListener(quantityListener);
        refillsButton.setOnTouchListener(refillsListener);
        prescNameButton.setOnTouchListener(prescNameListener);
        dateOfFillButton.setOnTouchListener(dateOfFillListener);
        auxLabelButton.setOnTouchListener(auxLabelListener);
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

    //listener for the nameAddr button
    // will show the text for nameAddr and say nameAddr text
    private View.OnTouchListener nameAddrListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewNameAddr.setVisibility(View.VISIBLE);
                    speaker.speak("Name and address of the pharmacy", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewNameAddr.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the prescName button
    // will show the text for prescName and say prescName text
    private View.OnTouchListener prescNameListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewPrescName.setVisibility(View.VISIBLE);
                    speaker.speak("Prescriber\'s name", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewPrescName.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the prescNum button
    // will show the text for prescNum and say prescNum text
    private View.OnTouchListener prescNumListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewPrescNum.setVisibility(View.VISIBLE);
                    speaker.speak("Prescription number", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewPrescNum.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the dateOfFill button
    // will show the text for dateOfFill and say dateOfFill text
    private View.OnTouchListener dateOfFillListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewDateOfFill.setVisibility(View.VISIBLE);
                    speaker.speak("Date of filling", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewDateOfFill.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the auxLabel button
    // will show the text for auxLabel and say auxLabel text
    private View.OnTouchListener auxLabelListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewAuxLabel.setVisibility(View.VISIBLE);
                    speaker.speak("Auxiliary labels", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewAuxLabel.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the patName button
    // will show the text for patName and say patName text
    private View.OnTouchListener patNameListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewPatName.setVisibility(View.VISIBLE);
                    speaker.speak("Patient\'s name", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewPatName.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the directions button
    // will show the text for directions and say directions text
    private View.OnTouchListener directionsListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewDirections.setVisibility(View.VISIBLE);
                    speaker.speak("Directions for use", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewDirections.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the medication button
    // will show the text for medication and say medication text
    private View.OnTouchListener medicationListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewMedication.setVisibility(View.VISIBLE);
                    speaker.speak("Medication name, strength, and dosage form", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewMedication.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the quantity button
    // will show the text for quantity and say quantity text
    private View.OnTouchListener quantityListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewQuantity.setVisibility(View.VISIBLE);
                    speaker.speak("Quantity dispensed", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewQuantity.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //listener for the refills button
    // will show the text for refills and say refills text
    private View.OnTouchListener refillsListener = new View.OnTouchListener(){
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    textViewRefills.setVisibility(View.VISIBLE);
                    speaker.speak("Refills left", TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    textViewRefills.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    //button action sends user to head page
    public void nextBtn(View view)
    {
        Intent intent = new Intent(getApplicationContext(), HeadPic.class);
        startActivity(intent);
    }

    //DOES NOTHING
    //button action sends user to main page
    public void mainBtn(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MenuList.class);
        startActivity(intent);
    }

    //button action sends user to body page
    public void prevBtn(View view)
    {
        Intent intent = new Intent(getApplicationContext(), BodyPic.class);
        startActivity(intent);
    }
}
