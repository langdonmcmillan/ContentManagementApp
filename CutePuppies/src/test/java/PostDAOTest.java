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
import com.sg.cutepuppies.daos.PostDaoInterface;
import com.sg.cutepuppies.models.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

//    @Test
//    public void testAddPost() {
//        String date = "2000-11-01";
//        java.sql.Date adminCreateDate = java.sql.Date.valueOf(date);
//
//        User admin = new User();
//        admin.setUserId(1);
//        admin.setRoleCode("ADMIN");
//        admin.setCreatedDate(adminCreateDate);
//        admin.setUserName("sadukie");
//
//        Post post = new Post();
//        post.setCreatedByUser(admin);
//
//        int numPosts = postDao.getAllPosts(true).size();
//        assertEquals(0, post.getPostId());
//
//        postDao.addPost(post);
//
//        assertEquals(numPosts + 1, postDao.getAllPosts(true).size());
//        assertNotEquals(0, post.getPostId());
//    }
    
    @Test
    public void testArchivePost() {
        int userId = 1;
        int postId = 5;

        postDao.archivePost(postId, userId);

        Post post = postDao.getPostByID(postId);
        
        assertNotNull(post.getArchivedOnDate());
        
    }
    
    @Test
    public void testGetAllPosts() {
        // All archived - 2, 3, 8
        
        //assertEquals(3, postDao.getAllPosts("ARCHIVED").size());
        
        // All Published
        
        assertEquals(6, postDao.getAllPosts("PUBLISHED").size());
        
        // All Draft
        
        assertEquals(5, postDao.getAllPosts("DRAFT").size());
        
        // All Awaiting
        
        assertEquals(2, postDao.getAllPosts("AWAITING").size());
    }
    
    @Test
    public void testGetPostsByAllCriteria() {
        // Arrange
        int pageNumber = 1;
        int fivePostsPerPg = 5;
        // Act
        List<Post> posts = postDao.getPostsByAllCriteria(pageNumber, fivePostsPerPg, 0, 0, "");
        // Assert
        assertEquals("This should return 5. 6 was published, but showing 5 posts per page.", 5, posts.size());
        assertNotNull("CreatedOnDate should not be null", posts.get(3).getCreatedOnDate());
    
        int tenPostsPerPg = 10;
        
        List<Post> posts2 = postDao.getPostsByAllCriteria(pageNumber, tenPostsPerPg, 0, 0, "");
        assertEquals("This should return 6. 6 was published, showing 10 posts per page.", 6, posts2.size());
        
        List<Post> posts3 = postDao.getPostsByAllCriteria(pageNumber, tenPostsPerPg, 0, 0, "test");
        assertEquals("This should return 6. 6 was published (all have 'test' in body), showing 10 posts per page.", 6, posts3.size());
    
        List<Post> posts4 = postDao.getPostsByAllCriteria(pageNumber, tenPostsPerPg, 0, 0, "Post 4, Content 1");
        assertEquals("Should only return 1 - only 1 has this title", 1, posts4.size());
    
        List<Post> posts5 = postDao.getPostsByAllCriteria(pageNumber, tenPostsPerPg, 0, 0, "abc it's easy as 123");
        assertEquals("Should return 0 - no title or body matches", 0, posts5.size());
    
    }

    @Test
    public void testGetPostByID() {
        Post post14 = postDao.getPostByID(14);
        // use simpledateformat
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date post14Date = post14.getCreatedOnDate();
        String post14DateStr = sdfDate.format(post14Date);
        assertEquals("CreatedOnDate of Post 14 should be 2011-01-14 01:11:11", "2011-01-14 01:11:11", post14DateStr);
    }
    
}

