/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class TagDbImpl implements TagDaoInterface {

    private JdbcTemplate jdbcTemplate;

    // setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    // SQL PREPARED STATEMENTS
    private static final String SQL_GET_TAGS_BY_CONTENT_ID = "select t.* from Tag t join content_tag ct on t.TagId = ct.TagId where ct.ContentId = ?";
    // This is for blog page, returns top 50 for tag cloud
    private static final String SQL_GET_PUBLISHED_TAGS = "select t.TagId, t.TagDescription, count(ct.TagId) as useNum from content_tag ct "
            + "join Content c on c.ContentId = ct.ContentId "
            + "join Tag t on t.TagId = ct.TagId "
            + "where c.ContentStatusCode = 'PUBLISHED' "
            + "group by t.TagId, t.TagDescription "
            + "order by useNum desc "
            + "limit 50";
    // This will return all tags alphabetically - for use in the dashboard (does not give count used for tag cloud)
    private static final String SQL_GET_ALL_TAGS = "select TagId, TagDescription from Tag "
            + "order by TagDescription asc";
    private static final String SQL_ADD_TAG = "insert ignore into Tag (TagDescription) "
            + "values(?)";
    private static final String SQL_UPDATE_TAG = "update Tag set TagDescription = ? where TagId = ?";
    private static final String SQL_DELETE_TAG = "delete from Tag where TagId = ?";
    private static final String SQL_DELETE_CONTENT_TAG = "delete from content_tag where TagId = ?";
    private static final String SQL_GET_TAG_ID = "select TagId from Tag where TagDescription = ?";
    
    @Override
    public List<Tag> getAllTags(boolean onlyPublished) {
        return (onlyPublished) ? jdbcTemplate.query(SQL_GET_PUBLISHED_TAGS, new TagMapper()) : jdbcTemplate.query(SQL_GET_ALL_TAGS, new TagMapper());
    }

    @Override
    public Tag getTagByName(String tag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tag addTag(String tag) {
        jdbcTemplate.update(SQL_ADD_TAG, tag);
        Tag newTag = new Tag();
        newTag.setTagDescription(tag);
        newTag.setTagID(jdbcTemplate.queryForObject(SQL_GET_TAG_ID, Integer.class, tag));
        return newTag;
    }

    @Override
    public Tag updateTag(Tag tag) {
        jdbcTemplate.update(SQL_UPDATE_TAG, tag.getTagDescription(), tag.getTagID());
        return tag;
    }

    @Override
    public void deleteTag(int tagID) {
        jdbcTemplate.update(SQL_DELETE_CONTENT_TAG, tagID);
        jdbcTemplate.update(SQL_DELETE_TAG, tagID);
    }

    @Override
    public List<Tag> getTagsByContentId(int contentId) {
        return jdbcTemplate.query(SQL_GET_TAGS_BY_CONTENT_ID, new TagMapper(), contentId);
    }
    
        private static final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {

            Tag tag = new Tag();
            tag.setTagID(rs.getInt("TagId"));
            tag.setTagDescription(rs.getString("TagDescription"));
            try {
                tag.setNumUsed(rs.getInt("useNum"));
            } catch (Exception e) {
                tag.setNumUsed(0);
            }

            return tag;
        }
    }
    
}
