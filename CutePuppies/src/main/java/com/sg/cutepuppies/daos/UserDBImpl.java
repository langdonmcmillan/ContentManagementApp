/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class UserDBImpl implements UserDAOInterface {

    private JdbcTemplate jdbcTemplate;

    // setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    // SQL PREPARED STATEMENTS
    private static final String SQL_GET_USER_FOR_USER_ID = "select * from User u where UserId = ?";
    
    @Override
    public User getCreatedByUserForPost(int createdByUserId) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_FOR_USER_ID, new UserMapper(), createdByUserId);
    }

    @Override
    public User getUpdatedByUserForContent(int updatedByUserId) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_FOR_USER_ID, new UserMapper(), updatedByUserId);
    }
    
    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("UserId"));
            user.setRoleCode(rs.getString("RoleCode"));
            user.setCreatedDate(rs.getDate("CreatedDate"));
            user.setUpdateDate(rs.getDate("UpdatedDate"));
            user.setDeletedDate(rs.getDate("DeletedDate"));
            user.setUserName(rs.getString("UserName"));
            user.setUserPassword(rs.getString("UserPassword"));
            user.setUserEmail(rs.getString("UserEmail"));
            
            return user;
        }
    }
}
