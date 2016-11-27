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
public class PostDBImpl implements PostDAOInterface {

    private JdbcTemplate jdbcTemplate;

    // setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // SQL PREPARED STATEMENTS
    private static final String SQL_SELECT_ALL_POSTS = "select * from Post";
    private static final String SQL_SELECT_PUBLISHED_CONTENT_OF_POST = "select c.* from Content c join Post p on c.PostId = p.PostId"
            + "where c.ContentStatusCode = 'PUBLISHED' and p.PostId = ?";
    private static final String SQL_GET_POSTS_BY_ALL_CRITERIA_NEWER
            = "select p.* from Post p "
            + "join Content c on c.PostId = p.PostId"
            + "join content_tag ct on c.ContentId = ct.ContentId"
            + "join Tag t on ct.TagId = t.TagId"
            + "join content_category cc on c.ContentId = cc.ContentId"
            + "join Category ctg on cc.CategoryId = ctg.CategoryId"
            + "where 1 = 1"
            + "and c.ContentStatusCode = 'PUBLISHED'"
            + "and p.CreatedOnDate > (select p.CreatedOnDate from Post p where p.PostId = ?)"
            + "and t.TagId = ?"
            + "and ctg.CategoryId = ?"
            + "order by p.CreatedOnDate desc"
            + "limit ?";
    
//    SELECT p.* from Post p
//JOIN Content c on c.PostId = p.PostId
//JOIN content_tag ct on c.ContentId = ct.ContentId
//JOIN Tag t ON ct.TagId = t.TagId
//JOIN content_category cc ON c.ContentId = cc.ContentId
//JOIN Category ctg ON ctg.CategoryId = cc.CategoryId
//WHERE 1 = 1
//AND c.ContentStatusCode = 'PUBLISHED'
//ORDER BY p.CreatedOnDate DESC
//LIMIT 5;
        private static final String SQL_GET_POSTS_BY_ALL_CRITERIA_OLDER
            = "select p.* from Post p"
            + " join Content c on c.PostId = p.PostId "
            + " join content_tag ct on c.ContentId = ct.ContentId "
            + " join Tag t on ct.TagId = t.TagId "
            + " join content_category cc on c.ContentId = cc.ContentId "
            + " join Category ctg on cc.CategoryId = ctg.CategoryId "
            + " where 1 = 1 "
            + " and c.ContentStatusCode = 'PUBLISHED' "
//            + "and p.CreatedOnDate < (select p.CreatedOnDate from Post p where p.PostId = ?)"
//            + "and t.TagId = ?"
//            + "and ctg.CategoryId = ?"
            + " order by p.CreatedOnDate desc "
            + " limit ?";

    private static final String SQL_UPDATE_POST = "update Post"
            + "set CreatedUserId = ?"
            + ", set CreatedOnDate = ?"
            + ", set UpdatedByUserId = ?"
            + ", set UpdatedOnDate = ?"
            + ", set ArchivedByUserId = ?"
            + ", set ArchivedByDate = ?"
            + "where PostId = ?";

    @Override
    public List<Post> getAllPosts() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POSTS, new PostMapper());
    }

    @Override
    public Content getPublishedContentOfPost(int postId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_PUBLISHED_CONTENT_OF_POST, new ContentMapper(), postId);
    }

    @Override
    public List<Content> getAllContentOfPost(int postId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getAllPostsInclArchived() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getPostByTag(int tagID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getPostByCategory(int categoryID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getPostBySearch(String searchTerm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Post getPostByID(int postID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Post addPost(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePost(int postID) {
        // this should 'archive the post, as well as set all associated
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Post updatePost(Post post) {
//        jdbcTemplate.update(SQL_UPDATE_POST,
//                post.getCreatedByUserId(),
//                post.getCreatedOnDate(),
//                post.getUpdatedByUserId(),
//                post.getUpdatedOnDate(),
//                post.getArchivedByUserId(),
//                post.getArchivedOnDate(),
//                post.getPostId()
//        );
//        
//        return post;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public List<Post> getPostsByAllCriteria(int newestPostIdInt, int oldestPostIdInt, int postsPerPageInt, String direction, int tagIdInt, int categoryIdInt) {
        if (direction.equalsIgnoreCase("previous")) {
            return jdbcTemplate.query(SQL_GET_POSTS_BY_ALL_CRITERIA_NEWER, new Object[]{oldestPostIdInt, tagIdInt, categoryIdInt, postsPerPageInt}, new PostMapper());
        } else {
            return jdbcTemplate.query(SQL_GET_POSTS_BY_ALL_CRITERIA_OLDER, new Object[]{postsPerPageInt}, new PostMapper());
        }
    }

    private static final class PostMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {

            Post post = new Post();
            post.setPostId(rs.getInt("PostId"));
            post.setCreatedByUserId(rs.getInt("CreatedByUserId"));
            post.setCreatedOnDate(rs.getDate("CreatedOnDate"));
            post.setUpdatedByUserId(rs.getInt("UpdatedByUserId"));
            post.setUpdatedOnDate(rs.getDate("UpdatedOnDate"));
            post.setArchivedByUserId(rs.getInt("ArchivedByUserId"));
            post.setArchivedOnDate(rs.getDate("ArchivedOnDate"));
            return post;

        }
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
            content.setCreatedByUserId(rs.getInt("CreatedByUserId"));
            content.setCreatedOnDate(rs.getDate("CreatedOnDate"));
            content.setUpdatedByUserId(rs.getInt("UpdatedByUserId"));
            content.setUpdatedOnDate(rs.getDate("UpdatedOnDate"));
            content.setArchivedByUserId(rs.getInt("ArchivedByUserId"));
            content.setArchivedOnDate(rs.getDate("ArchivedOnDate"));
            return content;
        }
    }

}
