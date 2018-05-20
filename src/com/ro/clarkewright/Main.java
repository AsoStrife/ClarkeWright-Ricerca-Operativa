package com.ro.clarkewright;

import com.ro.clarkewright.core.ClarkeWrightSequential;
import com.ro.clarkewright.core.Instance;

public class Main {

    public static void main(String[] args){

        // Read file passed as parameters
        Instance instance = new Instance("./data/A-VRP/A-n32-k5.vrp");
        // Create object ClarkeWrightSequential, crea il depot, tutti i nodi e le main route
        ClarkeWrightSequential cws = new ClarkeWrightSequential(instance);
        // Lancio l'algoritmo, fa i 3 controlli e fa i merge dove necessario
        cws.run();

        // Utile per il debug, printa su console eventuali dati [non necessario al fine del progetto]
        cws.debug();
    }
}
