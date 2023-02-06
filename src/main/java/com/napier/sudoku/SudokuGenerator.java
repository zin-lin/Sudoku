package com.napier.sudoku;

import com.napier.sudoku.models.Vector;
import com.napier.sudoku.random.Randomiser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

// Sudoku Generator. The Purpose of this class is to
// Author : Zin Lin Htun
// @matric : 40542237@live.napier.ac.uk
public class SudokuGenerator {

    // private materials
    // Privatising the Generator so that Grid must produce finite numbers of rows and columns
    private int [][] game;
    private SudokuGenerator()
    {
        // Nth to do here
    }


    // The Cheker to return int
    private static int _sudoku_checker (int [][] array,int check, int index, int limiterIndex, ArrayList<Integer>xAxis)
    {
        int ans = check;
        ArrayList<Integer>compare = new ArrayList<>();
        for (int [] ele : array){
            if (1<=ele[index] && ele[index]<=limiterIndex)
            {
                compare.add(ele [index]);
            }
        }
        if (compare.contains(check)) {
            ArrayList<Integer> availables = new ArrayList<>();
            for (int i = 1; i < limiterIndex + 1; i++) {
                if (!(compare.contains(i)) && !(xAxis.contains(i))) {
                    availables.add(i);
                }
            }

            try {
                ans = availables.get(1);
            } catch (Exception e) {
            }
        }
        return ans;
    }

    // public materials

    // Getters Only material
    public int [][] getMeGame(){
        return this.game;
    }

    // Public Constructor
    public SudokuGenerator(int yAxis, int xAxis){
        int [][] array = new int[yAxis][xAxis]; // Sudoku Array now stored in HEAP
        ArrayList<Integer>reference = new ArrayList<>();
        ArrayList<Vector>vectors = new ArrayList<>();

        // I Will Now Add 4x4 Random Numbers
        for (int i = 0; i < yAxis; i++) {

            int [] values = Randomiser.generate(4, yAxis); // values
            int [] positions = Randomiser.generate(4,xAxis); // positions
            int counter = 0;
            for (int pos : positions ){
                // Addition if Necessary
                if (!(reference.contains(pos))) {
                    // pos being the position
                    array[i][pos] = values[counter];
                    reference.add(pos);
                }
                else {
                    ArrayList <Integer> arr = new ArrayList<>();
                    Arrays.stream(values).forEach(integer -> arr.add(integer));
                    array[i][pos] = this._sudoku_checker(array,values[counter],pos,yAxis, arr);
                }//else
                counter++;
            }//inner for ends
        }// outer for ends

        this.game = array; //simply copies on stack for performance optimisation
        // With java space complexity is less to consider as the heap support is the only thing
        // and would enforce you to use heap referencing

    }//method ends

}