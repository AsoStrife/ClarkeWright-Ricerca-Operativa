package com.ro.clarkewright.handler;

public class DoubleHandler {
    /**
     * This method rounds a float/double number with a scale parameter
     * @param value a number which needs to be rounded
     * @param precision number of digits after the comma
     * @return
     */
    public static double round(double value, int precision) {
        return Math.round(value * Math.pow(10, precision)) / Math.pow(10, precision);
    }
}
