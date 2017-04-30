package com.example.demcom.wheter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.demcom.wheter.connectors.HttpRequests;
import com.example.demcom.wheter.interfaces.HtttpRequestCallback;
import com.example.demcom.wheter.models.Weather;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView_temperature);
        HttpRequests.getInstance().getWeather(this, new HtttpRequestCallback() {
            @Override
            public void HttpCallback(String response, String error) {
                if(error!=null){
                    Log.d("ERR", error);
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Weather weather = new Weather(jsonObject);
                        Log.d("HOLA", String.valueOf(weather.getTemperature()));
                    }catch (JSONException err){
                        Log.e("ERROR JSON", err.getMessage());
                    }
                }
            }
        });

    }



}
