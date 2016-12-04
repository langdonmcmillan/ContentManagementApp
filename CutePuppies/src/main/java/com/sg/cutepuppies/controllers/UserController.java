/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.controllers;

import com.sg.cutepuppies.daos.UserDaoInterface;
import com.sg.cutepuppies.models.User;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Adam
 */
@Controller
@RequestMapping(value = "/manageUsers")
public class UserController {

    private UserDaoInterface userDao;
    
    private PasswordEncoder encoder;

    @Inject
    public UserController(UserDaoInterface userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @RequestMapping(value = {"/", "/userList"}, method = RequestMethod.GET)
    public String displayAll(Model model) {
        List<User> users = userDao.getAllUsers();
        model.addAttribute("newUser", new User());
        model.addAttribute("users", users);
        return "manage-users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@Valid @RequestBody User user) {
        //  - Hash the password and then set it on the User object before saving it
        String clearPw = user.getUserPassword();
        String hashPw = encoder.encode(clearPw);
        user.setUserPassword(hashPw);
        userDao.addUser(user);
        return "redirect:userList";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@Valid @RequestBody User user) {
        userDao.updateUser(user);
    }
    
    @RequestMapping(value = "/remove/{userId}", method = RequestMethod.GET)
    public void removeUser(@Valid @RequestParam("userId") int userId) {
        userDao.deleteUser(userId);
    }

}
