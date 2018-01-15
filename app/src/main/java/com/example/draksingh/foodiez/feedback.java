package com.example.draksingh.foodiez;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class feedback extends AppCompatActivity {
    String regi="";
    RatingBar idratingbar;
    EditText inputtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        //
        // regi = getIntent().getExtras().getString("REGISTRATION");
        idratingbar = (RatingBar) findViewById(R.id.idratingbar);
        inputtxt = (EditText) findViewById(R.id.editText10);
    }
       /* Cursor c = getContentResolver().query(TableUsers.CONTENT_URI, null, null, null, null);
        String s = "";
        if (c != null && c.getCount() != 0) {
            c.moveToFirst();
            if (c.getString(c.getColumnIndex(TableUsers._REG)).equals(regi) && c.getString(c.getColumnIndex(TableUsers.RATING)) != "") {
                do {
                    s = c.getString(c.getColumnIndex(TableUsers.RATING));


                } while (c.moveToNext());
                Toast.makeText(getBaseContext(), s + "INSERTED", Toast.LENGTH_SHORT).show();
            }

        }

        idratingbar.setRating(Float.parseFloat(s));

    }
    */
/*

        s = "";
        if(c!=null&&c.getCount()!=0) {
            c.moveToFirst();
            if (c.getString(c.getColumnIndex(TableUsers._REG)).equals(regi)&&c.getString(c.getColumnIndex(TableUsers.COMMENT)) !="")
            {
                do {
                    s = s + c.getString(c.getColumnIndex(TableUsers.COMMENT));


                } while (c.moveToNext());
                Toast.makeText(getBaseContext(), s+"INSERTED", Toast.LENGTH_SHORT).show();
            }

        }
        if(s.equals("null")) inputtxt.setText("");
        else inputtxt.setText(s);
*/



    public void rateus(View v)
    {
        RatingBar idratingbar = (RatingBar) findViewById(R.id.idratingbar);
        Float ratingNumber = idratingbar.getRating();

        String r  = String.valueOf(ratingNumber);


        String typedText = inputtxt.getText().toString();

        ContentValues values = new ContentValues() ;
        values.put(TableUsers.RATING,r) ;
       // getContentResolver().update(TableUsers.CONTENT_URI,values,"_reg = "+regi,null) ;
 /*
        values.put(TableUsers.COMMENT,typedText) ;
        getContentResolver().update(TableUsers.CONTENT_URI,values,"_reg = "+regi,null) ;
*/
        //Cursor c = getContentResolver().query(TableUsers.CONTENT_URI,null,null, null,null) ;

       /* if(c!=null&&c.getCount()!=0) {
            c.moveToFirst();
            String s = "";
            do {
                s = s + c.getString(c.getColumnIndex(TableUsers.NAME))+c.getString(c.getColumnIndex(TableUsers.RATING)) + c.getString(c.getColumnIndex(TableUsers._REG));
                s = s + "\n";

            } while (c.moveToNext());
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();}
            */


        //values.put(TableUsers.COMMENT,typedText) ;
        //values.put(TableOrders._REG, c.getString(c.getColumnIndex(TempOrders._REG)));
        //values.put(TableOrders.OID, oidi);



        Toast.makeText(this,"Your Review is received "+ r + " Stars\n" + typedText,Toast.LENGTH_LONG).show();
    }
}