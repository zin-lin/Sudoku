package com.napier.sudoku.models;
import com.napier.sudoku.models.memory.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  Helper - The purpose of the helper class is to help check the Sudoku Grid
   Author : Zin Lin Htun
   @matric : 40542237@live.napier.ac.uk */
public class Helper {

    /**
     * This method will get the number of each needed number
     * @param array
     * @param game
     * @param req
     * @return
     */
    public static int getNumberOfGaps (int [][] array, int [][] game, int req ) {
        int count = 0;
        int x = game.length;
        int y = game[0].length;
        for (int row = 0;row< x; row++){
            for (int col = 0; col < y; col++){
                if (array[row][col] == req)
                    count++;
            }
        }

        int totalCount = x < y ? x: y;
        System.out.println("On board: " + count);
        System.out.println("Should have: " + totalCount);
        return totalCount - count;
    }


    /**
    Achive the first step of 1 in algo.txt
    @param array :: int [][], array to change.
     */
    public static int [][] shuffleStep1 (int [][] array) {

        int sqrt = (int)Math.sqrt(array.length) ;
        // Nae checks and 9 movements:: Smashingggg!
        for (int i = 0; i < array.length; i += sqrt) {
            // skips the array by 3
            int topRowOfSquare = i;
            for (int column = 0; column< array[i].length; column+=sqrt) {

                int desireColumn = column+(sqrt-1);
                int swap = array[topRowOfSquare + (sqrt-2)][desireColumn];
                array[topRowOfSquare + (sqrt-2)][desireColumn] = array[topRowOfSquare + (sqrt-1)][desireColumn];
                array[topRowOfSquare + (sqrt-1)][desireColumn] = swap;
            }
        }
        return array;
    }
    /**
    Achive the first step of 2 in algo.txt
    @param array :: int [][], array to change.
     */
    public static int [][] shuffleStep2(int [][] array){
        int sqrt = (int)Math.sqrt(array.length) ;
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
        int sqrt = (int)Math.sqrt(array.length) ;

        for (int i =0; i <array.length; i += sqrt){
            // switch 1
            int quo = i/sqrt;
            int rem = quo % sqrt;

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
        int sqrt = (int)Math.sqrt(array.length) ;
        {
            for (int i = 0; i < array.length; i +=sqrt) {
                //+3 so more efficient
                int quo = i / sqrt;
                int rem = quo %sqrt;

                if (rem == 0) {
                    for (int ii = 0; ii < array[i].length; ii += sqrt) {
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
                    for (int ii = 0; ii < array[i].length; ii += sqrt) {
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
            for (int i = 0; i < array.length; i+=sqrt){
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
        int sqrt = (int) Math.sqrt(yAxis);
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
            int quoY = cell.getRow()/sqrt;
            int quoX = cell.getColumn() /sqrt;
            int squareY = (quoY)*sqrt;
            int squareX = (quoX)*sqrt;
            for (int i = 0; i < sqrt; i++){
                for (int j = 0; j < sqrt; j++){
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

    public static int [][] shuffleTransverse(int[][] array){
        // step 1 : randomise all the rows
        ArrayList<Integer>factors = new ArrayList<Integer>();
        int sqrt = (int)Math.sqrt(array.length);
        for (int i = 0; i < sqrt; i++){
            factors.add(i);
        }
        for (int row = 0; row < array.length; row++){
            int remainder = row%sqrt;
            Random r = new Random();
            int random = r.nextInt(sqrt-1);
            // redoing if the value is the same enforcing the switching process
            {
                while (random == row)
                    random = r.nextInt(sqrt-1);
            }
            int [] arraySwitch = array[row];
            array[row] = array[(random*sqrt) + remainder];
            array[(random*sqrt) + remainder] = arraySwitch;
        }

        return array;
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
        // og means original
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
    public static void printSudoku (int [][]array) {
        int sqrt = (int)Math.sqrt(array.length);
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j +=sqrt ){
                try {
                    for (int k = 0; k < sqrt; k++) {

                        String code  = (array[i][j+k] == 0) ? "-": (array[i][j+k]+"");
                        System.out.print(((array[i][j+k] == 0) ? "-": (array[i][j+k]+"") )+ (code.length()==2?" ":"  "));

                    }

                    System.out.print("| ");
                }catch (Exception err){
                    //Do Abs Nth
                }
            }
            System.out.print("\n");
            if ((i+1) % sqrt == 0) {
                for (int j = 0; j < (array.length*3)+(sqrt-1)*2; j++)
                System.out.print("-");
                System.out.print("\n");
            }
        }
    }

    /**
     * extract numbers that are of else
     */
    private static ArrayList<Integer> _extractElse(Tree<Integer> checkAgainst, int bound){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 1; i < bound+1; i++){
            if (!checkAgainst.contains(i))
                result.add(i);
        }
        return result;
    }


    /**
     Check if the cell grid is valid
     @param array
     @param cell
     @param yAxis, @param xAxis
     */
    public static ArrayList<Integer> getPossibilities (int [][] array, Vector cell, int gridCount){
        int yAxis = gridCount; int xAxis = gridCount;
        int ans = 0;
        int desireColumn = cell.getColumn(); int desireRow = cell.getRow();
        // set up tree
        Tree <Integer> checkAgainst = _checkAgainstTree(array, cell, yAxis, xAxis);
        // if duplicates are to occur than it won't add helping both time and space complexity, since a tree has to be unique.
        ArrayList<Integer> availables = _extractElse(checkAgainst, yAxis);
        return availables;

    }


    /**
     * solve game brute force
     * @return
     */
    public static boolean solveGameBruteForce (int [][]array){


        int gridCount = array.length;
        ArrayList<Integer>avaialables = new ArrayList<>();

        // step 1 - skim
        for (int row = 0; row < gridCount; row++) {
            for (int column = 0; column< gridCount; column++) {
                if (array[row][column] ==0) {
                    avaialables = getPossibilities(array,new Vector(row, column),gridCount);
                    System.out.println(avaialables.size() );
                    for (int i = 0; i < avaialables.size(); i++) {

                        int num = avaialables.get(i);
                        array[row][column] = num;
                        if (solveGameBruteForce(array)) {
                            return true;
                        } else {
                            array[row][column] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Outer usage
     * @param integer
     * @param factor
     * @return
     */
    public static int getGridNum (int integer, int factor){
        return integer/factor;
    }

}
