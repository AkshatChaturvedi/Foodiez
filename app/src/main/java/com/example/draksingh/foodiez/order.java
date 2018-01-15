package com.example.draksingh.foodiez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dk.ui.circlelist.MatrixView;

public class order extends AppCompatActivity {

   /*RadioButton raj;
    RadioButton teerath;
    RadioButton chechi;
    RadioButton bikaner;*/
    ListView listview;
    private int[] images = new int[]{R.drawable.raj, R.drawable.chechi, R.drawable.teerath, R.drawable.bikaner};
    String regi="";

   // private String[] tt = new String[]{"A","B","C"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        regi = getIntent().getExtras().getString("REGISTRATION");
        listview = (ListView) findViewById(R.id.lv);
        listview.setAdapter(new MyAdapter());
        listview.setClipToPadding(false);
        listview.setClipChildren(false);
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                for (int i = 0; i < listview.getChildCount(); i++) {
                    listview.getChildAt(i).invalidate();
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               //Toast.makeText(getBaseContext(),""+i,Toast.LENGTH_SHORT).show();
                switch (i%4) {

                    case 0:
                        Intent i1 = new Intent(order.this, RajSplash.class);
                        i1.putExtra("REGISTRATION",regi);
                        startActivity(i1);
                        break;
                    case 1:
                        Intent i2 = new Intent(order.this, ChechiSplash.class);
                        i2.putExtra("REGISTRATION",regi);
                        startActivity(i2);
                        break;
                    case 2:
                        Intent i3 = new Intent(order.this, TeerathSplash.class);
                        i3.putExtra("REGISTRATION",regi);
                        startActivity(i3);
                        break;
                    case 3:
                        Intent i4 = new Intent(order.this, BikanerSplash.class);
                        i4.putExtra("REGISTRATION",regi);
                        startActivity(i4);
                        break;

                }
            }
        });
        /*raj=(RadioButton)findViewById(R.id.raj);

        raj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1=new Intent(order.this,RajSplash.class);
                startActivity(i1);
            }


        });

        teerath=(RadioButton)findViewById(R.id.teerath);
        teerath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i2 = new Intent(order.this, TeerathSplash.class);
                startActivity(i2);
            }


        });

        chechi=(RadioButton)findViewById(R.id.chechi);
        chechi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i3 = new Intent(order.this, ChechiSplash.class);
                startActivity(i3);
            }



        });

        bikaner=(RadioButton)findViewById(R.id.bikaner);
        bikaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i4 = new Intent(order.this, BikanerSplash.class);
                startActivity(i4);
            }



        }); */

    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 9999;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                MatrixView m = (MatrixView) LayoutInflater.from(order.this).inflate(R.layout.order, null);
                m.setParentHeight(listview.getHeight());
                convertView = m;
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
            imageView.setImageResource(images[position % images.length]);
            //TextView tv= (TextView)convertView.findViewById(R.id.text) ;
           // tv.setText(""+tt[position]);

            return convertView;

        }

    }


}
