package com.ro.clarkewright;

import com.ro.clarkewright.core.ClarkeWrightParallel;
import com.ro.clarkewright.core.ClarkeWrightSequential;
import com.ro.clarkewright.core.Instance;
import com.ro.clarkewright.handler.TimeHandler;

public class Main {

    public static void main(String[] args){

        TimeHandler time = new TimeHandler();

        // Read file passed as parameters
        Instance instance = new Instance("./data/A-VRP/A-n32-k5.vrp");

        // Create object ClarkeWrightSequential, crea il depot, tutti i nodi e le main route
        ClarkeWrightSequential cws = new ClarkeWrightSequential(instance);
        // Lancio l'algoritmo, fa i 3 controlli e fa i merge dove necessario
        cws.run();
        time.print("ClarkWright Sequential");
        // Utile per il debug, printa su console eventuali dati [non necessario al fine del progetto]
        cws.debug();

        System.out.println("\n----------------------------------\n");

        ClarkeWrightParallel cwp = new ClarkeWrightParallel(instance);
        // Lancio l'algoritmo, fa i 3 controlli e fa i merge dove necessario
        cwp.run();
        time.print("ClarkWright Parallel");
        // Utile per il debug, printa su console eventuali dati [non necessario al fine del progetto]
        cwp.debug();


    }

}
