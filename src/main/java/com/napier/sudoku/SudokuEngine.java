package com.napier.sudoku;

import com.napier.sudoku.models.Helper;
import com.napier.sudoku.models.SudokuGrid;
import com.napier.sudoku.models.memory.Tree;
import com.napier.sudoku.models.Vector;
import com.napier.sudoku.random.Randomiser;

import java.io.IOException;
import java.util.Scanner;

/* Class SudokuEngine
   Author : Zin Lin Htun
   @matric : 40542237@live.napier.ac.uk*/

public class SudokuEngine {

    // private materials
    private int xAxis;
    private int yAxis;
    private Vector cell;
    private int [][] array;
    private int [][] game;

    private int level;
    private boolean help;
    private Tree<Vector> cells;

    private void resetOrigins () {
        boolean have_not = true;
        cells = new Tree<>(null);
        for (int i = 0; i< array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                int current = array[i][j];
                if (current == 0) {
                    if (have_not) {
                        cell.setRow(i);
                        cell.setColumn(j);
                        have_not = !have_not;
                    }
                }
                else {
                    cells.add(new Vector(i,j));
                }

            }
        }
    }

    /*
    separator
     */
    private static void _separate ()  {
        // write a whole load of lines
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

    }

    // Generate Sudoku Array - two dimensional
    @Deprecated
    private static int [][] _generateSudokuArray (int xAxis, int yAxis) {

        int [][] array = new int[xAxis][yAxis];
        int [] xArray = new int [xAxis];
        int [] yArray = new int [yAxis];
        int [] refXArray = new int [xAxis];
        int [] refYArray = new int [yAxis];
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

    /*
    Write in Help mode
     */
    private void  _writeGrid_cmd(boolean start){

        final String ANSI_BLUE = "\u001B[34m"; final String ANSI_RESET = "\u001B[0m"; final String ANSI_YELLOW = "\u001b[33m";
        final String ANSI_RED = "\u001b[31m"; final String ANSI_GREEN = "\u001b[32m";

        boolean solved = false;
        if (!start)
            solved = Helper.checkGamneEnd(array, cells, yAxis, xAxis); // everyting check is with binary search tree so it's pretty fast.

        if (solved){
            char [] arrayList = new char []{'Y','o','u',' ','h','a','v','e', ' ', 's','o','l','v','e','d',
            ' ','t','h','e',' ','p','u','z','z','l','e','\n'
            };
            for (int i = 0; i < arrayList.length; i++) {
                System.out.print(arrayList[i]);
                try {
                    Thread.sleep(100);}
                catch (Exception e){
                    // Do Absoulte Nth
                }
            }
            return;
        }
        if (!solved){
            for (int i = 0; i < yAxis; i++) {
                Tree<Integer> random;
                if (start) {
                    // randomise the values to be ommitted
                    random = Randomiser.generateTreeList(level, xAxis);
                } else {
                    // random is now a balance tree. Usually from 1-9 would be a linear architecture
                    // with balancedTree from helper class, this can be done
                    random = new Tree<>(null);
                    random = Helper.balancedTree(1,9);
                }
                // set up strings
                String upperBound = "╔═════╗ ";
                String leftBound = "║  ";
                String rightBound = "  ║ ";
                String lowerBound = "╚═════╝ ";
                String printer = "";
                // each line is written
                for (int i1 = 0; i1 < xAxis; i1++) {
                    String ending = (i1 + 1) % 3 == 0 ? "  " : "";
                    printer += (upperBound + ending);
                    System.out.print(upperBound + ending);
                }

                System.out.print("\n");
                printer += "\n";
                for (int i1 = 0; i1 < xAxis; i1++) {
                    // get current int
                    int current = array[i][i1];
                    String randomizer;
                    if (random.contains(current)) {
                        // if the current number is in random, show otherwise replace with a zero
                        randomizer = Integer.toString(current);
                        if (start)
                            cells.add(new Vector(i, i1));

                    } else {
                        array[i][i1] = 0;
                        if (this.cell == null) {
                            randomizer = "X";
                            this.cell = new Vector(i, i1);
                        } else if (this.cell.getColumn() == i1 && this.cell.getRow() == i) {
                            randomizer = "X";
                        } else
                            randomizer = " ";
                    }
                    String ending = (i1 + 1) % 3 == 0 ? "  " : "";
                    if (!help) {
                        try {
                            if (!new Vector(i, i1).equals(cell)) {
                                if (cells.contains(new Vector(i, i1))) {
                                    System.out.print(leftBound + randomizer + rightBound + ending);
                                } else {
                                    // YELLOW
                                    System.out.print(leftBound);
                                    // set color de yellow
                                    System.out.print(ANSI_YELLOW);
                                    System.out.print(randomizer);
                                    // reseting OG color
                                    System.out.print(ANSI_RESET);
                                    System.out.print(rightBound + ending);
                                }
                            } else {

                                // BLUE
                                System.out.print(leftBound);
                                // set color de blue
                                System.out.print(ANSI_BLUE);
                                System.out.print(randomizer);
                                // reseting OG color
                                System.out.print(ANSI_RESET);
                                System.out.print(rightBound + ending);

                            }
                        } catch (NullPointerException err) {
                            // if null for cell
                            System.out.print(leftBound + randomizer + rightBound + ending);
                        }
                    } else {
                        // if cell contains white/ othewise check for validity. This way it is more efficient.
                        if (cells.contains(new Vector(i, i1))) {
                            System.out.print(leftBound + randomizer + rightBound + ending);
                        } else {
                            if (Helper.check(array, new Vector(i, i1), yAxis, xAxis)) {
                                // GREEN
                                System.out.print(leftBound);
                                // set color de blue
                                System.out.print(ANSI_GREEN);
                                System.out.print(randomizer);
                                // reseting OG color
                                System.out.print(ANSI_RESET);
                                System.out.print(rightBound + ending);
                            } else {
                                // RED
                                System.out.print(leftBound);
                                // set color de blue
                                System.out.print(ANSI_RED);
                                System.out.print(randomizer);
                                // reseting OG color
                                System.out.print(ANSI_RESET);
                                System.out.print(rightBound + ending);
                            }

                        }
                    }
                }// for
                System.out.print("\n");
                printer += "\n";

                for (int i1 = 0; i1 < xAxis; i1++) {
                    String ending = (i1 + 1) % 3 == 0 ? "  " : "";
                    printer += lowerBound + ending;
                    System.out.print(lowerBound + ending);
                }
                System.out.print("\n");
                printer += "\n";


                if ((i + 1) % 3 == 0) {
                    System.out.print("\n");
                }
            }// for
            showPrompts();
        }// if
    }



    /*
    Write in Sudoku Grid
     */
    private  void _writeGridStart(int yAxis, int xAxis){
        SudokuGrid generator = new SudokuGrid(yAxis,xAxis);

        this.game = generator.getGame(); // get the array
        for(int i = 0; i<game.length; i++){
            for (int i1 = 0; i1< game[i].length; i1++ ){
                System.out.print(game[i][i1]);
            }
            System.out.print("\n");
        }
        this.array = game;
        _writeGrid_cmd(true);

    }

    // public materials
    // getters and setters


    public void setArray (int [][] array) {
        this.array =array;
    }

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
        System.out.println("Current Grid is: (" + this.cell.getRow() +","
        + this.cell.getColumn() + "), use W,S,A,D to move around or type in values. Click 'q' to quit and 'h' to change to help mode"
        + "\n"
        );

        if (help){
            System.out.println("HELP MODE ON! Click h to return to game mode");
            Helper.printPossibilities(array, cell, yAxis, xAxis );
        }

        Scanner scanner = new Scanner(System.in);
        String value = scanner.next();

        // Move Around
        if (value.equals("W") ||value.equals("w") ){
            boolean found = false; int rightColumn = 0; int leftColumn = 0;
            int initRow = cell.getRow()-1;
            for (int i = initRow ; i >=0 ; i--) {
                for (int column = 0; column < xAxis; column++) {
                    int current = array[i][column];

                    if (!cells.contains(new Vector(i, column))) {
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

                    if (!cells.contains(new Vector(i, column))) {
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

                if (!cells.contains(new Vector(cell.getRow(), column))){
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
                if (!cells.contains(new Vector(cell.getRow(), column))){
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
            // Option de Help
            help = !help;
            _separate();
            _writeGrid_cmd(false);

        }
        else if (value.equals("Q") || value.equals("q") || value.equals("quit") || value.equals("Quit")|| value.equals("QUIT")){
            // Do Nth
        }
        else if (value.equals("dev") || value.equals("DEV")|| value.equals("Dev") ){
            final String ANSI_BLUE = "\u001B[34m";  final String ANSI_RESET = "\u001B[0m";  final String ANSI_YELLOW = "\u001b[33m";
            Scanner scannerDev = new Scanner(System.in);
            System.out.println(ANSI_BLUE + "You are now in developer mode, please type in the command, please check if the SUDOKU EDITION is VALID" + ANSI_YELLOW);

            while (true) {

                String cmd = scannerDev.nextLine();
                String[] args = cmd.split("\\s+");
                try{
                    if (args[1].equals("<setGame>")) {

                        String[] yDem = args[2].split("/"); // The Y demsion
                        for (int i = 0; i < yDem.length; i++) {
                            String[] xDem = yDem[i].split(",");
                            for (int j = 0; j < xDem.length; j++) {
                                int valueX = Integer.parseInt(xDem[j]); // parsing into int
                                array[i][j] = valueX;
                            }
                        }

                        System.out.println("Exiting Dev Mode ----> " + ANSI_RESET );
                        resetOrigins();
                        _writeGrid_cmd(false);
                    }


                    else if (args[1].equals("<quit>") || args[1].equals("<player>")) {
                        System.out.println("Exiting Dev Mode ----> " + ANSI_RESET );
                        _writeGrid_cmd(false);
                        break;
                    }
                    else {
                        System.out.println("Unknown Annex Function"+ANSI_YELLOW);
                    }
                }catch (Exception err){
                    System.out.println( "Wrong Syntax for AnnexCode" +ANSI_YELLOW);
                }
            }

        }
        else {
            try {
                // parsing to value
                int val = Integer.parseInt(value);
                array[cell.getRow()][cell.getColumn()] = val;
                _separate();
                _writeGrid_cmd(false);
            }
            catch (Exception err){
                try {
                    // parsing to coordinate
                    if (value.startsWith("(") && value.endsWith(")")){
                        String v1 =value.substring(1,2);
                        String v2 =value.substring(3,4);
                        System.out.println(v1);
                        int row = Integer.parseInt(v1);
                        int column = Integer.parseInt(v2);
                        if (!cells.contains(new Vector(row, column))){
                            this.cell.setColumn(column);
                            this.cell.setRow(row);
                        }
                        _separate();
                        _writeGrid_cmd(false);
                    }
                    else {
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
    public SudokuEngine(int xAxis, int yAxis, int level){
        this.level = level;
        help = false;
        this.xAxis = xAxis; this.yAxis = yAxis; // set things
        cells = new Tree<>(null); // Initiating
        // write sudoku grid
        _writeGridStart(this.yAxis, this.xAxis);

    }// Constructor
    // Default Constructor

    //Constructor
    public SudokuEngine(){
        help = false;
        this.xAxis = 9; this.yAxis = 9; // set things
        cells = new Tree<>(null); // Initiating
        // write sudoku grid
        _writeGridStart(this.yAxis, this.xAxis);

    }

}
