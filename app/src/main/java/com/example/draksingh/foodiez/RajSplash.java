package com.example.draksingh.foodiez;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RajSplash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    Intent i;
    String regi="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raj_splash);
        regi = getIntent().getExtras().getString("REGISTRATION");

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {

                Intent i = new Intent(RajSplash.this, RajMenu.class);
                i.putExtra("REGISTRATION",regi);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}