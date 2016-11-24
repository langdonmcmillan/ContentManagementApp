/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.models;

import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public class Post {
    private int postId;
    private int createdByUserId;
    private LocalDate createdOnDate;
    private int updatedByUserId;
    private LocalDate updatedOnDate;
    private int archivedByUserId;
    private LocalDate archivedOnDate;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(int createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public LocalDate getCreatedOnDate() {
        return createdOnDate;
    }

    public void setCreatedOnDate(LocalDate createdOnDate) {
        this.createdOnDate = createdOnDate;
    }

    public int getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(int updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public LocalDate getUpdatedOnDate() {
        return updatedOnDate;
    }

    public void setUpdatedOnDate(LocalDate updatedOnDate) {
        this.updatedOnDate = updatedOnDate;
    }

    public int getArchivedByUserId() {
        return archivedByUserId;
    }

    public void setArchivedByUserId(int archivedByUserId) {
        this.archivedByUserId = archivedByUserId;
    }

    public LocalDate getArchivedOnDate() {
        return archivedOnDate;
    }

    public void setArchivedOnDate(LocalDate archivedOnDate) {
        this.archivedOnDate = archivedOnDate;
    }
}
