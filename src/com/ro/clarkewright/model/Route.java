package com.ro.clarkewright.model;
import com.ro.clarkewright.handler.DoubleHandler;

import java.util.ArrayList;

public class Route {

    // This array list contains the routes of this Route object
    // For example 0 -> 1 -> 0 => ArrayList of three element
    private ArrayList<Node> routes = new ArrayList<>();
    // Distance matrix object
    private DistanceMatrix distanceMatrix;
    // Total distance between nodes of the current route list
    private double distance = 0;
    // Total demand of the nodes in the current route list
    private int demand = 0;

    /**
     * Creates the Route object and calculates the total distance and the total demand
     * @param depot the first node of each route
     * @param destination the node which represents the client
     * @param distanceMatrix the distance matrix used to find the distances of the routes list
     */
    public Route(Node depot, Node destination, DistanceMatrix distanceMatrix){
        this.distanceMatrix = distanceMatrix;

        // Creates the main route: depot, destination, depot
        routes.add(depot);
        routes.add(destination);
        routes.add(depot);

        calculateDistance();
        calculateDemand();
    }

    /**
     * Computes the total distance of the route. We use size-1 to avoid overflow
     */
    private void calculateDistance(){
        int size = routes.size();
        for(int i = 0; i < size-1; i++){
            distance = distance + distanceMatrix.getDistance(routes.get(i), routes.get(i+1));
        }
    }

    /**
     * Computes the total demand of the route
     */
    private void calculateDemand(){
        int size = routes.size();
        demand = 0;
        for(int i = 0; i < size; i++){
            demand = demand + routes.get(i).getDemand();
        }

    }

    /**
     * Merges a route on which this method is called with the newRoute parameter
     * @param newRoutes the route to merge with the route on which this method is called
     */
    public void merge(ArrayList<Node> newRoutes){

        // Remove the depot from the end of the route
        routes.remove(routes.size()-1);
        // Remove the depot from the start of the new route to merge
        newRoutes.remove(0);
        // Remove the depot from the end of the new route to merge
        newRoutes.remove(newRoutes.size()-1);
        // Merge the two routes
        routes.addAll(newRoutes);
        // Add again the depot
        routes.add(routes.get(0));
        // Compute the updated demand and distance
        calculateDemand();
        calculateDistance();
    }

    /**
     * Checks if a node is cointained into a route list
     * @param n the node to check
     * @return true if the route contains the node, false otherwise
     */
    public boolean checkContainNode(Node n){

        if(routes.contains(n))
            return true;
        else
            return false;

    }

    /**
     * Checks if a node is the first or the last inside a route
     * @param n the node to check
     * @return true if the node is the first or the last, false otherwise
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
     * Checks is a node is the first of the route
     * @param n the node to check
     * @return true if the node is the first, false otherwise
     */
    public boolean checkIsFirst(Node n){
        if(routes.get(1).equals(n))
            return  true;
        else
            return false;
    }

    /**
     * Checks is a node is the last of the route
     * @param n the node to check
     * @return true if the node is the last, false otherwise
     */
    public boolean checkIsLast(Node n){
        // Position -2 because in position -1 there is the depot
        int position = routes.size()-2;

        if(routes.get(position).equals(n))
            return  true;
        else
            return false;
    }
    /**
     * Getter
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Getter
     * @return the demand
     */
    public int getDemand(){
        return demand;
    }

    /**
     * Getter
     * @return the route list
     */
    public ArrayList<Node> getRoutes(){
        return routes;
    }

    /**
     * @Debug Prints all the route list with associated demand and distance
     */
    public void print(){
        System.out.print("Route: ");
        for(int i = 0; i < routes.size(); i++){
            System.out.print(routes.get(i).getIndex() + " ");
        }
        System.out.print("| Demand: " + DoubleHandler.round(demand, 2));
        System.out.print("| Distance: " + DoubleHandler.round(distance, 2));
        System.out.println(" ");

    }
}

