package com.napier.sudoku.models;
import com.napier.sudoku.models.memory.Tree;

/**
 *  Helper - The purpose of the helper class is to help check the Sudoku Grid
   Author : Zin Lin Htun
   @matric : 40542237@live.napier.ac.uk */
public class Helper {

    /**
    Achive the first step of 1 in algo.txt
    @param array :: int [][], array to change.
     */
    public static int [][] shuffleStep1 (int [][] array) {

        // Nae checks and 9 movements:: Smashingggg!
        for (int i = 0; i < array.length; i += 3) {
            // skips the array by 3
            int topRowOfSquare = i;
            for (int column = 0; column< array[i].length; column+=3) {

                int desireColumn = column+2;
                int swap = array[topRowOfSquare + 1][desireColumn];
                array[topRowOfSquare + 1][desireColumn] = array[topRowOfSquare + 2][desireColumn];
                array[topRowOfSquare + 2][desireColumn] = swap;
            }
        }
        return array;
    }
    /**
    Achive the first step of 2 in algo.txt
    @param array :: int [][], array to change.
     */
    public static int [][] shuffleStep2(int [][] array){
        {
            int [] a1 = array[0];
            int [] a2 = array[3];
            int [] a3 = array[6];
            array[0] = a3;
            array[3] = a1;
            array[6] = a2;
        }
        return array;
    }

    /**
     Achive the first step of 3 in algo.txt
    @param array :: int [][], array to change.
     */
    public static int [][] shuffleStep3(int [][] array){
        for (int i =0; i <array.length; i += 3){
            // switch 1
            int quo = i/3;
            int rem = quo % 3;

            if (rem == 1){
                int [] a1 = array[i];
                array[i] = array[i+1];
                array[i+1] = a1;
            }
            else if (rem ==2 ){
                int [] a1 = array[i];
                int [] a2 = array[i+1];
                int [] a3 = array[i+2];
                array[i] = a2;
                array[i+1] = a3;
                array[i+2] = a1;
            }

        }
        return array;
    }

    /**
    Achive the first step of 4 in algo.txt
    @param array :: int [][], array to change.
    // 9 moves
     */
    public static int [][] shuffleStep4(int [][] array) {
        // 4 a
        {
            for (int i = 0; i < array.length; i += 3) {
                //+3 so more efficient
                int quo = i / 3;
                int rem = quo % 3;

                if (rem == 0) {
                    for (int ii = 0; ii < array[i].length; ii += 3) {
                        // 1st line of squares

                        int i1 = array[i+2][ii];
                        int i2 = array[i+2][ii + 1];
                        int i3 = array[i+2][ii + 2];
                        array[i+2][ii] = i2;
                        array[i+2][ii + 1] = i3;
                        array[i+2][ii + 2] = i1;
                    }
                }

                else if (rem == 2 ||  rem ==1) {
                    for (int ii = 0; ii < array[i].length; ii += 3) {
                        // 1st line of squares

                        int i2 = array[i][ii + 1];
                        int i3 = array[i][ii + 2];

                        array[i][ii + 1] = i3;
                        array[i][ii + 2] = i2;
                    }
                }
            }
        }
        // 4 b
        {
            for (int i = 0; i < array.length; i+=3){
                int i1 = array[7][i];
                int i2 = array[6][i + 1];
                int i3 = array[7][i + 2];
                array[7][i] = i2;
                array[6][i + 1] = i3;
                array[7][i + 2] = i1;
            }
        }

        return array;
    }


    /**
    This method adds the number proceeding from x till y in a balanced way
    @param min - minimum number
    @param max - maximum number
     */
    public static Tree<Integer> balancedTree(int min, int max) {
        Tree<Integer> tree = new Tree<>(null); // null value is the starting value
        int mean = (min + max)/2;
        tree.add(mean); // adding mid as a root so the tree is balanced
        // field :: left
        {
            // left sub tree
            for (int i = min; i < mean; i++ ){
                tree.add(i);
            }
        }
        // field :: right
        {
            for (int i = max; i > mean; i-- ){
                tree.add(i);
            }
        }

        return tree;
    }

    private static Tree<Integer> _checkAgainstTree (int [][] array, Vector cell, int yAxis , int xAxis){
        Tree<Integer> checkAgainst = new Tree<Integer>(null);
        // for every row
        for (int row = 0; row < yAxis; row++) {
            if (!(row  == cell.getRow())){
                // don't check for itself
                if (array[row][cell.getColumn()]!= 0){
                    // only if the value is zero.
                    checkAgainst.add(array[row][cell.getColumn()]);
                }
            }
        }
        // for every column
        for (int column = 0; column < xAxis; column++){
            if (!(column  == cell.getColumn())){
                // don't check for itself
                if (array[cell.getRow()][column]!= 0){
                    // only if the value is zero.
                    checkAgainst.add(array[cell.getRow()][column]);
                }
            }
        }
        // for every square
        {
            int quoY = cell.getRow()/3;
            int quoX = cell.getColumn() /3;
            int squareY = (quoY)*3;
            int squareX = (quoX)*3;
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    if (!(i+squareY == cell.getRow() && squareX+ j == cell.getColumn())){
                        // don't check for itself
                        if (array[squareY+i][squareX+j]!= 0){
                            // only if the value is zero.
                            checkAgainst.add(array[squareY+i][squareX+j]);
                        }
                    }
                }
            }

        }
        return checkAgainst;
    }

    /**
    Check if the cell grid is valid
    @param array
    @param cell
    @param yAxis, @param xAxis
     */
    public static boolean check (int [][] array, Vector cell, int yAxis , int xAxis){
        boolean ans = false;
        int desireColumn = cell.getColumn(); int desireRow = cell.getRow();
        // set up tree
        Tree <Integer> checkAgainst = _checkAgainstTree(array, cell, yAxis, xAxis);
        // if duplicates are to occur than it won't add helping both time and space complexity, since a tree has to be unique.
        int value = array[desireRow][desireColumn];
        if (!checkAgainst.contains(value) && value != 0){
            ans = true;
        }

        return ans;
    }

    /**
    Check if the game has eneded
    @param array
    @param og
    @param yAxis, @param xAxis
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
        if (ans)
            System.out.println("Game's solved");

        return ans;
    }

    /**
    get all the possibilities
    @param
     */
    public static void printPossibilities(int [][] array,  Vector cell, int yAxis , int xAxis ) {

        int desireColumn = cell.getColumn(); int desireRow = cell.getRow();
        System.out.println("possibilities are :: ");
        // set up tree
        Tree <Integer> checkAgainst = _checkAgainstTree(array, cell, yAxis, xAxis);
        // if duplicates are to occur than it won't add helping both time and space complexity, since a tree has to be unique.

        for (int i = 1; i < xAxis +1; i ++) {
            if (!checkAgainst.contains(i))
                System.out.print(i + " ");
        }
        System.out.println("\n");

    }

    /**
    Print the sudoku 9x9 in fashion
    @param array
     */
    public static void printSudoku9x9 (int [][]array) {
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j +=3 ){
                try {
                    System.out.print(array[i][j] + " ");
                    Thread.sleep(50);
                    System.out.print(array[i][j + 1] + " ");
                    Thread.sleep(50);
                    System.out.print(array[i][j + 2] + " ");
                    Thread.sleep(50);
                    System.out.print("| ");
                    Thread.sleep(50);

                }catch (Exception err){
                    //Do Abs Nth
                }
            }
            System.out.print("\n");
            if ((i+1) % 3 == 0)
                System.out.println("-----------------------");
        }
    }
}
