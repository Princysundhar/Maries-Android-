package com.example.maries;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class user_view_profile extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7;
    ImageView i1;
    String url;
    SharedPreferences sh;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_profile);

        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"android_view_profile";
        t1 = findViewById(R.id.textView27);
        t2 = findViewById(R.id.textView30);
        t3 = findViewById(R.id.textView32);
        t4 = findViewById(R.id.textView34);
        t5 = findViewById(R.id.textView36);
        t6 = findViewById(R.id.textView38);
        t7 = findViewById(R.id.textView41);
        i1 = findViewById(R.id.imageView2);


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                          Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                t1.setText(jsonObj.getString("name"));
                                t2.setText(jsonObj.getString("gender"));
                                t3.setText(jsonObj.getString("place"));
                                t4.setText(jsonObj.getString("post"));
                                t5.setText(jsonObj.getString("pin"));
                                t6.setText(jsonObj.getString("contact"));
                                t7.setText(jsonObj.getString("email"));


                                String ip=sh.getString("ip","");

                                String url="http://" + ip + ":8000"+ jsonObj.getString("photo");

                                Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()). into(i1);



                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
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
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                params.put("lid",sh.getString("lid",""));

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }
}