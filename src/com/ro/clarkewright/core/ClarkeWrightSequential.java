package com.ro.clarkewright.core;
import com.ro.clarkewright.handler.DoubleHandler;
import com.ro.clarkewright.model.DistanceMatrix;
import com.ro.clarkewright.model.Node;
import com.ro.clarkewright.model.Route;
import com.ro.clarkewright.model.SavingsMatrix;
import java.util.ArrayList;

public class ClarkeWrightSequential {
    // Contains the .vrp file instance
    Instance instance;

    // All nodes with depot in position 0
    private ArrayList<Node> nodes = new ArrayList<>();

    // Contains the distance matrix
    private DistanceMatrix distanceMatrix;

    // Contains the saving matrix
    private SavingsMatrix savingsMatrix;

    // Contains the main routes
    private ArrayList<Route> mainRoutes = new ArrayList<>();

    /**
     * Constructor of the object
     * Generates the main routes
     * @param instance the instance of the file
     */
    public ClarkeWrightSequential(Instance instance){
        this.instance = instance;

        // Now nodes contains all nodes with depot in 0 position
        this.nodes.add(instance.getDepot());
        this.nodes.addAll(instance.getNodes());

        this.distanceMatrix = new DistanceMatrix(nodes);
        this.savingsMatrix = new SavingsMatrix(nodes, distanceMatrix);

        // generates the main routes
        mainRoutesHandler();
    }

    /**
     * Generates the main routes 0 -> i -> 0
     */
    public void mainRoutesHandler(){
        for(int i = 0; i < instance.nodeSize(); i++){
            mainRoutes.add( new Route(instance.getDepot(), instance.getNode(i), distanceMatrix));
        }
    }

    /**
     * Executes the algorithm
     */
    public void run(){
        // For the each element of the main routes
        for(int i = 0; i < mainRoutes.size(); i++){

            // The route will be merged with other nodes in the saving list
            Route route = mainRoutes.get(i);

            // For each element of the ordered savings list
            for(int j = 0; j < savingsMatrix.getOrderedSavingsList().size();j++){

                // These are the two object nodes of the pair [es: (4,5)] from which we take the savings value
                Node nodeA = nodes.get( savingsMatrix.getElementOrderedSavingList(j).get(0) );
                Node nodeB = nodes.get( savingsMatrix.getElementOrderedSavingList(j).get(1) );

                // Gets the routes which contain two nodes that eventually can be merged
                Route routeA = mainRoutes.get(getIndexRoute(nodeA));
                Route routeB = mainRoutes.get(getIndexRoute(nodeB));

                // (i) A e B can't stay in the same route, otherwise they can't be merged
                if(routeA == routeB)
                    continue;

                if(route == routeA){
                    // (ii) A e B must be the first or the last client of their route, otherwise they can't be merged
                    if(route.checkIsFirstOrLast(nodeA) == false)
                        continue;

                    // (iii) The capacity of the vehicle can't be overtaken
                    if(route.getDemand() + routeB.getDemand() > instance.getCapacity()){
                        continue;
                    }

                    // Merging of the routes removing the old main route
                    route.merge(routeB.getRoutes());
                    mainRoutes.remove(routeB);
                }

                if(route == routeB){
                    // (ii) A e B must be the first or the last client of their route, otherwise they can't be merged
                    if(route.checkIsFirstOrLast(nodeB) == false)
                        continue;

                    // (iii) The capacity of the vehicle can't be overtaken
                    if(route.getDemand() + routeA.getDemand() > instance.getCapacity()){
                        continue;
                    }

                    // Merging of the routes removing the old main route
                    route.merge(routeA.getRoutes());
                    mainRoutes.remove(routeA);
                }



            } // for j
        } // for i
    }

    /**
     * Getter
     * @param n the node of the route
     * @return the index of the mainRoutes which contains the node
     */
    private int getIndexRoute(Node n){
        for(int i = 0; i < mainRoutes.size(); i++) {
            if(mainRoutes.get(i).checkContainNode(n) == true)
                return i;
        }
        return -1;
    }

    /**
     * Getter
     * @return the array list of the main routes
     */
    public ArrayList<Route> getMainRoutes(){
        return mainRoutes;
    }

    /**
     * Getter
     * @return the total distance of one route
     */
    public double getTotalDistance(){
        double totalDistance = 0;
        for(int i = 0; i < mainRoutes.size(); i++)
            totalDistance = totalDistance + mainRoutes.get(i).getDistance();

        return totalDistance;
    }

    /**
     * Getter
     * @return the distance matrix object
     */
    public DistanceMatrix getDistanceMatrix(){
        return  distanceMatrix;
    }

    /**
     * Getter
     * @return the saving matrix object
     */
    public SavingsMatrix getSavingsMatrix(){
        return savingsMatrix;
    }

    /**
     * @Debug
     */
    public void debug(){
        /*
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

        System.out.println("Total Cost: "  + DoubleHandler.round(totalDistance,2) );
    }
}
