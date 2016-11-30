/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.Post;
import com.sg.cutepuppies.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author apprentice
 */
public class PostDbImpl implements PostDaoInterface {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate npJdbcTemplate;

    // setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    // SQL PREPARED STATEMENTS
    private static final String SQL_GET_POST_BY_ID = "select * from Post where postId = :postId";
    private static final String SQL_SELECT_ALL_POSTS
            = "select p.* from Post p"
            + " where 1 = 1";
    private static final String SQL_GET_POSTS_BY_ALL_CRITERIA
            = "select distinct p.* from Post p "
            + " join Content c on c.PostId = p.PostId"
            + " left join content_tag ct on c.ContentId = ct.ContentId"
            + " left join Tag t on ct.TagId = t.TagId"
            + " left join content_category cc on c.ContentId = cc.ContentId"
            + " left join Category ctg on cc.CategoryId = ctg.CategoryId"
            + " where 1 = 1"
            + " and c.ContentStatusCode = 'PUBLISHED'";

    private static final String SQL_UPDATE_POST = "update Post"
            + "set CreatedUserId = ?"
            + ", set CreatedOnDate = ?"
            + ", set UpdatedByUserId = ?"
            + ", set UpdatedOnDate = ?"
            + ", set ArchivedByUserId = ?"
            + ", set ArchivedByDate = ?"
            + "where PostId = ?";

    private static final String SQL_ADD_POST = "insert into Post (CreatedByUserId) "
            + "values(:createdByUserId)";
    
    private static final String SQL_ARCHIVE_POST = "update Post set ArchivedByUserId = :userId, "
            + "UpdatedByUserId = :userId, ArchivedOnDate = Current_Timestamp where PostId = :postId";

    @Override
    public List<Post> getAllPosts(boolean showArchived) {
        String SQL_BASE = SQL_SELECT_ALL_POSTS;
        if (showArchived == false) {
            SQL_BASE += " and p.archivedOnDate is null";
        }
        SQL_BASE += " order by p.CreatedOnDate desc";
        return jdbcTemplate.query(SQL_BASE, new PostMapper());

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
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("postId", postID);
        return npJdbcTemplate.queryForObject(SQL_GET_POST_BY_ID, namedParameters, new PostMapper());
    }

    @Override
    public Post addPost(Post post) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("createdByUserId", post.getCreatedByUser().getUserId());
        npJdbcTemplate.update(SQL_ADD_POST, namedParameters);
        post.setPostId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return post;
    }

    @Override
    public void archivePost(int postId, int userId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("userId", userId);
        namedParameters.addValue("postId", postId);
        npJdbcTemplate.update(SQL_ARCHIVE_POST, namedParameters);
    }

    @Override
    public Post updatePost(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public List<Post> getPostsByAllCriteria(int pageNumberInt, int postsPerPageInt, int tagIdInt, int categoryIdInt) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        // set base query
        String SQL_QUERY = SQL_GET_POSTS_BY_ALL_CRITERIA;
        // if filtering by tag
        if (tagIdInt != 0) {
            SQL_QUERY += " and t.TagId = :tagId";
            namedParameters.addValue("tagId", tagIdInt);
        }
        // if filtering by category
        if (categoryIdInt != 0) {
            SQL_QUERY += " and ctg.CategoryId = :categoryId";
            namedParameters.addValue("categoryId", categoryIdInt);
        }
        // always order by date and limit to selected posts per page
        SQL_QUERY += " order by p.CreatedOnDate desc limit :postsPerPage offset :postOffset";
        namedParameters.addValue("postsPerPage", postsPerPageInt);
        namedParameters.addValue("postOffset", postsPerPageInt * (pageNumberInt - 1));
        return npJdbcTemplate.query(SQL_QUERY, namedParameters, new PostMapper());

    }

    private static final class PostMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {

            Post post = new Post();
            post.setPostId(rs.getInt("PostId"));
            post.setCreatedOnDate(rs.getTimestamp("CreatedOnDate"));
            post.setUpdatedOnDate(rs.getTimestamp("UpdatedOnDate"));
            post.setArchivedOnDate(rs.getTimestamp("ArchivedOnDate"));
            
            return post;

        }
    }

}
