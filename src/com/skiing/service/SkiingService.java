package com.skiing.service;

import java.util.LinkedList;
import java.util.List;

import com.skiing.model.MNode;

/**
 * find the longest and steepest route for skiing
 * 
 * @author Abhijeet
 *
 */
public class SkiingService {

    /* list of mountain ranges */
    private List<MNode> nodes;

    /* column length */
    private int colLength;

    /* final result */
    private MNode result;

    public SkiingService(int colLength) {
        nodes = new LinkedList<>();
        this.colLength = colLength;
        this.result = new MNode(0);
    }

    /**
     * add next mountain range with elevation
     * 
     * @param elevation
     */
    public void addNextMRange(int elevation) {
        MNode nextNode = new MNode(elevation);
        nodes.add(nextNode);
        int topIndex = nodes.size() - colLength - 1;
        int leftIndex = nodes.size() - 2;
        if (leftIndex >= 0) {
            addObserver(nodes.get(leftIndex), nextNode);
        }
        if (topIndex >= 0) {
            addObserver(nodes.get(topIndex), nextNode);
        }
    }

    /**
     * get maximum depth with steep
     * 
     * @return
     */
    public String getResultStr() {
        return result.getDepth() + "" + result.getSteep() + " start point:" + result.getElevation();
    }

    /**
     * adding observer while adding new node
     * 
     * @param oldNode
     * @param newNode
     * @return
     */
    private void addObserver(MNode oldNode, MNode newNode) {
        if (newNode.getElevation() > oldNode.getElevation()) {
            oldNode.addObservers(newNode);
            newNode.chekAndUpdateDepthAndSteep(oldNode, true, result);
        } else if (newNode.getElevation() < oldNode.getElevation()) {
            newNode.addObservers(oldNode);
            // notifying all the observer
            newNode.notifyChanges(result);
        }
    }
}
