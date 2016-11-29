/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.models;

/**
 *
 * @author apprentice
 */
public class Tag {
    private int tagID;
    private String tagDescription;
    private int numUsed;

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public int getNumUsed() {
        return numUsed;
    }

    public void setNumUsed(int numUsed) {
        this.numUsed = numUsed;
    }
}
