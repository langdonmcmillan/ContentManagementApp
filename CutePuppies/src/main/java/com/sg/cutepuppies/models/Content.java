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
public class Content {
    private int contentId;
    private int postId;
    private String title;
    private String contentImgLink;
    private String body;
    private String snippet;
    private String contentStatusCode;
    private String urlPattern;
    private String contentTypeCode;
    private int createByUserId;
    private LocalDate createdOnDate;
    private int updatedByUserId;
    private LocalDate updateOnDate;
    private int archivedByUserId;
    private LocalDate archivedOnDate;

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentImgLink() {
        return contentImgLink;
    }

    public void setContentImgLink(String contentImgLink) {
        this.contentImgLink = contentImgLink;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getContentStatusCode() {
        return contentStatusCode;
    }

    public void setContentStatusCode(String contentStatusCode) {
        this.contentStatusCode = contentStatusCode;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getContentTypeCode() {
        return contentTypeCode;
    }

    public void setContentTypeCode(String contentTypeCode) {
        this.contentTypeCode = contentTypeCode;
    }

    public int getCreateByUserId() {
        return createByUserId;
    }

    public void setCreateByUserId(int createByUserId) {
        this.createByUserId = createByUserId;
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

    public LocalDate getUpdateOnDate() {
        return updateOnDate;
    }

    public void setUpdateOnDate(LocalDate updateOnDate) {
        this.updateOnDate = updateOnDate;
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
