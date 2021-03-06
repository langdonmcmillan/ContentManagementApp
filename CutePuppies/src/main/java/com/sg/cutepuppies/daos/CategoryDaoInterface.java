/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.Category;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface CategoryDaoInterface {
    public List<Category> getAllCategories();
    public List<Category> getCategoriesByContentId(int contentId);
    public Category addCategory(String category);
    public Category updateCategory(Category category);
    public void deleteCategory(int categoryID);
}
