package com.example.demcom.wheter.models;

import android.util.Log;

import com.example.demcom.wheter.common.Constants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Demcom on 4/30/2017.
 */

public class Weather {
    private int index;
    private int temperature;
    private int humidity;
    private String date;

    public Weather(){

    }

    public Weather(JSONObject jsonObject){
        try {
            if (jsonObject.has(Constants.WEATHER_DATE)) {
                this.date = jsonObject.getString(Constants.WEATHER_DATE);
            }
            if (jsonObject.has(Constants.WEATHER_HUMIDITY)) {
                this.humidity = jsonObject.getInt(Constants.WEATHER_HUMIDITY);
            }
            if (jsonObject.has(Constants.WEATHER_INDEX)) {
                this.index = jsonObject.getInt(Constants.WEATHER_INDEX);
            }
            if (jsonObject.has(Constants.WEATHER_TEMPERATURE)) {
                this.temperature = jsonObject.getInt(Constants.WEATHER_TEMPERATURE);
            }

        }catch (JSONException err){
            Log.e("JSON ERROR", err.getMessage());
        }
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
