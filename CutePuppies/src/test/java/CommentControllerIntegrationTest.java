/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.cutepuppies.daos.ContentDaoInterface;
import com.sg.cutepuppies.daos.UserDaoInterface;
import com.sg.cutepuppies.models.Content;
import com.sg.cutepuppies.models.User;
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
public class CommentControllerIntegrationTest {
    
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private ContentDaoInterface contentDao;
    private UserDaoInterface userDao;
    private JdbcTemplate jdbcTemplate;
    SimpleJdbcCall simpleJdbcCall;

    @Before
    public void setUp() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();

        contentDao = ctx.getBean("ContentDBImplTest", ContentDaoInterface.class);
        userDao = ctx.getBean("UserDBImplTest", UserDaoInterface.class);
        
        JdbcTemplate template = (JdbcTemplate) ctx.getBean("jdbcTemplate");

    }

    @After
    public void teardown() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();
    }
    
    public CommentControllerIntegrationTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testAddPostComment() {
        User commenter1 = new User();
        commenter1.setUserName("commenter1");
        commenter1.setRoleCode("ROLE_GUEST");
        commenter1.setUserEmail("");
        commenter1.setUserPassword("");
        
        Content comment1 = new Content();
        comment1.setPostId(13);
        comment1.setBody("FIRST!!!!!");
        comment1.setCreatedByUser(userDao.addUser(commenter1));
        
        contentDao.addPostComment(comment1);
        
        
        User commenter2 = new User();
        commenter2.setUserName("commenter2");
        commenter2.setRoleCode("ROLE_GUEST");
        commenter2.setUserEmail("");
        commenter2.setUserPassword("");
        
        Content comment2 = new Content();
        comment2.setPostId(13);
        comment2.setBody("SECOND!!!!!");
        comment2.setCreatedByUser(userDao.addUser(commenter2));
        
        contentDao.addPostComment(comment2);
        
        List<Content> commentsOfPost13 = contentDao.getAllPostCommentsPublished(13);
        
        int expectedNumOfComments = 2;
        int actualNumOfComments = commentsOfPost13.size();
        assertEquals(expectedNumOfComments, actualNumOfComments);
        
        String actualComment1Body = commentsOfPost13.get(0).getBody();
        String expectedComment1Body = "FIRST!!!!!";
        assertEquals(expectedComment1Body, actualComment1Body);
        
        String expectedComment2UserName = "commenter2";
        String actualComment2UserName = commentsOfPost13.get(1).getCreatedByUser().getUserName();
        
        assertEquals(expectedComment2UserName, actualComment2UserName);
    }
}
