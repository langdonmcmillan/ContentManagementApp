/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.Category;
import com.sg.cutepuppies.models.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 *
 * @author apprentice
 */
public class CategoryDbImpl implements CategoryDaoInterface {

    private JdbcTemplate jdbcTemplate;

    // setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // SQL PREPARED STATEMENTS
    private static final String SQL_GET_ALL_CATEGORIES = "select * from Category order by CategoryDescription asc";
    private static final String SQL_GET_CATEGORIES_BY_CONTENT_ID = "select ctg.* from Category ctg join content_category cc on ctg.CategoryId = cc.CategoryId where cc.ContentId = ?";
    private static final String SQL_ADD_CATEGORY = "insert ignore into Category (CategoryDescription) "
            + "values(?)";
    private static final String SQL_UPDATE_CATEGORY = "update Category set CategoryDescription = ? where CategoryId = ?";
    private static final String SQL_DELETE_CATEGORY = "delete from Category where CategoryId = ?";
    private static final String SQL_DELETE_CONTENT_CATEGORY = "delete from content_category where CategoryId = ?";
    private static final String SQL_GET_CATEGORY_ID = "select CategoryId from Category where CategoryDescription = ?";

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_GET_ALL_CATEGORIES, new CategoryMapper());
    }

    @Override
    public Category addCategory(String category) {
        jdbcTemplate.update(SQL_ADD_CATEGORY, category);
        Category newCategory = new Category();
        newCategory.setCategoryDescription(category);
        newCategory.setCategoryID(jdbcTemplate.queryForObject(SQL_GET_CATEGORY_ID, Integer.class, category));
        return newCategory;
    }

    @Override
    public Category updateCategory(Category category) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY, category.getCategoryDescription(), category.getCategoryID());
        return category;
    }

    @Override
    public void deleteCategory(int categoryID) {
        jdbcTemplate.update(SQL_DELETE_CONTENT_CATEGORY, categoryID);
        jdbcTemplate.update(SQL_DELETE_CATEGORY, categoryID);
    }

    @Override
    public List<Category> getCategoriesByContentId(int contentId) {
        return jdbcTemplate.query(SQL_GET_CATEGORIES_BY_CONTENT_ID, new CategoryMapper(), contentId);
    }


    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {

            Category category = new Category();
            category.setCategoryID(rs.getInt("CategoryId"));
            category.setCategoryDescription(rs.getString("CategoryDescription"));
            return category;
        }
    }

}
