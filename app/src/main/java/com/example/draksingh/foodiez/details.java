package com.example.draksingh.foodiez;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Dr. A K Singh on 10/7/2017.
 */

public class details extends AppCompatActivity {
    String regi="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        regi = getIntent().getExtras().getString("REGISTRATION");

        //Toast.makeText(getBaseContext(), "enter to details", Toast.LENGTH_SHORT).show();
        TextView p_name=(TextView) findViewById(R.id.textView9);
        TextView p_reg=(TextView) findViewById(R.id.textView11);
        TextView p_mobile=(TextView) findViewById(R.id.textView17);
        TextView p_email=(TextView) findViewById(R.id.textView18);
        TextView p_hostel=(TextView) findViewById(R.id.textView19);
        TextView p_room=(TextView) findViewById(R.id.textView20);
        TextView p_password=(TextView) findViewById(R.id.textView21);

        String login_reg[] =new String[]{regi };
        Cursor c = getContentResolver().query(TableUsers.CONTENT_URI,null,"_reg  = ?",login_reg,null);
        //Toast.makeText(getBaseContext(), "query updated", Toast.LENGTH_SHORT).show();
        if(c!=null&&c.getCount()!=0) {
            c.moveToFirst();
            /*String s = "";
            do {
                s = s + c.getString(c.getColumnIndex(TableUsers.NAME));
                s = s + "\n";

            } while (c.moveToNext());
            //Toast.makeText(getBaseContext(), "enter  details", Toast.LENGTH_SHORT).show();
            p_name.setText(s);
        }
*/
            p_name.setText(c.getString(c.getColumnIndex(TableUsers.NAME)));
            p_reg.setText(regi);
            p_mobile.setText(c.getString(c.getColumnIndex(TableUsers.PHONE)));
            p_email.setText(c.getString(c.getColumnIndex(TableUsers.EMAIL)));
            p_hostel.setText(c.getString(c.getColumnIndex(TableUsers.HOSTEL)));
            p_room.setText(c.getString(c.getColumnIndex(TableUsers.ROOM)));
            p_password.setText(c.getString(c.getColumnIndex(TableUsers.PASSWORD)));

        }
    }
}