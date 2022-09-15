package com.decathlon.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DecathlonUtils {

    private DecathlonUtils() {
    }

    public static final String SEPARATOR_FILE = ";";
    public static final Integer MAX_FIELDS_FILE = 10;

    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MIILISECONDS_IN_SECOND = 100;

    public static BigDecimal getMeters(String value) {
        return new BigDecimal(value);
    }

    /**
     * Convert give value to centimeters
     * @param  value String
     * @return BigDecimal.
     */
    public static BigDecimal getCentimeters(String value) {
        return new BigDecimal(value).multiply(new BigDecimal("100")).setScale(6, RoundingMode.DOWN);
    }

    public static BigDecimal getSecond(String value) {
        BigDecimal response = null;
        DateFormat formatter = new SimpleDateFormat("ss.SS");
        try {
            Date dt = formatter.parse(value);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            response = getSeconds(cal);
        } catch (ParseException e) {
            response = BigDecimal.ZERO;
        }
        return response;
    }

    /**
     * Convert give value to Minute
     * @param value it's the value that you wish change return BigDecimal
     *               with final value after convert
     */
    public static BigDecimal getMinuteToSecond(String value) {

        BigDecimal response = null;

        DateFormat formatter = new SimpleDateFormat("mm.ss.SS");
        try {
            Date dt = formatter.parse(value.trim());
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            response = getSeconds(cal);
        } catch (ParseException e) {
            response = BigDecimal.ZERO;
        }
        return response;
    }

    public static BigDecimal getSeconds(Calendar cal) {
        return BigDecimal.valueOf(cal.get(Calendar.MINUTE) * SECONDS_IN_MINUTE + cal.get(Calendar.SECOND)
                + ((float) cal.get(Calendar.MILLISECOND)) / MIILISECONDS_IN_SECOND);
    }
}
