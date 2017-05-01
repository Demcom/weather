package com.example.demcom.wheter;

import android.os.Handler;
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

    private TextView humidityTextView;
    private TextView temperatureTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temperatureTextView = (TextView) findViewById(R.id.textView_temperature);
        humidityTextView = (TextView) findViewById(R.id.textView_humidity);
        dateTextView = (TextView) findViewById(R.id.textView_date);
        getTemperatureFromAPI();

    }

    public void getTemperatureFromAPI(){
        HttpRequests.getInstance().getWeather(this, new HtttpRequestCallback() {
            @Override
            public void HttpCallback(String response, String error) {
                if(error!=null){
                    Log.d("ERR", error);
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Weather weather = new Weather(jsonObject);
                        temperatureTextView.setText(String.valueOf(weather.getTemperature()) + " ºC");
                        humidityTextView.setText(String.valueOf(weather.getHumidity()));
                        dateTextView.setText(weather.getDate());
                        Log.d("JSON", response);
                    }catch (JSONException err){
                        Log.e("ERROR JSON", err.getMessage());
                    }
                }
                startMonitoringTemperature();
            }
        });
    }

    public void startMonitoringTemperature(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getTemperatureFromAPI();
            }
        }, secondsFromMilliseconds(10));
    }

    public int secondsFromMilliseconds(int seconds){
        return seconds * 1000;
    }

}
