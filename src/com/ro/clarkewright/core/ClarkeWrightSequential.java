package com.ro.clarkewright.core;

import com.ro.clarkewright.model.DistanceMatrix;
import com.ro.clarkewright.model.Node;
import com.ro.clarkewright.model.Route;
import com.ro.clarkewright.model.SavingsMatrix;
import java.util.ArrayList;

public class ClarkeWrightSequential {
    // Contain the .vrp file instance
    Instance instance;

    // All nodes with depot in position 0
    private ArrayList<Node> nodes = new ArrayList<>();

    // This object contain the distance matrix
    private DistanceMatrix distanceMatrix;

    // This object contain the saving matrix
    private SavingsMatrix savingsMatrix;

    // Contain the main routes
    private ArrayList<Route> mainRoutes = new ArrayList<>();

    private ArrayList<Route> finalRoutes = new ArrayList<>();

    public ClarkeWrightSequential(Instance instance){
        this.instance = instance;

        // Now nodes contain all nodes with depot in 0 position
        this.nodes.add(instance.getDepot());
        this.nodes.addAll(instance.getNodes());

        this.distanceMatrix = new DistanceMatrix(nodes);
        this.savingsMatrix = new SavingsMatrix(nodes, distanceMatrix);

        // generate the main routes
        mainRoutesHandler();
    }

    /**
     * Generate the main routes 0 -> i -> 0
     */
    public void mainRoutesHandler(){
        for(int i = 0; i < instance.nodeSize(); i++){
            mainRoutes.add( new Route(instance.getDepot(), instance.getNode(i), distanceMatrix);
        }
    }


    public void run(){
        // Per ogni elemento della savingList ordinata
        for(int i = 0; i < savingsMatrix.getOrderedSavingsList().size(); i++){

            // I due oggetti nodi della coppia [es: (4,5)] ottenendo i valori dalla matrice dei risparmi
            Node nodeA = nodes.get( savingsMatrix.getElementOrderedSavingList(i).get(0) );
            Node nodeB = nodes.get( savingsMatrix.getElementOrderedSavingList(i).get(1) );

            // Ottengo le rotte che contengono i due nodi da mergiare (eventualmente)
            //Route routeA = mainRoutes.get(indexRoute(nodeA.getIndex()));
            //Route routeB = mainRoutes.get(indexRoute(nodeB.getIndex()));

            /*
            if(routeA.isMergeable(nodeA, nodeB) == true){
                System.out.println("Route: " + (nodeA.getIndex()) + " is mergeable with route:" + (nodeB.getIndex()));

                // CONTROLLO CHE CI SIA UNA finalRoute con uno di questi due nodi. Se non c'è la creo altrimenti aggiungo il nodo in coda
                if(finalRoutes.size() == 0){

                }
                Route finalA = finalRoutes.get(checkExistFinalRouteWithNode(nodeA.getIndex()));
                Route finalB = finalRoutes.get(checkExistFinalRouteWithNode(nodeA.getIndex()));

                routeA.addNode(nodeB);
            }
            else {
                System.out.println("Route: " + (nodeA.getIndex()) + " is not mergeable with route:" + (nodeB.getIndex()));
            }
               */
            //return;
        }
    }

    private int checkExistFinalRouteWithNode(int index){
        for(int i = 0; i < finalRoutes.size(); i++) {
            if(mainRoutes.get(i).checkContainNode(index) == true)
                return i;
        }
        return -1;
    }

    /**
     * DEBUG METHOD
     */
    public void debug(){
        /**
        System.out.println("-- DISTANCE MATRIX --");
        distanceMatrix.print();

        System.out.println("-- SAVINGS MATRIX --");
        savingsMatrix.print();
        **/

        //System.out.println("-- ORDERED SAVING LIST --");
        //savingsMatrix.printOrderedSavingsList();
        for(int i = 0; i < mainRoutes.size(); i++)
            mainRoutes.get(i).print(i);
    }
}
