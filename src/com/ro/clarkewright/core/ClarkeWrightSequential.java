package com.ro.clarkewright.core;

import com.ro.clarkewright.model.DistanceMatrix;
import com.ro.clarkewright.model.Route;
import com.ro.clarkewright.model.SavingsMatrix;
import java.util.ArrayList;

public class ClarkeWrightSequential {
    // Contain the .vrp file istance
    Instance instance;

    // This object contain the distance matrix
    private DistanceMatrix distanceMatrix;

    // This ojbect contain the saving matrix
    private SavingsMatrix savingsMatrix;

    // Contain the main routes
    private ArrayList<Route> mainRoutes = new ArrayList<>();

    public ClarkeWrightSequential(Instance instance){
        this.instance = instance;
        this.distanceMatrix = new DistanceMatrix(instance.getDepot(), instance.getNodes());
        this.savingsMatrix = new SavingsMatrix(instance.getDepot(), instance.getNodes(), distanceMatrix);

        mainRoutesHandler();

        debug();
    }

    /**
     * Generate the main routes 0 -> i -> 0
     */
    public void mainRoutesHandler(){
        for(int i = 0; i < instance.nodeSize(); i++){
            mainRoutes.add( new Route(instance.getDepot(), instance.getNode(i), distanceMatrix));
        }
    }

    private void debug(){
        System.out.println("-- DISTANCE MATRIX --");
        distanceMatrix.print();

        System.out.println("-- SAVINGS MATRIX --");
        savingsMatrix.print();
    }
}
