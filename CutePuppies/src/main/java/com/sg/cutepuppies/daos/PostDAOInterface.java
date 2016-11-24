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
public interface PostDAOInterface {
    public List<Post> getAllPosts();
    public List<Post> getAllPostsInclArchived();
    public List<Post> getPostByTag(int tagID);
    public List<Post> getPostByCategory(int categoryID);
    public List<Post> getPostBySearch(String searchTerm);
    public Post getPostByID(int postID);
    public Post addPost(Post post);
    public void deletePost(int postID);
    public Post updatePost(Post post);
}
