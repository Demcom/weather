package com.example.demcom.wheter;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.demcom.wheter.common.Constants;
import com.example.demcom.wheter.connectors.HttpRequests;
import com.example.demcom.wheter.dialogs.ResultDialog;
import com.example.demcom.wheter.enums.SectionEnum;
import com.example.demcom.wheter.enums.TempHumEnum;
import com.example.demcom.wheter.interfaces.HtttpRequestCallback;
import com.example.demcom.wheter.models.Weather;
import com.example.demcom.wheter.tools.DateFormats;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{

    private TextView humidityTextView;
    private TextView temperatureTextView;
    private TextView dateTextView;
    private TextView timeTextView;
    private TextView titleTextView;
    private SectionEnum currentSection;
    private ResultDialog resultDialog;

    public int hum;
    public int temp;
    public boolean isLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        resultDialog = new ResultDialog(this);
        currentSection = SectionEnum.BEER;
        temperatureTextView = (TextView) findViewById(R.id.textView_temperature);
        humidityTextView = (TextView) findViewById(R.id.textView_humidity);
        dateTextView = (TextView) findViewById(R.id.textView_date);
        timeTextView = (TextView) findViewById(R.id.textView_time);
        titleTextView = (TextView) findViewById(R.id.title);

//        titleTextView.setText(getResources().getString(R.string.wine_page));
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
                        isLoaded = true;
                        JSONObject jsonObject = new JSONObject(response);
                        Weather weather = new Weather(jsonObject);
                        temp = weather.getTemperature();
                        hum = weather.getHumidity();

                        //Change to check the current section
                        if(temp > TempHumEnum.WINE_TEMP.value() || temp < TempHumEnum.WINE_TEMP.value()) {
                            temperatureTextView.setTextColor(getResources().getColor(R.color.colorNegativeRed));
                        } else{
                            temperatureTextView.setTextColor(getResources().getColor(R.color.colorPositiveGreen));
                        }

                        //Change to check the current section
                        if(hum > TempHumEnum.WINE_HUM.value() || hum < TempHumEnum.WINE_HUM.value()){
                            humidityTextView.setTextColor(getResources().getColor(R.color.colorNegativeRed));
                        } else{
                            humidityTextView.setTextColor(getResources().getColor(R.color.colorPositiveGreen));
                        }

                        temperatureTextView.setText(String.valueOf(temp) + Constants.TEMPERATURE_MESURE);
                        humidityTextView.setText(String.valueOf(hum)+Constants.HUMIDITY_PERCENT);
                        DateFormats dateFormats = new DateFormats();
                        String[] dateTime = dateFormats.getDateStartingByMonth(weather.getDate()).split(" ");
                        dateTextView.setText(dateTime[0]);
                        timeTextView.setText(dateTime[1]);
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

    @OnClick(R.id.button_beer)
    public void onBeerClick(){

    }

    @OnClick(R.id.button_wine)
    public void onWineClick(){

    }

    @OnClick(R.id.info)
    public void onInfoClick(){
        if(isLoaded)
            resultDialog.showWithData("", temp, hum, SectionEnum.WINE);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    protected void onPause() {
        super.onPause();
    }
}
