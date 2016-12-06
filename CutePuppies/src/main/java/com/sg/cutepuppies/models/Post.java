/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class Post {

    private int postId;

    private User createdByUser;
    private Date createdOnDate;
    
    private User updatedByUser;
    private Date updatedOnDate;
    
    private User archivedByUser;
    private Date archivedOnDate;

    private Content mostRecentContent;
    private Content publishedContent;
    private List<Content> allContentRevisions;
    private List<Content> postComments;
    private Content newComment;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Date getCreatedOnDate() {
        return createdOnDate;
    }

    public void setCreatedOnDate(Date createdOnDate) {
        this.createdOnDate = createdOnDate;
    }

    public Date getUpdatedOnDate() {
        return updatedOnDate;
    }

    public void setUpdatedOnDate(Date updatedOnDate) {
        this.updatedOnDate = updatedOnDate;
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

    public User getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(User updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public User getArchivedByUser() {
        return archivedByUser;
    }

    public void setArchivedByUser(User archivedByUser) {
        this.archivedByUser = archivedByUser;
    }

    public Content getMostRecentContent() {
        return mostRecentContent;
    }

    public void setMostRecentContent(Content mostRecentContent) {
        this.mostRecentContent = mostRecentContent;
    }

    public List<Content> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<Content> postComments) {
        this.postComments = postComments;
    }

    public Content getNewComment() {
        return newComment;
    }

    public void setNewComment(Content newComment) {
        this.newComment = newComment;
    }
}
