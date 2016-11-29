/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.controllers;

import com.sg.cutepuppies.models.Content;
import com.sg.cutepuppies.models.Category;
import com.sg.cutepuppies.models.Post;
import com.sg.cutepuppies.models.Tag;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.sg.cutepuppies.daos.CategoryDaoInterface;
import com.sg.cutepuppies.daos.ContentDaoInterface;
import com.sg.cutepuppies.daos.PostDaoInterface;
import com.sg.cutepuppies.daos.TagDaoInterface;
import com.sg.cutepuppies.daos.UserDaoInterface;
import com.sg.cutepuppies.models.User;

/**
 *
 * @author apprentice
 */
@Controller
public class DashboardController {

    private PostDaoInterface postDao;
    private ContentDaoInterface contentDao;
    private CategoryDaoInterface categoryDao;
    private TagDaoInterface tagDao;
    private UserDaoInterface userDao;

    @Autowired
    @Inject
    public DashboardController(PostDaoInterface postDao, ContentDaoInterface contentDao,
            CategoryDaoInterface categoryDao, TagDaoInterface tagDao, UserDaoInterface userDao) {
        this.postDao = postDao;
        this.contentDao = contentDao;
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = {"/admin/dashboard"}, method = RequestMethod.GET)
    public String getDashBoardPage() {
        return "dashboard";
    }

    @RequestMapping(value = "admin/getAllPosts/{archiveBoxChecked}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts(@PathVariable("archiveBoxChecked") boolean showArchived) {
        List<Post> listOfAllPosts = postDao.getAllPosts(showArchived);
        listOfAllPosts.forEach(post -> {
            int postId = post.getPostId();
            post.setCreatedByUser(userDao.getUserWhoCreatedPost(postId));
            post.setUpdatedByUser(userDao.getUserWhoUpdatedPost(postId));
            post.setArchivedByUser(userDao.getUserWhoArchivedPost(postId)); 
           
            Content mostRecentPostContent = contentDao.getMostRecentPostContent(postId);
            int contentId = mostRecentPostContent.getContentId();
            mostRecentPostContent.setCreatedByUser(userDao.getUserWhoCreatedContent(contentId));
            mostRecentPostContent.setListOfCategories(categoryDao.getCategoriesByContentId(contentId));
            mostRecentPostContent.setListOfTags(tagDao.getTagsByContentId(contentId));
            post.setMostRecentContent(mostRecentPostContent);
        });
        return listOfAllPosts;
    }
    
    @RequestMapping(value = "admin/edit", method = RequestMethod.GET)
    public String displayAddPostPage() {
        return "edit";
    }
    
    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> populateCategories() {
        List<Category> listOfCategories = categoryDao.getAllCategories();
        return listOfCategories;
    }
    
    @RequestMapping(value = "/admin/tags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> populateTags() {
        List<Tag> listOfTags = tagDao.getAllTags();
        return listOfTags;
    }

    @RequestMapping(value = "admin/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post addPost(@Valid @RequestBody Post post) {
        post = postDao.addPost(post);
        post.getAllContentRevisions().get(0).setPostId(post.getPostId());
        contentDao.updatePostContent(post.getAllContentRevisions().get(0));
        return post;
    }
}
