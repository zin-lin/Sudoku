package com.napier.sudoku;

// Class SudokuEngine
// Author : Zin Lin Htun
// @matric : 40542237@live.napier.ac.uk
public class SudokuEngine {

    //private materials
    private int xAxis;
    private int yAxis;

    // getters and setters
    // setXAxis to set xAxis
    public void setXAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    // getXAxis to get xAxis
    public int getXAxis() {
        return xAxis;
    }

    // setYAxis to set yAxis
    public void setYAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    // getYAxis to set yAxis
    public int getYAxis() {
        return yAxis;
    }

    // Public Constructor
    public SudokuEngine(int xAxis, int yAxis){
        String upperBound = "╔═════╗ ";
        String leftBound  = "║  ";
        String rightBound = "  ║ ";
        String lowerBound = "╚═════╝ ";
        String blue = "";

        this.xAxis = xAxis; this.yAxis = yAxis; // set things
        // write rows
        for (int i = 0; i < yAxis; i++)
        {
            // each line is written
            for (int i1 = 0; i1 < xAxis; i1++)
            {
                String ending = (i1+1) %3 == 0? "  ":"";
                System.out.print(blue+ upperBound + ending);
            }
            System.out.print("\n");
            for (int i1 = 0; i1 < yAxis; i1++)
            {
                String randomizer = "_";
                String ending = (i1+1) %3 == 0? "  ":"";
                System.out.print(leftBound + randomizer + rightBound + ending);
            }
            System.out.print("\n");
            for (int i1 = 0; i1 < xAxis; i1++)
            {
                String ending = (i1+1) %3 == 0? "  ":"";
                System.out.print(lowerBound + ending);
            }
            System.out.print("\n");

            if ((i+1) % 3 == 0 ){
                System.out.print("\n");
            }
        }
    }
    // Default Constructor
    public SudokuEngine(){
        
    }

}
