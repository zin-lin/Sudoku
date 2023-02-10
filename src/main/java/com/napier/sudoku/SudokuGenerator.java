package com.napier.sudoku;

import com.napier.sudoku.models.Tree;
import com.napier.sudoku.models.Vector;
import com.napier.sudoku.random.Randomiser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

// Sudoku Generator. The Purpose of this class is to
// Author : Zin Lin Htun
// @matric : 40542237@live.napier.ac.uk
@Deprecated
public class SudokuGenerator {

    // private materials
    // Privatising the Generator so that Grid must produce finite numbers of rows and columns
    private int [][] game;
    private SudokuGenerator()
    {
        // Nth to do here
    }

    // The Cheker to return int
    private static int _sudoku_checker (int [][] array,int check, int index, int limiterIndex, ArrayList<Integer>xAxis, int row)
    {
        int ans = check;
        ArrayList<Integer>compare = new ArrayList<>();
        for (int [] ele : array){
            if (1<=ele[index] && ele[index]<=limiterIndex)
            {
                compare.add(ele [index]);
            }
        }

        ArrayList<Integer> availables = new ArrayList<>();

        for (int i = 1; i < limiterIndex + 1; i++) {
            if (!(compare.contains(i)) && !(xAxis.contains(i))) {
                availables.add(i);
            }
        }



        try {
            if (compare.contains(check)) {
                ans = availables.get(0);
            }
        } catch (Exception e) {
            // Do absolutely nth
        }


        // Check 3x3 Grid here
        if ((row) % 3 == 1){
            ArrayList<Integer>rowNext = new ArrayList<>();
            int remiander = index / 3;
            for (int i = 0; i < 3; i++){
                int column = i + (3*remiander);
                try {

                    rowNext.add(array[row-1][column]);
                } catch (Exception err){
                    // Do Absolutely Nth here
                }
            }// for
            if (rowNext.contains(ans)) {
                for (int rowVal : rowNext) {

                    availables.remove(Integer.valueOf(rowVal)); //Will remove if exist if not do nae thing
                }// for
                ans = availables.get(0);
            }

        }// if
        else if ((row) % 3 == 2){
            ArrayList<Integer>rowNext = new ArrayList<>();
            int remiander = index / 3;
            for (int i = 0; i < 3; i++){
                int column = i + (3*remiander);
                try {
                    rowNext.add(array[row-1][column]);
                    rowNext.add(array[row-2][column]);
                } catch (Exception err){
                    // Do Absolutely Nth here
                }
            }// for
            if (rowNext.contains(ans)) {
                for (int rowVal : rowNext) {

                    availables.remove(Integer.valueOf(rowVal)); //Will remove if exist if not do nae thing
                }// for
                ans = availables.get(0);
            }


        }// else

        int iot = xAxis.indexOf(check); // Adding to referenced material so the arr keeps getting longer
        if (!xAxis.contains(ans))
            xAxis.add(ans);
        return ans;
    }

    private void _generate_grid(int yAxis, int xAxis){

        // array
        int [][] array = new int [yAxis][xAxis];
        int [] firstLineGenerator = new int [xAxis];
        for (int i = 0; i <xAxis; i++){
            firstLineGenerator[i] = i+1;
        }

        AtomicInteger counter = new AtomicInteger();

        Arrays.stream(firstLineGenerator).parallel().forEach(val -> {
            array[0][counter.get()] = val;
            counter.getAndIncrement();
        }); // Super fast Super random

        //Arrays.stream(array[0]).forEach(e-> System.out.println(e));

        for (int i =0; i < yAxis; i++ ){
            //Tree<Integer> compareX = new Tree<Integer>(0, true);
            int index = 0;

            while (index < xAxis){
                Tree<Integer> compareY = new Tree<Integer>(xAxis+1, true);
                Tree<Integer> compareX = new Tree<Integer>(xAxis+1, true);
                Tree<Integer> compare = new Tree<Integer>(xAxis+1, true);
                Tree <Integer> compareGr = new Tree <Integer>(xAxis+1, true);
                int remainder = index/3;
                // quardrant division
                Tree <Integer> available = new Tree<>(xAxis+1, true);
                // Check Rows
                for (int i1 = 0; i1 < i ; i1++){
                    int com = array[i1][index]; // int to compare
                    compare.add(com);
                }
                // Check Columns
                for (int val = 0; val < index; val ++){
                    int com = array[i][val] ;
                    compare.add(com);
                }
                // Check Sub Grid
                if (i % 3 == 1){
                    for (int loop = 0; loop < 3; loop++){
                        int column = loop + (remainder * 3);
                        compareGr.add(array[i-1][column]);
                    }
                }
                else if (i % 3 == 2) {
                    for (int loop = 0; loop < 3; loop++){
                        int column = loop + (remainder * 3);
                        compareGr.add(array[i-1][column]);
                        compareGr.add(array[i-2][column]);
                    }
                }
                else {
                    compareGr = new Tree<>(xAxis+1, true);
                }// #endif
                for (int add = 1; add < xAxis+1; add++){
                    if (!(compare.contains(add)) && !(compareGr.contains(add)))
                        available.add(add);
                }
                System.out.println(available.getSmallest(0));

                if (array[i][index] == 0)
                    array[i][index] = available.getSmallest(0);

                index++;
            }// end while
        }// end for

        this.game = array;
    }

    // public materials

    // Getters Only material
    public int [][] getMeGame(){
        return this.game;
    }

    // Public Constructor
    public SudokuGenerator(int yAxis, int xAxis){
//        int [][] array = new int[yAxis][xAxis]; // Sudoku Array now stored in HEAP
//        ArrayList<Integer>reference = new ArrayList<>();
//        ArrayList<Vector>vectors = new ArrayList<>();
//
//        // I Will Now Add 4x4 Random Numbers
//        for (int i = 0; i < yAxis; i++) {
//
//            int [] values = Randomiser.generate(4, yAxis); // values
//            int [] positions = Randomiser.generate(4,xAxis); // positions
//
//            ArrayList <Integer> arr = new ArrayList<>();
//
//            Arrays.stream(values).parallel().forEach(integer -> arr.add(integer));
//            int counter = 0;
//            for (int pos : positions ){
//                // Addition if Necessary
//                if (i == 0)
//                    array[i][pos] = values[counter];
//                else
//                    array[i][pos] = this._sudoku_checker(array,values[counter],pos,yAxis, arr, i);
//
//                counter++;
//            }//inner for ends
//        }// outer for ends

//        this.game = array; //simply copies on stack for performance optimisation
        // With java space complexity is less to consider as the heap support is the only thing
        // and would enforce you to use heap referencing

        this._generate_grid(yAxis,xAxis);
    }//method ends

}