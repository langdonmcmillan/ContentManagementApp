/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.cutepuppies.daos.CategoryDAOInterface;
import com.sg.cutepuppies.daos.ContentDAOInterface;
import com.sg.cutepuppies.daos.PostDAOInterface;
import com.sg.cutepuppies.daos.TagDAOInterface;
import com.sg.cutepuppies.models.Content;
import com.sg.cutepuppies.models.Post;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class ContentDAOTest {
    
    private PostDAOInterface postDAO;
    private ContentDAOInterface contentDAO;
    private CategoryDAOInterface categoryDAO;
    private TagDAOInterface tagDAO;
    
    public ContentDAOTest() {
        
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        postDAO = ctx.getBean("PostDBImplTest", PostDAOInterface.class);
        contentDAO = ctx.getBean("ContentDBImplTest", ContentDAOInterface.class);
        categoryDAO = ctx.getBean("CategoryDBImplTest", CategoryDAOInterface.class);
        tagDAO = ctx.getBean("TagDBImplTest", TagDAOInterface.class);
        
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
