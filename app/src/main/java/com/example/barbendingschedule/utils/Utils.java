package com.example.barbendingschedule.utils;

import java.text.DecimalFormat;

public class Utils {
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public static String getTwodecimalPoint(double value){
        return df2.format(value);
    }
}
