package com.coinbase.interview.component.util;

public class Node {

    Integer i,j;
    private Node parent;
    Integer f, g, h;

    Node(int i, int j){
        this.i = i;
        this.j = j;
    }

    void setParent(Node parent){
        this.parent = parent;
    }


    public boolean hasParent(){
        return this.parent != null;
    }




}
