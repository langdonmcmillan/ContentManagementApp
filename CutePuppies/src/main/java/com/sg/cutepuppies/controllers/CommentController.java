/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.controllers;

import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.sg.cutepuppies.daos.ContentDaoInterface;
import com.sg.cutepuppies.daos.PostDaoInterface;
import com.sg.cutepuppies.daos.UserDaoInterface;
import com.sg.cutepuppies.models.Content;
import com.sg.cutepuppies.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author apprentice
 */
@RequestMapping(value = "/comments")
@Controller
public class CommentController {

    private PostDaoInterface postDao;
    private ContentDaoInterface contentDao;
    private UserDaoInterface userDao;

    @Inject
    public CommentController(PostDaoInterface postDao, ContentDaoInterface contentDao,
            UserDaoInterface userDao) {
        this.postDao = postDao;
        this.contentDao = contentDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = "/ajax/addComment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Content addComment(@Valid @RequestBody Content comment) {
        User user = comment.getCreatedByUser();
        user.setRoleCode("ROLE_GUEST");
        user.setUserEmail("");
        user.setUserPassword("");
        comment.setCreatedByUser(userDao.addUser(user));
        contentDao.addPostComment(comment);
        return comment;
    }

    @RequestMapping(value = "/archiveComment/{postId}/{contentId}", method = RequestMethod.GET)
    public String archiveContent(@PathVariable("postId") int postId, @PathVariable("contentId") int contentId) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        int userId = userDao.getUserIdByUsername(username);
        int userId = 1;
        contentDao.archiveContent(contentId, userId);
        return "redirect:/post/" + postId;
    }
}
