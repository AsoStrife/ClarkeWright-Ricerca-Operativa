package com.ro.clarkewright.handler;

/**
 *
 */
public class DoubleHandler {
    /**
     * This method round a float/double number with a scale parameter
     * @param value
     * @param scale
     * @return
     */
    public static double round(double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }
}
