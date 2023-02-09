package com.napier.sudoku.test;

import com.napier.sudoku.SudokuGenerator;
import com.napier.sudoku.models.SudokuGrid;
import com.napier.sudoku.models.Tree;
import com.napier.sudoku.random.Randomiser;

import java.util.Arrays;

public class Test {

    private static void testTree() {
        Tree<Integer> integerTree = new Tree<>(-1234, true);
        integerTree.add(15);
        integerTree.add(140000);
        integerTree.add(1);
        integerTree.add(-34);
        integerTree.add(45);
        integerTree.add(30);
        integerTree.add(16);
        integerTree.remove(-348);
        System.out.println("The answer is :: " + integerTree.getSmallest(-1234));
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
        // tests here
        testCPWArray();

    }

}
