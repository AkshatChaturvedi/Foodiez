package com.example.draksingh.foodiez;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    EditText reg_no,name,room,phone_no,email,password,repassword;
    Button sp1;
    Spinner hostel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.e("Int the","Sighnup Activity");
        reg_no=(EditText)findViewById(R.id.e_reg);
        name=(EditText)findViewById(R.id.e_name);
        hostel=(Spinner)findViewById(R.id.s_hostel);
        room=(EditText)findViewById(R.id.e_room);
        phone_no=(EditText)findViewById(R.id.e_phone);
        email=(EditText)findViewById(R.id.e_email);
        password=(EditText)findViewById(R.id.e_password);
        repassword=(EditText)findViewById(R.id.e_repassword);



    }
    /*
    public void movenext(View v)
    {
        Log.e("Going to","Chechi");
        Intent i = new Intent(this,HomePage.class);
        startActivity(i);
    }
    */
   public void createButtonClicked(View view){
 if(!AlphaCheck( name.getText().toString()))
       {
           Toast.makeText(SignupActivity.this,"Enter Correct name",Toast.LENGTH_SHORT).show();
       }
 else if(reg_no.getText().toString().equals(""))
 {
     Toast.makeText(SignupActivity.this,"Enter Registration Number",Toast.LENGTH_SHORT).show();
 }

 else if(hostel.getSelectedItem().toString().equals("HOSTEL"))
 {
     Toast.makeText(getBaseContext(), "ENTER THE HOSTEL", Toast.LENGTH_SHORT).show();
 }
 else if(room.getText().toString().equals("") || Integer.parseInt(room.getText().toString())<=0 || Integer.parseInt(room.getText().toString())>=800)
 {
     Toast.makeText(getBaseContext(), "ENTER Valid Room no.", Toast.LENGTH_SHORT).show();
 }
else if(!validEmail(email.getText().toString()))
 {
     Toast.makeText(SignupActivity.this,"Enter valid e-mail!",Toast.LENGTH_LONG).show();
 }
 else if(phone_no.getText().toString().length()!=10 || !ValidMobile(phone_no.getText().toString()))
 {
     Toast.makeText(SignupActivity.this,"Enter valid mobile number!",Toast.LENGTH_LONG).show();
 }
 else if(password.getText().toString().length()<6 || !isValidPassword(password.getText().toString())){
     Toast.makeText(SignupActivity.this,"Password must contains minimum 6 characters ,at least 1 Capital Letter,1 Number and 1 Special Character",Toast.LENGTH_SHORT).show();

 }
 else if(!password.getText().toString().equals(repassword.getText().toString())) {
     Toast.makeText(SignupActivity.this,"Password Not matching",Toast.LENGTH_SHORT).show();
 }
 else if(!uniqueNumber(reg_no.getText().toString()))
 {
     Toast.makeText(getBaseContext(), "USER ALREADY EXISTS", Toast.LENGTH_SHORT).show();
 }


 else {
     ContentValues values = new ContentValues();
     values.put(TableUsers._REG, reg_no.getText().toString());
     values.put(TableUsers.NAME, name.getText().toString());
     values.put(TableUsers.HOSTEL, hostel.getSelectedItem().toString());
     values.put(TableUsers.ROOM, room.getText().toString());
     values.put(TableUsers.PHONE, phone_no.getText().toString());
     values.put(TableUsers.EMAIL, email.getText().toString());
     values.put(TableUsers.PASSWORD, password.getText().toString());
     values.put(TableUsers.RATING,"4");
     values.put(TableUsers.COMMENT,"YOUR COMMENTS PLEASE");


     getContentResolver().insert(TableUsers.CONTENT_URI, values);


     Intent i = new Intent(this, LoginActivity.class);
     startActivity(i);

 }

    }
    public void show(View view){

        //getContentResolver().delete(TableUsers.CONTENT_URI,"_reg = 4",null) ;
        //ContentValues values = new ContentValues() ;
        //values.put(TableUsers._REG,"48") ;
        //getContentResolver().update(TableUsers.CONTENT_URI,values,"_reg = 24",null) ;


        Cursor c = getContentResolver().query(TableUsers.CONTENT_URI,null,null, null,null) ;

        if(c!=null&&c.getCount()!=0) {
            c.moveToFirst();
            String s = "";
            do {
                s = s + c.getString(c.getColumnIndex(TableUsers.PASSWORD)) + "  " + c.getString(c.getColumnIndex(TableUsers._REG));
                s = s + "\n";

            } while (c.moveToNext());
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
        }


    }

    private boolean ValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
    private boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    public boolean AlphaCheck(String name) {
        return name.matches("[a-zA-Z - ]+");
    }

   public static boolean isValidPassword(final String password) {

       Pattern pattern;
       Matcher matcher;
       final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=||S+$).{4,}$";
       pattern = Pattern.compile(PASSWORD_PATTERN);
       matcher = pattern.matcher(password);

       return matcher.matches();

   }

   private boolean uniqueNumber(String regis)
   {
       Cursor c = getContentResolver().query(TableUsers.CONTENT_URI,null,null, null,null) ;

       if(c!=null&&c.getCount()!=0) {
           c.moveToFirst();
           String s = "";
           do {
               s = s+ c.getString(c.getColumnIndex(TableUsers._REG));
               if(s.equals(regis)) {

                   return false;
               }
           } while (c.moveToNext());

       }
       return true;
   }


}
