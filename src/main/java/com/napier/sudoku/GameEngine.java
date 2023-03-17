package com.napier.sudoku;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
Game Engine is the entry point
Author : Zin Lin Htun
@matric : 40542237@live.napier.ac.uk
*/
public class GameEngine {

    /**
     * starting the gamethread in accordance with time constraints
     * @param timeLimit, the maximum time when the game will stop
     * @param level , level of the game
     */
    private static void _parallelise(int timeLimit, int level) {
        SudokuEngine engine = new SudokuEngine(9,9,6-level,true);
        GameThread gameThread = new GameThread(engine, Thread.currentThread());

        gameThread.start();
        try {
            Thread.sleep(timeLimit);
            System.out.println("Game Over, time limit of " + timeLimit + " seconds reached, enter any key to exit");
            gameThread.setStop(true);
        } catch (InterruptedException e) {
            System.out.println("Game Won");
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
        System.out.println("2) Sudoku Define Yourself");
        System.out.println("3) Hardcore Sudoku");
        System.out.println("4) settings");
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
                                SudokuEngine sudokuEngine = new SudokuEngine(9, 9, 5); // Standard Sudoku
                                break;
                            }
                            case 2: {
                                SudokuEngine sudokuEngine = new SudokuEngine(9, 9, 4); // Standard Sudoku
                                break;
                            }
                            case 3:{
                                SudokuEngine sudokuEngine = new SudokuEngine(9, 9, 3); // Standard Sudoku
                                break;
                            }
                            default:
                                SudokuEngine sudokuEngine = new SudokuEngine(9, 9,3); // Standard Sudoku
                                break;
                        }
                    }
                    default:
                    {
                        SudokuEngine sudokuEngine = new SudokuEngine(9, 9,3); // Standard Sudoku
                        _game();
                        break;
                    }
                }
            }
            case 2: {
                // Customs

            }
            case 3: {
                // Hardcore Sudoku

            }
            case 4: {
                // settings

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