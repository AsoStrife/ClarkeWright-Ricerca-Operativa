package com.ro.clarkewright;
import com.ro.clarkewright.core.ClarkeWrightParallel;
import com.ro.clarkewright.core.ClarkeWrightSequential;
import com.ro.clarkewright.core.Instance;
import com.ro.clarkewright.handler.TimeHandler;
import com.ro.clarkewright.manager.FileManager;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Main class of the project.
 * It calculates first the sequential, then the parallel version of the Clarke & Wright algorithm
 */

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // Create the list of the files in ./data/A-VRP/
        ArrayList<String> filenames = FileManager.getAllInput();

        /**
         * If you want to test the algorithm for each file
         * you have to remove the for cycle and set
         * String filename = filenames.get( index )
         * with index starting from 0 to filenames.size() - 1 (it should be 26)
         */

        for(int i = 0; i < filenames.size(); i++) {
            String filename = filenames.get(i); // "A-n80-k10";

            System.out.println("Filename: " + filenames.get(i));

            /**
             * ClarkeWright Sequential
             */
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

            /**
             * ClarkeWright Parallel
             */
            TimeHandler time2 = new TimeHandler();
            // Creates object ClarkeWrightParallel
            ClarkeWrightParallel cwp = new ClarkeWrightParallel(instance);

            // Executes the parallel version of the algorithm
            cwp.run();
            time2.print("ClarkWright Parallel");
            // @Debug
            //cwp.debug();



            // Write the full .txt file

            FileManager.write(filename, cws, cwp, time1.getSeconds(), time2.getSeconds());

            System.out.println("\n----------------------------------\n");



        }



    }



}