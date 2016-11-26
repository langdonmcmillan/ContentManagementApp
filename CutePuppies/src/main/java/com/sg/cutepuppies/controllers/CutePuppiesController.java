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
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class CutePuppiesController {

    private CategoryDAOInterface categoryDao;
    private TagDAOInterface tagDao;
    private ContentDAOInterface contentDao;
    private PostDAOInterface postDao;
    private UserDAOInterface userDao;

    @Inject
    public CutePuppiesController(CategoryDAOInterface categoryDao, TagDAOInterface tagDao, ContentDAOInterface contentDao, PostDAOInterface postDao, UserDAOInterface userDao) {
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
        this.contentDao = contentDao;
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String getLandingPage() {
        return "blog";
    }

    @RequestMapping(value = "getPagePosts/", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getPagePosts(String newestPostId, String oldestPostId, String postsPerPage, String direction, String tagId, String categoryId) {
        // parse to int: newestPostId, oldestPostId, postsPerPage, tagId, categoryId
        int newestPostIdInt = 0;
        int oldestPostIdInt = 0;
        int postsPerPageInt = 0;
        int tagIdInt = 0;
        int categoryIdInt = 0;
        
        if (!newestPostId.equals("null")) {
            Integer.parseInt(newestPostId);
        }
        if (!oldestPostId.equals("null")) {
            oldestPostIdInt = Integer.parseInt(oldestPostId);
        }
        if (!postsPerPage.equals("null")) {
            postsPerPageInt = Integer.parseInt(postsPerPage);
        }
        if (!tagId.equals("null")) {
            tagIdInt = Integer.parseInt(tagId);
        }
        if (!categoryId.equals("null")) {
            categoryIdInt = Integer.parseInt(categoryId);
        }        
        
        // get me a list of Post objects.
        List<Post> listOfPosts = postDao.getPostsByAllCriteria(newestPostIdInt, oldestPostIdInt, postsPerPageInt, direction, tagIdInt, categoryIdInt);

        listOfPosts.forEach((post) -> {
            int postId = post.getPostId();
            Content postContent = contentDao.getPublishedPostContent(postId);
            postContent.setListOfTags(tagDao.getTagsByContentId(postContent.getContentId()));
            postContent.setListOfCategories(categoryDao.getCategoriesByContentId(postContent.getContentId()));
            postContent.setUpdatedByUser(userDao.getUpdatedByUserForContent(postContent.getUpdatedByUserId()));
            post.setCreatedByUser(userDao.getCreatedByUserForPost(post.getCreatedByUserId()));
            post.setPublishedContent(postContent);
        });
        return listOfPosts;
    }

}
