/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import java.util.List;

/**
 *
 * @author Adam
 */
public interface PermissionDaoInterface {
    
    public List<String> getPermissionsByUserId(int userId);
    
}
