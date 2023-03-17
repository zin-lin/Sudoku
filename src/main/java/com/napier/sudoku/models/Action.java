package com.napier.sudoku.models;

/**
 * This class represents an action taken by the user
 * @Author: Zin Lin Htun
 * @matric number: 40542237
 */
public class Action {

    private String action;
    private Vector currentVector;
    private Vector nextVector;
    private int value;
    private int oldValue;


    /**
     *
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     *
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     *
     * @return
     */
    public Vector getCurrentVector() {
        return currentVector;
    }

    /**
     *
     * @param currentVector
     */
    public void setCurrentVector(Vector currentVector) {
        this.currentVector = currentVector;
    }

    /**
     *
     * @return
     */
    public Vector getNextVector() {
        return nextVector;
    }
    /**
     *
     * @param nextVector
     */
    public void setNextVector(Vector nextVector) {
        this.nextVector = nextVector;
    }

    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }
    public int getOldValue() {
        return oldValue;
    }

    /*
    set oldValue
     */
    public void setOldValue(int oldValue) {
        this.oldValue = oldValue;
    }

    public Action(String action, Vector currentVector, Vector nextVector, int value) {
        /**
         * Setting all the values for all the attributes
         */
        this.action = action;
        this.currentVector = currentVector;
        this.nextVector = nextVector;
        this.value = value;
    }

    @Override
    public String toString() {
        if (this.action.equals("<MOVE>"))
            return ("<MOVE>," +(currentVector.getRow()+"-"+currentVector.getColumn())+","+
                    (nextVector.getRow()+"-"+nextVector.getColumn())+ "\n");
        else
            return "<INS>," +(currentVector.getRow()+"-"+currentVector.getColumn())+","+
                    ","+ oldValue  +"," + value + "\n";
    }
}
