package com.napier.sudoku.models;
import com.napier.sudoku.models.memory.Tree;

/* Helper - The purpose of the helper class is to help check the Sudoku Grid
   Author : Zin Lin Htun
   @matric : 40542237@live.napier.ac.uk */
public class Helper {

    /*
    Check if the cell grid is valid
    <param> array
    <param> cell
    <param> yAxis, <param> xAxis
     */
    public static boolean check (int [][] array, Vector cell, int yAxis , int xAxis){
        boolean ans = false;
        int desireColumn = cell.getColumn(); int desireRow = cell.getRow();
        // set up tree
        Tree <Integer> checkAgainst = new Tree<Integer>(null);
        // for every row
        for (int row = 0; row < yAxis; row++) {
            if (!(row  == cell.getRow())){
                // don't check for itself
                if (array[row][desireColumn] != 0){
                    // only if the value is zero.
                    checkAgainst.add(array[row][desireColumn]);
                }
            }
        }
        // for every column
        for (int column = 0; column < xAxis; column++){
            if (!(column  == cell.getColumn())){
                // don't check for itself
                if (array[desireRow][column] != 0){
                    // only if the value is zero.
                    checkAgainst.add(array[desireRow][column]);
                }
            }
        }
        // if duplicates are to occur than it won't add helping both time and space complexity, since a tree has to be unique.
        int value = array[desireRow][desireColumn];
        if (!checkAgainst.contains(value) && value != 0){
            ans = true;
        }

        return ans;
    }

    /*
    Check if the game has eneded
    <param> array
    <param> og
    <param> yAxis, <param> xAxis
     */
    public static boolean checkGamneEnd (int [][] array, Tree<Vector> og, int yAxis , int xAxis) {
        boolean ans = true;
        // og meeans original
        for (int row = 0; row < yAxis; row ++ ){
            for (int column = 0; column < xAxis; column ++){
                Vector cell = new Vector(row, column);
                if (!og.contains(cell)){
                    if (!check(array, cell, yAxis, xAxis)){
                        return false;
                    }
                }

            }
        }

        return ans;
    }

    /*
    get all the possibilities
    <param>
     */
    public static void printPossibilities(int [][] array,  Vector cell, int yAxis , int xAxis ) {

        int desireColumn = cell.getColumn(); int desireRow = cell.getRow();
        System.out.println("possibilities are :: ");
        // set up tree
        Tree <Integer> checkAgainst = new Tree<Integer>(null);
        // for every row
        for (int row = 0; row < yAxis; row++) {
            if (!(row  == cell.getRow())){
                // don't check for itself
                if (array[row][desireColumn] != 0){
                    // only if the value is zero.
                    checkAgainst.add(array[row][desireColumn]);
                }
            }
        }
        // for every column
        for (int column = 0; column < xAxis; column++){
            if (!(column  == cell.getColumn())){
                // don't check for itself
                if (array[desireRow][column] != 0){
                    // only if the value is zero.
                    checkAgainst.add(array[desireRow][column]);
                }
            }
        }
        // if duplicates are to occur than it won't add helping both time and space complexity, since a tree has to be unique.

        for (int i = 1; i < xAxis +1; i ++) {
            if (!checkAgainst.contains(i))
                System.out.print(i + " ");
        }
        System.out.println("\n");

    }

}
