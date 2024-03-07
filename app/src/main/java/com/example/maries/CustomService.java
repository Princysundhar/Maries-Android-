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

public class CustomService extends BaseAdapter {

    private Context context;
    String [] id,descrption,price,photo;
    SharedPreferences sh;
    public CustomService(Context context, String[] descrption, String[] price, String[] photo ,String[] id ) {

        this.context=context;
        this.descrption=descrption;
        this.id=id;
        this.price=price;
        this.photo=photo;

    }
//
//    public CustomService(Context applicationContext, String[] id, String[] descrption, String[] price, String[] photo) {
//    }


    @Override
    public int getCount() {
        return photo.length;
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
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_service,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView43);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView44);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView3);
        Button b1=(Button) gridView.findViewById(R.id.button7);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("sid",id[i]);
                ed.commit();
                Intent i=new Intent(context, ViewService.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        Button b2=(Button) gridView.findViewById(R.id.button8);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("sid",id[i]);
                ed.commit();
                Intent i=new Intent(context, ViewService.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


        tv1.setTextColor(Color.BLACK);

//
        tv1.setText(descrption[i]);
        tv2.setText(price[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ipadres","");

        String url="http://" + ip + ":8000"+photo[i]+".jpg";


        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);


        return gridView;



    }
}