package com.napier.sudoku.models;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

// Class SudokuGrid :: purpose :: to draw the grid for Sudoku
// Author : Zin Lin Htun
// @matric : 40542237@live.napier.ac.uk
public class SudokuGrid {

    // private materials
    private int [][] game;
    // no default, default be private
    private SudokuGrid (){
        // Do absolutely Nth Here
    }

    // Shuffle 1
    private void _shuffle1 () {
        int [][] arr = this.getGame();
        for (int i = 0; i < arr.length; i+=3){

            if ((i/3)  == 0){
                // Simple switching
                int [] row = arr [i];
                int [] nextNext = arr[i +2];
                int [] next = arr[i +1];
                this.game[i] = next;
                this.game[i+2] = row;
                this.game[i+1] = nextNext;
            }

            else if ((i/3) == 1){
                int [] row = arr [i+1];
                int [] next = arr [i+2];
                this.game[i+1] = next;
                this.game[i+2] = row;
            }
        }// row for
        int xAxis = arr[0].length;
        for (int columnIndex = 0; columnIndex < xAxis; columnIndex+=3){
            if (columnIndex%3 ==0 && columnIndex/3 != 1) {
                int[] column = new int[arr.length];
                int[] columnNext = new int[arr.length];
                try {
                    for (int rowIndex = 0; rowIndex < arr.length; rowIndex++) {
                        column[rowIndex] = game[rowIndex][columnIndex];
                        columnNext[rowIndex] = game[rowIndex][columnIndex + 2];
                        // get the whole column like this
                    }// for
                    for (int rowIndex = 0; rowIndex < arr.length; rowIndex++) {
                        game[rowIndex][columnIndex] = columnNext[rowIndex];
                        game[rowIndex][columnIndex + 2] = column[rowIndex];
                        // get the whole column like this
                    }// for
                }
                catch (Exception err){
                    // Do No Switching
                }
            }// if columns
        }// for columns





        }
    // private materials end

    // public materials

    // public Get Grid
    public int [][] getGame () {
        return this.game;
    }

    // public constructor
    public SudokuGrid (int yAxis, int xAxis){
        // Generate ArrayList <Integer>
        int [][] array = new int [yAxis][xAxis];
        CopyOnWriteArrayList<Integer> fLine = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Integer> firstLineGenerator = new CopyOnWriteArrayList<>(); // Thread safe parallel
        for (int i = 1; i < xAxis+1; i ++) {
            firstLineGenerator.add(i);
        }
        AtomicInteger integer = new AtomicInteger();
        firstLineGenerator.stream().parallel().forEach(
                val -> {
                    fLine.add(integer.get(), val);
                    integer.getAndIncrement();
                }
        ); // randomising
        for (int i = 0; i < xAxis; i++){
            array[0][i] = fLine.get(i);
        }

        for (int row = 1; row < yAxis; row++){
            // for all except line 1
            if (row % 3 != 0) {
                // for every first line of
                for (int column = 0; column < xAxis; column++) {
                    try {
                        array[row][column] = array[row - 1][column + 3];
                    } catch (Exception err) {
                        array[row][column] = array[row - 1][column - 6];
                    }
                }
            } // if

            else {
                // for every second line of
                for (int column = 0; column < xAxis; column++) {
                    try {
                        if (column%3 == 0)
                            array[row][column] = array[row - 3][column + 2];
                        else
                            array[row][column] = array[row - 3][column - 1];
                    } catch (Exception err){
                        try {
                            array[row][column] = array[row - 3][column + 2];
                        }catch ( Exception error){
                            // Do nth
                        }
                    }
                }
            } // else


        }
        // Step one lay out of the first 3 rows x columns

        this.game = array;
        _shuffle1();

    }// SudokuGrid

    // public materials ends here
}
