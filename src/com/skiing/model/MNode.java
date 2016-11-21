package com.skiing.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Mountain node
 * 
 * @author Abhijeet
 *
 */
public class MNode {

    /* elevation */
    private int elevation;

    /* number of nodes it can traverse */
    private int depth;

    /* fall in elevation */
    private int steep;

    /* list of observers to notify on change */
    private List<MNode> observers;

    public MNode(int value) {
        super();
        this.elevation = value;
        this.depth = 1;
        this.steep = 0;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public int getDepth() {
        return depth;
    }

    public int getSteep() {
        return steep;
    }

    public void setDepthAndSteepFall(int depth, int steepFall) {
        this.depth = depth;
        this.steep = steepFall;
    }

    /**
     * adding new observers for this node
     * 
     * @param node
     */
    public void addObservers(MNode node) {
        if (null == this.observers) {
            this.observers = new LinkedList<>();
        }
        observers.add(node);
    }

    /**
     * check and update depth and steep fall called when observed node value
     * changes
     * 
     * @param childNode
     */
    public void chekAndUpdateDepthAndSteep(MNode childNode, boolean isNotify, MNode maxNode) {
        int newDepth = childNode.getDepth() + 1;
        int newSteepFall = childNode.getSteep() + (this.getElevation() - childNode.getElevation());
        if (newDepth > this.getDepth() || (newDepth == this.getDepth() && newSteepFall > this.getSteep())) {
            this.setDepthAndSteepFall(newDepth, newSteepFall);
            // check and set max node
            if (newDepth > maxNode.getDepth()
                    || (newDepth == maxNode.getDepth() && newSteepFall > maxNode.getSteep())) {
                maxNode.setDepthAndSteepFall(newDepth, newSteepFall);
                maxNode.setElevation(this.elevation);
            }
            // if notify then notify all the observers
            if (isNotify) {
                this.notifyChanges(maxNode);
            }
        }
    }

    /**
     * Notify changes in depth and steep to observers
     */
    public void notifyChanges(MNode maxNode) {
        if (null != this.observers) {
            this.observers.forEach(node -> node.chekAndUpdateDepthAndSteep(this, true, maxNode));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MNode [elevation=");
        builder.append(elevation);
        builder.append(", depth=");
        builder.append(depth);
        builder.append(", steep=");
        builder.append(steep);
        builder.append(", observers=");
        builder.append(observers);
        builder.append("]");
        return builder.toString();
    }

}
