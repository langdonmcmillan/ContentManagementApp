/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.controllers;

import com.sg.cutepuppies.daos.CategoryDAOInterface;
import com.sg.cutepuppies.daos.ContentDAOInterface;
import com.sg.cutepuppies.daos.PostDAOInterface;
import com.sg.cutepuppies.daos.TagDAOInterface;
import com.sg.cutepuppies.daos.UserDAOInterface;
import com.sg.cutepuppies.models.Content;
import com.sg.cutepuppies.models.Post;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class DashboardController {

    private PostDAOInterface postDao;
    private ContentDAOInterface contentDao;
    private CategoryDAOInterface categoryDao;
    private TagDAOInterface tagDao;
    private UserDAOInterface userDao;

    @Autowired
    @Inject
    public DashboardController(PostDAOInterface postDao, ContentDAOInterface contentDao,
            CategoryDAOInterface categoryDao, TagDAOInterface tagDao, UserDAOInterface userDao) {
        this.postDao = postDao;
        this.contentDao = contentDao;
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String getDashBoardPage() {
        return "dashboard";
    }

    @RequestMapping(value = "getAllPostsNotArchived", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts() {
        // gets me a list of every single post
        List<Post> listOfAllPosts = postDao.getAllPosts();
        listOfAllPosts.forEach((post) -> {
            int postId = post.getPostId();
            // for each post, get a list of Content whose ContentStatusCode is *not* PUBLISHED.
            List<Content> contentsPerPost = contentDao.getContentNotArchivedByPostId(postId);
            post.setAllContentRevisions(contentsPerPost);
            contentsPerPost.forEach((content -> {
                content.setListOfTags(tagDao.getTagsByContentId(content.getContentId()));
                content.setListOfCategories(categoryDao.getCategoriesByContentId(content.getContentId()));
                content.setCreatedByUser(userDao.getUserWhoCreatedContent(content.getCreatedByUserId()));
                
            }));
        });
        return listOfAllPosts;
    }

    @RequestMapping(value = "admin/post", method = RequestMethod.POST)
    public Post addPost() {
        Post post = new Post();
        return post;
    }
}
