package com.ro.clarkewright;

import com.ro.clarkewright.core.ClarkeWrightSequential;
import com.ro.clarkewright.core.Instance;

public class Main {

    public static void main(String[] args){

        // Read file passed as parameters
        Instance instance = new Instance("./data/A-VRP/A-n32-k5.vrp");
        // Create object ClarkeWrightSequential
        ClarkeWrightSequential cws = new ClarkeWrightSequential(instance);
        cws.run();
    }
}
