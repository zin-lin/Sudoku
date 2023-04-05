package com.napier.sudoku.models;
import com.napier.sudoku.models.memory.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class SudokuEngine :: This class deals with the tree architecture.
 Author : Zin Lin Htun
 @matric : 40542237@live.napier.ac.uk */
public class SudokuGrid {

    // private materials
    private int [][] game;

    // private materials end

    // public materials

    /**public Get Grid
     *
     * @return get game
     */
    public int [][] getGame () {
        return this.game;
    }

    /**
     * public Constructor do nae thing
     */
    public SudokuGrid(int y, int x){
        this.game = new int[y][x];
    }
    /**
     *  public constructor
     * @param yAxis
     * @param xAxis
     */
    public SudokuGrid (int gridCount){
        // Generate ArrayList <Integer>
        int yAxis =gridCount; int xAxis =gridCount;
        int [][] array = new int [yAxis][xAxis];
        CopyOnWriteArrayList<Integer> fLine = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Integer> firstLineGenerator = new CopyOnWriteArrayList<>(); // Thread safe parallel
        for (int i = 1; i < xAxis+1; i ++) {
            fLine.add(i);
        }
        int sqrt =  (int)Math.sqrt(gridCount); // static casting
        Collections.shuffle(fLine); // randomising
        if (gridCount>0){
            for (int i = 0; i < xAxis; i++) {
                array[0][i] = fLine.get(i);
            }
            for (int row = 1; row < yAxis; row++) {
                // for all except line 1
                if (row % sqrt != 0) {
                    // for every first line of
                    for (int column = 0; column < xAxis; column++) {
                        try {
                            array[row][column] = array[row - 1][column + sqrt];
                        } catch (Exception err) {
                            array[row][column] = array[row - 1][column - (sqrt*(sqrt-1))];
                        }
                    }
                } // if
                else {
                    // for every second line of
                    for (int column = 0; column < xAxis; column++) {
                        try {
                            if (column % sqrt == 0)
                                array[row][column] = array[row - sqrt][column + (sqrt-1)];
                            else {
                                    array[row][column] = array[row - sqrt][column - 1];
                            }
                        } catch (Exception err) {
                            try {
                                array[row][column] = array[row - sqrt][column + 2];
                            } catch (Exception error) {
                                // Do nth
                            }
                        }
                    }
                } // else
            }
            // 9x9 grid special shuffle
            // if the grid is not 9x9 let's not do a shuffle
            if (gridCount  == 9){
                array = Helper.shuffleStep1(array);
                array = Helper.shuffleStep2(array);
                array = Helper.shuffleStep3(array);
                array = Helper.shuffleStep4(array);
            }
            else{
                array =Helper.shuffleTransverse(array);
                array = Helper.shuffleStep3(array);
            }
        }
        this.game = array;

    }// SudokuGrid

    /**
     *
     * @param gridCount
     * @return
     */
    public int [][] getGameBruteForce(int gridCount) {
        int [][] array = new int[gridCount][gridCount];
        Helper.solveGameBruteForce(array);
        return array;
    }

    /**
     * Backtracking solver
     * @return
     */
    public boolean solveGameBruteForce (){
        int [][] array = this.game;
        int gridCount = array.length;
        // for all grid
        for (int row = 0; row < gridCount; row++) {
            for (int column = 0; column< array[row].length; column++) {
                if (array[row][column] ==0) {
                    for (int i = 1; i <=gridCount; i++) {
                        int num = i;
                        array[row][column] =num;
                        if (Helper.check(array,new Vector(row, column),  gridCount, array[row].length)){

                            if (solveGameBruteForce()) {
                                return true;
                            } else {
                                array[row][column] = 0;
                            }
                        }
                        else {
                            array[row][column] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // public materials ends here
}
