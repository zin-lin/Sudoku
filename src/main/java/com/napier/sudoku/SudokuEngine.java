package com.napier.sudoku;

import com.napier.sudoku.models.SudokuGrid;
import com.napier.sudoku.models.Tree;
import com.napier.sudoku.models.Vector;
import com.napier.sudoku.random.Randomiser;

import java.io.IOException;
import java.util.Scanner;

// Class SudokuEngine
// Author : Zin Lin Htun
// @matric : 40542237@live.napier.ac.uk
public class SudokuEngine {

    // private materials
    private int xAxis;
    private int yAxis;
    private Vector cell;
    private int [][] array;
    private int [][] game;

    //Separator
    private static void _separate ()  {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }

    }

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

    private void _writeGrid_cmd(boolean start){
        final String ANSI_BLUE = "\u001B[34m"; final String ANSI_RESET = "\u001B[0m";
        for (int i = 0; i < yAxis; i++)
        {
            Tree<Integer> random;
            if (start) {
                random = Randomiser.generateTreeList(5, 9);
            }
            else {
                random = new Tree<>(null);
                for (int integer = 1; integer < xAxis; integer++ ){
                    random.add(integer);
                }
            }
            String upperBound = "╔═════╗ ";
            String leftBound  = "║  ";
            String rightBound = "  ║ ";
            String lowerBound = "╚═════╝ ";
            String printer ="";
            // each line is written
            for (int i1 = 0; i1 < xAxis; i1++)
            {
                String ending = (i1+1) %3 == 0? "  ":"";
                printer += (upperBound + ending);
                System.out.print(upperBound + ending);
            }

            System.out.print("\n");
            printer+="\n";
            for (int i1 = 0; i1 < xAxis; i1++)
            {

                int current = array[i][i1];
                String randomizer;
                if (random.contains(current)){
                    randomizer = Integer.toString(current) ;
                }
                else {
                    array[i][i1] = 0;
                    if (this.cell == null) {
                        randomizer = "X";
                        this.cell = new Vector(i,i1);
                    }
                    else if (this.cell.getColumn() == i1 && this.cell.getRow() == i) {
                        randomizer = "X";
                    } else
                        randomizer = " ";
                }
                String ending = (i1+1) %3 == 0? "  ":"";
                if (!randomizer.equals("X")){

                    System.out.print(leftBound + randomizer + rightBound + ending);
                }

                else {
                    System.out.print(leftBound);
                    // set color de blue

                    System.out.print(ANSI_BLUE);
                    System.out.print(randomizer);
                    // reseting OG color
                    System.out.print(ANSI_RESET);
                    System.out.print(rightBound + ending);

                }


            }
            System.out.print("\n");
            printer+="\n";

            for (int i1 = 0; i1 < xAxis; i1++)
            {
                String ending = (i1+1) %3 == 0? "  ":"";
                printer += lowerBound + ending;
                System.out.print(lowerBound + ending);
            }
            System.out.print("\n");
            printer+="\n";


            if ((i+1) % 3 == 0 ){
                System.out.print("\n");
            }
        }//for
        showPrompts();
    }
    // Writing the sudoku grid
    private  void _writeGridStart(int yAxis, int xAxis){
        SudokuGrid generator = new SudokuGrid(yAxis,xAxis);
        this.game = generator.getGame(); // get the array
        this.array = game;
        _writeGrid_cmd(true);

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

    // showing prompts
    public void showPrompts (){
        System.out.println("Current Grid is: (" + this.cell.getRow() +"," + this.cell.getColumn() + "), use W,S,A,D to move around or type in values"
        + "\n"
        );
        Scanner scanner = new Scanner(System.in);
        String value = scanner.next();

        // Move Around
        if (value.equals("W") ||value.equals("w") ){
            boolean found = false; int left =Integer.MAX_VALUE; int right = Integer.MAX_VALUE; int rightColumn = 0; int leftColumn = 0;
            int initRow = cell.getRow()-1;
            for (int i = initRow ; i >=0 ; i--) {
                for (int column = 0; column < xAxis; column++) {
                    int current = array[i][column];

                    if (current == 0) {
                        this.cell.setColumn(column);
                        this.cell.setRow(i);
                        _separate();
                        found = true;
                        _writeGrid_cmd(false);
                        break;
                    }
                }
            }
            if (!found)
                _writeGrid_cmd(false);
        }


        else if (value.equals("S")|| value.equals("s")){
            boolean found = false; int left =Integer.MAX_VALUE; int right = Integer.MAX_VALUE; int rightColumn = 0; int leftColumn = 0;
            int initRow = cell.getRow()+1 ; int finalRow = this.cell.getRow();
            for (int i = initRow ; i < yAxis; i++) {
                for (int column = 0; column < xAxis; column++) {
                    int current = array[i][column];

                    if (current == 0) {
                        this.cell.setColumn(column);
                        this.cell.setRow(i);
                        _separate();
                        found = true;
                        _writeGrid_cmd(false);
                        break;
                    }
                }
            }
            if (!found)
                _writeGrid_cmd(false);
        }
        else if (value.equals("A")|| value.equals("a")){
            boolean found = false;

            for (int column = this.cell.getColumn()-1 ; column >= 0; column-- ){
                int current = array[cell.getRow()][column];

                if (current == 0){
                    this.cell.setColumn(column);
                    _separate();
                    found = true;
                    _writeGrid_cmd(false);
                    break;
                }
            }
            if (!found)
                _writeGrid_cmd(false);
        }
        else if (value.equals("D")|| value.equals("d")){
            boolean found = false;
            for (int column = this.cell.getColumn()+1 ; column < this.xAxis; column++ ){
                int current = array[cell.getRow()][column];
                if (current == 0){
                    found = true;
                    this.cell.setColumn(column);
                    _separate();
                    _writeGrid_cmd(false);
                    break;
                }

            }
            if (!found)
                _writeGrid_cmd(false);

        }
        else if (value.equals("H")|| value.equals("h")){

        }
        else if (value.equals("Q") || value.equals("q") || value.equals("quit") || value.equals("Quit")|| value.equals("QUIT")){
            // Do Nth
        }
        else {
            try {
                int val = Integer.parseInt(value);
            }
            catch (Exception err){
                try {
                    if (value.startsWith("(") && value.endsWith(")")){
                        String v1 =value.substring(1,2);
                        String v2 =value.substring(3,4);
                        System.out.println(v1);
                        int row = Integer.parseInt(v1);
                        int column = Integer.parseInt(v2);
                        if (array[row][column] == 0){
                            this.cell.setColumn(column);
                            this.cell.setRow(row);
                        }
                        _separate();
                        _writeGrid_cmd(false);
                    }
                }catch (Exception err1){
                    _separate();
                    _writeGrid_cmd(false);
                }
            }
        }
    }

    // Public Constructor
    public SudokuEngine(int xAxis, int yAxis){
        this.xAxis = xAxis; this.yAxis = yAxis; // set things
        // write sudoku grid
        _writeGridStart(this.xAxis, this.yAxis);

    }// Constructor
    // Default Constructor

    //Constructor
    public SudokuEngine(){
        this.xAxis = 9; this.yAxis = 9; // set things
        // write sudoku grid
        _writeGridStart(this.xAxis, this.yAxis);

    }

}
