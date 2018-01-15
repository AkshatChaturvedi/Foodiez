package com.example.draksingh.foodiez;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class bill extends AppCompatActivity {
    TextView fib,prb,qtb,canb,spb,nmb,gtb,oidv;
    int gtotal=0;
    String oidi="";
    String regi="";
    String phoni="";
    public static String sreg1=confirmation.sreg;


    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oidi = getIntent().getExtras().getString("OID");
        //Toast.makeText(getBaseContext(), "OIDI=="+oidi, Toast.LENGTH_LONG).show();
        for(int j=0;j<oidi.length();j++)
        {
            if(oidi.charAt(j)=='*') {
                regi=oidi.substring(j+1,oidi.length());
                oidi=oidi.substring(0,j);
                break;
            }
        }
      //  Toast.makeText(getBaseContext(), "OIDI=="+oidi + "::::REGI=="+regi, Toast.LENGTH_LONG).show();


        setContentView(R.layout.activity_bill);
        fib = (TextView) findViewById(R.id.b_fooditem);
        prb = (TextView) findViewById(R.id.b_price);
        canb = (TextView) findViewById(R.id.b_canteen);
        qtb = (TextView) findViewById(R.id.b_quant);
        nmb = (TextView) findViewById(R.id.b_name);
        spb = (TextView) findViewById(R.id.subprice);
        gtb = (TextView) findViewById(R.id.grandtotal);
        oidv=(TextView) findViewById(R.id.orderidvalue);

        Cursor c2 = getContentResolver().query(TableOrders.CONTENT_URI, null, null, null, null);

        if (c2 != null && c2.getCount() != 0) {
            c2.moveToFirst();

            do {
                if (c2.getString(c2.getColumnIndex(TableOrders.OID)).equals(oidi)) {
                    fib.setText(fib.getText().toString() + "" + c2.getString(c2.getColumnIndex(TableOrders.FOODNAME)) + "\n");
                    prb.setText(prb.getText().toString() + "" + c2.getString(c2.getColumnIndex(TableOrders.PRICE)) + "\n");
                    canb.setText(canb.getText().toString() + "" + c2.getString(c2.getColumnIndex(TableOrders.CANTEEN)) + "\n");
                    qtb.setText(qtb.getText().toString() + "" + c2.getString(c2.getColumnIndex(TableOrders.QTY)) + "\n");

                    regi=c2.getString(c2.getColumnIndex(TableOrders._REG));
                    //Toast.makeText(bill.this,regi+"***"+Integer.toString(a),Toast.LENGTH_LONG).show();
                    int price=Integer.parseInt(c2.getString(c2.getColumnIndex(TableOrders.PRICE)));
                    int qty=Integer.parseInt(c2.getString(c2.getColumnIndex(TableOrders.QTY)));

                    spb.setText(spb.getText().toString() + "" +(price*qty)+"\n");
                    gtotal=gtotal+(price*qty);

                }



            }while (c2.moveToNext()) ;
        }
        gtb.setText(""+gtotal);
        oidv.setText(oidi);


        Cursor c3=  getContentResolver().query(TableUsers.CONTENT_URI, null, null,null, null);

        if (c3 != null && c3.getCount() != 0) {
            c3.moveToFirst();

            do {

                    nmb.setText(c3.getString(c3.getColumnIndex(TableUsers.NAME)));
                   // phoni=c3.getString(c3.getColumnIndex(TableUsers.PHONE));
                                phoni=c3.getString(c3.getColumnIndex(TableUsers.PHONE));
            } while (c3.moveToNext());


        }

















        Toast.makeText(this,"Thank you for ordering with us. you will receive your order soon.",Toast.LENGTH_LONG).show();

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
           MyMessage();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
    }

    public void MyMessage(){
        String myNumber = phoni ;
        String myMsg = "Your order of "+gtotal+" has been confirmed and it will arrive soon -FooDiez";
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(myNumber,null,myMsg,null,null);

    }

    /*@Override
    public void onRequestPermissionsResult
    */
    public void backhome(View v){
        Intent i = new Intent(this,HomePage.class);
        Toast.makeText(bill.this,regi+" Back to home screen",Toast.LENGTH_SHORT).show();
        i.putExtra("REGISTRATION",regi);
        startActivity(i);
    }
}
