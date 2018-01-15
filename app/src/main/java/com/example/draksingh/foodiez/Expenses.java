package com.example.draksingh.foodiez;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Expenses extends AppCompatActivity {
    String regi="";
    TextView er,ec,et,eb;
    int btotal=0;
    int ctotal=0;
    int ttotal=0;
    int rtotal=0;
    //String cteen="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        regi = getIntent().getExtras().getString("REGISTRATION");


        er = (TextView) findViewById(R.id.rajval);
        ec = (TextView) findViewById(R.id.chechival);
        et = (TextView) findViewById(R.id.teerathval);
        eb = (TextView) findViewById(R.id.bikanerval);

        Cursor c=  getContentResolver().query(TableOrders.CONTENT_URI, null, null,null, null);

        if (c != null && c.getCount() != 0) {
            c.moveToFirst();

            do {
                if (c.getString(c.getColumnIndex(TableOrders._REG)).equals(regi)) {

                    if (c.getString(c.getColumnIndex(TableOrders.CANTEEN)).equals("RAJ"))       rtotal += Integer.parseInt(c.getString(c.getColumnIndex(TableOrders.TPRICE)));
                    if (c.getString(c.getColumnIndex(TableOrders.CANTEEN)).equals("CHECHI"))    ctotal += Integer.parseInt(c.getString(c.getColumnIndex(TableOrders.TPRICE)));
                    if (c.getString(c.getColumnIndex(TableOrders.CANTEEN)).equals("TEERATH"))   ttotal += Integer.parseInt(c.getString(c.getColumnIndex(TableOrders.TPRICE)));
                    if (c.getString(c.getColumnIndex(TableOrders.CANTEEN)).equals("BIKANER"))   btotal += Integer.parseInt(c.getString(c.getColumnIndex(TableOrders.TPRICE)));
 }
            } while (c.moveToNext());

            eb.setText(""+ Integer.toString(btotal));
            et.setText(""+ Integer.toString(ttotal));
            ec.setText(""+ Integer.toString(ctotal));
            er.setText(""+ Integer.toString(rtotal));


        }

    }
}
