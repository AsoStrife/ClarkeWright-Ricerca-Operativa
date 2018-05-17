package com.ro.clarkewright.core;

import com.ro.clarkewright.model.Node;
import com.ro.clarkewright.manager.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Andrea Corriga
 */
public class Instance {
    private BufferedReader file;

    private String name;
    private String comment;
    private String type;
    private int dimension;
    private String edgeWeightType;
    private int capacity;

    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Integer> demands = new ArrayList<>();

    private Node depot = new Node(0, 1, -1);
    //private String eof;

    /**
     *
     * @param filePath
     * @throws IOException
     */
    public Instance(String filePath){
        // Read the file
        readFile(filePath);
        // Concatenate the BufferReader line into only one string with \n as end line
        String string = file.lines().collect(Collectors.joining("\n"));
        // Set the main attribute as name, comment, type, dimension etc
        this.setInstanceMainAttributes(string);
        // Set the list of Nodes
        this.setNodes(string);
        // Set the list of demands
        this.setDemands(string);
    }

    /**
     * Set the attribute BufferReader file
     * @param filePath
      */
    private void readFile(String filePath){
        this.file = FileManager.read(filePath);
    }

    /**
     * Setto gli attributi della classe che nel formato VRP corrispondono alle prime righe
     * formattate ATTRIBUTO : VALORE
     * @param string
     */
    private void setInstanceMainAttributes(String string){
        String regex = "([A-Z]*) : (.*)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            switch (matcher.group(1)){
                case "NAME":
                    this.name = matcher.group(2);
                    break;
                case "COMMENT":
                    this.comment = matcher.group(2);
                    break;
                case "TYPE":
                    this.type = matcher.group(2);
                    break;
                case "DIMENSION":
                    this.dimension = Integer.parseInt(matcher.group(2));
                    break;
                case "EDGE_WEIGHT_TYPE":
                    this.edgeWeightType = matcher.group(2);
                    break;
                case "CAPACITY":
                    this.capacity = Integer.parseInt(matcher.group(2));
                    break;
            }
        }
    }

    /**
     * Creo per ogni nodo presente nel file VRP un elemento nella lista nodes
     * aggiungendo index, x e y (passati come stringa e castati nel costruttore della classe)
     * @param string
     */
    private void setNodes(String string){
        String regex = "^ ([0-9]*\\S) ([0-9]*\\S) ([0-9]*\\S)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            nodes.add( new Node(matcher.group(1), matcher.group(2), matcher.group(3)) );
        }
    }

    /**
     * Setto sia il campo demand della lista nodes sia aggiungo il valore
     * nella lista demands
     * @param string
     */
    private void setDemands(String string){
        String regex = "^([0-9]*\\S) ([0-9]*\\S)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        int i = 0;
        while (matcher.find()) {
            demands.add(Integer.parseInt(matcher.group(2)));
            nodes.get(i).setDemand(Integer.parseInt(matcher.group(2)));
            i++;
        }
    }

    /**
     *
     * Getter e setter generici degli attributi della classe
     *
     */

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDimension() {
        return this.dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public String getEdgeWeightType() {
        return this.edgeWeightType;
    }

    public void setEdgeWeightType(String edgeWeightType) {
        this.edgeWeightType = edgeWeightType;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     *
     * @return tutti i nodi come ArrayList
     */
    public ArrayList<Node> getNodes(){
        return this.nodes;
    }

    /**
     *
     * @param i
     * @return un nodo della lista
     */
    public Node getNode(int i){
        return this.nodes.get(i);
    }

    /**
     *
     * @return depot Node
     */
    public Node getDepot() {
        return depot;
    }

    /**
     *
     * @return the size of the node list
     */
    public int nodeSize(){
        return this.nodes.size();
    }
}
