package com.example.coach.outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MesOutils {

    /**
     * Converti une date au format String en format Date
     * @param uneDate
     * @return
     */
    public static Date convertStringToDate(String uneDate, String patern){
        String expectedPattern = patern;
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            Date date = formatter.parse(uneDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converti une date au format Date en format String
     * @param uneDate
     * @return
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(uneDate);
    }

    public static Date convertStringToDate(String uneDate){
        return MesOutils.convertStringToDate(uneDate, "EEE MMM dd hh:mm:ss 'GMT+00:00' yyyy");
    }

}
