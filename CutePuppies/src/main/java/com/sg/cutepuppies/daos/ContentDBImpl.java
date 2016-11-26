/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.Content;
import com.sg.cutepuppies.models.Post;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class ContentDBImpl implements ContentDAOInterface {

    private JdbcTemplate jdbcTemplate;

    // setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // SQL PREPARED STATEMENTS
    private static final String SQL_GET_CONTENT_BY_POST_ID = "select * from Content c join Post p "
            + "where c.PostId = p.PostId and c.PostId = ?";
    private static final String SQL_GET_PUBLISHED_CONTENT_BY_POST_ID = "select c.* from Content c join Post p "
            + "on c.PostId = p.PostId where c.ContentStatusCode = 'PUBLISHED' and c.PostId = ?";

    @Override
    public List<Content> getContentByPostID(int postID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Content updatePostContent(Content content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Content> getAllStaticPages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Content getStaticPageByURL(String urlPattern) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Content addStaticPage(Content content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Content updateStaticPage(Content content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Content getPublishedPostContent(int postID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class ContentMapper implements RowMapper<Content> {

        @Override
        public Content mapRow(ResultSet rs, int i) throws SQLException {

            Content content = new Content();
            content.setContentId(rs.getInt("ContentId"));
            content.setPostId(rs.getInt("PostId"));
            content.setTitle(rs.getString("Title"));
            content.setContentImgLink(rs.getString("ContentImgLink"));
            content.setBody(rs.getString("Body"));
            content.setSnippet(rs.getString("Snippet"));
            content.setContentStatusCode(rs.getString("ContentStatusCode"));
            content.setUrlPattern(rs.getString("UrlPattern"));
            content.setContentTypeCode(rs.getString("ContentTypeCode"));
            content.setCreateByUserId(rs.getInt("CreatedByUserId"));
            content.setCreatedOnDate(rs.getDate("CreatedOnDate"));
            content.setUpdatedByUserId(rs.getInt("UpdatedByUserId"));
            content.setUpdatedOnDate(rs.getDate("UpdatedOnDate"));
            content.setArchivedByUserId(rs.getInt("ArchivedByUserId"));
            content.setArchivedOnDate(rs.getDate("ArchivedOnDate"));
            return content;
        }
    }
}
