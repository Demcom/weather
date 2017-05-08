package com.example.demcom.wheter.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Demcom on 5/7/2017.
 */

public class DateFormats {

    public DateFormats(){
    }

    public String getDateStartingByMonth(String date){
        String[] dateArray = date.split(" ", 2);
        date = dateArray[1];
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm:ss yyyy");
        try {
            Date newDate = sdf.parse(date);
            sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            date = sdf.format(newDate);
            return  date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}
