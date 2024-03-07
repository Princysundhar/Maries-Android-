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
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_view_servicetype_subcategory extends BaseAdapter {
    String[] sub_id,sub_category_name;
    private Context context;

    public custom_view_servicetype_subcategory(Context applicationContext, String[] sub_id, String[] sub_category_name) {
        this.context = applicationContext;
        this.sub_id = sub_id;
        this.sub_category_name = sub_category_name;

    }


    @Override
    public int getCount() {
        return sub_category_name.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_servicetype_subcategory, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView17);
        Button b1 =(Button) gridView.findViewById(R.id.button6);
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=(int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("sub_id",sub_id[pos]);
                ed.commit();
                Intent i = new Intent(context,view_service.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });
//        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView5);
//        TextView tv2 = (TextView) gridView.findViewById(R.id.t51);
//        TextView tv3 = (TextView) gridView.findViewById(R.id.t61);


        tv1.setTextColor(Color.BLACK);//color setting
//        tv2.setTextColor(Color.BLACK);
//        tv3.setTextColor(Color.BLACK);


        tv1.setText(sub_category_name[i]);
//        tv2.setText(post[i]);
//        tv3.setText(date[i]);


//
        return gridView;
    }
}