package com.napier.sudoku.test;

import com.napier.sudoku.models.Tree;
import com.napier.sudoku.random.Randomiser;

import java.util.Arrays;

public class Test {

    public static void main (String args []){
        Tree<Integer> integerTree = new Tree<>(12, true);
        integerTree.add(15);
        integerTree.add(140000);
        integerTree.add(1);
        integerTree.add(-34);
        integerTree.add(45);
        integerTree.add(30);
        integerTree.add(16);
        integerTree.remove(-348);
        System.out.print(integerTree.getSmallest(-1000000));
        int [] xox = {1,2,3,4,5,6,7,8,9};
        Arrays.stream(xox).parallel().forEach(e-> System.out.println(e));
    }

}
