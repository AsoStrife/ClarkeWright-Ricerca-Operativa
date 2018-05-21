package com.ro.clarkewright.model;
import com.ro.clarkewright.handler.DoubleHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SavingsMatrix {

    // Nodes list with all nodes. In position (0) there is the depot
    private ArrayList<Node> nodes = new ArrayList<>();
    // Distance matrix object
    private DistanceMatrix distances;
    // Double matrix to contain the savings between all nodes
    private double matrix[][];
    // Contains the position of the savings in the matrix in decreasing fashion
    private List<List<Integer>> orderedSavingsList;

    /**
     * Constructor of the savings matrix
     * @param nodes the list of all the nodes
     * @param distances the distance matrix
     */
    public SavingsMatrix(ArrayList<Node> nodes, DistanceMatrix distances){

        // Initialization
        this.nodes.addAll(nodes);
        this.distances = distances;
        // Creation of the matrix
        this.matrix = new double[nodes.size()+1][nodes.size()+1];

        // Generates the saving matrix
        savingsHandler();
        // Generates the list that contain the element of savings matrix in decreasing fashion
        orderedSavingsListHandler();
    }

    /**
     * Calculates the savings matrix using the following formula:
     * s(i,j) = c(0,i) + c(0,j) - c(i,j)
     */
    private void savingsHandler() {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {

                if( j > i) {
                    // nodes.get(0) is the depot
                    double distDepotI = distances.getDistance(nodes.get(0), nodes.get(i));
                    double distDepotJ = distances.getDistance(nodes.get(0), nodes.get(j));
                    double distIJ = distances.getDistance(nodes.get(i), nodes.get(j));
                    // Rounds the result with two digits after comma
                    double saving = DoubleHandler.round((distDepotI + distDepotJ - distIJ), 2);

                    matrix[nodes.get(i).getIndex()][nodes.get(j).getIndex()] = saving;
                }
            }
        }
    }

    /**
     * Sorts ther savings list using a lambda expression
     */
    public void orderedSavingsListHandler(){
        int rows = nodes.size();
        int cols = nodes.size();

        orderedSavingsList = IntStream.range(1, rows).mapToObj(i ->
                IntStream.range(0, cols).mapToObj(j ->
                        new double[]{i, j, matrix[i][j]}
                )
        )
        .flatMap(x -> x).filter(t -> t[0] < t[1])
        .sorted((a, b) -> Double.compare(b[2], a[2]))
        .map(a -> Arrays.asList((int) a[0], (int) a[1]))
        .collect(Collectors.toList());

        // @Debug
        //orderedSavingsList.forEach(System.out::println);
    }


    /**
     * Getter
     * @return the ordered savings list
     */
    public List<List<Integer>> getOrderedSavingsList(){
        return orderedSavingsList;
    }

    /**
     * Getter
     * @param i index of the element
     * @return the element at index i of the list
     */
    public List<Integer> getElementOrderedSavingList(int i){
        return orderedSavingsList.get(i);
    }

    /**
     * @Debug
     * Prints the savings matrix
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
     * @Debug
     * Prints the orderedSavingsList
     */
    public void printOrderedSavingsList(){
        for(int i = 0; i < orderedSavingsList.size(); i++){
            System.out.println("Coppia: " + orderedSavingsList.get(i) + " valore: " + matrix[orderedSavingsList.get(i).get(0)][orderedSavingsList.get(i).get(1)]);
        }
    }
}
