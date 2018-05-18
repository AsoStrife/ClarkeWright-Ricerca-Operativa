package com.ro.clarkewright.model;

import java.util.ArrayList;

/**
 *
 */
public class Route {

    // This array list contain the route of this route
    // For example 0 -> 1 -> 0 => ArrayList of three element
    private ArrayList<Node> routes = new ArrayList<>();

    private DistanceMatrix distanceMatrix;
    private double distance = 0;
    private int capacity;
    private int demand = 0;

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
        routes.add(depot);
        routes.add(destination);
        routes.add(depot);

        calculateDistance();

    }

    /**
     * Calcolo le distanze tra i nodi. size-1 perché l'ultimo elemento sarà sempre il depot e quindi non devo
     * calcolare depot + 1 che va in overflow
     */
    public void calculateDistance(){
        int size = routes.size();
        for(int i = 0; i < size-1; i++){
            distance = distance + distanceMatrix.getDistance(routes.get(i), routes.get(i+1));
        }
    }

    public void calculateDemand(){
        int size = routes.size();
        demand = 0;
        for(int i = 1; i < size-1; i++){
            demand = demand + routes.get(i).getDemand();
        }

        if(demand > capacity){
            throw new RuntimeException("Demand supera capacity");
        }
    }

    /**
     *
     * @param newDemand
     * @return
     */
    private boolean checkDemand(int newDemand){
        if(demand + newDemand > 500)
            return false;
        else
            return  true;
    }

    /**
     *
     * @param n
     * @return
     */
    private boolean checkContainNode(Node n){
        if(routes.contains(n))
            return true;
        else
            return false;
    }

    /**
     *
     * @param n
     * @return
     */
    private boolean checkIsFirstOrLast(Node n){
        int size = routes.size();

        if(routes.get(1).equals(n))
            return true;
        if(routes.get(size - 2).equals(n))
            return true;

        return false;
    }

    /**
     * Elimino il depot, aggiungo il nuovo nodo, riaggiungo il depot alla fine e
     * ricalcolo le distanze
     * @param n
     */
    public void addNode(Node n){
        int size = routes.size();

        // Tolgo il depot
        routes.remove(size-1);
        // Aggiungo il nuovo nodo
        routes.add(n);
        // Riaggiungo il depot
        routes.add(routes.get(0));
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

