/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class UserDbImpl implements UserDaoInterface {

    private JdbcTemplate jdbcTemplate;

    // setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // SQL PREPARED STATEMENTS
    private static final String SQL_GET_USER_WHO_CREATED_CONTENT
            = "select * from User u "
            + " join Content c "
            + " on u.UserId = c.CreatedByUserId "
            + " where c.ContentId = ?";

    private static final String SQL_GET_USER_WHO_CREATED_POST
            = "select * from User u "
            + " join Post p "
            + " on u.UserId = p.CreatedByUserId "
            + " where p.PostId = ?";

    private static final String SQL_GET_USER_WHO_UPDATED_CONTENT
            = "select * from User u "
            + " join Content c "
            + " on u.UserId = c.UpdatedByUserId "
            + " where c.ContentId = ?";

    private static final String SQL_GET_USER_WHO_UPDATED_POST
            = "select * from User u "
            + " join Post p "
            + " on u.UserId = p.UpdatedByUserId "
            + " where p.PostId = ?";

    private static final String SQL_GET_USER_WHO_ARCHIVED_CONTENT
            = "select * from User u "
            + " join Content c "
            + " on u.UserId = c.ArchivedByUserId "
            + " where c.ContentId = ?";

    private static final String SQL_GET_USER_WHO_ARCHIVED_POST
            = "select * from User u "
            + " join Post p "
            + " on u.UserId = p.ArchivedByUserId "
            + " where p.PostId = ?";

    @Override
    public User getUserWhoCreatedPost(int postId) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_WHO_CREATED_POST, new UserMapper(), postId);
    }

    @Override
    public User getUserWhoCreatedContent(int contentId) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_WHO_CREATED_CONTENT, new UserMapper(), contentId);

    }

    @Override
    public User getUserWhoUpdatedPost(int postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_USER_WHO_UPDATED_POST, new UserMapper(), postId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User getUserWhoUpdatedContent(int contentId) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_WHO_UPDATED_CONTENT, new UserMapper(), contentId);
    }

    @Override
    public User getUserWhoArchivedPost(int postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_USER_WHO_ARCHIVED_POST, new UserMapper(), postId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User getUserWhoArchivedContent(int contentId) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_WHO_ARCHIVED_CONTENT, new UserMapper(), contentId);
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("UserId"));
            user.setRoleCode(rs.getString("RoleCode"));
            user.setCreatedDate(rs.getDate("CreatedDate"));
            user.setUpdatedDate(rs.getDate("UpdatedDate"));
            user.setDeletedDate(rs.getDate("DeletedDate"));
            user.setUserName(rs.getString("UserName"));
            user.setUserPassword(rs.getString("UserPassword"));
            user.setUserEmail(rs.getString("UserEmail"));

            return user;
        }
    }
}
