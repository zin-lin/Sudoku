package com.napier.sudoku.test;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

// Randomiser class tests the randomness of number generation to get unique random numbers
public class Randomiser {

    // 2nd entry point of this app
    public static void main (String args[]){

        ArrayList<Integer>printer = new ArrayList<Integer>();
        ThreadLocalRandom.current().ints(1, 9).distinct(). // Thread safety is done with this
                limit(5).forEach(printer::add);
        for (int o: printer
             ) {
            System.out.println(o);
        }
    }

}
