/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.Content;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ContentDaoInterface {
    
    public List<Content> getAllContentsByPostId(int postID);
    public Content getPublishedPostContent(int postID);
    public Content getMostRecentPostContent(int postID);
    public Content updatePostContent(Content content);
    public List<Content> getAllStaticPages();
    public Content getStaticPageByURL(String urlPattern);
    public Content addStaticPage(Content content);
    public Content updateStaticPage(Content content);
    public void archiveContent(int contentID, int userID);
    public void archivePost(int postID, int userID);
    public Content getContentById(int contentId);
}
