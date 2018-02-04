package com.example.shreemoyee.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity {

    TextView _name, _email, _response;
    AppCompatButton _sendRequest;
    ProgressBar _proProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hooking the UI views for usage
        _response = findViewById(R.id.response);
        _proProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        _sendRequest = (AppCompatButton) findViewById(R.id.send_request);

        //hooking onclick listener of button
        _sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                //this is the url where you want to send the request
                //TODO: replace with your own url to send request, as I am using my own localhost for this tutorial
                String url = "http://172.17.19.182:8000/learn/";

                url=url+"?building"+"="+"Mathematics Dept"+"&"+"user"+"="+"1"+"&"+"room"+"="+"alley"+"&"+"wifi"+"="+"[{\"rssi\": -47, \"mac\": \"80:37:73:ba:f7:d8\"}, {\"rssi\": -42, \"mac\": \"80:37:73:ba:f7:dc\"}]"+"&"+"userhandle"+"="+"gaurav";
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response string.
                                _response.setText(response);
                                Log.d("res",response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volly Error", error.toString());

                        // _response.setText("That didn't work!");
                        //Log.d("res",toString(error));

                    }
                }) {
                    //adding parameters to the request
                    @Override
                    protected Map<String, String> getParams()  {
                        Map<String, String> params = new HashMap<>();
                        params.put("building", "library");
                        params.put("room", "alley");
                        params.put("wifi", "[{\"rssi\": -47, \"mac\": \"80:37:73:ba:f7:d8\"}, {\"rssi\": -42, \"mac\": \"80:37:73:ba:f7:dc\"}]");
                        params.put("userhandle", "gaurav");

                        return params;
                    }
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/json");
                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

    }
}
