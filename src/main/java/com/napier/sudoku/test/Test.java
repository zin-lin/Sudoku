package com.napier.sudoku.test;

import com.napier.sudoku.models.Tree;

public class Test {

    public static void main (String args []){
        Tree<Integer> integerTree = new Tree<>(12, true);
        integerTree.add(15);
        integerTree.add(1);
        integerTree.add(-1);
        integerTree.add(45);
        integerTree.add(30);
        integerTree.add(16);

        if (integerTree.contains(-100))
            System.out.println("Hello");
        else
            System.out.println("World");
    }

}
