/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.cutepuppies.daos.TagDaoInterface;
import com.sg.cutepuppies.models.Category;
import com.sg.cutepuppies.models.Tag;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 *
 * @author apprentice
 */
public class TagDAOTest {
    
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private TagDaoInterface tagDao;

    private JdbcTemplate jdbcTemplate;
    SimpleJdbcCall simpleJdbcCall;

    public TagDAOTest() {

    }

    @Before
    public void setUp() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();

        tagDao = ctx.getBean("TagDBImplTest", TagDaoInterface.class);
    }

    @After
    public void teardown() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();
    }

    @Test
    public void testAddTag() {
        int numTags = tagDao.getAllTags(false).size();
        
        tagDao.addTag("New Tag");
        
        assertEquals(numTags + 1, tagDao.getAllTags(false).size());
    }

    @Test
    public void testUpdateTag() {
        Tag tag = tagDao.getAllTags(false).get(0);
        
        // Because we do not have a get Tag by ID, started it with ZZ so it will be last alphabetically
        tag.setTagDescription("ZZUPDATED TAG");
        
        int numTags = tagDao.getAllTags(false).size();
        
        tagDao.updateTag(tag);
        
        assertEquals(numTags, tagDao.getAllTags(false).size());
        assertEquals("ZZUPDATED TAG", tagDao.getAllTags(false).get(numTags - 1).getTagDescription());
    }

    @Test
    public void testDeleteTag() {
        List<Tag> tags = new ArrayList<>();
        tags = tagDao.getAllTags(false);
        int numTags = tags.size();
        int firstTagID = tags.get(0).getTagID();
        
        tagDao.deleteTag(firstTagID);
        
        assertEquals(numTags - 1, tagDao.getAllTags(false).size());
        assertNotEquals(firstTagID, tagDao.getAllTags(false).get(0).getTagID());
    }
    
    @Test
    public void testGetTagsByContentId() {
        assertEquals(3, tagDao.getTagsByContentId(1).size());
        assertEquals(6, tagDao.getTagsByContentId(3).size());
    }
    
    @Test
    public void testGetTagsPublishedUnpublished() {
        assertEquals(5, tagDao.getAllTags(true).size());
        assertEquals(7, tagDao.getAllTags(false).size());
    }
}
