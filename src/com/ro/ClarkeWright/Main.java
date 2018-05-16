package com.ro.ClarkeWright;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.ro.ClarkeWright.Core.Route;
import com.ro.ClarkeWright.Manager.FileManager;
import com.ro.ClarkeWright.Core.Instance;
import com.ro.ClarkeWright.Core.DistanceMatrix;

public class Main {

    public static void main(String[] args) throws IOException {
        //FileManager fileManager = new FileManager();
        BufferedReader file = FileManager.read("./data/A-VRP/A-n32-k5.vrp");
        Instance instance = new Instance(file);

        DistanceMatrix distanceMatrix = new DistanceMatrix(instance.getDepot(), instance.getNodes());

        // Calcolo le rotte principali
        ArrayList<Route> mainRoutes = new ArrayList<Route>();
        for(int i = 0; i < instance.nodeSize(); i++){
            mainRoutes.add( new Route(instance.getDepot(), instance.getNode(i), distanceMatrix));
        }

    }
}
