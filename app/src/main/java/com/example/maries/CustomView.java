package com.example.maries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomView extends BaseAdapter {
    private Context context;
    String[] date,rate;

    public CustomView(Context applicationContext, String[] date, String[] rate) {

        this.context=applicationContext;
        this.date=date;
        this.rate=rate;
    }


    @Override
    public int getCount() {
        return 0;
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
            gridView = inflator.inflate(R.layout.activity_custom_view, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView6);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView8);



        tv1.setText(date[i]);
        tv2.setText(rate[i]);




//        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
//        String ip = sh.getString("ip", "");
//
//        String url = "http://" + ip + ":5000/static/game/" + gamecode[i] + ".jpg";


//        Picasso.with(context).load(url).transform(new CircleTransform()).into(im);

        return gridView;

    }
}