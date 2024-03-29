package com.napier.sudoku.models;

import com.napier.sudoku.models.memory.Data;
import com.napier.sudoku.models.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Zin Lin Htun
 */
public class Resizoku {

    public int [][] grid;
    public int count;

    /**
     * Constructor
     * @param gridCount
     */
    public Resizoku(int gridCount) {
        count = gridCount;
        grid = new int[gridCount][gridCount];
    }
    /**
     * Constructor
     * @param x - row
     * @param y - column
     */
    public Resizoku(int x, int y) {
        if (x >= 9 || y >= 9){
            count = x > y ? x : y;
            grid = new int[y][x];
        }
        else {
            System.out.println("Bad input, one value must be  sqaure number greater than 9 or equal");
            return;
        }
    }

    /**
     *
     * @param values
     * @param value
     * @return
     */
    private int getPosition(int []values, int value){
        for (int i = 0; i < values.length; i++){
            int number = values[i];
            if (number==value){
                return i;
            }
        }
        return value;
    }

    /**
     *
     * @param values
     * @param value
     * @return
     */
    private ArrayList<Integer> getPositions(int []values, int value){
        ArrayList<Integer> positions = new ArrayList<Integer>();
        for (int i = 0; i < values.length; i++){
            int number = values[i];
            if (number==value){
                positions.add(i);
            }
        }
        return positions;
    }

    /**
     *
     * @param array
     * @return
     */
    private Data<Integer> getData (int[]values) {
        ArrayList<Integer>duplicates = new ArrayList<Integer>();
        ArrayList<Integer>needs = new ArrayList<Integer>();
        CopyOnWriteArrayList<Integer> com = new CopyOnWriteArrayList<Integer>();
        CopyOnWriteArrayList<Integer> all = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Integer> pre = new CopyOnWriteArrayList<>();

        for (int i = 0; i < values.length; i++){
            int compare = values[i];
            if (com.contains(compare))
                duplicates.add(compare);
            else
                com.add(compare);
            all.add(i+1);
        }
        for (int i: all){
            if (!com.contains(i)){
                needs.add(i);
            }
        }
        Data data = new Data();
        data.duplicates = duplicates;
        data.needs = needs;

        return data;
    }

    /**
     * serach forward fixing method
     * @param index
     * @param array
     */
    private void searchAndFixLine(int index, int [] array){
        int sqrt = (int)Math.sqrt(array.length);
        int len = array.length;
        Data data = getData(array);
        ArrayList<Integer> duplicates = data.duplicates;
        ArrayList<Integer> needs = data.needs;
        for (int need : needs){
            boolean found = false;
            for (int duplicate: duplicates){
                ArrayList <Integer> positions = getPositions(grid[index],  duplicate);
                for (int position: positions){
                    int gridNum = Helper.getGridNum(position, sqrt);

                    for (int rg = index; rg < sqrt; rg++) {
                        for (int cg = gridNum * sqrt; cg < (gridNum * sqrt) + sqrt; cg++) {
                            int num = grid[rg][cg];
                            if (num == need) {
                                // switching
                                grid[rg][cg] = duplicate;
                                grid[index][position] = num;
                                found = true;
                                break;
                            }
                        }
                        if (found)
                            break;
                    }
                    if (found) {
                        duplicates.remove(new Integer(duplicate));
                        break;
                    }
                    else{
                    }
                }
                if (found)
                    break;
            }
        }

    }

    /**
     *
     */
    public boolean checkStartEnd() {
        int sqrt= (int)Math.sqrt(grid.length);
        for (int row = 0; row < sqrt; row++ ){
            for (int column = 0; column < grid.length; column++){
                if (!check(row, column, grid[row][column]))
                    return false;
            }
        }

        return true;
    }

    /**
     * Fixing all the arrays
     */
    private void fix(){
        int [][] array = this.grid;
        int sqrt = (int)Math.sqrt(array.length);

        for (int row = 0; row < sqrt; row++){
            searchAndFixLine(row, grid[row]);
        }

    }

    /**
     * Brute force solving way of Sudoku
     */
    public void solveBruteForce(){
        long t = System.currentTimeMillis();
        while (true){
            grid = new int[count][count];
            int[][] array = grid;
            int len = array.length;

            int sqrt = (int) Math.sqrt(len);
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 1; i <= len; i++) {
                list.add(i);
            }
            int indexCount = 0;
            for (int subX = 0; subX < sqrt; subX += sqrt) {
                for (int subY = 0; subY < grid.length; subY += sqrt) {
                    Collections.shuffle(list);
                    int index = 0;
                    for (int row = subX; row < subX + sqrt; row++) {
                        for (int column = subY; column < subY + sqrt; column++) {
                            if (array[row][column] == 0) {
                                array[row][column] = list.get(index);
                                index++;
                            }
                        }
                    }
                }
            }
            this.fix();
            if (checkStartEnd())
                break;
            long t1 = System.currentTimeMillis();
            if (t1-t >3000){
                SudokuGrid sudokuGrid = new SudokuGrid(count);
                sudokuGrid.getGame();
                break;
            }
        }

        int sqrt = (int)Math.sqrt(grid.length);
        for (int row = sqrt; row < grid.length; row++){
            for (int column = 0; column< grid.length; column+=sqrt){
                for (int columnG = column; columnG < column+sqrt; columnG++){
                    if(columnG+1 < column+sqrt){
                        grid[row][columnG] = grid[row - sqrt][columnG + 1];
                    }else{
                        grid[row][columnG] = grid[row - sqrt][column];
                    }
                }
            }
        }
        Helper.shuffleTransverse(grid);
        Helper.shuffleStep3(grid);

    }


    /**
     * Outter usage
     * @param array
     * @return boolean
     */
    public static boolean check(int [][] array, int row, int column, int num ){
        int sqrt  = (int)Math.sqrt(array.length);
        int xFactor = row/sqrt;
        int yFactor = column/sqrt;
        ArrayList <Integer> checkAgainst = new ArrayList<Integer>();
        // For the required subgrid
        for (int r = (xFactor*sqrt); r <  (xFactor*sqrt)+sqrt; r++ ){
            for (int c = yFactor*sqrt; c< (yFactor*sqrt)+sqrt; c++){
                if ((!checkAgainst.contains(array[r][c]) )){
                    if (!(r == row && c == column)) {
                        checkAgainst.add(array[r][c]);

                    }
                }
            }
        }
        // For the required row
        for (int r = 0; r < array.length; r++){
            if ((!checkAgainst.contains(array[r][column]))){
                if (!(r == row)) {
                    checkAgainst.add(array[r][column]);
                }
            }
        }

        // For the required column
        for (int c = 0; c < array.length; c++){
            if ((!checkAgainst.contains(array[row][c]))){
                if (c != column) {
                    checkAgainst.add(array[row][c]);
                }
            }
        }

        // check operation
        if (!checkAgainst.contains(num) && num != 0) {
            return true;
        }
        return false;
    }

    /**
     * Inner usage
     * @return boolean
     */
    public boolean check (int row, int column, int num){
        return check(this.grid, row, column, num);
    }
}
