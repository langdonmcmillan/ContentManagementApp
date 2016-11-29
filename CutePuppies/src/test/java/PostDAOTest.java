/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.cutepuppies.daos.ContentDaoInterface;
import com.sg.cutepuppies.models.Post;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import com.sg.cutepuppies.daos.PostDaoInterface;
import com.sg.cutepuppies.models.User;
import org.junit.After;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 *
 * @author apprentice
 */
public class PostDAOTest {
    
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private PostDaoInterface postDao;

    private JdbcTemplate jdbcTemplate;
    SimpleJdbcCall simpleJdbcCall;
    
    public PostDAOTest() {

    }
    
    @Before
    public void setUp() {
        jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();

        postDao = ctx.getBean("PostDBImplTest", PostDaoInterface.class);
        JdbcTemplate template = (JdbcTemplate) ctx.getBean("jdbcTemplate");

    }
    
    @After
    public void teardown() {
        jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();
    }

    @Test
    public void testAddPost() {
        String date = "2000-11-01";
        java.sql.Date adminCreateDate = java.sql.Date.valueOf(date);

        User admin = new User();
        admin.setUserId(1);
        admin.setRoleCode("ADMIN");
        admin.setCreatedDate(adminCreateDate);
        admin.setUserName("sadukie");

        Post post = new Post();
        post.setCreatedByUser(admin);

        int numPosts = postDao.getAllPosts(true).size();
        assertEquals(0, post.getPostId());

        postDao.addPost(post);

        assertEquals(numPosts + 1, postDao.getAllPosts(true).size());
        assertNotEquals(0, post.getPostId());
    }
    
    @Test
    public void testArchivePost() {
        int userId = 1;
        int postId = 5;

        postDao.archivePost(postId, userId);

        Post post = postDao.getPostByID(postId);
        
        assertNotNull(post.getArchivedOnDate());
        
    }
}

