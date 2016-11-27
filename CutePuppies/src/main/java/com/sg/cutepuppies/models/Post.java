/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.models;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class Post {

    private int postId;
    private User createdByUser;
    private int createdByUserId;
    private Date createdOnDate;
    private int updatedByUserId;
    private Date updatedOnDate;
    private int archivedByUserId;
    private Date archivedOnDate;
    private Content publishedContent;
    private List<Content> allContentRevisions;

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

    public Date getCreatedOnDate() {
        return createdOnDate;
    }

    public void setCreatedOnDate(Date createdOnDate) {
        this.createdOnDate = createdOnDate;
    }

    public int getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(int updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public Date getUpdatedOnDate() {
        return updatedOnDate;
    }

    public void setUpdatedOnDate(Date updatedOnDate) {
        this.updatedOnDate = updatedOnDate;
    }

    public int getArchivedByUserId() {
        return archivedByUserId;
    }

    public void setArchivedByUserId(int archivedByUserId) {
        this.archivedByUserId = archivedByUserId;
    }

    public Date getArchivedOnDate() {
        return archivedOnDate;
    }

    public void setArchivedOnDate(Date archivedOnDate) {
        this.archivedOnDate = archivedOnDate;
    }

    public Content getPublishedContent() {
        return publishedContent;
    }

    public void setPublishedContent(Content publishedContent) {
        this.publishedContent = publishedContent;
    }

    public List<Content> getAllContentRevisions() {
        return allContentRevisions;
    }

    public void setAllContentRevisions(List<Content> allContentRevisions) {
        this.allContentRevisions = allContentRevisions;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }
}
