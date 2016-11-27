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

    private PostDAOInterface postDAO;
    private ContentDAOInterface contentDAO;
    private CategoryDAOInterface categoryDAO;
    private TagDAOInterface tagDAO;
    private UserDAOInterface userDAO;

    @Autowired
    @Inject
    public DashboardController(PostDAOInterface postDAO, ContentDAOInterface contentDAO,
            CategoryDAOInterface categoryDAO, TagDAOInterface tagDAO, UserDAOInterface userDAO) {
        this.postDAO = postDAO;
        this.contentDAO = contentDAO;
        this.categoryDAO = categoryDAO;
        this.tagDAO = tagDAO;
        this.userDAO = userDAO;
    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String getDashBoardPage() {
        return "dashboard";
    }

    @RequestMapping(value = "getAllPostsNotArchived", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts () {
        List<Post> listOfAllPosts = postDAO.getAllPosts();
        
        return listOfAllPosts;
    }
    
    @RequestMapping(value = "admin/post", method = RequestMethod.POST)
    public Post addPost() {
        Post post = new Post();
        return post;
    }
}
