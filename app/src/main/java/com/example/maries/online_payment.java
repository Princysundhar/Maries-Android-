package com.example.maries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class online_payment extends AppCompatActivity {
    EditText e1,e2,e3;
    TextView t1;
    SharedPreferences sh;
    String url;
    Button b1;
    String amount="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_payment);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1 = findViewById(R.id.editTextTextPersonName8);
        e2 = findViewById(R.id.editTextTextPersonName9);
        e3 = findViewById(R.id.editTextTextPersonName10);
        t1 = findViewById(R.id.textView64);
        b1 = findViewById(R.id.button17);
        t1.setText(sh.getString("amount",""));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bank_name = e1.getText().toString();
                String IFSC_Code = e2.getText().toString();
                String account_no = e3.getText().toString();
                String amount = sh.getString("amount","");
                int flag =0;
                if(bank_name.equalsIgnoreCase("")){
                    e1.setError("Enter your bank name");
                    flag++;
                }
                if(IFSC_Code.equalsIgnoreCase("")){
                    e2.setError("Enter your IFSC code");
                    flag++;
                }
                if(account_no.equalsIgnoreCase("")){
                    e3.setError("Enter your accountnumber");
                    flag++;
                }
                if(flag == 0) {


                    sh.getString("ipaddress", "");
                    url = sh.getString("url", "") + "android_online_payment";

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                    try {
                                        JSONObject jsonObj = new JSONObject(response);
                                        if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                            Toast.makeText(online_payment.this, "payment via online", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), Home.class);
                                            startActivity(i);
                                        }

                                        if (jsonObj.getString("status").equalsIgnoreCase("insuff")) {
                                            Toast.makeText(online_payment.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                                            Intent i =new Intent(getApplicationContext(),Home.class);
                                            startActivity(i);
                                        } else if (jsonObj.getString("status").equalsIgnoreCase("no")) {
                                            Toast.makeText(online_payment.this, "wrong bank details", Toast.LENGTH_SHORT).show();
                                            Intent i =new Intent(getApplicationContext(),Home.class);
                                            startActivity(i);
                                        }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Inefficient balance", Toast.LENGTH_LONG).show();
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
                            params.put("lid", sh.getString("lid", ""));//passing to python
                            params.put("rid", sh.getString("rid", ""));//passing to python
                            params.put("spid", sh.getString("spid", ""));//passing to python

                            params.put("bank_name", bank_name);
                            params.put("account_no", account_no);
                            params.put("ifsc_code", IFSC_Code);
                            params.put("amount", amount);

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
            }
        });
    }
}