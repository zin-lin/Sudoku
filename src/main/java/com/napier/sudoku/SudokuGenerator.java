package com.napier.sudoku;

import com.napier.sudoku.random.Randomiser;

import java.util.Arrays;

// Sudoku Generator. The Purpose of this class is to
// Author : Zin Lin Htun
// @matric : 40542237@live.napier.ac.uk
public class SudokuGenerator {

    // private materials

    // Privatising the Generator so that Grid must produce finite numbers of rows and columns
    private SudokuGenerator()
    {
        // Nth to do here
    }

    // The Cheker to return int
    private static int _sudoku_checker (int [][] array,int check, int index, int limiterIndex)
    {
        int ans = check;
        Arrays.stream(array).parallel().forEach(
                ele->{

                }
        );
        return ans;
    }

    // public materials
    // Public Constructor
    public SudokuGenerator(int xAxis, int yAxis){
        int [][] array = new int[xAxis][yAxis]; // Sudoku Array now stored in HEAP
        int [] xArray = new int[xAxis]; int [] yArray = new int[yAxis];
        // I Will Now Add 4x4 Random Numbers
        for (int i = 0; i < yAxis; i++) {
            int [] values = Randomiser.generate(4, 9); // values
            int [] positions = Randomiser.generate(4,9);// positions
            int counter = 0;
            for (int pos : positions ){
                // pos being the position
                array[i][pos] = values[counter];
                counter++;
            }
        }
    }

}