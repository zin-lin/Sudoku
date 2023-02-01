package com.napier.sudoku;

/// 9X9 ONLY
public class EasyTestSudokuEngine {

    public EasyTestSudokuEngine(){
        for (int i = 0; i <9; i++) {
            for (int i1 = 0; i1 < 9; i1++) {
                String ending = (i1 + 1) % 3 == 0 ? "  " : "";
                System.out.print(ending);
            }

            System.out.print("\n");
            for (int i1 = 0; i1 < 9; i1++) {
                String randomizer = "_";
                String ending = (i1 + 1) % 3 == 0 ? "  " : "";
                System.out.print(randomizer + ending);
            }
            System.out.print("\n");


            for (int i1 = 0; i1 < 9; i1++) {
                String ending = (i1 + 1) % 3 == 0 ? "  " : "";

                System.out.print(ending);
            }//for
        }//outer for
    }

}
