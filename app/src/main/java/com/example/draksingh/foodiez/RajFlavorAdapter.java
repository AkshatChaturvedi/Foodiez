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

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/*
* {@link RajFlavorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link RajFlavor} objects.
* */
public class RajFlavorAdapter extends ArrayAdapter<RajFlavor> {

    private static final String LOG_TAG = RajFlavorAdapter.class.getSimpleName();
    public static ArrayList<String> selectedStrings = new ArrayList<String>();
    public static ArrayList<String> selectedString = new ArrayList<String>();

   ArrayList<Boolean> posA ;
    int[] a ;
    String[] item = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"} ;
     int size ;
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param androidFlavors A List of RajFlavor objects to display in a list
     */
    public RajFlavorAdapter(Activity context, ArrayList<RajFlavor> androidFlavors) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.


        super(context, 0, androidFlavors);
        size = androidFlavors.size() ;
        posA = new ArrayList<Boolean>(size) ;
        for(int i =0;i<size;i++)
            posA.add(false);

       a =new int[size] ;
        for(int i =0;i<size;i++)
          a[i]=0 ;

    }

    @Override
    public int getCount() {
        return size;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        Holder holder = null ;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            holder = new Holder() ;
            holder.tv = (CheckBox)listItemView.findViewById(R.id.select) ;
            holder.s = (Spinner)listItemView.findViewById(R.id.qtycount) ;
            listItemView.setTag(holder);
        }
        else
        {
            holder = (Holder)convertView.getTag() ;
            holder.tv.setOnCheckedChangeListener(null);
        }

        // Get the {@link RajFlavor} object located at this position in the list
        RajFlavor currentRajFlavor = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.food_name);
        // Get the version name from the current RajFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentRajFlavor.getFoodName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.price);
        // Get the version number from the current RajFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentRajFlavor.getPrice());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        // Get the image resource ID from the current RajFlavor object and
        // set the image to iconView
        iconView.setImageResource(currentRajFlavor.getImageResourceId());

        holder.s.setSelection(a[position],true);
        final Holder finalHolder = holder;
        holder.s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(finalHolder.tv.isChecked()) {


                    selectedString.remove(item[position]) ;
                    selectedStrings.remove(finalHolder.s.getSelectedItem().toString()) ;
                    selectedStrings.add(item[position]);
                    selectedString.add(finalHolder.s.getSelectedItem().toString());
                }
                Log.i("pos",""+Integer.parseInt(finalHolder.s.getSelectedItem().toString() )) ;
                a[position] =Integer.parseInt(finalHolder.s.getSelectedItem().toString())-1 ;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        holder.tv.setChecked(posA.get(position));

        finalHolder.tv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("pos",""+position) ;
                if (isChecked) {
                    posA.set(position,true) ;
                    selectedStrings.add(item[position]);
                    selectedString.add(finalHolder.s.getSelectedItem().toString()) ;
                }else{
                    posA.set(position,false) ;
                    selectedStrings.remove(item[position]);
                    selectedString.remove(finalHolder.s.getSelectedItem().toString()) ;
                }

            }
        });
       // tv.setChecked(true);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
    static class Holder
    {
        CheckBox tv ;
        Spinner s ;

    }

}
