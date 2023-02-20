package com.napier.sudoku.test;
import com.napier.sudoku.models.SudokuGrid;
import com.napier.sudoku.models.memory.Tree;

import java.awt.event.KeyEvent;

public class Test {

    private static void listen(KeyEvent keyEvent){
        int aa = keyEvent.getKeyCode();
        if (aa == KeyEvent.VK_RIGHT){
            System.out.println("hello");
        }
        else {
            System.out.println(aa);
        }
    }
    private static void testTree() {
        Tree<Integer> integerTree = new Tree<>(0, true);
        integerTree.add(15);
        integerTree.add(140000);
        integerTree.add(1);
        integerTree.add(-34);
        integerTree.add(-45);
        integerTree.add(-465);
        integerTree.add(30);
        integerTree.add(16);
        integerTree.remove(-34);
        System.out.println("The answer is :: " + integerTree.contains(0) );
    }

    private static void testCPWArray () {
        SudokuGrid grid = new SudokuGrid(9,9);
        for (int [] row: grid.getGame()){
            for  (int column :row)
                System.out.print(column + " " );

            System.out.println();
        }

    }
    public static void main (String args []){
        testTree();
        System.out.println(  ("200, 90".compareTo("200, 90") ));
    }

}
