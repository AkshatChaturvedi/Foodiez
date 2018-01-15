package com.example.draksingh.foodiez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button signup;
    Button login;
    Button direct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.Signup);
        //direct=(Button)findViewById(R.id.direct);
        getContentResolver().delete(TempOrders.CONTENT_URI,null,null) ;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i1);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(i2);
            }
        });
        /*direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(MainActivity.this,confirmation.class);
                startActivity(i2);
            }
        });
*/

    }
}
