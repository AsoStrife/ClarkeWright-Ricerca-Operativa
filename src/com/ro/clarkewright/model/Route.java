package com.ro.clarkewright.model;

import java.util.ArrayList;

/**
 *
 */
public class Route {

    // This array list contain the route of this route
    // For example 0 -> 1 -> 0 => ArrayList of three element
    private ArrayList<Node> route = new ArrayList<>();

    private DistanceMatrix distanceMatrix;
    private double distance = 0;
    private int capacity = 100;
    private int totalDemand = 0;

    /**
     * Starting by two nodes, calculate the distances roundtrip
     * @param depot
     * @param destination
     * @param distanceMatrix
     */
    public Route(Node depot, Node destination, DistanceMatrix distanceMatrix, int capacity){
        this.distanceMatrix = distanceMatrix;
        this.capacity = capacity;

        // Creo la rotta base: deposito, destinazione, deposito
        route.add(depot);
        route.add(destination);
        route.add(depot);

        calculateDistance();

    }

    /**
     * Calcolo le distanze tra i nodi. size-1 perché l'ultimo elemento sarà sempre il depot e quindi non devo
     * calcolare depot + 1 che va in overflow
     */
    public void calculateDistance(){
        int size = route.size();
        for(int i = 0; i < size-1; i++){
            distance = distance + distanceMatrix.getDistance(route.get(i), route.get(i+1));
        }
    }

    /**
     * Elimino il depot, aggiungo il nuovo nodo, riaggiungo il depot alla fine e
     * ricalcolo le distanze
     * @param n
     */
    public void addNode(Node n){
        int size = route.size();

        // Tolgo il depot
        route.remove(size-1);
        // Aggiungo il nuovo nodo
        route.add(n);
        // Riaggiungo il depot
        route.add(route.get(0));
        // Ricalcolo la disana percorsa nuovamente da questa rotta
        calculateDistance();
    }

    /**
     *
     * @return
     */
    public double getDistance() {
        return distance;
    }


}

