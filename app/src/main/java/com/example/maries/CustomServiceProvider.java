package com.example.maries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class CustomServiceProvider extends BaseAdapter {
    String[] name,place,post,email,contact,photo,latitude,longitude;
    Context context;


    public CustomServiceProvider(Context applicationContext, String[] name, String[] place, String[] post, String[] email, String[] contact, String[] photo, String[] latitude, String[] longitude) {

        this.context = applicationContext;
        this.name = name;
        this.place=place;
        this.post=post;
        this.email=email;
        this.contact=contact;
        this.photo=photo;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    @Override
    public int getCount() {
        return name.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_service_provider, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView6);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView8);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView10);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView12);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView15);
        ImageView im = (ImageView) gridView.findViewById(R.id.imageView);
        Button b1= (Button)gridView.findViewById(R.id.button9);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv1.setTextColor(Color.BLACK);


        tv1.setText(name[i]);
        tv2.setText(place[i]);
        tv3.setText(post[i]);
        tv4.setText(email[i]);
        tv5.setText(contact[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url=sh.getString("url","")+photo[i];


        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);





//        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
//        String ip = sh.getString("ip", "");
//
//        String url = "http://" + ip + ":5000/static/game/" + gamecode[i] + ".jpg";


//        Picasso.with(context).load(url).transform(new CircleTransform()).into(im);

        return gridView;
    }
}