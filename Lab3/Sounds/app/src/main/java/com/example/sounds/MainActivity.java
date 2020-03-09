package com.example.sounds;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.sounds.R.raw.bell_clang;
import static com.example.sounds.R.raw.drum;
import static com.example.sounds.R.raw.funky_gong;
import static com.example.sounds.R.raw.random_ha;
import static com.example.sounds.R.raw.spooky_cry;

public class MainActivity extends AppCompatActivity {

    Button play_btn;
    Button pause_btn;
    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, drum);

        play_btn = (Button) findViewById(R.id.button);
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 System.out.println("PLAYING");
                 mp.start();
            }
        });

        pause_btn = (Button) findViewById(R.id.button2);
        pause_btn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               System.out.println("PAUSING");
               mp.pause();
            }
        });
    }
}
