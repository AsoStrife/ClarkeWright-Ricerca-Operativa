package com.ro.ClarkeWright.Core;

public class Node {

    private int index;
    private int x = 0;
    private int y = 0;
    private int demand = 0;

    /**
     * Costruttore vuoto
     */
    public Node(){

    }
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
    public void setDemand(int demand) {
        this.demand = demand;
    }
}
