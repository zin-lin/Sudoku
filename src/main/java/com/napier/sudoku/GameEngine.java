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
                                _game();
                                break;
                            }
                            case 2: {
                                SudokuEngine sudokuEngine = new SudokuEngine(9, 9, 4); // Standard Sudoku
                                _game();
                                break;
                            }
                            case 3:{
                                SudokuEngine sudokuEngine = new SudokuEngine(9, 9, 3); // Standard Sudoku
                                _game();
                                break;
                            }
                            default:
                                SudokuEngine sudokuEngine = new SudokuEngine(9, 9,3); // Standard Sudoku
                                _game();
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