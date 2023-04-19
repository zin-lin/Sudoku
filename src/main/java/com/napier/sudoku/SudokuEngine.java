package com.napier.sudoku;

import com.napier.sudoku.models.*;
import com.napier.sudoku.models.memory.Tree;
import com.napier.sudoku.persistence.Database;
import com.napier.sudoku.random.Randomiser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
   Class SudokuEngine
   @author : Zin Lin Htun
   @matric : 40542237@live.napier.ac.uk*/

public class SudokuEngine {

    // private materials

    private int xAxis;
    private int yAxis;
    private Vector cell;
    private int [][] array;
    private int [][] game;
    private boolean forceStop;

    private int level;
    private boolean help;
    private Tree<Vector> cells;

    private ArrayList<Action> actions;
    private int index;

    /**
     * write to file :: undo
     * @throws IOException
     */
    private void writeToUndo() throws IOException {

    }

    /**
     * write to file :: redo
     * @throws IOException
     */
    private void writeToRedo() throws IOException {

    }

    /**
     * retrive from file :: undo
     * @throws IOException
     */
    private void retrieveFromUndo() throws IOException {

    }

    /**
     * retrieve to redo
     * @throws IOException
     */
    private void retrieveToRedo() throws IOException {

    }

    /**
     * resetting the game
     */
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
                    System.out.print("added\n");
                }

            }
        }
    }

    /**
    separator - clearing the console screen
     */
    public static void separate ()  {

        System.out.print("\033[H\033[2J");
        System.out.flush();
        // write a whole load of lines

    }

    /** Generate Sudoku Array - two dimensional
     * DEPRECATED
     * @param xAxis
     * @param yAxis
     * @return
     */
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

    /**
    Write in Help mode
     */
    private void  _writeGrid_cmd(boolean start){

        int sqrt = (int)Math.sqrt(this.yAxis);
        final String ANSI_BLUE = "\u001B[34m"; final String ANSI_RESET = "\u001B[0m"; final String ANSI_YELLOW = "\u001b[33m";
        final String ANSI_RED = "\u001b[31m"; final String ANSI_GREEN = "\u001b[32m";
        if (forceStop){
            finalizeObject();
            return;
        }
        boolean solved = false;
        if (!start) {
            solved = Helper.checkGamneEnd(array, cells, yAxis, xAxis); // everyting check is with binary search tree so it's pretty fast.
        }
        if (solved) {
            System.out.println(ANSI_GREEN + "Solved!" + ANSI_RESET+", would you like to save game?");
            Scanner scanner = new Scanner(System.in);
            String yes = scanner.nextLine();
            if (yes.equals("Y") || yes.equals("y")){
                System.out.println("Please give a name to the game");
                String fileName = scanner.nextLine() + ".anxgame";
                try {
                    Database.writeDatabase(fileName);
                    for (Action action: actions)
                        Database.addAction(action, fileName);
                }catch (Exception e){

                }
            }else {

            }
        }
        // if not solved
        if (!solved){
            for (int i = 0; i < yAxis; i++) {
                Tree<Integer> random;
                if (start) {
                    // randomise the values to be ommitted
                    if (xAxis ==9 && yAxis ==9) {
                        random = Randomiser.generateTreeList(level, xAxis);
                    }
                    else{
                        random = new Tree<>(null);
                        for (int c = 1; c < yAxis; c++){
                            Random ran = new Random();
                            if (ran.nextInt(2) == 0)
                                random.add(c);
                        }
                    }
                } else {
                    // random is now a balance tree. Usually from 1-9 would be a linear architecture
                    // with balancedTree from helper class, this can be done
                    random = new Tree<>(null);
                    random = Helper.balancedTree(1, yAxis>xAxis?yAxis:xAxis);

                }
                // set up strings
                String upperBound = "╔═════╗ ";
                String leftBound = "║  ";
                String rightBound = " ║ ";
                String lowerBound = "╚═════╝ ";
                String printer = "";
                // each line is written
                for (int i1 = 0; i1 < xAxis; i1++) {
                    String ending = (i1 + 1) % sqrt == 0 ? "  " : "";
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
                        randomizer = randomizer.length() == 2? randomizer: randomizer + " ";
                        if (start)
                            cells.add(new Vector(i, i1));

                    } else {
                        array[i][i1] = 0;
                        // determining the starting position
                        if (this.cell == null) {
                            randomizer = "X ";
                            this.cell = new Vector(i, i1);
                        } else if (this.cell.getColumn() == i1 && this.cell.getRow() == i) {
                            randomizer = "X ";
                        } else
                            randomizer = "  ";
                    }
                    String ending = (i1 + 1) % sqrt == 0 ? "  " : "";
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
                                // set color de bflue
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
                    String ending = (i1 + 1) % sqrt == 0 ? "  " : "";
                    printer += lowerBound + ending;
                    System.out.print(lowerBound + ending);
                }
                System.out.print("\n");
                printer += "\n";


                if ((i + 1) % sqrt == 0) {
                    System.out.print("\n");
                }
            }// for

            showPrompts();
        }// if
    }



    /**
    Write in Sudoku Grid
     */
    private  void _writeGridStart(int yAxis, int xAxis){
        SudokuGrid generator = new SudokuGrid(yAxis);
        // I want a clear console here
        separate();
        this.game = generator.getGame(); // get the array
        this.array = new int [yAxis][xAxis];
        for(int i = 0; i<game.length; i++){
            for (int i1 = 0; i1< game[i].length; i1++ ){
                //System.out.print(game[i][i1]);
                array[i][i1] = game[i][i1];
            }
            //System.out.print("\n");
        }

        _writeGrid_cmd(true);

    }

    /**
     Write in Sudoku Grid
     */
    private  void _writeGridStart(int gridcount){
        Resizoku generator = new Resizoku(yAxis);
        // I want a clear console here
        separate();
        generator.solveBruteForce();
        this.game = generator.grid ; // get the array
        this.array = new int [yAxis][xAxis];
        for(int i = 0; i<game.length; i++){
            for (int i1 = 0; i1< game[i].length; i1++ ){
                array[i][i1] = game[i][i1];
            }
        }
        _writeGrid_cmd(true);

    }

    /**
     Write in Sudoku Grid
     */
    private  void _writeGridStart(int x, int y, boolean irreg){
        Resizoku generator = new Resizoku(xAxis,yAxis);
        // I want a clear console here
        separate();
        generator.solveBruteForce();
        this.game = generator.grid ; // get the array
        System.out.println(" "+ game.length+ " "+ game[0].length);
        this.array = new int [yAxis][xAxis];
        for(int i = 0; i<array.length; i++){
            for (int i1 = 0; i1< array[i].length; i1++ ){
                array[i][i1] = game[i][i1];
            }
        }
        _writeGrid_cmd(true);

    }

    // public materials
    // getters and setters


    /**
     * array setting
     * @param array
     */
    public void setArray (int [][] array) {
        this.array =array;
    }


    /** getYAxis to set yAxis
     *
     * @return, default
     */
    public int getYAxis() {
        return yAxis;
    }

    /**
     * showing prompts, for the game
     */
    public void showPrompts (){

        System.out.println("Current Grid is: (" + this.cell.getRow() +","
        + this.cell.getColumn() + "), use W,S,A,D to move around or type in values. Click 'q' to quit and 'h' to change to help mode"
        + "\n"
        );

        if (help){
            System.out.println("HELP MODE ON! Click h to return to game mode");
            Helper.printPossibilities(array, cell, yAxis, xAxis );
        }

        Scanner scanner = new Scanner(System.in); // create a scanner
        String value = scanner.nextLine(); // get input from user

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
                        separate();
                        found = true;
                        _writeGrid_cmd(false);
                        return;
                    }
                }
            }
            if (!found) {
                separate();
                _writeGrid_cmd(false);
            }
            return;

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
                        separate();
                        found = true;
                        _writeGrid_cmd(false);
                        return;
                    }
                }
            }
            if (!found) {
                separate();
                _writeGrid_cmd(false);
            }
            return;

        }
        else if (value.equals("A")|| value.equals("a")){
            boolean found = false;

            for (int column = this.cell.getColumn()-1 ; column >= 0; column-- ){
                int current = array[cell.getRow()][column];

                if (!cells.contains(new Vector(cell.getRow(), column))){
                    this.cell.setColumn(column);
                    separate();
                    found = true;
                    _writeGrid_cmd(false);
                    break;
                }
            }
            if (!found){
                separate();
                _writeGrid_cmd(false);
            }
            return;

        }
        else if (value.equals("D")|| value.equals("d")){
            boolean found = false;
            for (int column = this.cell.getColumn()+1 ; column < this.xAxis; column++ ){
                int current = array[cell.getRow()][column];
                if (!cells.contains(new Vector(cell.getRow(), column))){
                    found = true;
                    this.cell.setColumn(column);
                    separate();
                    _writeGrid_cmd(false);
                    break;
                }

            }
            if (!found) {
                separate(); // call separator function
                _writeGrid_cmd(false);
            }
            return;

        }
        else if (value.equals("H")|| value.equals("h")){
            // Option de Help
            help = !help;
            separate();
            _writeGrid_cmd(false);
            return;

        }

        else if (value.startsWith("H")|| value.startsWith("h")){
            // Option de Help
            try{
                String posiOne = value.split(" ")[1];
                int num = Integer.parseInt(posiOne);
                System.out.println("SS: " + num);
                int larger = xAxis > yAxis ? xAxis : yAxis;
                if (num >= 0 && num <= larger){
                    int x = Helper.getNumberOfGaps(array, game, num);
                    System.out.println("Numbers of "+ num + " left: "+ x);
                    separate();
                    _writeGrid_cmd(false);
                }else {
                    separate();
                    _writeGrid_cmd(false);
                }

            }catch (Exception e){
                separate();
                _writeGrid_cmd(false);
            }
            return;

        }

        else if (value.equals("Q") || value.equals("q") || value.equals("quit") || value.equals("Quit")|| value.equals("QUIT")){
            System.out.println("Do you want to save the game");
            String yes = scanner.nextLine();
            if (yes.equals("Y") || yes.equals("y")){
                System.out.println("Please give a name to the game");
                String textIput =scanner.nextLine();
                String fileName =  textIput + ".anxgame";
                String fileNameGrid = textIput + ".anxgrid";
                try {
                    Database.writeDatabase(fileName);
                    for (Action action: actions)
                        Database.addAction(action, fileName);
                    Database.writeDatabase(fileNameGrid);
                    for (int row = 0; row < game.length; row++){
                        String line = "";
                        for (int col = 0; col < game[row].length; col++){
                            line += ((cells.contains(new Vector(row,col)) ? array[row][col]: 0) + (col == game[row].length ?"":","));
                        }
                        Database.addLine(line, fileNameGrid);
                    }

                }catch (Exception e){

                }
            }
            return;

        }

        else if (value.equals("u") || value.equals("U") || value.equals("undo")|| value.equals("Undo")){
            if (index >=0){
                Action action = actions.get(index);
                index--;
                Vector v = action.getCurrentVector();
                int row = v.getRow();
                int col = v.getColumn();
                int val = action.getOldValue();

                array[row][col] = val;
            }

            separate();
            _writeGrid_cmd(false);
            return ;
        }
        else if (value.equals("r") || value.equals("R") || value.equals("redo")|| value.equals("Redo")){
            if (index < actions.size()){

                index++;
                Action action = actions.get(index);
                Vector v = action.getCurrentVector();
                int row = v.getRow();
                int col = v.getColumn();
                int val = action.getValue();
                System.out.println(val);
                array[row][col] = val;
            }
            separate();
            _writeGrid_cmd(false);
            return ;
        }

        else if (value.equals("dev") || value.equals("DEV")|| value.equals("Dev") ){
            final String ANSI_BLUE = "\u001B[34m";  final String ANSI_RESET = "\u001B[0m";  final String ANSI_YELLOW = "\u001b[33m";

            System.out.println(ANSI_BLUE + "You are now in developer mode, please type in the command, please check if the SUDOKU EDITION is VALID" + ANSI_YELLOW);

            while (true) {


                String cmd = scanner.nextLine();
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

                        break;

                    }

                    else if (args[1].equals("<quit>") || args[1].equals("<player>")) {
                        System.out.println("Exiting Dev Mode ----> " + ANSI_RESET );

                        break;
                    }
                    else if (args[1].equals("<printGrid>") || args[1].equals("<printAnswer>")) {
                        System.out.println("Printing Answer ----> " + ANSI_BLUE );
                        Helper.printSudoku(game);
                        System.out.print(ANSI_YELLOW);

                    }
                    else if (args[1].equals("<solve>") || args[1].equals("<solveGame>")) {
                        array = new int[yAxis][xAxis];
                        Helper.printSudoku(game);
                        for (int i = 0; i < game.length; i++){
                            array[i] = game[i];
                        }
                        System.out.println("Exiting Dev Mode ----> " + ANSI_RESET );
                        break; // exit the while loop

                    }
                    else {
                        System.out.println("Unknown Annex Function"+ANSI_YELLOW);
                    }
                }catch (Exception err){
                    System.out.println( "Wrong Syntax for AnnexCode" +ANSI_YELLOW);
                }
            }


            _writeGrid_cmd(false);
            return;

        }
        else {
            try {
                // parsing to value
                int val = Integer.parseInt(value);
                int larger = yAxis> xAxis? yAxis: xAxis;
                if (val >= 0 && val <= larger) {
                    int old = array[cell.getRow()][cell.getColumn()];
                    array[cell.getRow()][cell.getColumn()] = val;
                    Action action = new Action("<INS>", new Vector(cell), new Vector(cell), val, old);
                    if (index < actions.size() - 1) {
                        ArrayList<Action> newActions = new ArrayList<Action>();
                        for (int i = 0; i < index + 1; i++) {
                            newActions.add(actions.get(i));
                        }
                        actions = newActions;
                    }
                    actions.add(action);
                    System.out.println(actions.size());
                    index = actions.size()-1;
                }
                separate();
                _writeGrid_cmd(false);
            }
            catch (Exception err){
                try {
                    // parsing to coordinate
                    if (value.startsWith("(") && value.endsWith(")")){
                        String v1 =value.substring(1,2);
                        String v2 =value.substring(3,4);
                        int row = Integer.parseInt(v1);
                        int column = Integer.parseInt(v2);
                        if (row >= 0 && row < yAxis && column >= 0 && column < xAxis) {
                            if (!cells.contains(new Vector(row, column))) {
                                this.cell.setColumn(column);
                                this.cell.setRow(row);

                                separate();
                                _writeGrid_cmd(false);
                            }
                        }else {
                            separate();
                            _writeGrid_cmd(false);
                        }
                    }
                    else {
                        separate();
                        _writeGrid_cmd(false);
                    }

                }catch (Exception err1){
                    separate();
                    _writeGrid_cmd(false);
                }
            }
            return;

        }

    }


    /**
     * Setting Force Stop
     * @param forceStop
     */
    public void setForceStop(boolean forceStop) {
        //System.out.println("forced");
        this.forceStop = forceStop;
    }

    /**
     * Constructor
     * @param gridCount
     */
    public SudokuEngine(int gridCount){

        this.actions = new ArrayList<Action>();
        this.level = 5;
        help = false;
        this.xAxis = gridCount; this.yAxis = gridCount; // set things
        cells = new Tree<>(null); // Initiating
        forceStop = false;
        // write sudoku grid
        _writeGridStart(gridCount);
    }

    /** Public Constructor
     *
     * @param xAxis, length for x axis
     * @param yAxis, length for y axis
     * @param level, hard, medium, easy
     */
    public SudokuEngine(int count, int level){
        this.actions = new ArrayList<Action>();
        this.level = level;
        index = 0;
        help = false;
        this.xAxis = count; this.yAxis = count; // set things
        cells = new Tree<>(null); // Initiating
        forceStop = false;
        // write sudoku grid
        _writeGridStart(this.yAxis, this.xAxis);

    }

    /**
     * Constructor
     */
    public SudokuEngine(int count, int level, boolean isTimed){
        this.actions = new ArrayList<Action>();
        index = 0;
        this.level = level;
        help = false;
        this.xAxis = count; this.yAxis = count; // set things
        cells = new Tree<>(null); // Initiating
        forceStop = false;
        if (!isTimed) {// write sudoku grid
            _writeGridStart(this.yAxis, this.xAxis);
        }
    }

    /**
     * Constructor
     */
    public SudokuEngine(Vector vector){
        this.actions = new ArrayList<Action>();
        index = 0;
        help = false;
        this.xAxis = vector.getColumn(); this.yAxis = vector.getRow(); // set things
        cells = new Tree<>(null); // Initiating
        forceStop = false;
        _writeGridStart(this.yAxis, this.xAxis, true);
    }

    /**
     * test starting
     */
    public void startGame(){
        _writeGridStart(this.yAxis, this.xAxis);
    }

    /**
     * finaliser
     */
    public void finalizeObject() {
        System.out.println("Exiting Sudoku Engine---------------------------------------------------------------->  " );

    }
}
