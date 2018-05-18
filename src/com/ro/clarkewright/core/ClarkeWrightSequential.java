package com.ro.clarkewright.core;

import com.ro.clarkewright.model.DistanceMatrix;
import com.ro.clarkewright.model.Node;
import com.ro.clarkewright.model.Route;
import com.ro.clarkewright.model.SavingsMatrix;
import java.util.ArrayList;

public class ClarkeWrightSequential {
    // Contain the .vrp file instance
    Instance instance;

    // All nodes with depot in position 0
    private ArrayList<Node> nodes = new ArrayList<>();

    // This object contain the distance matrix
    private DistanceMatrix distanceMatrix;

    // This object contain the saving matrix
    private SavingsMatrix savingsMatrix;

    // Contain the main routes
    private ArrayList<Route> mainRoutes = new ArrayList<>();

    public ClarkeWrightSequential(Instance instance){
        this.instance = instance;

        // Now nodes contain all nodes with depot in 0 position
        this.nodes.add(instance.getDepot());
        this.nodes.addAll(instance.getNodes());

        this.distanceMatrix = new DistanceMatrix(nodes);
        this.savingsMatrix = new SavingsMatrix(nodes, distanceMatrix);

        // generate the main routes
        mainRoutesHandler();

        //debug();
    }

    /**
     * Generate the main routes 0 -> i -> 0
     */
    public void mainRoutesHandler(){
        for(int i = 0; i < instance.nodeSize(); i++){
            mainRoutes.add( new Route(instance.getDepot(), instance.getNode(i), distanceMatrix, instance.getCapacity()));
        }
    }


    public void run(){
        // Per ogni elemento della savingList ordinata
        for(int i = 0; i < savingsMatrix.getOrderedSavingsList().size(); i++){
            System.out.println(savingsMatrix.getElementOrderedSavingList(i));
        }
    }


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

    }
}
