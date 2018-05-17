package com.ro.clarkewright.model;

import com.ro.clarkewright.handler.DoubleHandler;

import java.util.ArrayList;

public class SavingsMatrix {
    // Nodes list contain all nodes. In the first position (0) there is the depot
    private ArrayList<Node> nodes = new ArrayList<>();
    // Contain the distances Matrix
    private DistanceMatrix distances;
    // Matrix of savings
    private double matrix[][];

    public SavingsMatrix(Node depot, ArrayList<Node> nodes, DistanceMatrix distances){
        // The first nodes is the depot
        this.nodes.add(depot);
        this.nodes.addAll(nodes);
        // distance matrix
        this.distances = distances;

        this.matrix = new double[nodes.size()+1][nodes.size()+1];

        savingsHandler();
    }

    /**
     * This method calculate the savings matrix
     */
    private void savingsHandler() {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {

                if( j >= i) {
                    // nodes.get(0) is the depot
                    double distDepotI = distances.getDistance(nodes.get(0), nodes.get(i));
                    double distDepotJ = distances.getDistance(nodes.get(0), nodes.get(j));
                    double distIJ = distances.getDistance(nodes.get(i), nodes.get(j));
                    // Round the result with two value after coma
                    double saving = DoubleHandler.round((distDepotI + distDepotJ - distIJ), 2);

                    matrix[nodes.get(i).getIndex()][nodes.get(j).getIndex()] = saving;
                }
            }
        }
    }
        /**
         * Print starting by the i = 1 because the depot is not calculate in the savings matrix
         */
        public void print(){
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
}
