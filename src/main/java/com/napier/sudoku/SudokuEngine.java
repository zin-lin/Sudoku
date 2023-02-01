package com.napier.sudoku;

// Class SudokuEngine
// Author : Zin Lin Htun
// @matric : 40542237@live.napier.ac.uk
public class SudokuEngine {

    // private materials
    private int xAxis;
    private int yAxis;

    // Generate Sudoku Array - two dimensional
    private static int [][] _generateSudokuArray (int xAxis, int yAxis) {

        int [][] array = new int[xAxis][yAxis];
        int [] xArray = new int [xAxis]; int [] yArray = new int [yAxis]; int [] refXArray = new int [xAxis]; int [] refYArray = new int [yAxis];
        // xAxis set up
        for (int i = 1; i < xAxis; i++){
            xArray[i-1] = i;
            refXArray[i-1] = i;
        }
        // yAxis set up
        for (int i = 1; i < yAxis; i++){
            yArray[i-1] = i;
            refYArray[i-1] = i;
        }

        return array;
    }

    // Writing the sudoku grid
    private static void _writeGrid(int xAxis, int yAxis){
        for (int i = 0; i < yAxis; i++)
        {
            String upperBound = "╔═════╗ ";
            String leftBound  = "║  ";
            String rightBound = "  ║ ";
            String lowerBound = "╚═════╝ ";

            // each line is written
            for (int i1 = 0; i1 < xAxis; i1++)
            {
                String ending = (i1+1) %3 == 0? "  ":"";
                System.out.print(upperBound + ending);
            }
            System.out.print("\n");
            for (int i1 = 0; i1 < xAxis; i1++)
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
        }//for
    }

    // public materials
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
        this.xAxis = xAxis; this.yAxis = yAxis; // set things
        // write sudoku grid
        _writeGrid(this.xAxis, this.yAxis);

    }// Constructor
    // Default Constructor

    //Constructor
    public SudokuEngine(){
        
    }

}
