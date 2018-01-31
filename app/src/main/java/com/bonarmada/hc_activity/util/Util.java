package com.bonarmada.hc_activity.util;

/**
 * Created by bonbonme on 1/31/2018.
 */

public class Util {

    // Converts to celcius
    public static float toCelcius(float fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }

    // Converts to fahrenheit
    public static float toFahrenheit(float celsius) {
        return ((celsius * 9) / 5) + 32;
    }
}
