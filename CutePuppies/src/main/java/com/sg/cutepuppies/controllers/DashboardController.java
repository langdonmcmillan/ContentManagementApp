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
import com.sg.cutepuppies.models.Post;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "admin/post", method = RequestMethod.POST)
    public Post addPost() {
        Post post = new Post();
        return post;
    }
}
