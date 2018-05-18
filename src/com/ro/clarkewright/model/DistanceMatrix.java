package com.ro.clarkewright.model;
import com.ro.clarkewright.handler.DoubleHandler;

import java.util.ArrayList;

public class DistanceMatrix {

    // Nodes list contain all nodes. In the first position (0) there is the depot
    private ArrayList<Node> nodes  = new ArrayList<>();
    private double matrix[][];

    /**
     *
     * @param depot
     * @param nodes
     */
    public DistanceMatrix( ArrayList<Node> nodes){
        this.nodes.addAll(nodes);

        this.matrix = new double[nodes.size()+1][nodes.size()+1];

        distanceHandler();
    }

    /**
     * Set the matrix attribute with the value
     */
    private void distanceHandler(){
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                matrix[nodes.get(i).getIndex()][nodes.get(j).getIndex()] = calculateDistance(nodes.get(i), nodes.get(j));
            }
        }
    }

    /**
     *
     * @param first
     * @param second
     * @return the distance between two node
     */
    public static double calculateDistance(Node first, Node second){
        double value =  Math.sqrt(Math.pow(first.getX()-second.getX(), 2) + Math.pow(first.getY()-second.getY(), 2));
        return DoubleHandler.round(value, 1);
    }

    /**
     *
     * @param first
     * @param second
     * @return get the distance using the index of the nodes
     */
    public double getDistance(Node first, Node second){
        return matrix[first.getIndex()][second.getIndex()];
    }

    /**
     * Debug function, print all values
     */
    public void print(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


}
