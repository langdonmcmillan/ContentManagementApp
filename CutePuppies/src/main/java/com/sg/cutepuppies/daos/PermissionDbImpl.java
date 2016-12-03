/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Adam
 */
public class PermissionDbImpl implements PermissionDaoInterface{
    
    private JdbcTemplate jdbcTemplate;

    // setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // SQL PREPARED STATEMENTS
    private static final String SQL_PERMISSIONS_FOR_USER
            = "select rp.PermissionCode from role_permission rp "
            + " join User u "
            + " on u.RoleCode = rp.RoleCode "
            + " where u.UserId = ?";

    @Override
    public List<String> getPermissionsByUserId(int userId) {
        return jdbcTemplate.query(SQL_PERMISSIONS_FOR_USER, new PermissionMapper(), userId);
    }
    
    private static final class PermissionMapper implements RowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int i) throws SQLException {
            String permission;
            permission = rs.getString("PermissionCode");
            
            return permission;
        }
    }
    
}
