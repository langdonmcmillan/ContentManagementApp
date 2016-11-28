/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class PostDAOTest {
    
    private PostDaoInterface postDAO;
    private ContentDaoInterface contentDAO;
    private CategoryDaoInterface categoryDAO;
    private TagDaoInterface tagDAO;
    
    public PostDAOTest() {
        
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
    public void testAddPost() {
        Post post = new Post();
        post.setCreatedByUserId(1);
        
        int numPosts = postDAO.getAllPosts(true).size();
        assertEquals(0, post.getPostId());
        
        postDAO.addPost(post);
        
        assertEquals(numPosts + 1, postDAO.getAllPosts(true).size());
        assertNotEquals(0, post.getPostId());
    }
}
