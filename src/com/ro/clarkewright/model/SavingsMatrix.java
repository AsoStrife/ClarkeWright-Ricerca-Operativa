package com.ro.clarkewright.model;

import com.ro.clarkewright.handler.DoubleHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        order();
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

        public void order(){
            int rows = nodes.size()-1;
            int cols = nodes.size()-1;

            List<List<Integer>> l = IntStream.range(0, rows).mapToObj(i ->
                    IntStream.range(0, cols).mapToObj(j ->
                            new double[]{i, j, matrix[rows][cols]}
                    )
            )
            .flatMap(x -> x).filter(t -> t[0] < t[1])
            .sorted((a, b) -> Double.compare(b[2], a[2]))
            .map(a -> Arrays.asList((int) a[0], (int) a[1]))
            .collect(Collectors.toList());

            l.forEach(System.out::println);
        }
}
