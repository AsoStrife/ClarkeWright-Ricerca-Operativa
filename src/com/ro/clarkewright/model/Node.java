package com.ro.clarkewright.model;

public class Node {

    private int index;
    private int x = 0;
    private int y = 0;
    private int demand = 0;


    /**
     * Constructor of the Node object which takes the parameters as String type
     * @param index the id of the node
     * @param x x-coordinate of the point
     * @param y y-coordinate of the point
     */
    public Node(String index, String x, String y){
        this.index = Integer.parseInt(index) - 1;
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }

    /**
     * Constructor of the Node object which takes the parameters as Int type
     * @param index the id of the node
     * @param x x-coordinate of the point
     * @param y y-coordinate of the point
     */
    public Node(int index, int x, int y){
        this.index = index;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter & setter for the index
     */
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    /***
     * Getter & setter for the x-coordinate
     */
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter & setter for the y-coordinate
     */
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter & setter for the demand of a client
     */
    public int getDemand() {
        return demand;
    }
    public void setDemand(int demand){
        this.demand = demand;
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if "o" is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Node)) {
            return false;
        }

        // typecast "o" to Complex so that we can compare data members
        Node n = (Node) o;

        // Compare the data members and return accordingly
        if( n.getX() == x & n.getY() == y & n.getDemand() == demand)
            return true;
        else
            return false;
    }
}
