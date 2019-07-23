package com.coinbase.interview.component.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FindPath {

    private LinkedList<Node> open = new LinkedList<>();
    private List<Node> visited = new ArrayList<>();
    private Boolean finished = false;

    //start from top-left
    private Node start = new Node(0, 0);

    public void findPath(Maze grid){
        start.g = 0;
        start.h = calculateH(start.i, start.j, grid);
        start.f = start.g + start.h;
        open.push(start);

        while(!open.isEmpty()){

            Node current = open.pop();
            visited.add(current);

            /*
            Top -       (i-1, j)
            Bottom -    (i+1, j)
            Left -      (i, j-1)
            Right -     (i, j+1)
            */

            List<Node> adj = new ArrayList<>();
            adj.add(new Node(current.i -1, current.j));
            adj.add(new Node(current.i +1, current.j));
            adj.add(new Node(current.i, current.j -1));
            adj.add(new Node(current.i, current.j +1));

            adj
                .forEach(temp -> {
                    int tempG, tempH, tempF;

                    // Not a wall + not outside the maze
                    if (isValid(temp, grid)){
                        if (grid.isEnd(temp)){
                            temp.setParent(current);
                            System.out.print("Found it");
                            finished = true;
                        }
                        //adjacent node is not visited
                        else if (!visited.contains(temp)){
                            tempG = current.g + 1;
                            tempH = calculateH(temp.i, temp.j, grid);
                            tempF = tempG + tempH;

                            if (temp.f > tempF) {
                                temp.g = tempG;
                                temp.h = tempH;
                                temp.f = tempF;
                                temp.setParent(current);
                                open.push(temp);
                                System.out.printf("added to open: (" +temp.i +", " + temp.j +")");
                            }
                        }
                    }

                });
        }

        visited.forEach(a -> {
            System.out.print("(" + a.i + ", " + a.j + ")" );
            System.out.printf("  ");
        });

    }

    private boolean isValid(Node temp, Maze grid) {
        boolean isOutside = (temp.i >= 0) && (temp.i < grid.getRow()) && (temp.j >= 0) && (temp.j < grid.getCol());

        return isOutside && grid.isWall(temp);
    }

    private int calculateH(int row, int col, Maze grid) {

        return Math.abs(grid.getRow() - row) + Math.abs(grid.getCol() - col);


    }


}
