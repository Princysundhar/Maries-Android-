package com.example.maries;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
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

public class view_nearby_service_provider extends AppCompatActivity {
    ListView li;
    String [] spid,name,address,email,phone_no,photo,lattitude,longitude;
    SharedPreferences sh;
    String url;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_nearby_service_provider);
        li = findViewById(R.id.lst);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ipaddress", "");
        url = sh.getString("url", "") + "android_view_nearby_serviceprovider";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js = jsonObj.getJSONArray("data");//from python
                                spid = new String[js.length()];
                                name = new String[js.length()];
                                address = new String[js.length()];
                                lattitude = new String[js.length()];
                                longitude = new String[js.length()];
                                email = new String[js.length()];
                                phone_no = new String[js.length()];
                                photo = new String[js.length()];


                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    spid[i] = u.getString("spid"); // dbcolumn name in double quotes
                                    name[i] = u.getString("name");
                                    address[i] = u.getString("place") + "\n" + u.getString("post") + "\n" + u.getString("pin");
                                    photo[i] = u.getString("photo");
                                    lattitude[i] = u.getString("lattitude");
                                    longitude[i] = u.getString("longitude");
                                    email[i] = u.getString("email");
                                    phone_no[i] = u.getString("phone_no");
                                    photo[i] = u.getString("photo");
                                }
                                li.setAdapter(new custom_view_nearby_serviceprovider(getApplicationContext(), spid, name, address, photo, lattitude, longitude, email, phone_no));//custom_view_service.xml and li is the listview object


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
//                params.put("id", sh.getString("lid",""));//passing to python
                params.put("lati", gpstracker.lati);
                params.put("longi", gpstracker.longi);
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

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Home.class);
        startActivity(i);
//        super.onBackPressed();
    }

}
