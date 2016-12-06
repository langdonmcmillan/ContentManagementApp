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
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("formUser", new User());
        model.addAttribute("users", users);
        model.addAttribute("addEdit", "add");
        return "manage-users";
    }
    
    @RequestMapping(value = {"/edit/{userId}"}, method = RequestMethod.GET)
    public String editUser(Model model, @PathVariable int userId) {
        List<User> users = userDao.getAllUsers();
        User user = userDao.getUserById(userId);
        user.setUserPassword("");
        model.addAttribute("formUser", user);
        model.addAttribute("users", users);
        model.addAttribute("addEdit", "edit");
        return "manage-users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute User user, Model model, String password2) {
        boolean userNameExists = (userDao.getUserIdByUsername(user.getUserName()) != 0);
        boolean userEmailExists = (userDao.getUserIdByEmail(user.getUserEmail()) != 0);
        boolean userNameEmpty = (user.getUserName() == "" || user.getUserName() == null);
        boolean userEmailEmpty = (user.getUserEmail()== "" || user.getUserEmail()== null);
        boolean userPasswordEmpty = (user.getUserPassword()== "" || user.getUserPassword()== null);
        boolean userRoleCodeEmpty = (user.getRoleCode() == "" || user.getRoleCode() == null);
        boolean userPasswordsMismatch = (!user.getUserPassword().equals(password2));
        if (userNameExists || userEmailExists || userNameEmpty || userEmailEmpty || userPasswordEmpty || userRoleCodeEmpty) {
            List<User> users = userDao.getAllUsers();
            user.setUserPassword("");
            model.addAttribute("formUser", user);
            model.addAttribute("users", users);
            model.addAttribute("userNameExists", userNameExists);
            model.addAttribute("userEmailExists", userEmailExists);
            model.addAttribute("userNameEmpty", userNameEmpty);
            model.addAttribute("userEmailEmpty", userEmailEmpty);
            model.addAttribute("userPasswordEmpty", userPasswordEmpty);
            model.addAttribute("userRoleCodeEmpty", userRoleCodeEmpty);
            model.addAttribute("userPasswordsMismatch", userPasswordsMismatch);
            model.addAttribute("addEdit", "add");
            return "manage-users";
        }
        //  - Hash the password and then set it on the User object before saving it
        String clearPw = user.getUserPassword();
        String hashPw = encoder.encode(clearPw);
        user.setUserPassword(hashPw);
        User newUser = userDao.addUser(user);

        return "redirect:userList";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute User user, Model model, String password2) {
        boolean userNameExists = (userDao.getUserIdByUsername(user.getUserName()) != 0 
                && userDao.getUserIdByUsername(user.getUserName()) != user.getUserId());
        boolean userEmailExists = (userDao.getUserIdByEmail(user.getUserEmail()) != 0 
                && userDao.getUserIdByEmail(user.getUserEmail()) != user.getUserId());
        boolean userNameEmpty = (user.getUserName() == "" || user.getUserName() == null);
        boolean userEmailEmpty = (user.getUserEmail()== "" || user.getUserEmail()== null);
        boolean userPasswordEmpty = (user.getUserPassword()== "" || user.getUserPassword()== null);
        boolean userRoleCodeEmpty = (user.getRoleCode() == "" || user.getRoleCode() == null);
        boolean userPasswordsMismatch = (!user.getUserPassword().equals(password2));
        
        if (userNameExists || userEmailExists || userNameEmpty || userEmailEmpty || userPasswordEmpty || userRoleCodeEmpty) {
            List<User> users = userDao.getAllUsers();
            user.setUserPassword("");
            model.addAttribute("formUser", user);
            model.addAttribute("users", users);
            model.addAttribute("userNameExists", userNameExists);
            model.addAttribute("userEmailExists", userEmailExists);
            model.addAttribute("userNameEmpty", userNameEmpty);
            model.addAttribute("userEmailEmpty", userEmailEmpty);
            model.addAttribute("userPasswordEmpty", userPasswordEmpty);
            model.addAttribute("userRoleCodeEmpty", userRoleCodeEmpty);
            model.addAttribute("userPasswordsMismatch", userPasswordsMismatch);
            model.addAttribute("addEdit", "edit");
            return "manage-users";
        }
        //  - Hash the password and then set it on the User object before saving it
        String clearPw = user.getUserPassword();
        String hashPw = encoder.encode(clearPw);
        user.setUserPassword(hashPw);
        userDao.updateUser(user);
        
        return "redirect:userList";
        
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeUser(int userId, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int currentUserId = userDao.getUserIdByUsername(username);
        if (currentUserId == userId) {
            List<User> users = userDao.getAllUsers();
            model.addAttribute("formUser", new User());
            model.addAttribute("users", users);
            model.addAttribute("isCurrentUser", true);
            model.addAttribute("userId", userId);
            model.addAttribute("addEdit", "add");
            return "manage-users";
        }
        userDao.deleteUser(userId);
        return "redirect:userList";
    }

}
