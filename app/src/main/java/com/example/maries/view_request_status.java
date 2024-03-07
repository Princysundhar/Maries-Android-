package com.example.maries;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class view_request_status extends AppCompatActivity {
    ListView li;
    TextView t1;
    Button b1;
    String [] req_id,service,req_date,date,description,price,photo,service_provider_info,subcategory;
    SharedPreferences sh;
    String url;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request_status);
        li = findViewById(R.id.listview);
        t1 = findViewById(R.id.textView50);
        b1 = findViewById(R.id.button15);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ipaddress","");
        url = sh.getString("url","")+"android_view_request_status";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                String amount = jsonObj.getString("amount");        // Getting Total amount
                                t1.setText(amount);
                                t1.setTextColor(Color.RED);


                                SharedPreferences.Editor ed = sh.edit();        // ti=0 set the amount
                                ed.putString("amount",amount);
                                ed.commit();

                                JSONArray js = jsonObj.getJSONArray("data");//from python
                                req_id = new String[js.length()];
                                req_date = new String[js.length()];
                                date = new String[js.length()];
                                description = new String[js.length()];
                                price = new String[js.length()];
                                photo = new String[js.length()];
                                service_provider_info = new String[js.length()];
                                subcategory = new String[js.length()];



                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    req_id[i] = u.getString("req_id"); // dbcolumn name in double quotes
                                    req_date[i] = u.getString("req_date");
                                    date[i] = u.getString("date");
                                    description[i] = u.getString("description");
                                    price[i] = u.getString("price");
                                    photo[i] = u.getString("photo");
                                    service_provider_info[i] = u.getString("name")+"\n"+u.getString("email");
                                    subcategory[i] = u.getString("subcategory");

                                }
                                li.setAdapter(new custom_view_request_status(getApplicationContext(),req_id,req_date,date,description,price,photo,service_provider_info,subcategory));//custom_view_service.xml and li is the listview object


                            } else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {

            //                value Passing android to python
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();
                params.put("lid", sh.getString("lid",""));//passing to python
//                params.put("req_id",sh.getString("req_id",""));


                return params;
            }
        };



        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),make_payement.class);
                startActivity(i);
            }
        });
    }
}