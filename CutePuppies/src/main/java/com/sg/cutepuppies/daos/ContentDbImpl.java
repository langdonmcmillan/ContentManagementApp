/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.Content;
import com.sg.cutepuppies.models.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author apprentice
 */
public class ContentDbImpl implements ContentDaoInterface {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate npJdbcTemplate;

    // setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    // SQL PREPARED STATEMENTS
    private static final String SQL_GET_ALL_STATIC_PAGES
            = "select c.* from Content c "
            + " where 1 = 1 "
            + " and c.ContentTypeCode = 'STATIC PAGE'";
    private static final String SQL_GET_PUBLISHED_STATIC_PAGES
            = "select c.* from Content c "
            + " where 1 = 1 "
            + " and c.ContentTypeCode = 'STATIC PAGE' "
            + " and c.ContentStatusCode = 'PUBLISHED'";

    private static final String SQL_GET_STATIC_PAGE_BY_STATUS_CODE
            = "select c.* from Content c "
            + " where 1 = 1"
            + " and c.ContentTypeCode = 'STATIC PAGE' "
            + " and c.ContentStatusCode = ? "
            + " order by c.CreatedOnDate desc";

    private static final String SQL_GET_STATIC_PAGE_BY_URL
            = "select c.* from Content c "
            + " where 1 = 1 "
            + " and c.ContentTypeCode = 'STATIC PAGE'"
            + " and c.UrlPattern = ?";
    private static final String SQL_GET_ALL_REVISIONS_BY_POST_ID
            = "select c.* from Content c "
            + " join Post p on c.PostId = p.PostId "
            + " where c.ContentTypeCode = 'POST' "
            + " and c.PostId = ?";
    private static final String SQL_GET_PUBLISHED_CONTENT_BY_POST_ID = "select c.* from Content c join Post p "
            + "on c.PostId = p.PostId where c.ContentStatusCode = 'PUBLISHED' and c.ContentTypeCode = 'POST' and c.PostId = ?";

    private static final String SQL_GET_CONTENT_BY_ID = "select * from Content where ContentId = ?";

    private static final String SQL_GET_MOST_RECENT_CONTENT_BY_POST_ID
            = " select c.* "
            + " from Content c "
            + " join Post p "
            + " on c.PostId = p.PostId"
            + " where p.PostId = ? "
            + " and c.ContentTypeCode = 'POST' "
            + " order by c.CreatedOnDate desc"
            + " limit 1";

    private static final String SQL_ADD_CONTENT_TO_POST = "insert into Content (PostId, Title, "
            + "ContentImgLink, ContentImgAltTxt, Body, Snippet, ContentStatusCode, UrlPattern, ContentTypeCode, "
            + "CreatedByUserId) "
            + "values (:postID, :title, :contentImgLink, :contentImgAltTxt, :body, :snippet, :contentStatusCode, "
            + ":urlPattern, :contentTypeCode, :createdByUserID)";
    private static final String SQL_ARCHIVE_OLD_CONTENT = "update Content set ContentStatusCode = 'ARCHIVED' "
            + "where ContentStatusCode = 'PUBLISHED' and ContentTypeCode = 'POST' and postID = :postID";
    private static final String SQL_GET_CONTENT_AWAITING
            = " select c.* "
            + " from Content c "
            + " join Post p "
            + " on c.PostId = p.PostId "
            + " where c.ContentStatusCode = 'AWAITING' "
            + " and p.PostId = ?";
    private static final String SQL_SET_AWAITING_TO_ARCHIVED
            = " update Content "
            + " set ContentStatusCode = 'ARCHIVED', "
            + " ArchivedOnDate = now(), "
            + " ArchivedByUserId = ?"
            + " where ContentStatusCode = 'AWAITING' "
            + " and PostId = ?";
    private static final String SQL_UPDATE_CONTENT_CATEGORIES = "insert into content_category (ContentId, CategoryId) "
            + "values (?, ?)";
    private static final String SQL_UPDATE_CONTENT_TAGS = "insert into content_tag (ContentId, TagId) "
            + "values (?, ?)";
    private static final String SQL_ARCHIVE_POST = "update Content set ArchivedByUserId = :userId, "
            + "UpdatedByUserId = :userId, ArchivedOnDate = now(), ContentStatusCode = 'ARCHIVED' where PostId = :postId";
    private static final String SQL_ARCHIVE_CONTENT = "update Content set ArchivedByUserId = :userId, "
            + "UpdatedByUserId = :userId, ArchivedOnDate = now(), ContentStatusCode = 'ARCHIVED' where ContentId = :contentId";

    private static final String SQL_ARCHIVE_CONTENT_BY_STATUS = "update Content set ContentStatusCode = 'ARCHIVED'"
            + " where ContentStatusCode =:contentStatusCode and ContentTypeCode = 'POST' and PostId = :postID";

    private static final String SQL_UPDATE_STATIC_PAGE
            = " update Content c "
            + " set c.Title = :title, "
            + " c.ContentImgLink = :contentImgLink, "
            + " c.ContentImgAltTxt = :contentImgAltTxt, "
            + " c.Body = :body, "
            + " c.Snippet = :snippet, "
            + " c.ContentStatusCode = :contentStatusCode, "
            + " c.UrlPattern = :urlPattern, "
            + " c.ContentTypeCode = :contentTypeCode, "
            + " c.UpdatedByUserId = :updatedByUserID, "
            + " c.updatedOnDate = now() "
            + " where c.ContentId = :contentID";

    private static final String SQL_GET_POST_COMMENTS
            = "select c.* from Content c "
            + " where 1 = 1 "
            + " and c.ContentTypeCode = 'COMMENT'"
            + " and c.ContentStatusCode = 'PUBLISHED'"
            + " and PostId = ? ";

    // test written.
    @Override
    public Content updateStaticPage(Content content) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("postID", null);
        namedParameters.addValue("title", content.getTitle());
        namedParameters.addValue("contentImgLink", content.getContentImgLink());
        namedParameters.addValue("contentImgAltTxt", content.getContentImgAltTxt());
        namedParameters.addValue("body", content.getBody());
        namedParameters.addValue("snippet", content.getSnippet());
        namedParameters.addValue("contentStatusCode", content.getContentStatusCode());
        namedParameters.addValue("urlPattern", content.getUrlPattern());
        namedParameters.addValue("contentTypeCode", content.getContentTypeCode());
        namedParameters.addValue("updatedByUserID", content.getUpdatedByUser().getUserId());
        namedParameters.addValue("contentID", content.getContentId());
        npJdbcTemplate.update(SQL_UPDATE_STATIC_PAGE, namedParameters);
        return content;
    }

    // test written.
    @Override
    public List<Content> getAllContentsByPostId(int postID) {
        return jdbcTemplate.query(SQL_GET_ALL_REVISIONS_BY_POST_ID, new ContentMapper(), postID);
    }

    // void return value. unit test NOT written.
    @Override
    public void setAwaitingToArchived(Post post) {
        jdbcTemplate.update(SQL_SET_AWAITING_TO_ARCHIVED, post.getUpdatedByUser().getUserId(), post.getPostId());
    }

    // test written.
    @Override
    public Content updatePostContent(Content content) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("postID", content.getPostId());
        namedParameters.addValue("title", content.getTitle());
        namedParameters.addValue("contentImgLink", content.getContentImgLink());
        namedParameters.addValue("contentImgAltTxt", content.getContentImgAltTxt());
        namedParameters.addValue("body", content.getBody());
        namedParameters.addValue("snippet", content.getSnippet());
        namedParameters.addValue("contentStatusCode", content.getContentStatusCode());
        namedParameters.addValue("urlPattern", content.getUrlPattern());
        namedParameters.addValue("contentTypeCode", content.getContentTypeCode());
        namedParameters.addValue("createdByUserID", content.getCreatedByUser().getUserId());
        namedParameters.addValue("createdOnDate", content.getCreatedOnDate());

        archiveContentByStatus(content.getPostId(), content.getContentStatusCode());

        npJdbcTemplate.update(SQL_ADD_CONTENT_TO_POST, namedParameters);
        content.setContentId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        updateContentCategories(content);
        updateContentTags(content);
        return content;
    }

    // private method, void return value. unit test NOT written.
    private void archiveContentByStatus(int postId, String contentStatusCode) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("postID", postId);
        namedParameters.addValue("contentStatusCode", contentStatusCode);
        npJdbcTemplate.update(SQL_ARCHIVE_CONTENT_BY_STATUS, namedParameters);

    }

    // private method, void return value. unit test NOT written.
    private void archiveOldContent(int postID) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("postID", postID);
        npJdbcTemplate.update(SQL_ARCHIVE_OLD_CONTENT, namedParameters);
    }

    // private method, void return value. unit test NOT written.
    private void updateContentCategories(Content content) {
        try {
            jdbcTemplate.batchUpdate(SQL_UPDATE_CONTENT_CATEGORIES, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, content.getContentId());
                    ps.setInt(2, content.getListOfCategories().get(i).getCategoryID());
                }

                @Override
                public int getBatchSize() {
                    return content.getListOfCategories().size();
                }
            });
        } catch (Exception e) {
        }
    }

    // private method, void return value. unit test NOT written.
    private void updateContentTags(Content content) {
        try {
            jdbcTemplate.batchUpdate(SQL_UPDATE_CONTENT_TAGS, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, content.getContentId());
                    ps.setInt(2, content.getListOfTags().get(i).getTagID());
                }

                @Override
                public int getBatchSize() {
                    return content.getListOfTags().size();
                }
            });
        } catch (Exception e) {
        }

    }

    // test written.
    @Override
    public List<Content> getPublishedStaticPages() {
        return jdbcTemplate.query(SQL_GET_PUBLISHED_STATIC_PAGES, new ContentMapper());
    }

    // test written.
    @Override
    public List<Content> getStaticPageByStatus(String statusCode) {
        return jdbcTemplate.query(SQL_GET_STATIC_PAGE_BY_STATUS_CODE, new ContentMapper(), statusCode);
    }

    // test written.
    // use this method to throw error if urlPattern is 
    @Override
    public Content getStaticPageByURL(String urlPattern) {
        Content content = new Content();
        try {
            return jdbcTemplate.queryForObject(SQL_GET_STATIC_PAGE_BY_URL, new ContentMapper(), urlPattern);
        } catch (EmptyResultDataAccessException ex) {
            return content;
        }
    }

    // test written.
    @Override
    public Content addStaticPage(Content content) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("postID", null);
        namedParameters.addValue("title", content.getTitle());
        namedParameters.addValue("contentImgLink", content.getContentImgLink());
        namedParameters.addValue("contentImgAltTxt", content.getContentImgAltTxt());
        namedParameters.addValue("body", content.getBody());
        namedParameters.addValue("snippet", content.getSnippet());
        namedParameters.addValue("contentStatusCode", content.getContentStatusCode());
        namedParameters.addValue("urlPattern", content.getUrlPattern());
        namedParameters.addValue("contentTypeCode", content.getContentTypeCode());
        namedParameters.addValue("createdByUserID", content.getCreatedByUser().getUserId());
        namedParameters.addValue("createdOnDate", content.getCreatedOnDate());

        npJdbcTemplate.update(SQL_ADD_CONTENT_TO_POST, namedParameters);
        content.setContentId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return content;
    }

     // test written.
    @Override
    public Content getPublishedPostContent(int postID) {
        return jdbcTemplate.queryForObject(SQL_GET_PUBLISHED_CONTENT_BY_POST_ID, new ContentMapper(), postID);
    }

    // test written.
    @Override
    public Content getMostRecentPostContent(int postID) {
        return jdbcTemplate.queryForObject(SQL_GET_MOST_RECENT_CONTENT_BY_POST_ID, new ContentMapper(), postID);
    }

    // test written.
    @Override
    public void archiveContent(int contentId, int userId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("userId", userId);
        namedParameters.addValue("contentId", contentId);
        npJdbcTemplate.update(SQL_ARCHIVE_CONTENT, namedParameters);
    }

    // test written.
    @Override
    public void archivePost(int postId, int userId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("userId", userId);
        namedParameters.addValue("postId", postId);
        npJdbcTemplate.update(SQL_ARCHIVE_POST, namedParameters);
    }

    // test written.
    @Override
    public Content getContentById(int contentId) {
        return jdbcTemplate.queryForObject(SQL_GET_CONTENT_BY_ID, new ContentMapper(), contentId);
    }

    // test written, integration and unit.
    @Override
    public Content addPostComment(Content newComment) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("postID", newComment.getPostId());
        namedParameters.addValue("title", null);
        namedParameters.addValue("contentImgLink", null);
        namedParameters.addValue("contentImgAltTxt", null);
        namedParameters.addValue("body", newComment.getBody());
        namedParameters.addValue("snippet", null);
        namedParameters.addValue("contentStatusCode", "PUBLISHED");
        namedParameters.addValue("urlPattern", "");
        namedParameters.addValue("contentTypeCode", "COMMENT");
        namedParameters.addValue("createdByUserID", newComment.getCreatedByUser().getUserId());
        namedParameters.addValue("createdOnDate", "now()");

        npJdbcTemplate.update(SQL_ADD_CONTENT_TO_POST, namedParameters);
        newComment.setContentId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return newComment;
    }

    // test written.
    @Override
    public List<Content> getAllPostCommentsPublished(int postId) {
        return jdbcTemplate.query(SQL_GET_POST_COMMENTS, new ContentMapper(), postId);
    }

    private static final class ContentMapper implements RowMapper<Content> {

        @Override
        public Content mapRow(ResultSet rs, int i) throws SQLException {

            Content content = new Content();
            content.setContentId(rs.getInt("ContentId"));
            content.setPostId(rs.getInt("PostId"));
            content.setTitle(rs.getString("Title"));
            content.setContentImgLink(rs.getString("ContentImgLink"));
            content.setContentImgAltTxt(rs.getString("ContentImgAltTxt"));
            content.setBody(rs.getString("Body"));
            content.setSnippet(rs.getString("Snippet"));
            content.setContentStatusCode(rs.getString("ContentStatusCode"));
            content.setUrlPattern(rs.getString("UrlPattern"));
            content.setContentTypeCode(rs.getString("ContentTypeCode"));

            Timestamp createdTS = rs.getTimestamp("CreatedOnDate");
            Timestamp updatedTS = rs.getTimestamp("UpdatedOnDate");
            Timestamp archivedTS = rs.getTimestamp("ArchivedOnDate");
            Date createDate = createdTS;
            Date updateDate = updatedTS;
            Date archiveDate = archivedTS;
            content.setCreatedOnDate(createDate);
            content.setUpdatedOnDate(updateDate);
            content.setArchivedOnDate(archiveDate);

            return content;
        }
    }
}
