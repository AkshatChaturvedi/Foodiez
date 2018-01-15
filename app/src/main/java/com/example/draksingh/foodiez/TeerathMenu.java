/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.draksingh.foodiez;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static com.example.draksingh.foodiez.TeerathFlavorAdapter.selectedString;
import static com.example.draksingh.foodiez.TeerathFlavorAdapter.selectedStrings;

/**
 * {@link MainActivity} shows a list of Android platform releases.
 * For each release, display the name, version number, and image.
 */
public class TeerathMenu extends AppCompatActivity {
    TeerathFlavorAdapter flavorAdapter;
    //ArrayList<String> car;
    String regi="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_design);
        regi = getIntent().getExtras().getString("REGISTRATION");


        // Create an ArrayList of TeerathFlavor objects
        ArrayList<TeerathFlavor> teerathFlavors = new ArrayList<TeerathFlavor>();

       /* for(int i=selectedStrings.size()-1;i>=0;i--)
        {

                    selectedStrings.remove(i) ;
                    selectedString.remove(i) ;



            i=selectedStrings.size() ;
        }
        selectedStrings.clear();
        selectedString.clear();
        */
        teerathFlavors.add(new TeerathFlavor("Samosa", "Rs.5", R.drawable.r_samosa));
        teerathFlavors.add(new TeerathFlavor("Aloo Parathe", "Rs.40", R.drawable.r_aloo_parathe));
        teerathFlavors.add(new TeerathFlavor("Aloo Pyaaz Parathe", "Rs.40", R.drawable.r_aloo_pyaaz));
        teerathFlavors.add(new TeerathFlavor("Paneer Parathe", "Rs.40", R.drawable.r_paneer_parathe));
        teerathFlavors.add(new TeerathFlavor("Bhurjhi Parathe", "Rs.40", R.drawable.r_bhurjhi_parathe));
        teerathFlavors.add(new TeerathFlavor("Hot Dog", "Rs.20", R.drawable.r_hot_dog));
        teerathFlavors.add(new TeerathFlavor("Egg Hot Dog", "Rs.20", R.drawable.r_egg_hot_dog));
        teerathFlavors.add(new TeerathFlavor("Plain Maggi", "Rs.20", R.drawable.r_plain_maggi));
        teerathFlavors.add(new TeerathFlavor("Fried Maggi", "Rs.25", R.drawable.r_fried_maggi));
        teerathFlavors.add(new TeerathFlavor("Fried Maggi Half Fry", "Rs.40", R.drawable.r_maggi_half_fry));
        teerathFlavors.add(new TeerathFlavor("Burger", "Rs.20", R.drawable.r_burger));
        teerathFlavors.add(new TeerathFlavor("Cold Drink", "Rs.40", R.drawable.r_cold_drinks));
        teerathFlavors.add(new TeerathFlavor("Coffee", "Rs.15", R.drawable.r_coffee));
        teerathFlavors.add(new TeerathFlavor("Chai", "Rs.10", R.drawable.r_chai));
        // Create an {@link TeerathFlavorAdapter}, whose data source is a list of
        // {@link TeerathFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        flavorAdapter = new TeerathFlavorAdapter(this, teerathFlavors);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_flavor);
        listView.setAdapter(flavorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //  final  CheckBox tv = (CheckBox)view.findViewById(R.id.select) ;
                //  TextView tv = (TextView)view.findViewById(R.id.food_name) ;

                ;
            }
        });
    }


    public void proceedToContinue(View view) {

        //Set<Integer> in = new HashSet<>();
        //ArrayList<String> aa = new ArrayList<>() ;
        for(int i=selectedStrings.size()-1;i>=1;i--)
        {
            for(int j=i-1;j>=0;j--)
            {
                if(selectedStrings.get(i).equals(selectedStrings.get(j)))
                {
                    selectedStrings.remove(j) ;
                    selectedString.remove(j) ;
                    i-- ;
                }
            }
            //i=selectedStrings.size() ;
        }
        //Toast.makeText(getBaseContext(), ""+ selectedStrings, Toast.LENGTH_SHORT).show();
       // Toast.makeText(getBaseContext(), ""+ selectedString, Toast.LENGTH_SHORT).show();



        for(int k=0;k<selectedStrings.size();k++)
        {   ContentValues values = new ContentValues();
            values.put(TempOrders._REG,regi );
            values.put(TempOrders.CANTEEN, "TEERATH");

            if(selectedStrings.get(k).equals("0"))
            {
                //Log.i()
                values.put(TempOrders.FOODNAME, "SAMOSA");
                values.put(TempOrders.PRICE,  5);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*5);

            }
           /* if(selectedStrings.get(k).equals("1"))
            {
                values.put(TempOrders.FOODNAME, "BHAJI SAMOSA");
                values.put(TempOrders.PRICE, 20);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*20);
            }
            */
            if(selectedStrings.get(k).equals("1"))
            {
                values.put(TempOrders.FOODNAME, "ALOO PARATHA");
                values.put(TempOrders.PRICE, 40);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*40);
            }
            if(selectedStrings.get(k).equals("2"))
            {
                values.put(TempOrders.FOODNAME, "ALOO PYAZ PARATHA");
                values.put(TempOrders.PRICE, 40);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*40);
            }
            if(selectedStrings.get(k).equals("3"))
            {
                values.put(TempOrders.FOODNAME, "PANEER PARATHA");
                values.put(TempOrders.PRICE, 40);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*40);
            }
            if(selectedStrings.get(k).equals("4"))
            {
                values.put(TempOrders.FOODNAME, "BHURJHI PARATHA");
                values.put(TempOrders.PRICE, 40);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*40);
            }
            if(selectedStrings.get(k).equals("5"))
            {
                values.put(TempOrders.FOODNAME, "HOT DOG");
                values.put(TempOrders.PRICE, 20);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*20);
            }
            if(selectedStrings.get(k).equals("6"))
            {
                values.put(TempOrders.FOODNAME, "EGG HOT DOG");
                values.put(TempOrders.PRICE, 20);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*20);
            }
            if(selectedStrings.get(k).equals("7"))
            {
                values.put(TempOrders.FOODNAME, "PLAIN MAGGI");
                values.put(TempOrders.PRICE, 20);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*20);
            }
            if(selectedStrings.get(k).equals("8"))
            {
                values.put(TempOrders.FOODNAME, "FRIED MAGGI");
                values.put(TempOrders.PRICE, 25);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*25);
            }
            if(selectedStrings.get(k).equals("9"))
            {
                values.put(TempOrders.FOODNAME, "FRIED MAGGI HALF FRY");
                values.put(TempOrders.PRICE,""+ 40);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*40);;

            }


            if(selectedStrings.get(k).equals("10"))
            {
                values.put(TempOrders.FOODNAME, "PAV BHAJI");
                values.put(TempOrders.PRICE, 40);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*35);
            }
            if(selectedStrings.get(k).equals("11"))
            {
                values.put(TempOrders.FOODNAME, "COLD DRINK(600 ml)");
                values.put(TempOrders.PRICE, 40);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*40);
            }
            if(selectedStrings.get(k).equals("12"))
            {
                values.put(TempOrders.FOODNAME, "COFFEE");
                values.put(TempOrders.PRICE, 15);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*15);
            }
            if(selectedStrings.get(k).equals("13"))
            {
                values.put(TempOrders.FOODNAME, "TEA");
                values.put(TempOrders.PRICE, 10);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*10);

            }
            /*
            if(selectedStrings.get(k).equals("15"))
            {
                values.put(TempOrders.FOODNAME, "SHIKANJI");
                values.put(TempOrders.PRICE, 10);
                values.put(TempOrders.QTY, Integer.parseInt(selectedString.get(k)));
                values.put(TempOrders.TPRICE, Integer.parseInt(selectedString.get(k))*10);

            }
*/
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

            values.put(TempOrders.DATE,df.format(c.getTime()));
            values.put(TempOrders.TIME, ""+c.getTime().toString()) ;

            // Toast.makeText(getBaseContext(),df.format(c.getTime())+"**"+c.getTime(), Toast.LENGTH_SHORT).show();

            getContentResolver().insert(TempOrders.CONTENT_URI, values);

        }




        //Toast.makeText(getBaseContext(),""+ selectedString.size(), Toast.LENGTH_SHORT).show();
        // Toast.makeText(getBaseContext(), "ADDED", Toast.LENGTH_SHORT).show();
        Intent i1 = new Intent(TeerathMenu.this, confirmation.class);

        //Toast.makeText(getBaseContext(),regi+"///", Toast.LENGTH_SHORT).show();
        i1.putExtra("REGISTRATION",regi);
        startActivity(i1);
        // Toast.makeText(getBaseContext(), ""+ selectedString, Toast.LENGTH_SHORT).show();

    }
/*
    public void show(View view){

        //getContentResolver().delete(TableUsers.CONTENT_URI,"_reg = 4",null) ;
        //ContentValues values = new ContentValues() ;
        //values.put(TableUsers._REG,"48") ;
        //getContentResolver().update(TableUsers.CONTENT_URI,values,"_reg = 24",null) ;


        Cursor c = getContentResolver().query(TempOrders.CONTENT_URI,null,null, null,null) ;

        if(c!=null&&c.getCount()!=0) {
            c.moveToFirst();
            String s = "";
            do {
                s = s + c.getString(c.getColumnIndex(TempOrders.FOODNAME)) + "  " + c.getString(c.getColumnIndex(TempOrders.PRICE));
                s = s + "\n";

            } while (c.moveToNext());
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
        }


    }
*/
}
