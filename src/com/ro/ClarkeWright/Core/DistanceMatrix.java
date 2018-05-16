package com.ro.ClarkeWright.Core;

import java.util.ArrayList;

public class DistanceMatrix {

    private ArrayList<Node> nodes  = new ArrayList<>();
    private double matrix[][];

    /**
     *
     * @param depot
     * @param nodes
     */
    public DistanceMatrix(Node depot, ArrayList<Node> nodes){
        this.nodes.add(depot);
        this.nodes.addAll(nodes);

        this.matrix = new double[nodes.size()+1][nodes.size()+1];

        calculateMatrix();
    }

    /**
     *
     */
    private void calculateMatrix(){
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
     * @return
     */
    public static double calculateDistance(Node first, Node second){
        double value =  Math.sqrt(Math.pow(first.getX()-second.getX(), 2) + Math.pow(first.getY()-second.getY(), 2));
        return round(value, 1);
    }

    /**
     *
     * @param first
     * @param second
     * @return
     */
    public double getDistance(Node first, Node second){
        return matrix[first.getIndex()][second.getIndex()];
    }

    /**
     *
     */
    public void printMatrix(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param value
     * @param scale
     * @return
     */
    public static double round(double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }
}
