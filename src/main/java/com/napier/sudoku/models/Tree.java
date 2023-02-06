package com.napier.sudoku.models;
// Purpose : Faster Accessing
// Author : Zin Lin Htun
// @matric : 40542237@live.napier.ac.uk
public class Tree <Template extends Object>{
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

    // public constructor
    public Tree(Template value, boolean isRoot)
    {
        this.value = value;
        if (isRoot){
            this.rightNode = new Tree<Template>();
            this.leftNode = new Tree<Template>();
        }
    }


    // public methods

    public void add(Template  t) {
        if (this.value == null) {
            this.value = t;
            this.rightNode = new Tree<Template>(); // empty nodes saying terminal
            this.leftNode = new Tree<Template>(); // empty nodes saying terminal
        } else {
            if ((int)t > (int)value){
                // Compare if the check is bigger than value go right else left
                this.rightNode.add(t);
            }
            else if ((int) t < (int) value) {
                this.leftNode.add(t);
            }
            else if ((int)t == (int) value) {
                // Do Nth
            }
        }
    }
    // public methods end

    public boolean contains(Template t){
        boolean ans = false;
        if (this.value != null) {
            if (this.value == t) {
                // value is terminal
                ans = true;
            } else if ((int) t > (int) value) {
                // Compare if the check is bigger than value go right else left
                ans = this.rightNode.contains(t);
            } else if ((int) t < (int) value) {
                ans = this.leftNode.contains(t);
            }
        }
        return ans;
    }

}
