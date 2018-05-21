package com.ro.clarkewright.core;
import com.ro.clarkewright.handler.DoubleHandler;
import com.ro.clarkewright.model.DistanceMatrix;
import com.ro.clarkewright.model.Node;
import com.ro.clarkewright.model.Route;
import com.ro.clarkewright.model.SavingsMatrix;
import java.util.ArrayList;

/**
 *
 */
public class ClarkeWrightParallel {
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

    /**
     *
     * @param instance
     */
    public ClarkeWrightParallel(Instance instance){
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
            mainRoutes.add( new Route(instance.getDepot(), instance.getNode(i), distanceMatrix));
        }
    }

    /**
     *
     */
    public void run(){
        // Per ogni elemento della savingList ordinata
        for(int i = 0; i < savingsMatrix.getOrderedSavingsList().size(); i++){

            // I due oggetti nodi della coppia [es: (4,5)] ottenendo i valori dalla matrice dei risparmi
            Node nodeA = nodes.get( savingsMatrix.getElementOrderedSavingList(i).get(0) );
            Node nodeB = nodes.get( savingsMatrix.getElementOrderedSavingList(i).get(1) );

            // Ottengo le rotte che contengono i due nodi da mergiare (eventualmente)
            Route routeA = mainRoutes.get(getIndexRoute(nodeA));
            Route routeB = mainRoutes.get(getIndexRoute(nodeB));

            // (i) Se i nodi A e B sono contenuti nella stessa route non rispettano la condizione (i), ovvero stanno sulla stessa route e non vanno mergiati
            if(routeA == routeB){
                //System.out.println("(i) Route: " + (nodeA.getIndex()) + " is not mergeable with route: " + (nodeB.getIndex()));
                continue;
            }

            // (iii) I nodi A e B devono essere rispettivamente i primi o ultimi della loro route, altrimenti non posso mergiarli. Condizione (iii)
            if(routeA.checkIsLast(nodeA) == false && routeB.checkIsFirst(nodeB) == false){
                //System.out.println("(iii) Route: " + (nodeA.getIndex()) + " is not mergeable with route: " + (nodeB.getIndex()));
                continue;
            }


            // (ii) Condizione (ii) per poter mergiare le route non devo superare la capacità del veicolo
            if(routeA.getDemand() + routeB.getDemand() > instance.getCapacity()){
                //System.out.println("(ii) Route: " + (nodeA.getIndex()) + " is not mergeable with route: " + (nodeB.getIndex()));
                continue;
            }

            //System.out.println("Route: " + (nodeA.getIndex()) + " is mergeable with route:" + (nodeB.getIndex()));

            routeA.merge(routeB.getRoutes());
            //routeA.print();
            mainRoutes.remove(routeB);

        }
    }

    /**
     *
     * @param n
     * @return
     */
    private int getIndexRoute(Node n){
        for(int i = 0; i < mainRoutes.size(); i++) {
            if(mainRoutes.get(i).checkContainNode(n) == true)
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


         System.out.println("-- ORDERED SAVING LIST --");
         savingsMatrix.printOrderedSavingsList();
         System.out.println("Size: " + mainRoutes.size());
         */

         for(int i = 0; i < mainRoutes.size(); i++)
            mainRoutes.get(i).print();


        double totalDistance = 0;
        for(int i = 0; i < mainRoutes.size(); i++)
            totalDistance = totalDistance + mainRoutes.get(i).getDistance();

        System.out.println("Total Distance: "  + DoubleHandler.round(totalDistance,2) );
    }
}
