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
    private static final String SQL_GET_PUBLISHED_TAGS = "select t.TagId, t.TagDescription, count(ct.TagId) as useNum from content_tag ct "
            + "join Content c on c.ContentId = ct.ContentId "
            + "join Tag t on t.TagId = ct.TagId "
            + "where c.ContentStatusCode = 'PUBLISHED' "
            + "group by t.TagId, t.TagDescription";
    private static final String SQL_GET_ALL_TAGS = "select t.TagId, t.TagDescription, count(ct.TagId) as useNum from content_tag ct "
            + "join Content c on c.ContentId = ct.ContentId "
            + "join Tag t on t.TagId = ct.TagId "
            + "group by t.TagId, t.TagDescription";
    
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tag updateTag(Tag tag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTag(int tagID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
