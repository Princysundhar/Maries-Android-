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
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_view_request_status extends BaseAdapter {
    String [] req_id,req_date,date,description,price,photo,service_provider_info,subcategory;
    private  Context context;
    SharedPreferences sh;
    String url;


    public custom_view_request_status(Context applicationContext, String[] req_id, String[] req_date, String[] date, String[] description, String[] price, String[] photo, String[] service_provider_info,String[] subcategory) {
            this.context = applicationContext;
            this.req_id = req_id;
//            this.service = service;
            this.req_date = req_date;
            this.date = date;
            this.description = description;
            this.price = price;
            this.photo = photo;
            this.service_provider_info = service_provider_info;
            this.subcategory = subcategory;
    }

    @Override
    public int getCount() {
        return service_provider_info.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_request_status, null);//same class name

        } else {
            gridView = (View) view;

        }

        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView7);
//        TextView tv1 = (TextView) gridView.findViewById(R.id.textView52);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView54);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView56);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView58);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView60);
        TextView tv6 = (TextView) gridView.findViewById(R.id.textView62);
        TextView tv7 = (TextView) gridView.findViewById(R.id.textView63);

//        tv1.setTextColor(Color.BLACK);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);


//        tv1.setText(service[i]);
        tv2.setText(req_date[i]);
        tv3.setText(date[i]);
        tv4.setText(description[i]);
        tv5.setText(price[i]);
        tv6.setText(service_provider_info[i]);
        tv7.setText(subcategory[i]);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ipaddress", "");
        String url = "http://" + ip + ":8000" + photo[i];
        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);//circle

//
        return gridView;
    }
}