package com.ro.clarkewright;
import com.ro.clarkewright.core.ClarkeWrightSequential;
import com.ro.clarkewright.core.Instance;
import com.ro.clarkewright.handler.TimeHandler;
import com.ro.clarkewright.manager.FileManager;
import com.ro.clarkewright.model.DistanceMatrix;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> filenames = FileManager.getAllInput();

        String filename = "A-n32-k5";


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

        DistanceMatrix dm = cws.getDistanceMatrix();
        double distance = 0;
        //1 31 13 27 17 2 21 6 15 30 4
        distance = distance + dm.getDistance(21, 31);
        distance = distance + dm.getDistance(31, 19);
        distance = distance + dm.getDistance(19, 17);
        distance = distance + dm.getDistance(17, 13);
        distance = distance + dm.getDistance(13, 7);
        distance = distance + dm.getDistance(7, 26);
        System.out.println(distance);
    }
}