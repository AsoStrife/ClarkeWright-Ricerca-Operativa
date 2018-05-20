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
    private int demand = 0;

    /**
     * Starting by two nodes, calculate the distances roundtrip
     * @param depot
     * @param destination
     * @param distanceMatrix
     */
    public Route(Node depot, Node destination, DistanceMatrix distanceMatrix){
        this.distanceMatrix = distanceMatrix;

        // Creo la rotta base: deposito, destinazione, deposito
        routes.add(depot);
        routes.add(destination);
        routes.add(depot);

        calculateDistance();
        calculateDemand();
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
        for(int i = 0; i < size; i++){
            demand = demand + routes.get(i).getDemand();
        }

    }

    /**
     *
     * @param n
     * @return
     */
    public boolean checkContainNode(Node n){

        if(routes.contains(n))
            return true;
        else
            return false;

    }

    public void merge(ArrayList<Node> newRoutes){

        routes.remove(routes.size()-1); // Rimuovo il depot delle routes
        newRoutes.remove(0); // Rimuovo il depot dall'inizio delle routes da mergiare
        newRoutes.remove(newRoutes.size()-1); // Rimuovo il depot dalla fine delle routes da mergiare
        // Aggiungo le rotte
        routes.addAll(newRoutes);
        // Riaggiungo il depot
        routes.add(routes.get(0));
        // Ricalcolo le demand
        calculateDemand();
    }

    /**
     *
     * @param n
     * @return
     */
    public boolean checkIsFirstOrLast(Node n){
        int size = routes.size();

        if(size < 3)
            return false;

        if(routes.get(1).equals(n))
            return true;
        if(routes.get(size - 2).equals(n)) {
            return true;
        }
        return false;
    }



    /**
     *
     * @return
     */
    public double getDistance() {
        return distance;
    }


    public void print(){
        System.out.print("Route: ");
        for(int i = 0; i < routes.size(); i++){
            System.out.print(routes.get(i).getIndex() + " ");
        }
        System.out.print("| Demand: " + demand);
        System.out.println(" ");

    }

    public int getDemand(){
        return demand;
    }


    public ArrayList<Node> getRoutes(){
        return routes;
    }
}

