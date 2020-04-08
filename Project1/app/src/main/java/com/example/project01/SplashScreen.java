package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class SplashScreen extends AppCompatActivity {
    boolean validEmail = false;
    boolean validPass = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        EditText emailField = findViewById(R.id.emailField);
        emailField.addTextChangedListener(emailChange);

        EditText passField = findViewById(R.id.passField);
        passField.addTextChangedListener(passChange);


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
            if(email.contains("@gulls.salisbury.edu"))
            {
                validEmail = true;
                checkInput();
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
            if(password.equals("Pa55W0RD"))
            {
                validPass = true;
                checkInput();
            }
        }
    };

    private void checkInput()
    {
        if(validEmail && validPass)
        {
            Intent intent = new Intent(getApplicationContext(), MenuList.class);
            startActivity(intent);
        }
    }

}