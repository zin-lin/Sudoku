package com.napier.sudoku.persistence;

/**
 Class Database :: to deal with data persistence for undo and redo
 Author : Zin Lin Htun
 @matric : 40542237@live.napier.ac.uk */

import com.napier.sudoku.models.Action;
import com.napier.sudoku.models.Vector;
import com.napier.sudoku.models.memory.Tree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Database {


    /**
    write Database will write file for each game.
    <param> name :: String
     */
    public static void writeDatabase(String name) throws IOException {
        FileWriter fileWriter = new FileWriter(name);
        fileWriter.append("");
        fileWriter.close();
    }


    /**
     add an action to the database file
     <param> file :: String
     <param> origin :: Vector
     <param> destination :: Vector
     */
    public static void addAction (Action action, String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(action.toString());
        writer.newLine();
        writer.close();
        fileWriter.close();
    }

    /**
     add a movement to the file
     <param> file :: String
     <param> origin :: Vector
     <param> destination :: Vector
     */
    public static void addLine (String str, String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(str.toString());
        writer.newLine();
        writer.close();
        fileWriter.close();
    }


    /**
     load games
     */
    public static ArrayList<String> loadGames () {
        File folder = new File("./"); // replace with your folder path
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> games = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".anxgame")) {
                games.add(file.getName().replace(".anxgame", ""));
            }
        }
        return games ;
    }


    /**
     * load a single list of commands of a game
     * @param fileName
     * @return
     */
    public static ArrayList<String> loadGame (String fileName){
        Path filePath = Paths.get(fileName);
        ArrayList <String> ans = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (!line.equals("\n"))
                    ans.add(line);
            }
        } catch (IOException e) {
        }
        return ans;
    }

    /**
     * load the grid
     * @param fileName
     * @return
     */
    public static int [][] loadGrid (String fileName){
        Path filePath = Paths.get(fileName);
        int length = 0;
        ArrayList <String> ans = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines){
                if (!line.startsWith("\n")) {
                    ans.add(line);
                    length = (line.split(",")).length;
                }
            }
        } catch (IOException e) {
        }
        int [][] returner = new int [ans.size()][length];
        for (int row = 0; row < returner.length; row++){
            for (int column = 0; column < returner[row].length; column++){
                try {
                    String thing = (ans.get(row).split(","))[column];
                    int val = Integer.parseInt(thing);
                    returner[row][column] = val;
                }catch (Exception e) {

                }
            }
        }
        return returner;
    }


}
