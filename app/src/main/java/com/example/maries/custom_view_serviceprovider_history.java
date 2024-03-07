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

public class custom_view_serviceprovider_history extends BaseAdapter {
    String [] hid,name,email,photo,contact,amount,date;
    private Context context;
    SharedPreferences sh;
    String url;

    public custom_view_serviceprovider_history(Context applicationContext, String[] hid, String[] name, String[] email, String[] contact, String[] photo, String[] amount, String[] date) {
        this.context =applicationContext;
        this.hid = hid;
        this.name = name;
        this.email = email;
        this.photo = photo;
        this.contact = contact;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public int getCount() {
        return contact.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_serviceprovider_history, null);//same class name

        } else {
            gridView = (View) view;

        }
        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView8);
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView66);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView68);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView70);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView72);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView74);

        Button b1 =(Button) gridView.findViewById(R.id.button18);
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {          // request
            @Override
            public void onClick(View view) {
                int pos=(int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed = sh.edit();
                ed.putString("hid",hid[pos]);
//                ed.putString("pname",name[pos]);

                ed.commit();

                Intent i = new Intent(context,view_requestsub_items.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        Button b2 =(Button) gridView.findViewById(R.id.button19);       // send rate
        b2.setTag(i);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=(int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed = sh.edit();
                ed.putString("hid",hid[pos]);
//                ed.putString("rid",sh.getString("rid",""));
                ed.commit();

                Intent i = new Intent(context,send_rate.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });
        Button b3 =(Button) gridView.findViewById(R.id.button20); // chat
        b3.setTag(i);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int)view.getTag();
                sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("hid",hid[pos]);
                ed.putString("pname",name[pos]);
                ed.commit();
                Intent k = new Intent(context,Chat.class);
                k.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(k);

            }
        });

        tv1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);

        tv1.setText(name[i]);
        tv2.setText(email[i]);
        tv3.setText(contact[i]);
        tv4.setText(amount[i]);
        tv5.setText(date[i]);


        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ipaddress", "");
        String url = "http://" + ip + ":8000" + photo[i];
        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);//circle


//
        return gridView;
    }
}