/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.controllers;

import com.sg.cutepuppies.daos.CategoryDaoInterface;
import com.sg.cutepuppies.daos.ContentDaoInterface;
import com.sg.cutepuppies.daos.PostDaoInterface;
import com.sg.cutepuppies.daos.TagDaoInterface;
import com.sg.cutepuppies.daos.UserDaoInterface;
import com.sg.cutepuppies.models.Content;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Adam
 */
@Controller
public class LoginController {
    private ContentDaoInterface contentDao;
    // #1 - respond to all GET requests for /login

    @Inject
    public LoginController(ContentDaoInterface contentDao) {
        this.contentDao = contentDao;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        List<Content> allStaticPages = contentDao.getPublishedStaticPages();
        model.addAttribute("allStaticPages", allStaticPages);
        return "login";
    }
}
