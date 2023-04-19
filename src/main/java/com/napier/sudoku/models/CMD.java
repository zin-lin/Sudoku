package com.napier.sudoku.models;

import com.napier.sudoku.SudokuEngine;
import jdk.jfr.Description;

@Deprecated
@Description("Tester")
public class CMD {
    /*

     */
    private static void setGame (SudokuEngine engine, int[][] array) throws Exception {
        if (array.length == 9){
            if (array[0].length == 9){
                // if x and y are 9
                engine.setArray(array);
            }
        }
        else{
            throw new Exception("ERROR: SUD101: Grid Alternation fault");
        }
    }
}
