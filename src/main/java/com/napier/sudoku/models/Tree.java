package com.napier.sudoku.models;
/* Purpose : Faster Accessing, O Log(n) for accessing O(n) for space allocation
// Author : Zin Lin Htun
// @matric : 40542237@live.napier.ac.uk*/

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

    public Tree(Template value)
    {
        this.value = value;
        this.rightNode = new Tree<Template>();
        this.leftNode = new Tree<Template>();

    }

    private void _add_node (Tree<Template>t){
        if (this.value == null) {
            this.value = t.value;
            rightNode = new Tree<Template>();
            leftNode = new Tree<Template>();
            this.rightNode = t.rightNode; // empty nodes saying terminal
            this.leftNode = t.leftNode; // empty nodes saying terminal
            System.out.println(this.leftNode.value);
        } else {
            if ((int)t.value > (int)value){
                // Compare if the check is bigger than value go right else left
                this.rightNode._add_node(t);
            }
            else if ((int) t.value < (int) value) {
                this.leftNode._add_node(t);
            }
            else if ((int)t.value == (int) value) {
                // Do Nth
            }
        }
    }

    // public methods

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
            else {
                // equals
                // Do Nth
            }
        }
    }
    // public methods end

    // get smallest but not the one that has
    public Template getSmallest (Template not){
        Template ans = null;
        if (this.leftNode.value == null || (int)this.leftNode.value == (int)not) {

            if ((int)this.value == (int)not) {
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

    // Overloading // get actual loading
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

    public boolean contains(Template t){
        boolean ans = false;
        if (this.value != null) {
            if ((int)this.value == (int)t) {
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
            } else if ((int) t > (int) value) {
                // Compare if the check is bigger than value go right else left
                this.rightNode.remove(t);
            } else if ((int) t < (int) value) {
                this.leftNode.remove(t);
            }
        }
    }

}
