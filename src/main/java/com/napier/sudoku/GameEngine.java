package com.napier.sudoku;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/*
Game Engine is the entry point
Author : Zin Lin Htun
@matric : 40542237@live.napier.ac.uk
*/
public class GameEngine {

    private static void _game(){
        System.out.println("Choose Game: ");
        System.out.println("1) Standard Sudoku Game \uD83D\uDE03 ");
        System.out.println("2) Sudoku Define Yourself \uD83D\uDE0E ");
        System.out.println("3) Hardcore Sudoku \uD83D\uDE24");

        Scanner scanner = new Scanner(System.in);
        int choice;
        try {

            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception err){
            choice = 0;
        }

        switch (choice){
            case 1:
            {
                // prompts for additions
                System.out.println("Choose Extra Features for the game: ");
                System.out.println("1) Ninja - Against Timer \uD83E\uDD77\uD83C\uDFFC ");
                System.out.println("2) Nothing - normal game \uD83D\uDE43");
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
                        System.out.println("1) Beginner \uD83D\uDE4B\uD83C\uDFFC\u200D♂️ ");
                        System.out.println("2) Mediocre \uD83D\uDE4B\uD83C\uDFFC");
                        System.out.println("3) Master \uD83D\uDC69\uD83C\uDFFC\u200D\uD83C\uDF93");
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
                                SudokuEngine sudokuEngine = new SudokuEngine(9, 9, 3); // Standard Sudoku
                                _game();
                                break;
                            }
                            case 3:{
                                SudokuEngine sudokuEngine = new SudokuEngine(9, 9, 2); // Standard Sudoku
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
            default: {
                System.out.println("Choice Not Understandable");
                _game();
                break;
            }
        }

    }

    /*
    main :: void ::: ENTRYPOINT
     */
    public static void main (String [] args){
        // Call the game function
        _game();
    }

}