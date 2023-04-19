package com.napier.sudoku.random;
import com.napier.sudoku.models.memory.Tree;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Ranomiser Class
 * @author : Zin Lin Htun
 */

// Randomiser class tests the randomness of number generation to get unique random numbers
public class Randomiser {

    // 2nd entry point of this app
    @Deprecated
    public static int [] generate (int iteration, int maxBound){

        int [] array = new int [iteration];
        AtomicInteger i = new AtomicInteger();
        ArrayList<Integer>printer = new ArrayList<Integer>();

        ThreadLocalRandom.current().ints(1, maxBound).distinct(). // Thread safety is done with this // parallelstreaming
                limit(iteration).forEach(integer -> {
            array[i.get()] = integer;
            i.getAndIncrement();
        });
        return  array;
    }



    /**
     * Generate using tree structure
     * @param iteration
     * @param maxBound
     * @return
     */
    public static Tree<Integer> generateTreeList (int iteration, int maxBound){

        Tree<Integer> array = new Tree<>(null,true);
        ThreadLocalRandom.current().ints(1, maxBound+1).distinct(). // Thread safety is done with this // parallelstreaming
                limit(iteration).forEach(integer -> {
            array.add(integer);
        });
        return  array;
    }

}
