package com.example.draksingh.foodiez;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView login_reg;
    TextView login_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        View view=getWindow().getDecorView();
        //jo activity hai uska view hai
        login_reg=(TextView)  findViewById(R.id.e_reg);
        login_pass=(TextView)  findViewById(R.id.e_password);


    }
    public void proceedToHome (View v)
    {   String lreg[] =new String[]{login_reg.getText().toString() };
        //Toast.makeText(getBaseContext(), lreg[0], Toast.LENGTH_SHORT).show();
        String s="-1";

       Cursor c = getContentResolver().query(TableUsers.CONTENT_URI, null,"_reg = ?",lreg ,null) ;
        if(c!=null&&c.getCount()!=0) {
            c.moveToFirst();
            s="";
            s = s + c.getString(c.getColumnIndex(TableUsers.PASSWORD));

        }
        if(login_reg.getText().toString().equals(""))
            Toast.makeText(getBaseContext(),"Enter Registration number", Toast.LENGTH_SHORT).show();
        else if(login_pass.getText().toString().equals(""))
            Toast.makeText(getBaseContext(),"Enter Password", Toast.LENGTH_SHORT).show();

        else if(s.equals(login_pass.getText().toString())) {
            Intent i = new Intent(this, HomePage.class);
            i.putExtra("REGISTRATION",login_reg.getText().toString());
            startActivity(i);
        }
        else
            Toast.makeText(getBaseContext(),"Invalid Registration Number or Password", Toast.LENGTH_SHORT).show();
    }




}
