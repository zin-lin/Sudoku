package com.napier.sudoku;
import com.napier.sudoku.models.Vector;
import com.napier.sudoku.persistence.Database;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
Game Engine is the entry point
@author : Zin Lin Htun
@matric : 40542237@live.napier.ac.uk
*/
public class GameEngine {

    /**
     * starting the gamethread in accordance with time constraints
     * @param timeLimit, the maximum time when the game will stop
     * @param level , level of the game
     */
    private static void _parallelise(int timeLimit, int level) {
        SudokuEngine engine = new SudokuEngine(9,6-level,true);
        GameThread gameThread = new GameThread(engine, Thread.currentThread());

        gameThread.start();
        try {
            Thread.sleep(timeLimit);
            System.out.println("Game Over, time limit of " + timeLimit + " seconds reached, enter any key to exit");
            gameThread.setStop(true);
        } catch (InterruptedException e) {
            System.out.println("Game is Finished");
        }
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // DO ABS NTH
        }
        _game();
    }

    /**
     * The main method
     * @param args
     */
    private static void _game(){
        System.out.println("Choose Game: ");
        System.out.println("1) Standard Sudoku Game");
        System.out.println("2) Irregular Sudoku Game");
        System.out.println("3) Hardcore Sudoku");
        System.out.println("4) replay games");
        System.out.println("q to quit");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (Exception err){
            if (input.equals("q")){
                choice = -1;
            }
            else{
                choice = 0;
            }
        }

        switch (choice){
            case 1:
            {
                // prompts for additions
                System.out.println("Choose Extra Features for the game: ");
                System.out.println("1) Ninja - Against Timer");
                System.out.println("2) Nothing - normal game");
                Scanner adder = new Scanner(System.in);
                int adderText;
                try {
                    adderText = Integer.parseInt(scanner.nextLine());
                }catch (Exception err){
                    adderText = 0;
                }

                switch (adderText)
                {
                    case 1:{

                            System.out.println("Choose Your Level");
                            System.out.println("1) Beginner");
                            System.out.println("2) Mediocre ");
                            System.out.println("3) Master");
                            Scanner level = new Scanner(System.in);
                            int levelText;
                            try{
                                levelText = Integer.parseInt(scanner.nextLine());
                            }catch (Exception exception){
                                levelText = 0;
                            }
                            System.out.println("Enter in seconds:");
                            int time;
                            try {
                                time = level.nextInt()*1000;
                            }catch (Exception exception){
                                time = 70000;
                            }
                            switch (levelText)
                            {
                                case 1: {
                                    _parallelise(time,1);
                                    break;
                                }
                                case 2: {
                                    _parallelise(time,2);
                                    break;
                                }
                                case 3:{
                                    _parallelise(time,3);
                                    break;
                                }
                                default:
                                    _parallelise(time,2);
                                    break;
                            }
                    }
                    case 2:{
                        System.out.println("Choose Your Level");
                        System.out.println("1) Beginner");
                        System.out.println("2) Mediocre ");
                        System.out.println("3) Master");
                        Scanner level = new Scanner(System.in);
                        int levelText;
                        try{
                             levelText = Integer.parseInt(scanner.nextLine());
                        }catch (Exception exception){
                            levelText = 0;
                        }
                        switch (levelText)
                        {
                            case 1: {
                                SudokuEngine sudokuEngine = new SudokuEngine(9,  5); // Standard Sudoku
                                sudokuEngine = null;
                                _game();
                                break;
                            }
                            case 2: {
                                SudokuEngine sudokuEngine = new SudokuEngine(9, 4); // Standard Sudoku
                                sudokuEngine = null;
                                _game();
                                break;
                            }
                            case 3:{
                                SudokuEngine sudokuEngine = new SudokuEngine(9,  3); // Standard Sudoku
                                sudokuEngine = null;
                                _game();
                                break;
                            }
                            default:
                                SudokuEngine sudokuEngine = new SudokuEngine(9, 3); // Standard Sudoku
                                sudokuEngine = null;
                                _game();
                                break;
                        }
                    }
                    default:
                    {
                        SudokuEngine sudokuEngine = new SudokuEngine(9, 3); // Standard Sudoku
                        sudokuEngine = null;
                        _game();
                        break;
                    }
                }
            }
            case 2: {
                // Customs
                System.out.println("Enter row and column: ");
                int x;
                int y;

                try {
                    x = Integer.parseInt(scanner.nextLine());
                    y = Integer.parseInt(scanner.nextLine());
                }catch (Exception exception){
                    x = 0; y=0;
                    break;
                }
                try{
                    SudokuEngine sudokuEngine = new SudokuEngine(new Vector(x, y));
                    sudokuEngine = null;
                    _game();
                }catch (Exception e)
                {
                    _game();
                }
                break;

            }
            case 3: {
                // Hardcore Sudoku

                System.out.println("Enter a square number: ");
                int square;
                try {
                    square = Integer.parseInt(scanner.nextLine());
                    int sqrt = (int)Math.sqrt(square);
                    square = sqrt*sqrt;
                }catch (Exception exception){
                    square = 0;
                    break;
                }
                SudokuEngine sudokuEngine = new SudokuEngine(square);
                sudokuEngine = null;
                _game();
                break;

            }
            case 4: {
                // replays
                ArrayList<String> games = Database.loadGames();
                int index = 1;
                if (games.size()>0){
                    for (String game : games) {
                        System.out.println(index + ". " + game);
                        index++;
                    }
                }
                else {
                    System.out.println("Nth here");
                    _game();
                }
                String game = scanner.nextLine();
                try {
                    ArrayList<String>cmds = Database.loadGame(game+ ".anxgame");
                    System.out.println(cmds.size() );
                    int [][] array = Database.loadGrid(game+ ".anxgrid");
                    ReplayEngine replayEngine = new ReplayEngine(array, cmds);
                    replayEngine = null;
                    _game();

                }catch (Exception e){
                    _game();
                }

            }
            case -1:{
                System.out.println("Shutting Game Down! bye bye!");
                System.exit(0);
            }
            default: {
                System.out.println("Choice Not Understandable");
                _game();
                break;
            }
        }

    }

    /**
    main :: void ::: ENTRYPOINT
     */
    public static void main (String [] args){
        // Call the game function
        _game();
    }

}

/**
 * Protected class
 */
class GameThread extends Thread {
    public SudokuEngine engine;
    public Thread thread;
    private volatile Boolean stop = false;

    /**
     * getter method
     * @return
     */
    public Boolean getStop() {
        return stop;
    }

    /**
     * setter method
     * @param stop
     */
    public void setStop(Boolean stop) {
        engine.setForceStop(true);
        this.stop = stop;
    }

    /**
     * Public Constructor
     * @param engine
     */
    public GameThread(SudokuEngine engine, Thread thread) {
        this.engine = engine;
        this.thread = thread;
    }

    /**
     * override method, run to parrallel threads
     */
    @Override
    public void run() {
        engine.startGame();
        thread.interrupt();
        return; // ending the thread
    }
}