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

public class CustomeServiceType extends BaseAdapter {
    private Context context;
    String[] servicetype,id;

    public CustomeServiceType(Context applicationContext, String[] servicetype, String[] id) {
        this.context=applicationContext;
        this.servicetype=servicetype;
        this.id=id;
    }


    @Override
    public int getCount() {
        return id.length;
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
                gridView=inflator.inflate(R.layout.activity_custome_service_type,null);

            }
            else
            {
                gridView=(View)view;

            }
            TextView tv1=(TextView)gridView.findViewById(R.id.textView3);
            Button b1=(Button) gridView.findViewById(R.id.button5);
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
//            ImageView im=(ImageView) gridView.findVie//wById(R.id.imageView10);

            tv1.setTextColor(Color.BLACK);


            tv1.setText(servicetype[i]);


//            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//            String ip=sh.getString("ip","");
//
//            String url="http://" + ip + ":5000/static/game/"+gamecode[i]+".jpg";
//
//
//            Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

            return gridView;

        }
}