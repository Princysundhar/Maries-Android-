package com.example.maries;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

public class view_servicetype_subcategory extends AppCompatActivity {
    String[] sub_id,sub_category_name;
    String[] cid,ct;
    Spinner sp;
    Button b1;
    ListView li;
    SharedPreferences sh;

    String url,typeid,url1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_servicetype_subcategory);
        sp = findViewById(R.id.spinner);
        li = findViewById(R.id.listview);
        b1 = findViewById(R.id.button5);

//
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ipaddress", "");
        url = sh.getString("url", "") + "and_view_category";

//        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        sh.getString("ipaddress","");
        url1 = sh.getString("url","")+"android_view_servicetype_subcategory";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//view service code8
                                JSONArray js = jsonObj.getJSONArray("data");//from python
                                cid = new String[js.length()];
                                ct = new String[js.length()];


                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    cid[i] = u.getString("cid");//dbcolumn name
                                    ct[i] = u.getString("ctype");


                                }
                                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, ct);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp.setAdapter(adapter);

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

//                params.put("lid", sh.getString("lid",""));//passing to python
//                params.put("cid", sh.getString("cid",""));//passing to python

//                Toast.makeText(Add_academic_peformence.this, "akid", Toast.LENGTH_SHORT).show();//passing to python
//                params.put("sub_id", subid);//passing to python


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


       sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               typeid=cid[i];
//               Toast.makeText(view_servicetype_subcategory.this, ""+typeid, Toast.LENGTH_SHORT).show();
               RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
               StringRequest postRequest1 = new StringRequest(Request.Method.POST, url1,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                               try {
                                   JSONObject jsonObj = new JSONObject(response);
                                   if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                       li.setVisibility(View.VISIBLE);

                                       JSONArray js = jsonObj.getJSONArray("data");//from python
                                       sub_id = new String[js.length()];
                                       sub_category_name = new String[js.length()];


                                       for (int i = 0; i < js.length(); i++) {
                                           JSONObject u = js.getJSONObject(i);
                                           sub_id[i] = u.getString("sub_id"); // dbcolumn name in double quotes
                                           sub_category_name[i] = u.getString("sub_category_name");

                                       }
//                                       Toast.makeText(view_servicetype_subcategory.this, "yessss", Toast.LENGTH_SHORT).show();
                                       li.setAdapter(new custom_view_servicetype_subcategory(getApplicationContext(), sub_id,sub_category_name));//custom_view_service.xml and li is the listview object


                                   } else {
//                                       Toast.makeText(view_servicetype_subcategory.this, "nooo", Toast.LENGTH_SHORT).show();
                                       li.setVisibility(View.INVISIBLE);
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
                       params.put("type_id", typeid);//passing to python
                       params.put("sid", sh.getString("spid",""));//passing to python

                       return params;
                   }
               };


               int MY_SOCKET_TIMEOUT_MS1 = 100000;

               postRequest1.setRetryPolicy(new DefaultRetryPolicy(
                       MY_SOCKET_TIMEOUT_MS1,
                       DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                       DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
               requestQueue1.add(postRequest1);

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

            }
        });


           }


}
