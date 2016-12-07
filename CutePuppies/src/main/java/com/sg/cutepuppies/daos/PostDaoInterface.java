/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.Post;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface PostDaoInterface {
//    public List<Post> getAllPosts(boolean showArchived);
    public List<Post> getAllPosts(String statusCode);
    public List<Post> getPostsByAllCriteria(int pageNumberInt, int postsPerPage, int tagId, int categoryId, String searchTerm, int userId);
    public Post getPostByID(int postID);
    public Post addPost(Post post);
    public void archivePost(int postID, int userID);
    public Post updatePost(Post post);
}
