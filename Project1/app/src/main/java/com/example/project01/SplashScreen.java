package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    boolean validEmail = false;
    boolean validPass = false;
    EditText emailField;
    EditText passField;
    TextView invalidE;
    TextView invalidP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        emailField  = findViewById(R.id.emailField);
        emailField.addTextChangedListener(emailChange);

        passField = findViewById(R.id.passField);
        passField.addTextChangedListener(passChange);

        invalidE = findViewById(R.id.invalidE);
        invalidP = findViewById(R.id.invalidP);
    }

    private TextWatcher emailChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String email;
            try {
                email = s.toString();
            }catch(NumberFormatException e){
                email = "";
            }
            if(email.contains("@gulls.salisbury.edu")) {
//            if(email.contains("@")) {
                validEmail = true;
                invalidE.setText(R.string.blank);
                checkInput();
            }
            else {
                invalidE.setText(R.string.invalidE);
            }
        }
    };

    private TextWatcher passChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String password;
            try {
                password = s.toString();
            }catch(NumberFormatException e){
                password = "";
            }
            if(password.equals("Pa55W0RD")) {
//            if(password.equals("P")) {
                validPass = true;
                invalidE.setText(R.string.blank);
                checkInput();
            }
            else {
                invalidE.setText(R.string.invalidP);
            }
        }
    };

    private void checkInput()
    {
        if(validEmail && validPass)
        {
            Intent intent = new Intent(getApplicationContext(), MenuList.class);
            finish();
            startActivity(intent);
        }
    }

}