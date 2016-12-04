/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.User;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface UserDaoInterface {

    public User addUser(User newUser);

    public void updateUser(User user);

    public void deleteUser(int userId);
    
    public List<User> getAllUsers();

    public User getUserWhoCreatedPost(int postId);

    public User getUserWhoUpdatedPost(int postId);

    public User getUserWhoArchivedPost(int postId);

    public User getUserWhoCreatedContent(int contentId);

    public User getUserWhoUpdatedContent(int contentId);

    public User getUserWhoArchivedContent(int contentId);

}
