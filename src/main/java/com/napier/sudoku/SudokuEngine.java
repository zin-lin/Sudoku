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
        String upperBound = "╔═══╗";
        String midBound   = "║   ║";
        String lowerBound = "╚═══╝";
        String blue = "\u001B[34m";

        this.xAxis = xAxis; this.yAxis = yAxis; // set things
        for (int i = 0; i < xAxis; i++)
        {
            for (int i1 = 0; i1 < yAxis; i1++)
            {

                System.out.print("╔═══╗" + blue);
            }
            System.out.print("\n");
            for (int i1 = 0; i1 < xAxis; i1++)
            {
                System.out.print("║   ║" + blue);
            }
            System.out.print("\n");
            for (int i1 = 0; i1 < yAxis; i1++)
            {
                System.out.print("╚═══╝" + blue);
            }
            System.out.print("\n");

        }

    }

    // Default Constructor
    public SudokuEngine(){

    }

}
