package com.example.maries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class custom_view_rate extends BaseAdapter {
    ListView li;
    String [] rid,rate,date,userinfo;
    private Context context;

    public custom_view_rate(Context applicationContext, String[] rid, String[] rate, String[] date, String[] userinfo) {
        this.context = applicationContext;
        this.rid = rid;
        this.rate = rate;
        this.date = date;
        this.userinfo = userinfo;
    }


    @Override
    public int getCount() {
        return rate.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_rate, null);//same class name

        } else {
            gridView = (View) view;

        }
//        TextView tv1 = (TextView) gridView.findViewById(R.id.ratingBar2);
        RatingBar r1 =(RatingBar) gridView.findViewById(R.id.ratingBar2);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView25);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView27);
//        TextView tv4 = (TextView) gridView.findViewById(R.id.textView68);

//        r1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
//        tv4.setTextColor(Color.BLACK);

//        tv1.setText(rate[i]);
//        tv2.setText(review[i]);
        tv2.setText(date[i]);
        tv3.setText(userinfo[i]);

        return gridView;
    }
}
