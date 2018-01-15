package com.example.draksingh.foodiez;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class confirmation extends AppCompatActivity {
    TextView fi, pr, qt, can, st, nm;
    int subtotal = 0;
    String regi = "";
    int max_oid=0,oid=0;
    String oidi = "";
    public static String sreg="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        regi = getIntent().getExtras().getString("REGISTRATION");

        //Toast.makeText(getBaseContext(), regi+"inConFIRM", Toast.LENGTH_SHORT).show();
        sreg=regi;

        setContentView(R.layout.activity_confirmation);
        fi = (TextView) findViewById(R.id.fooditem);
        pr = (TextView) findViewById(R.id.prices);
        can = (TextView) findViewById(R.id.canteen);
        qt = (TextView) findViewById(R.id.quant);
        nm = (TextView) findViewById(R.id.deliveryto);
        st = (TextView) findViewById(R.id.snum);
        Cursor c2 = getContentResolver().query(TempOrders.CONTENT_URI, null, null, null, null);


        Cursor c3 = getContentResolver().query(TableUsers.CONTENT_URI, null, null, null, null);

            if (c3 != null && c3.getCount() != 0) {
                c3.moveToFirst();

                do {
                    nm.setText(c3.getString(c3.getColumnIndex(TableUsers.NAME)));

                } while (c3.moveToNext());


            //Toast.makeText(getBaseContext(), "query updated", Toast.LENGTH_SHORT).show();
            if (c2 != null && c2.getCount() != 0) {
                c2.moveToFirst();
                // String print= "";
                do {
                    fi.setText(fi.getText().toString() + "" + c2.getString(c2.getColumnIndex(TempOrders.FOODNAME)) + "\n");
                    pr.setText(pr.getText().toString() + "" + c2.getString(c2.getColumnIndex(TempOrders.PRICE)) + "\n");
                    can.setText(can.getText().toString() + "" + c2.getString(c2.getColumnIndex(TempOrders.CANTEEN)) + "\n");
                    qt.setText(qt.getText().toString() + "" + c2.getString(c2.getColumnIndex(TempOrders.QTY)) + "\n");
                    subtotal = subtotal + (Integer.parseInt(c2.getString(c2.getColumnIndex(TempOrders.PRICE))) * Integer.parseInt(c2.getString(c2.getColumnIndex(TempOrders.QTY))));

                } while (c2.moveToNext());
                //Toast.makeText(getBaseContext(), "******", Toast.LENGTH_SHORT).show();
                st.setText("" + subtotal);

                //Toast.makeText(getBaseContext(),print, Toast.LENGTH_LONG).show();


            /*p_name.setText(s);
        }

            p_name.setText(c.getString(c.getColumnIndex(TableUsers.NAME)));
            p_reg.setText("2044");
            p_mobile.setText(c.getString(c.getColumnIndex(TableUsers.PHONE)));
            p_email.setText(c.getString(c.getColumnIndex(TableUsers.EMAIL)));
            p_hostel.setText(c.getString(c.getColumnIndex(TableUsers.HOSTEL)));
            p_room.setText(c.getString(c.getColumnIndex(TableUsers.ROOM)));
            p_password.setText(c.getString(c.getColumnIndex(TableUsers.PASSWORD)));

        }

        //Toast.makeText(getBaseContext(), "query updated", Toast.LENGTH_SHORT).show();

*/


            }
        }
    }

    public void backtocanteen(View v) {
        Intent i1 = new Intent(confirmation.this, order.class);
        //Toast.makeText(getBaseContext(), "OIDI=="+oidi + "::::REGI==is being passed from other canteens"+regi, Toast.LENGTH_LONG).show();
        i1.putExtra("REGISTRATION", regi);
        startActivity(i1);
    }

    public void bill(View v) {

        Cursor c4 = getContentResolver().query(TableOrders.CONTENT_URI, null, null, null, null);
        if (c4 != null && c4.getCount() != 0) {
            c4.moveToFirst();
            String s4 = "";
            do {
                ContentValues values = new ContentValues();
                // s = s + c.getString(c.getColumnIndex(TableUsers.HOSTEL)) + "  " + c.getString(c.getColumnIndex(TableUsers._REG));
                oidi = c4.getString(c4.getColumnIndex(TableOrders.OID));
                oid=Integer.parseInt(oidi);

                if(oid>max_oid)
                    max_oid=oid;


            }while (c4.moveToNext());
        }
        max_oid++;
        oidi=Integer.toString(max_oid);



        Cursor c = getContentResolver().query(TempOrders.CONTENT_URI, null, null, null, null);
        if (c != null && c.getCount() != 0) {
            c.moveToFirst();
            String s = "";
            do {
                ContentValues values = new ContentValues();
                // s = s + c.getString(c.getColumnIndex(TableUsers.HOSTEL)) + "  " + c.getString(c.getColumnIndex(TableUsers._REG));
                values.put(TableOrders._REG, c.getString(c.getColumnIndex(TempOrders._REG)));
                values.put(TableOrders.OID, oidi);
                values.put(TableOrders.FOODNAME, c.getString(c.getColumnIndex(TempOrders.FOODNAME)));
                values.put(TableOrders.CANTEEN, c.getString(c.getColumnIndex(TempOrders.CANTEEN)));
                values.put(TableOrders.DATE, c.getString(c.getColumnIndex(TempOrders.DATE)));
                values.put(TableOrders.TIME, c.getString(c.getColumnIndex(TempOrders.TIME)));
                values.put(TableOrders.PRICE, c.getString(c.getColumnIndex(TempOrders.PRICE)));
                values.put(TableOrders.TPRICE, c.getString(c.getColumnIndex(TempOrders.TPRICE)));
                values.put(TableOrders.QTY, c.getString(c.getColumnIndex(TempOrders.QTY)));

                getContentResolver().insert(TableOrders.CONTENT_URI, values);
            } while (c.moveToNext());

            getContentResolver().delete(TempOrders.CONTENT_URI, null, null);

        }
            /*
            if(c!=null&&c.getCount()!=0) {
                c.moveToFirst();
                String s1 = "";
                do {
                    s1 = s1 + c.getString(c.getColumnIndex(TableOrders.FOODNAME)) + "  " + c.getString(c.getColumnIndex(TableOrders.QTY));
                    s1 = s1 + "\n";

                } while (c.moveToNext());
                //Toast.makeText(getBaseContext(), s1, Toast.LENGTH_SHORT).show();
            }

*/
        Intent i1 = new Intent(confirmation.this, bill.class);
        i1.putExtra("OID", oidi+"*"+regi);
        startActivity(i1);


    }
}





        // Intent i2 = new Intent(confirmation.this,bill.class);
        //startActivity(i2);

