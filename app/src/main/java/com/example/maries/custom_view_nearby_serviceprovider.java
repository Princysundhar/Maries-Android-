package com.example.maries;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_view_nearby_serviceprovider extends BaseAdapter {
    String [] spid,name,address,email,phone_no,photo,lattitude,longitude;

    private Context context;

    public custom_view_nearby_serviceprovider(Context applicationContext, String[] spid, String[] name, String[] address, String[] photo, String[] lattitude, String[] longitude, String[] email, String[] phone_no) {
        this.context = applicationContext;
        this.spid = spid;
        this.name = name;
        this.address =address;
        this.email =email;
        this.phone_no =phone_no;
        this.photo =photo;
        this.lattitude =lattitude;
        this.longitude =longitude;
    }


    @Override
    public int getCount() {
        return longitude.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.activity_custom_view_nearby_serviceprovider, null);//same class name

        } else {
            gridView = (View) view;

        }

        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView5);
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView31);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView33);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView35);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView37);


        Button b1 = (Button) gridView.findViewById(R.id.button10); // Locate
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ik=(int)view.getTag();
                String url = "http://maps.google.com/?q=" + lattitude[ik] + "," + longitude[ik];
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        Button b2 = (Button) gridView.findViewById(R.id.button11);  // view rating
        b2.setTag(i);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=(int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed = sh.edit();
                ed.putString("spid",spid[pos]);

                ed.commit();

                Intent i = new Intent(context,view_rate.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });

        Button b3 = (Button) gridView.findViewById(R.id.button12);  // service sub category
        b3.setTag(i);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=(int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed = sh.edit();
                ed.putString("spid",spid[pos]);

                ed.commit();

                Intent i = new Intent(context,view_servicetype_subcategory.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });



        tv1.setTextColor(Color.BLACK);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);


        tv1.setText(name[i]);
        tv2.setText(address[i]);
        tv3.setText(email[i]);
        tv4.setText(phone_no[i]);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ipaddress", "");
        String url = "http://" + ip + ":8000" + photo[i];
        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);//circle

//
        return gridView;
    }

}
