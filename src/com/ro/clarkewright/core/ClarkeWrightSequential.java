package com.ro.clarkewright.core;

import com.ro.clarkewright.model.DistanceMatrix;
import com.ro.clarkewright.model.Route;
import com.ro.clarkewright.model.SavingsMatrix;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClarkeWrightSequential {
    // Contain the .vrp file instance
    Instance instance;

    // This object contain the distance matrix
    private DistanceMatrix distanceMatrix;

    // This object contain the saving matrix
    private SavingsMatrix savingsMatrix;

    // Contain the main routes
    private ArrayList<Route> mainRoutes = new ArrayList<>();

    public ClarkeWrightSequential(Instance instance){
        this.instance = instance;
        this.distanceMatrix = new DistanceMatrix(instance.getDepot(), instance.getNodes());
        this.savingsMatrix = new SavingsMatrix(instance.getDepot(), instance.getNodes(), distanceMatrix);

        // generate the main routes
        mainRoutesHandler();

        debug();
    }

    /**
     * Generate the main routes 0 -> i -> 0
     */
    public void mainRoutesHandler(){
        for(int i = 0; i < instance.nodeSize(); i++){
            mainRoutes.add( new Route(instance.getDepot(), instance.getNode(i), distanceMatrix, instance.getCapacity()));
        }
        // sort
        //orderRoutes();
    }

    /**
     * Sort the main routes decreasing
     * This sort is not required
    public void orderRoutes(){
        Collections.sort(mainRoutes, Comparator.comparingDouble(Route::getTotalDistance));
    }
    */

    /**
     * DEBUG METHOD
     */
    private void debug(){
        System.out.println("-- DISTANCE MATRIX --");
        distanceMatrix.print();

        System.out.println("-- SAVINGS MATRIX --");
        savingsMatrix.print();

        System.out.println("-- ORDERED SAVING LIST --");
        savingsMatrix.printOrderedSavingsList();

        /**
        System.out.println("-- ROUTE DISTANCES --");
        for (int i = 0; i < mainRoutes.size(); i++) {
            System.out.println("Total distance: " + mainRoutes.get(i).getTotalDistance() + ". Route: " + mainRoutes.get(i).getDepot().getIndex() + " " + mainRoutes.get(i).getDestination().getIndex() + " " + mainRoutes.get(i).getDepot().getIndex());
        }**/
    }
}
