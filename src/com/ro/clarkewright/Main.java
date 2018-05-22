package com.ro.clarkewright;

import com.ro.clarkewright.core.ClarkeWrightParallel;
import com.ro.clarkewright.core.ClarkeWrightSequential;
import com.ro.clarkewright.core.Instance;
import com.ro.clarkewright.handler.TimeHandler;
import com.ro.clarkewright.manager.FileManager;

import java.io.FileNotFoundException;

/**
 * Main class of the project.
 * It calculates first the sequential, then the parallel version of the Clarke & Wright algorithm
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String filename = "A-n38-k5";

        TimeHandler time1 = new TimeHandler();

        // Reads file passed as parameters
        Instance instance = new Instance(filename);

        // Creates object ClarkeWrightSequential
        ClarkeWrightSequential cws = new ClarkeWrightSequential(instance);
        // Executes the sequential version of the algorithm
        cws.run();
        time1.print("ClarkWright Sequential");
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

        FileManager.write(filename, cws, cwp, time1.getSeconds(), time2.getSeconds());

    }

}
