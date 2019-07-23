package com.coinbase.interview.component.util;

public class Maze {

    private int[][] grid;
    private int row, col;

    public Maze(int[][] grid){
        this.grid = grid;
        this.row = grid.length;
        this.col = grid[0].length;
    }


    public int getRow(){
        return row;
    }


    public int getCol(){
        return col;
    }

    public boolean isWall(Node node){
        if (grid[node.i][node.j] == 0){
            return false;
        }
        return true;
    }

    public boolean isEnd(Node node){
        if (node.i == row && node.j == col){
            return true;
        }
        return false;
    }
}
