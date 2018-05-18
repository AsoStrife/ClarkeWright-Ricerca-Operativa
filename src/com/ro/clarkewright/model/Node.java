package com.ro.clarkewright.model;

public class Node {

    private int index;
    private int x = 0;
    private int y = 0;
    private int demand = 0;


    /**
     * Costruttore che prende i valori stringa degli attributi
     * @param index
     * @param x
     * @param y
     */
    public Node(String index, String x, String y){
        this.index = Integer.parseInt(index);
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }

    /**
     * Costruttore che prende i valori interi degli attributi
     * @param index
     * @param x
     * @param y
     */
    public Node(int index, int x, int y){
        this.index = index;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter & setter per index
     * @return
     */
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    /***
     * Getter & setter per x
     * @return
     */
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter & setter per y
     * @return
     */
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter & setter per demand
     * @return
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

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Node)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Node n = (Node) o;

        // Compare the data members and return accordingly
        if( n.getX() == x & n.getY() == y & n.getDemand() == demand)
            return true;
        else
            return false;
    }
}
