package com.ro.clarkewright;

import com.ro.clarkewright.core.ClarkeWrightParallel;
import com.ro.clarkewright.core.ClarkeWrightSequential;
import com.ro.clarkewright.core.Instance;
import com.ro.clarkewright.handler.TimeHandler;

/**
 * Main class of the project.
 * It calculates first the sequential, then the parallel version of the Clarke & Wright algorithm
 */
public class Main {

    public static void main(String[] args){

        TimeHandler time = new TimeHandler();

        // Reads file passed as parameters
        Instance instance = new Instance("./data/A-VRP/A-n32-k5.vrp");

        // Creates object ClarkeWrightSequential
        ClarkeWrightSequential cws = new ClarkeWrightSequential(instance);
        // Executes the sequential version of the algorithm
        cws.run();
        time.print("ClarkWright Sequential");
        // @Debug
        cws.debug();

        System.out.println("\n----------------------------------\n");

        TimeHandler time2 = new TimeHandler();
        // Creates object ClarkeWrightParallel
        ClarkeWrightParallel cwp = new ClarkeWrightParallel(instance);
        // Executes the parallel version of the algorithm
        cwp.run();
        time2.print("ClarkWright Parallel");
        // @Debug
        cwp.debug();


    }

}
