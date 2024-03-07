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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_view_requestsub_items extends BaseAdapter {
    String [] req_sub_id,description,photo,price,subcategory,req_date;
    private Context context;


    public custom_view_requestsub_items(Context applicationContext, String[] req_sub_id, String[] description,String [] photo, String[] price, String[] subcategory, String[] req_date) {
        this.context = applicationContext;
        this.req_sub_id = req_sub_id;
        this.description = description;
        this.photo = photo;
        this.price = price;
//        this.amount = amount;
        this.subcategory = subcategory;
        this.req_date = req_date;
    }

    @Override
    public int getCount() {
        return subcategory.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_requestsub_items, null);//same class name

        } else {
            gridView = (View) view;

        }

        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView9);
//        TextView tv1 = (TextView) gridView.findViewById(R.id.textView52);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView76);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView78);
//        TextView tv4 = (TextView) gridView.findViewById(R.id.textView80);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView82);
        TextView tv6 = (TextView) gridView.findViewById(R.id.textView84);
//        TextView tv7 = (TextView) gridView.findViewById(R.id.textView63);

//        tv1.setTextColor(Color.BLACK);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
//        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);
//        tv6.setTextColor(Color.BLACK);


//        tv1.setText(service[i]);
        tv2.setText(description[i]);
        tv3.setText(price[i]);
//        tv4.setText(amount[i]);
        tv5.setText(subcategory[i]);
        tv6.setText(req_date[i]);
//        tv7.setText(subcategory[i]);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ipaddress", "");
        String url = "http://" + ip + ":8000" + photo[i];
        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);//circle

//
        return gridView;
    }
}