/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.cutepuppies.daos.CategoryDAOInterface;
import com.sg.cutepuppies.daos.ContentDAOInterface;
import com.sg.cutepuppies.daos.PostDAOInterface;
import com.sg.cutepuppies.daos.TagDAOInterface;
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
public class PostDAOTest {
    
    private PostDAOInterface postDAO;
    private ContentDAOInterface contentDAO;
    private CategoryDAOInterface categoryDAO;
    private TagDAOInterface tagDAO;
    
    public PostDAOTest() {
        
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
    public void testAddPost() {
        Post post = new Post();
        post.setCreatedByUserId(1);
        
        int numPosts = postDAO.getAllPosts().size();
        assertEquals(0, post.getPostId());
        
        postDAO.addPost(post);
        
        assertEquals(numPosts + 1, postDAO.getAllPosts().size());
        assertNotEquals(0, post.getPostId());
    }
}
