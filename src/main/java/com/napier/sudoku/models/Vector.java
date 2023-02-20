package com.napier.sudoku.models;

import java.util.ArrayList;

// Game Engine is the entry point
// Author : Zin Lin Htun
// @matric : 40542237@live.napier.ac.uk
public class Vector {
    // private materials
    private int row;
    private int column;
    // Eliminating default constructor
    private Vector(){

    }

    //public materials
    //get column value
    public int getColumn() {
        return column;
    }

    //get row gets the row value
    public int getRow() {
        return row;
    }

    // set the value of private :: row
    public void setRow(int row) {
        this.row = row;
    }

    // set the value of private :: column
    public void setColumn(int column) {
        this.column = column;
    }

    //public constructor
    public Vector(int row, int column)
    {
        // row is Y Axis where X is column
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString (){
        return  this.row +"," + this.column;
    }

    @Override
    public boolean equals(Object compare){
        boolean ans = false;
        if (row == ((Vector)compare).row && column == ((Vector)compare).column)
            ans = true;
        return ans;
    }

    public static ArrayList<Integer>getRows(ArrayList<Vector> arrayList, int column){
        ArrayList<Integer>integers = new ArrayList<>();

        return integers;
    }

}
