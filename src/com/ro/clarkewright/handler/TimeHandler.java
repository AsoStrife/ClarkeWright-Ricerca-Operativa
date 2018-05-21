package com.ro.clarkewright.handler;

/**
 *
 */
public class TimeHandler {

    private long startTime;
    private long endTime;
    private float milliseconds;

    /**
     *
     */
    public TimeHandler(){
        this.startTime = System.nanoTime();
    }

    /**
     *
     */
    private void calculate(){
        this.endTime = System.nanoTime();
        this.milliseconds = (endTime - startTime) / 1000000;
    }

    /**
     *
     * @param s
     */
    public void print(String s) {
        calculate();
        System.out.printf(s + " time:  %.3f seconds %n", milliseconds / 1000);
    }
}
