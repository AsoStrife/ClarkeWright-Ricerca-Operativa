package com.ro.clarkewright.manager;

import com.ro.clarkewright.core.ClarkeWrightParallel;
import com.ro.clarkewright.core.ClarkeWrightSequential;
import com.ro.clarkewright.handler.DoubleHandler;
import com.ro.clarkewright.model.DistanceMatrix;
import com.ro.clarkewright.model.Node;
import com.ro.clarkewright.model.Route;
import com.ro.clarkewright.model.SavingsMatrix;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static String folder = "./data/";
    private static String inputFolder = folder + "A-VRP/";
    private static String outputFolder = folder + "output/";

    /**
     * Read a file passed as parameter (with path)
     * @param filename of the file to read
     * @return the buffer reader object
     */
    public static BufferedReader read(String filename) {

        String path = inputFolder + filename + ".vrp";

        FileReader fr;
        BufferedReader br = null;

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return br;
    }

    /**
     *
     */
    public static void write(String name, ClarkeWrightSequential cws, ClarkeWrightParallel cwp, double time1, double time2) throws FileNotFoundException {
        String filepath = outputFolder + name + ".txt";

        PrintWriter writer = new PrintWriter(filepath);

        writeSequential(writer, cws, time1);
        writer.println("-----------------------------------------");
        writer.println("");
        writeParallel(writer, cwp, time2);

        writer.close();

    }

    /**
     *
     * @param writer
     * @param cws
     */
    public static void writeSequential(PrintWriter writer, ClarkeWrightSequential cws, double time){
        ArrayList<Route> routes = cws.getMainRoutes();
        double totalDistance = cws.getTotalDistance();
        DistanceMatrix dm = cws.getDistanceMatrix();
        SavingsMatrix sm = cws.getSavingsMatrix();

        writer.println("ClarkeWright Sequential: \r\n ");

        //printDistanceMatrix(writer, dm);
        //printSavingMatrix(writer, sm);

        printRoutes(writer, routes);

        writer.println("Total cost: " + DoubleHandler.round(totalDistance,2));
        writer.println("Execution time: " + DoubleHandler.round(time,4));
        writer.println("");
    }

    /**
     *
     * @param writer
     * @param cwp
     */
    public static void writeParallel(PrintWriter writer, ClarkeWrightParallel cwp, double time){
        ArrayList<Route> routes = cwp.getMainRoutes();
        double totalDistance = cwp.getTotalDistance();
        DistanceMatrix dm = cwp.getDistanceMatrix();
        SavingsMatrix sm = cwp.getSavingsMatrix();

        writer.println("ClarkeWright Parallel: \r\n ");

        //printDistanceMatrix(writer, dm);
        //printSavingMatrix(writer, sm);

        printRoutes(writer, routes);

        writer.println("Total cost: " + DoubleHandler.round(totalDistance,2));
        writer.println("Execution time: " + DoubleHandler.round(time,4));
        writer.println("");
    }

    /**
     *
     * @param writer
     * @param dm
     */
    private static void printDistanceMatrix(PrintWriter writer, DistanceMatrix dm){
        writer.println("Distance Matrix: ");
        for (int i = 0; i < dm.getMatrix().length-1; i++) {
            for (int j = 0; j < dm.getMatrix()[i].length-1; j++) {
                writer.print(dm.getMatrix()[i][j] + " ");
            }
            writer.println("");
        }

        writer.println("");
    }

    /**
     *
     * @param writer
     * @param sm
     */
    private static void printSavingMatrix(PrintWriter writer, SavingsMatrix sm){
        writer.println("Savings Matrix: ");
        for (int i = 1; i < sm.getMatrix().length-2; i++) {
            for (int j = 1; j < sm.getMatrix()[i].length-1; j++) {
                writer.print(sm.getMatrix()[i][j] + " ");
            }
            writer.println("");
        }
        writer.println("");
    }

    /**
     *
     * @param writer
     * @param routes
     */
    private static void printRoutes(PrintWriter writer, ArrayList<Route> routes){
        writer.println("Routes: ");
        for(int i = 0; i < routes.size(); i++) {
            writer.print("Route: ");

            Route r = routes.get(i);
            ArrayList<Node> n = r.getRoutes();

            for(int j = 0; j < n.size(); j++){
                writer.print(n.get(j).getIndex() + " ");
            }
            writer.print("| Demand: " + DoubleHandler.round(r.getDemand(), 2));
            writer.print("| Cost: " + DoubleHandler.round(r.getDistance(), 2));
            writer.println("");
        }
        writer.println("");
    }
}


