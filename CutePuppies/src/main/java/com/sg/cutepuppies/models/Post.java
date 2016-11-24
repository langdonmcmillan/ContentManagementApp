/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.models;

import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public class Post {
    private int postID;
    private int createUserID;
    private LocalDate createDate;
    private int lastUpdateUserID;
    private LocalDate lastUpdateDate;
    private int deleteUserID;
    private LocalDate deleteDate;
}
