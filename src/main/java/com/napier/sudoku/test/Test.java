package com.napier.sudoku.test;
import com.napier.sudoku.GameEngine;
import com.napier.sudoku.SudokuEngine;
import com.napier.sudoku.models.Helper;
import com.napier.sudoku.models.Resizoku;
import com.napier.sudoku.models.SudokuGrid;
import com.napier.sudoku.models.memory.Tree;

import java.awt.desktop.SystemEventListener;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

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
        integerTree.add(25);
        integerTree.remove(-34);
        System.out.println("The answer is :: " + integerTree.contains(0));
    }

    private static void testArrayList(){
        Tree<Integer> integerTree = new Tree<>(null);
        integerTree.add(15);
        integerTree.add(140000);
        integerTree.add(1);
        integerTree.add(-34);
        integerTree.add(-45);
        integerTree.add(-465);
        integerTree.add(30);
        integerTree.add(25);
        integerTree.remove(-34);
        integerTree.forEach(e-> {
            System.out.println(e);
        });
    }

    private static void testCPWArray () {
        SudokuGrid grid = new SudokuGrid(25);
        for (int [] row: grid.getGame()){
            for  (int column :row)
                System.out.print(column + " " );

            System.out.println();
        }

    }

    /*
    Testing code generated by ChatGPT
    Not generating a code good enough
     */
    @Deprecated
    private static void testCHATGPT(){
        // TEST CHAT GPT CODE
        int[][] grid = new int[25][25];

        // Initialize the grid with numbers 1 to 25 in each row and column
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                grid[i][j] = (i % 25) + 1;
            }
        }

        // Shuffle the rows
        for (int i = 0; i < 25; i++) {
            Collections.shuffle(Arrays.asList(grid[i]));
        }

        // Transpose the array to shuffle the columns
        for (int i = 0; i < 25; i++) {
            for (int j = i + 1; j < 25; j++) {
                int temp = grid[i][j];
                grid[i][j] = grid[j][i];
                grid[j][i] = temp;
            }
        }

        // print array of grid
        for (int i = 0; i < grid.length; i++){
            for (int i1 = 0; i1 <grid.length; i1 +=3){
                System.out.print(grid[i][i1]);
                System.out.print(grid[i][i1+1]);
                System.out.print(grid[i][i1+2]);
                System.out.print("  ");
            }
            System.out.println("");
            if ((i+1) % 3 == 0 ){
                System.out.println("\n");
            }
        }
    }



    /**
     * Testing code generated by ChatGPT
     * @param args
     */
    public static void main (String args []){
//
//        SudokuGrid grid = new SudokuGrid(25);
//        long i = 0; long i1= 0;
//        int xAxis = 25; int yAxis = 25;
//        i = System.currentTimeMillis();
//        {
//            int[][] arr = grid.getGame();
//            for (int row = 0; row < xAxis; row++) {
//                Random rand = new Random();
//                int x = rand.nextInt(2);
//                if (x==1)
//
//                    for (int column = 0; column < yAxis; column += 2) {
//                        arr[row][column] = 0;
//                    }
//
//                else{
//                    for (int column = 1; column < yAxis; column += 2) {
//                        arr[row][column] = 0;
//                    }
//                }
//            }
//            i1 = System.currentTimeMillis();
//            System.out.println("Generation Time: " + (i1-i) +"ms");
//            Helper.printSudoku(arr);
//        }



        long i = System.currentTimeMillis();
        Resizoku grid = new Resizoku( 36);
        grid.solveBruteForce();
        long i1 = System.currentTimeMillis();
        System.out.println(i1-i + " ms");
        Helper.printSudoku(grid.grid);


    }
}
