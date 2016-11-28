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
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author apprentice
 */
public class PostDBImpl implements PostDAOInterface {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate npJdbcTemplate;

    // setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    // SQL PREPARED STATEMENTS
    private static final String SQL_GET_POST_BY_ID = "select * from Post where postId = :postId";
    private static final String SQL_SELECT_ALL_POSTS = "select * from Post";
    private static final String SQL_GET_POSTS_BY_ALL_CRITERIA
            = "select p.* from Post p "
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
    
    private static final String SQL_ADD_POST = "insert into Post (CreatedByUserId, CreatedOnDate) "
            + "values(:createdByUserId, :createdOnDate)";

    @Override
    public List<Post> getAllPosts() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POSTS, new PostMapper());
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
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("postId",postID);
        return npJdbcTemplate.queryForObject(SQL_GET_POST_BY_ID, namedParameters, new PostMapper());
    }

    @Override
    public Post addPost(Post post) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        /*
        Calendar today = Calendar.getInstance();
        Date currentDate = new Date((today.getTime()).getTime());
        post.setCreatedOnDate(currentDate); */
        namedParameters.addValue("createdByUserId", post.getCreatedByUserId());
        namedParameters.addValue("createdOnDate", post.getCreatedOnDate());
        npJdbcTemplate.update(SQL_ADD_POST, namedParameters);
        post.setPostId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return post;
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
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        // set base query
        String SQL_QUERY = SQL_GET_POSTS_BY_ALL_CRITERIA;
        if (direction.equalsIgnoreCase("previous") && newestPostIdInt != 0) {
            // if getting newer posts (previous page)
            SQL_QUERY += " and p.CreatedOnDate > (select p.CreatedOnDate from Post p where p.PostId = :newestPostId)";
            namedParameters.addValue("newestPostId", newestPostIdInt);
        } else if (oldestPostIdInt != 0) {
            // if getting older posts (next page)
            SQL_QUERY += " and p.CreatedOnDate < (select p.CreatedOnDate from Post p where p.PostId = :oldestPostId)";
            namedParameters.addValue("oldestPostId", oldestPostIdInt);
        }
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
        SQL_QUERY += " order by p.CreatedOnDate desc limit :postsPerPage";
        namedParameters.addValue("postsPerPage", postsPerPageInt);
        return npJdbcTemplate.query(SQL_QUERY, namedParameters, new PostMapper());

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

}
