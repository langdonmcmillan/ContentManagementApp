/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.cutepuppies.models.Content;
import com.sg.cutepuppies.models.Post;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import com.sg.cutepuppies.daos.CategoryDaoInterface;
import com.sg.cutepuppies.daos.ContentDaoInterface;
import com.sg.cutepuppies.daos.PostDaoInterface;
import com.sg.cutepuppies.daos.TagDaoInterface;

/**
 *
 * @author apprentice
 */
public class ContentDAOTest {
    
    private PostDaoInterface postDAO;
    private ContentDaoInterface contentDAO;
    private CategoryDaoInterface categoryDAO;
    private TagDaoInterface tagDAO;
    
    public ContentDAOTest() {
        
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        postDAO = ctx.getBean("PostDBImplTest", PostDaoInterface.class);
        contentDAO = ctx.getBean("ContentDBImplTest", ContentDaoInterface.class);
        categoryDAO = ctx.getBean("CategoryDBImplTest", CategoryDaoInterface.class);
        tagDAO = ctx.getBean("TagDBImplTest", TagDaoInterface.class);
        
        JdbcTemplate template = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        
    }
    
    @Test
    public void testAddPostContent() {
        Content content = new Content();
        content.setPostId(1);
        content.setTitle("Title");
        content.setContentImgLink("Image Link");
        content.setContentImgAltTxt("Image Text");
        content.setBody("Body");
        content.setSnippet("Snippet");
        content.setContentTypeCode("POST");
        content.setCreatedByUserId(1);
        content.setUrlPattern("URL Pattern");
        content.setContentStatusCode("PUBLISHED");
        
        // int numRevisions = contentDAO.getContentByPostID(0).size();
        assertEquals(0, content.getContentId());
        
        contentDAO.updatePostContent(content);
        
        // assertEquals(numRevisions + 1, contentDAO.getContentByPostID(0).size());
        assertNotEquals(0, content.getContentId());
    }
    
}
