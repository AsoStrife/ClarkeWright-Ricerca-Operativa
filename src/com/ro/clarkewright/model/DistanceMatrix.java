package com.ro.clarkewright.model;
import com.ro.clarkewright.handler.DoubleHandler;

import java.util.ArrayList;

public class DistanceMatrix {

    // Nodes list with all nodes. In position (0) there is the depot
    private ArrayList<Node> nodes  = new ArrayList<>();
    // Double matrix to contain the distances between all nodes
    private double matrix[][];

    /**
     * Builds the distance matrix taking the nodes list as parameter
     * @param nodes a list which contains all the nodes
     */
    public DistanceMatrix(ArrayList<Node> nodes){
        this.nodes.addAll(nodes);

        this.matrix = new double[nodes.size()+1][nodes.size()+1];

        // Calculates the distances
        distanceHandler();
    }

    /**
     * For each pair of nodes, it calculates the distance between them
     */
    private void distanceHandler(){
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                matrix[nodes.get(i).getIndex()][nodes.get(j).getIndex()] = calculateDistance(nodes.get(i), nodes.get(j));
            }
        }
    }

    /**
     * Computes the euclidean distance between two nodes
     * @param first the first node
     * @param second the second node
     * @return the distance between the nodes
     */
    public static double calculateDistance(Node first, Node second){
        double value =  Math.sqrt(Math.pow(first.getX()-second.getX(), 2) + Math.pow(first.getY()-second.getY(), 2));
        return DoubleHandler.round(value, 1);
    }

    /**
     * Getter of a single value between two nodes
     * @param first the first node
     * @param second the second node
     * @return the distance using the index of the nodes
     */
    public double getDistance(Node first, Node second){
        return matrix[first.getIndex()][second.getIndex()];
    }

    /**
     * Debug function, print all values of the distance matrix
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
