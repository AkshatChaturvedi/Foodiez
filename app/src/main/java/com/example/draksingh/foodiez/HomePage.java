package com.example.draksingh.foodiez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {
    //Intent i1,i2,i3;
    String regi="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        regi = getIntent().getExtras().getString("REGISTRATION");
        getContentResolver().delete(TempOrders.CONTENT_URI,null,null) ;


        }



       public void order(View v)
    {
       Intent i1 = new Intent(this,order.class);
        i1.putExtra("REGISTRATION",regi);
        startActivity(i1);
    }

    public void feedback(View v)
    {
      Intent  i2 = new Intent(this,feedback.class);
        i2.putExtra("REGISTRATION",regi);
        startActivity(i2);
    }

    public void details(View v)
    {
       Intent i3 = new Intent(this,details.class);
        i3.putExtra("REGISTRATION",regi);
        startActivity(i3);
    }
    public void about(View v)
    {
        Intent i4 = new Intent(this,about.class);

        startActivity(i4);
    }
    public void expense(View v) {
        Intent i5 = new Intent(this,Expenses.class);
        i5.putExtra("REGISTRATION",regi);

        startActivity(i5);
    }
}
