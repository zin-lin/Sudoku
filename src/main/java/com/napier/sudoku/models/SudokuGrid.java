package com.napier.sudoku.models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* Class SudokuEngine :: This class deals with the tree architecture.
 Author : Zin Lin Htun
 @matric : 40542237@live.napier.ac.uk */
public class SudokuGrid {

    // private materials
    private int [][] game;
    // no default, default be private
    private SudokuGrid (){
        // Do absolutely Nth Here
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
        if (xAxis == 9 && yAxis == 9){
            for (int i = 0; i < xAxis; i++) {
                array[0][i] = fLine.get(i);
            }
            for (int row = 1; row < yAxis; row++) {
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
                            if (column % 3 == 0)
                                array[row][column] = array[row - 3][column + 2];
                            else
                                array[row][column] = array[row - 3][column - 1];
                        } catch (Exception err) {
                            try {
                                array[row][column] = array[row - 3][column + 2];
                            } catch (Exception error) {
                                // Do nth
                            }
                        }
                    }
                } // else


            }
            // 9x9 grid special shuffle
            // if the grid is not 9x9 let's not do a shuffle
            array = Helper.shuffleStep1(array);
            array = Helper.shuffleStep2(array);
            array = Helper.shuffleStep3(array);
            array = Helper.shuffleStep4(array);
        }

        this.game = array;

    }// SudokuGrid

    // public materials ends here
}
