package com.ro.ClarkeWright.Core;

import java.util.ArrayList;

public class SavingsMatrix {

    private ArrayList<Node> nodes;
    private Node depot;
    private DistanceMatrix distances;
    private double matrix[][];

    public SavingsMatrix(Node depot, ArrayList<Node> nodes, DistanceMatrix distances){
        this.nodes = nodes;
        this.depot = depot;
        this.distances = distances;

        this.matrix = new double[nodes.size()+1][nodes.size()+1];

        computeSavings();
    }

    /**
     *
     */
    private void computeSavings() {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {

                if( j >= i) {
                    double distDepotI = distances.getDistance(depot, nodes.get(i));
                    double distDepotJ = distances.getDistance(depot, nodes.get(j));
                    double distIJ = distances.getDistance(nodes.get(i), nodes.get(j));

                    double saving = distDepotI + distDepotJ - distIJ;

                    matrix[nodes.get(i).getIndex()][nodes.get(j).getIndex()] = saving;
                }
            }
            // distance depot - nodo i
            // distance depot - nodo j
            // distance i - j
        }
    }
        /**
         *
         */
        public void printSavings(){
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
}
