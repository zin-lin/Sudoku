package com.napier.sudoku;

import com.napier.sudoku.models.Action;
import com.napier.sudoku.models.Helper;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author : Zin Lin Htun
 */
public class ReplayEngine {

    /**
     * region of private
     */
    private int [][] array;
    private ArrayList <String> cmds;
    private int index;

    /**
     * replay function
     */
    private void replay (){
        SudokuEngine.separate();
        System.out.println("Press n to continue, b to back off and q to quit");
        Helper.printSudoku(array);
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (line.equals("n")){
            if (index < cmds.size()){
                String cmd = cmds.get(index);
                Action action = Action.getActionFromString(cmd);
                int row = action.getCurrentVector().getRow();
                int col = action.getCurrentVector().getColumn();
                System.out.println("Shifting ");
                int val = action.getValue();
                array[row][col] = val;
                index++;
            }
            replay();

        } else if (line.equals("b")) {

            if(index>=1){
                index--;
                String cmd = cmds.get(index);
                Action action = Action.getActionFromString(cmd);
                int row = action.getCurrentVector().getRow();
                int col = action.getCurrentVector().getColumn();
                int val = action.getOldValue();
                array[row][col] = val;

            }
            replay();

        } else if (line.equals("q")) {
            return;
        }
        return;
    }

    //public

    /**
     * Constructor
     * @param array
     * @param cmds
     */
    public ReplayEngine (int [][] array, ArrayList<String> cmds) {
        this.array = array;
        this.cmds = cmds;
        index = 0;
        replay();
    }
}
