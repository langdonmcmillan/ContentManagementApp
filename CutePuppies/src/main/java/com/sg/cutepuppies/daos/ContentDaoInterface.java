/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.Category;
import com.sg.cutepuppies.models.Content;
import com.sg.cutepuppies.models.Tag;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ContentDaoInterface {
    
    public List<Content> getAllContentsByPostId(int postID);
    public Content getPublishedPostContent(int postID);
    public Content updatePostContent(Content content);
    public List<Content> getAllStaticPages();
    public Content getStaticPageByURL(String urlPattern);
    public Content addStaticPage(Content content);
    public Content updateStaticPage(Content content);
    
}
