package com.napier.sudoku.models.memory;


/* Class SudokuEngine :: This class deals with the tree architecture.
// Author : Zin Lin Htun
// @matric : 40542237@live.napier.ac.uk */

import java.util.function.Consumer;

public class Tree <Template>{
    //private materials
    private Tree () {this.value = null; this.rightNode = null; this.leftNode = null;}
    private Template value;
    private Tree<Template> leftNode;
    private Tree<Template> rightNode;

    // public material

    // Getters and setters
    public Tree<Template> getLeftNode() {
        return leftNode;
    }

    // Get the Right Node
    public Tree<Template> getRightNode() {
        return rightNode;
    }

    // Get the Left Node
    public Template getValue() {
        return value;
    }

    // Set Left Node
    public void setLeftNode(Tree<Template> leftNode) {
        this.leftNode = leftNode;
    }

    // Set Right Node
    public void setRightNode(Tree<Template> rightNode) {
        this.rightNode = rightNode;
    }
    // Could nae set value

    /*
    _add_node is the node adder.
    <param> Tree <Template> t to compare and add
     */
    private void _add_node (Tree<Template> t){
        if (this.value == null) {
            this.value = t.value;
            rightNode = new Tree<Template>();
            leftNode = new Tree<Template>();
            this.rightNode = t.rightNode; // empty nodes saying terminal
            this.leftNode = t.leftNode; // empty nodes saying terminal
            System.out.println(this.leftNode.value);
        } else {
            if (  t.value.toString().compareTo(value.toString()) > 0 ){
                // Compare if the check is bigger than value go right else left
                this.rightNode._add_node(t);
            }
            else if ( t.value.toString().compareTo(value.toString()) < 0) {
                this.leftNode._add_node(t);
            }
            else if ( t.value.toString().compareTo(value.toString()) == 0) {
                // Do Nth
            }
        }
    }

    // public constructor
    /*
    Public Constructor
     */
    public Tree(Template value, boolean isRoot)
    {
        this.value = value;
        if (isRoot){
            this.rightNode = new Tree<Template>();
            this.leftNode = new Tree<Template>();
        }
    }

    /*
    Public Constructor
    <param>
     */
    public Tree(Template value)
    {
        this.value = value;
        this.rightNode = new Tree<Template>();
        this.leftNode = new Tree<Template>();

    }

    // public methods

    /*
     a dedicated for method
     <param> lambda function
     */
    public void forEach (Consumer<? super Template> action){
        if (this.value != null){

            this.leftNode.forEach(action); // call recursive
            action.accept(this.value); // deal with lamdas
            this.rightNode.forEach(action); // call recursive
        }
    }

    /*
    get the indexed element with left side or right side
    <param> tims
    <param> left
     */
    public Template get (int times, boolean left){
        Template ans = null;
        if (times == 0){
            ans= this.value;
        }
        else {
            if (left) {
                times--;
                ans = this.leftNode.get(times, true);
            }
            else{
                times--;
                ans = this.rightNode.get(times, false);
            }
        }
        return ans;
    }

    /*
    add element to tree
    <param> t :: Template
     */
    public void add(Template  t) {
        if (this.value == null) {
            this.value = t;
            this.rightNode = new Tree<Template>(); // empty nodes saying terminal
            this.leftNode = new Tree<Template>(); // empty nodes saying terminal
        } else {
            if ( t.toString().compareTo(value.toString()) >0){
                // Compare if the check is bigger than value go right else left
                this.rightNode.add(t);
            }
            else if ( t.toString().compareTo(value.toString()) <0) {
                this.leftNode.add(t);
            }
            else {
                // equals
                // Do Nth
            }
        }
    }
    // public methods end

    /*
     get smallest but not the one that has
     <param> not :: Template
     */
    public Template getSmallest (Template not){
        Template ans = null;
        if (this.leftNode.value == null || this.leftNode.value.toString().equals(not.toString())) {

            if (this.value.toString().equals(not.toString())) {
                try {
                    ans = this.rightNode.getSmallest();
                } catch (Exception err) {
                    // Do nth
                }
            }
            else ans = this.value;
        }
        else{
            ans = this.leftNode.getSmallest(not);
        }
        return ans;
    }

    /*
    get smallest
     */
    public Template getSmallest (){
        Template ans = null;
        if (this.leftNode.value == null ) {
            ans = this.value;
        }
        else{
            ans = this.leftNode.getSmallest();
        }
        return ans;
    }

    /*
    conatins method to decide if Tree contains a particular element
    <param> t :: Template : to add
     */
    public boolean contains(Template t){
        boolean ans = false;
        if (this.value != null) {
            if (this.value.toString().equals(t.toString())) {
                ans = true;
            } else if ( t.toString().compareTo(value.toString()) > 0) {
                // Compare if the check is bigger than value go right else left
                ans = this.rightNode.contains(t);
            } else if (t.toString().compareTo(value.toString()) < 0) {
                ans = this.leftNode.contains(t);
            }
        }
        return ans;
    }

    /*
    remove element t :: Template from Tree
    <param> t :: Template
     */
    public void remove (Template t){
        boolean ans = false;
        if (this.value != null) {
            if ((int)this.value == (int)t) {
                if (this.leftNode.value != null && this.rightNode.value != null ){
                    this.value = null;
                    _add_node(leftNode);
                    //this.leftNode = null;
                    _add_node(rightNode);
                    //this.rightNode = null;
                }

                else if (this.rightNode.value != null ){
                    this.value = null;
                    _add_node(rightNode);
                    //this.rightNode = null;
                }
                else if (this.leftNode.value != null ){
                    this.value = null;
                    _add_node(leftNode);
                    //this.leftNode = null;
                }

                else if (this.leftNode.value == null && this.rightNode.value == null)
                    this.value = null; // end tree
            } else if (t.toString().compareTo(value.toString()) > 0) {
                // Compare if the check is bigger than value go right else left
                this.rightNode.remove(t);
            } else if (t.toString().compareTo(value.toString()) < 0) {
                this.leftNode.remove(t);
            }
        }
    }

}
