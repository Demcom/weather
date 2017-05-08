package com.example.demcom.wheter.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import com.example.demcom.wheter.R;
import com.example.demcom.wheter.common.Constants;
import com.example.demcom.wheter.enums.SectionEnum;
import com.example.demcom.wheter.enums.TempHumEnum;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Demcom on 5/7/2017.
 */

public class ResultDialog extends Dialog {

    Activity activity;

    @BindView(R.id.textViewActualHum)
    TextView textViewActualHum;
    @BindView(R.id.textViewIdealHum)
    TextView textViewIdealHum;
    @BindView(R.id.textViewActualTemp)
    TextView textViewActualTemp;
    @BindView(R.id.textViewIdealTemp)
    TextView textViewIdealTemp;
    @BindView(R.id.textViewTempRes)
    TextView textViewTempRes;
    @BindView(R.id.textViewHumRes)
    TextView textViewHumRes;


    public static final int WINE_TEMP = 16;

    String title;
    int temp;
    int hum;
    SectionEnum sectionEnum;

    public ResultDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_dialog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageViewClose)
    public void OnCloseClick(){
        this.dismiss();
    }

    public void showWithData(String title, int temp, int hum, SectionEnum sectionEnum){
        super.show();

        this.title = title;
        this.temp = temp;
        this.hum = hum;
        this.sectionEnum = sectionEnum;
        int tres= TempHumEnum.WINE_TEMP.value() - temp;
        int thum = TempHumEnum.WINE_HUM.value() - hum;

        if(tres == 0){
            textViewTempRes.setTextColor(activity.getResources().getColor(R.color.colorPositiveGreen));
        }else{
            textViewTempRes.setTextColor(activity.getResources().getColor(R.color.colorNegativeRed));
        }

        if(thum == 0){
            textViewHumRes.setTextColor(activity.getResources().getColor(R.color.colorPositiveGreen));
        }else{
            textViewHumRes.setTextColor(activity.getResources().getColor(R.color.colorNegativeRed));
        }

        textViewTempRes.setText("Temperatura: " + String.valueOf(tres) + Constants.TEMPERATURE_MESURE);
        textViewHumRes.setText("Humedad: " + String.valueOf(thum) + Constants.HUMIDITY_PERCENT);
        switch (sectionEnum){
            case WINE:
                textViewIdealTemp.setText(String.valueOf(TempHumEnum.WINE_TEMP.value()) + Constants.TEMPERATURE_MESURE);
                textViewActualTemp.setText(String.valueOf(temp) + Constants.TEMPERATURE_MESURE);
                textViewIdealHum.setText(String.valueOf(TempHumEnum.WINE_HUM.value()) + Constants.HUMIDITY_PERCENT);
                textViewActualHum.setText(String.valueOf(hum) + Constants.HUMIDITY_PERCENT);
                checkForWineConditions();
                break;
        }

    }

    public void checkForWineConditions(){
        //Change to check the current section (repeating code from main activity)
        if(temp > TempHumEnum.WINE_TEMP.value() || temp < TempHumEnum.WINE_TEMP.value()) {
            textViewActualTemp.setTextColor(activity.getResources().getColor(R.color.colorNegativeRed));
        } else{
            textViewActualTemp.setTextColor(activity.getResources().getColor(R.color.colorPositiveGreen));
        }

        //Change to check the current section (repeating code from main activity)
        if(hum > TempHumEnum.WINE_HUM.value() || hum < TempHumEnum.WINE_HUM.value()){
            textViewActualHum.setTextColor(activity.getResources().getColor(R.color.colorNegativeRed));
        } else{
            textViewActualHum.setTextColor(activity.getResources().getColor(R.color.colorPositiveGreen));
        }

    }

}
