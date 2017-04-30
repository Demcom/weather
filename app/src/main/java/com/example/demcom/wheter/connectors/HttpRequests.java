package com.example.demcom.wheter.connectors;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.demcom.wheter.common.Constants;
import com.example.demcom.wheter.interfaces.HtttpRequestCallback;

/**
 * Created by Demcom on 4/29/2017.
 */
public class HttpRequests {

    private static HttpRequests ourInstance = new HttpRequests();

    public static HttpRequests getInstance() {
        return ourInstance;
    }

    private HttpRequests() {
    }

    public void getWeather(Context context, final HtttpRequestCallback callback){

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.WEATHER_API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.HttpCallback(response, null);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.HttpCallback("", error.getMessage());
            }
        });
        queue.add(stringRequest);

    }
}
