package com.napier.sudoku.persistence;

/**
 Class Database :: to deal with data persistence for undo and redo
 Author : Zin Lin Htun
 @matric : 40542237@live.napier.ac.uk */

import com.napier.sudoku.models.Action;
import com.napier.sudoku.models.Vector;
import com.napier.sudoku.models.memory.Tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Database {

    /**
    getGames get all the games from the database
    <return> games :: Tree <String>
     */
    public static Tree <String> getGames (){
        Tree <String> tree = new Tree<>(null);
        File currentFolder = new File("./");
        File [] list = currentFolder.listFiles();
        for (File file: list){
            if (file.getName().endsWith(".csv") && file.getName().startsWith("annexsudoku"))
                tree.add(file.getName());
        }
        return tree;
    }

    /**
    write Database will write file for each game.
    <param> name :: String
     */
    public static void writeDatabase(String name) throws IOException {
        FileWriter fileWriter = new FileWriter(name);
        fileWriter.append("");
    }

    /**
    add a movement to the file
    <param> file :: String
    <param> origin :: Vector
    <param> destination :: Vector
     */
    public static void addMove (String file, Vector origin, Vector destination) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.append("<MOVE>," +(origin.getRow()+"-"+origin.getColumn())+","+
                (destination.getRow()+"-"+destination.getColumn())+ "\n");
    }

    /**
    add a movement to the file
     */
    public static void addInsertion (String file, Vector cell, int oldVal, int newVal) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.append("<INS>," +(cell.getRow()+"-"+cell.getColumn())+","+
                oldVal+","+ newVal + "\n");
    }

    /**
    add an action in.
     */
    public static void addStack (ArrayList<Action> actions){

    }
}
