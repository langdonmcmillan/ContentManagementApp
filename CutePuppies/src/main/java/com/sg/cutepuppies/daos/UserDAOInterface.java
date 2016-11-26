/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.User;

/**
 *
 * @author apprentice
 */
public interface UserDAOInterface {
    
    public User getCreatedByUserForPost(int createdByUserId);
    
    public User getUpdatedByUserForContent(int updatedByUserId);
}
