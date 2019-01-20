package com.myapps.daggermvvm;

public class PriceUtil {
    public static String getPriceInRs(double price) {
        return "\u20B9" + " " + String.valueOf(Math.round(price));
    }
}
