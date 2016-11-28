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
import com.sg.cutepuppies.models.Category;
import com.sg.cutepuppies.models.Post;
import com.sg.cutepuppies.models.Tag;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@Controller
public class DashboardController {

    private PostDAOInterface postDAO;
    private ContentDAOInterface contentDAO;
    private CategoryDAOInterface categoryDAO;
    private TagDAOInterface tagDAO;

    @Autowired
    @Inject
    public DashboardController(PostDAOInterface postDAO, ContentDAOInterface contentDAO,
            CategoryDAOInterface categoryDAO, TagDAOInterface tagDAO) {
        this.postDAO = postDAO;
        this.contentDAO = contentDAO;
        this.categoryDAO = categoryDAO;
        this.tagDAO = tagDAO;
    }
    
    @RequestMapping(value = "admin/edit", method = RequestMethod.GET)
    public String displayAddPostPage() {
        return "edit";
    }
    
    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> populateCategories() {
        List<Category> listOfCategories = categoryDAO.getAllCategories();
        return listOfCategories;
    }
    
    @RequestMapping(value = "/admin/tags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> populateTags() {
        List<Tag> listOfTags = tagDAO.getAllTags();
        return listOfTags;
    }

    @RequestMapping(value = "admin/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post addPost(@Valid @RequestBody Post post) {
        post = postDAO.addPost(post);
        post.getAllContentRevisions().get(0).setPostId(post.getPostId());
        contentDAO.updatePostContent(post.getAllContentRevisions().get(0));
        return post;
    }
}
