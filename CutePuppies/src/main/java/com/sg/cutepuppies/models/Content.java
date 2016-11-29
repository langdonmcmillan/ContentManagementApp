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
public class Content {

    private int contentId;
    private int postId;

    private String title;
    private String contentImgLink;
    private String contentImgAltTxt;
    private String body;
    private String snippet;
    private String contentStatusCode;
    private String urlPattern;
    private String contentTypeCode;

    private User createdByUser;
    private Date createdOnDate;

    private User updatedByUser;
    private Date updatedOnDate;

    private User archivedByUser;
    private Date archivedOnDate;

    private List<Tag> listOfTags;
    private List<Category> listOfCategories;

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

    public String getContentImgAltTxt() {
        return contentImgAltTxt;
    }

    public void setContentImgAltTxt(String contentImgAltTxt) {
        this.contentImgAltTxt = contentImgAltTxt;
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

    public List<Tag> getListOfTags() {
        return listOfTags;
    }

    public void setListOfTags(List<Tag> listOfTags) {
        this.listOfTags = listOfTags;
    }

    public List<Category> getListOfCategories() {
        return listOfCategories;
    }

    public void setListOfCategories(List<Category> listOfCategories) {
        this.listOfCategories = listOfCategories;
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
}
