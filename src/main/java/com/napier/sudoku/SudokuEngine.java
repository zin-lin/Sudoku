package com.napier.sudoku;

public class SudokuEngine {

    private int xAxis;
    private int yAxis;

    public void setXAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public int getXAxis() {
        return xAxis;
    }

    public void setYAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    // Public Constructor
    public SudokuEngine(int xAxis, int yAxis){
        String upperBound = "╔═════╗ ";
        String midBound   = "║     ║ ";
        String lowerBound = "╚═════╝ ";
        String blue = "";

        this.xAxis = xAxis; this.yAxis = yAxis; // set things
        for (int i = 0; i < xAxis; i++)
        {
            for (int i1 = 0; i1 < yAxis; i1++)
            {
                String ending = (i1+1) %3 == 0? "  ":"";
                System.out.print(blue+ upperBound + ending);
            }
            System.out.print("\n");
            for (int i1 = 0; i1 < xAxis; i1++)
            {
                String ending = (i1+1) %3 == 0? "  ":"";
                System.out.print(midBound + ending);
            }
            System.out.print("\n");
            for (int i1 = 0; i1 < yAxis; i1++)
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
