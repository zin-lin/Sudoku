package com.napier.sudoku.random;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

// Randomiser class tests the randomness of number generation to get unique random numbers
public class Randomiser {

    // 2nd entry point of this app
    public static int [] generate (int iteration, int maxBound){

        int [] array = new int [iteration];
        AtomicInteger i = new AtomicInteger();
        ArrayList<Integer>printer = new ArrayList<Integer>();
        ThreadLocalRandom.current().ints(1, 9).distinct(). // Thread safety is done with this
                limit(maxBound).forEach(integer -> {
                    // Lambda
            array[i.get()] = integer;
            i.getAndIncrement();
        });
        return  array;
    }

}
