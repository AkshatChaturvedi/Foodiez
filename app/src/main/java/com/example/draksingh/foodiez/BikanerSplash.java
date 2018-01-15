package com.example.draksingh.foodiez;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BikanerSplash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    String regi="";
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikaner_splash);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {

                Intent i = new Intent(BikanerSplash.this, BikanerMenu.class);

                i.putExtra("REGISTRATION",regi);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}