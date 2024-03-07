package com.example.maries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

public class custom_view_service extends BaseAdapter {
    String [] sid,photo,description,price;
    private Context context;



    public custom_view_service(Context applicationContext, String[] sid, String[] photo, String[] description, String[] price) {
        this.context = applicationContext;
        this.sid = sid;
        this.photo = photo;
        this.description = description;
        this.price = price;

    }

    @Override
    public int getCount() {
        return description.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_service, null);//same class name

        } else {
            gridView = (View) view;

        }
        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView4);
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView19);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView21);
        Button b1 =(Button) gridView.findViewById(R.id.button7);
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {          // request
            @Override
            public void onClick(View view) {
                int pos=(int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed = sh.edit();
                ed.putString("sid",sid[pos]);

                ed.commit();

                Intent i = new Intent(context,choose_date.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        tv1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);

        tv1.setText(description[i]);
        tv2.setText(price[i]);


        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ipaddress", "");
        String url = "http://" + ip + ":8000" + photo[i];
        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);//circle


//
        return gridView;
    }
}