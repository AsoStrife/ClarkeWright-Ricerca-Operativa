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
    // Questa lista contiene gli indici della matrice in ordine decrescente
    private List<List<Integer>> orderedSavingsList;

    public SavingsMatrix(ArrayList<Node> nodes, DistanceMatrix distances){

        this.nodes.addAll(nodes);
        // distance matrix
        this.distances = distances;

        this.matrix = new double[nodes.size()+1][nodes.size()+1];

        // Generate the saving matrix
        savingsHandler();
        // Generate the list that contain the element of savings matrix in decrease order
        orderedSavingsListHandler();
    }

    /**
     * This method calculate the savings matrix
     */
    private void savingsHandler() {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {

                if( j > i) {
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
     * Genera il vettore contenente per ogni posizione gli indici della matrice
     */
    public void orderedSavingsListHandler(){
        int rows = nodes.size();
        int cols = nodes.size();

        // Ordino i risultati con una lambda
        orderedSavingsList = IntStream.range(1, rows).mapToObj(i ->
                IntStream.range(0, cols).mapToObj(j ->
                        new double[]{i, j, matrix[i][j]}
                )
        )
        .flatMap(x -> x).filter(t -> t[0] < t[1])
        .sorted((a, b) -> Double.compare(b[2], a[2]))
        .map(a -> Arrays.asList((int) a[0], (int) a[1]))
        .collect(Collectors.toList());

        //l.forEach(System.out::println);
    }

    /**
     *
     * @return
     */
    public double[][] getMatrix(){
        return matrix;
    }

    /**
     *
     * @return
     */
    public double getElementMatrix(int i, int j){
       return matrix[i][j];
    }

    /**
     *
     * @return
     */
    public List<List<Integer>> getOrderedSavingsList(){
        return orderedSavingsList;
    }

    public List<Integer> getElementOrderedSavingList(int i){
        return orderedSavingsList.get(i);
    }

    /**
     * Print the matrix
     */
    public void print(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Print the orderedSavingsList
     */
    public void printOrderedSavingsList(){
        for(int i = 0; i < orderedSavingsList.size(); i++){
            System.out.println("Coppia: " + orderedSavingsList.get(i) + " valore: " + matrix[orderedSavingsList.get(i).get(0)][orderedSavingsList.get(i).get(1)]);
        }
    }
}
