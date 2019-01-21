package com.myapps.daggermvvm.util;

public class PriceUtil {
    public static String getPriceInRs(Double price) {
        return "\u20B9" + " " + String.valueOf(price);
    }
}
