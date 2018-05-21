package com.ro.clarkewright.handler;

public class TimeHandler {

    private long startTime;
    private long endTime;
    private float milliseconds;

    /**
     * Constructor which initializes the object taking the timestamp of the creation
     */
    public TimeHandler(){
        this.startTime = System.nanoTime();
    }

    /**
     * Measures the time in milliseconds between startTime and the execution of this method
     */
    private void calculate(){
        this.endTime = System.nanoTime();
        this.milliseconds = (endTime - startTime) / 1000000;
    }

    /**
     * Prints the execution time in seconds
     * @param s a string which is printed before the computed time
     */
    public void print(String s) {
        calculate();
        System.out.printf(s + " time:  %.3f seconds %n", milliseconds / 1000);
    }
}
