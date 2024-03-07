package com.example.maries;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

public class custom_view_service_cart extends BaseAdapter {
    String [] cart_id,subcategory,description,photo,price,req_date,serviceprovider_info;
    private Context context;
    SharedPreferences sh;
    String url;

    public custom_view_service_cart(Context applicationContext, String[] cart_id, String[] subcategory, String[] description, String[] photo, String[] price, String[] req_date, String[] serviceprovider_info) {
        this.context = applicationContext;
        this.cart_id = cart_id;
        this.subcategory = subcategory;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.req_date = req_date;
        this.serviceprovider_info = serviceprovider_info;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_service_cart, null);//same class name

        } else {
            gridView = (View) view;

        }

        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView6);
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView29);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView39);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView41);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView44);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView46);
//        TextView tv4 = (TextView) gridView.findViewById(R.id.textView37);
        Button b1 = (Button) gridView.findViewById(R.id.button13);
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {          // order cancel
            @Override
            public void onClick(View view) {
                final int pos=(int)view.getTag();

                sh = PreferenceManager.getDefaultSharedPreferences(context);
                sh.getString("ipaddress","");
                url = sh.getString("url","")+"android_cancel_order";

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                        Toast.makeText(context, "order cancel", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(context.getApplicationContext(),view_service_cart.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);


                                    } else {
                                        Toast.makeText(context, "Not found", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {

                    //                value Passing android to python
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("cart_id", cart_id[pos]);//passing to python



                        return params;
                    }
                };


                int MY_SOCKET_TIMEOUT_MS = 100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);
            }
        });

        tv1.setTextColor(Color.BLACK);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);


        tv1.setText(description[i]);
        tv2.setText(price[i]);
        tv3.setText(req_date[i]);
        tv4.setText(subcategory[i]);
        tv5.setText(serviceprovider_info[i]);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ipaddress", "");
        String url = "http://" + ip + ":8000" + photo[i];
        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);//circle

//
        return gridView;
    }
}