package com.ro.clarkewright.core;
import com.ro.clarkewright.model.Node;
import com.ro.clarkewright.manager.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    private Node depot; //  new Node(0, 1, -1);
    //private String eof;

    /**
     * Constructor of the Instance
     * @param filename the path of the file to read
     */
    public Instance(String filename){
        // Reads the file
        readFile(filename);
        // Concatenates the BufferReader line into only one string with \n as end line
        String string = file.lines().collect(Collectors.joining("\n"));
        // Sets the main attribute as name, comment, type, dimension etc
        this.setInstanceMainAttributes(string);
        // Sets the list of Nodes
        this.setNodes(string);
        // Sets the list of demands
        this.setDemands(string);


        // Depot is the first nodes
        depot = nodes.get(0);
        // Remove from the list of nodes the depot
        nodes.remove(0);
        // Remove from demands the depot
        demands.remove(0);
    }

    /**
     * Sets the attribute BufferReader file
     * @param filename the path of the file to read
      */
    private void readFile(String filename){
        this.file = FileManager.read(filename);
    }

    /**
     * Sets the attributes of the class which represents data in VRP format as the following
     * ATTRIBUTO : VALORE
     * @param string the string taken from the VRP
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
     * For each line of the VRP file, creates a Node element
     * with index, x-coordinate and y-coordinate (passed as strings and casted in the constructor)
     * @param string the string taken from the VRP
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
     * For each line of the VRP vile, sets the demands for each node
     * @param string the string taken from the VRP
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
     * Getter e setter for each class attributes
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
     * Getter
     * @return all the nodes as ArrayList
     */
    public ArrayList<Node> getNodes(){
        return this.nodes;
    }

    /**
     * Getter
     * @param i an index
     * @return the node at position i in the list
     */
    public Node getNode(int i){
        return this.nodes.get(i);
    }

    /**
     * Getter
     * @return depot Node
     */
    public Node getDepot() {
        return depot;
    }

    /**
     * Getter
     * @return the size of the node list
     */
    public int nodeSize(){
        return this.nodes.size();
    }
}
