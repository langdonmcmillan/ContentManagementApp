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

/**
 *
 * @author apprentice
 */
public class PostDAOTest {

    private PostDaoInterface postDAO;

    public PostDAOTest() {

    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        postDAO = ctx.getBean("PostDBImplTest", PostDaoInterface.class);

        JdbcTemplate template = (JdbcTemplate) ctx.getBean("jdbcTemplate");

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

        int numPosts = postDAO.getAllPosts(true).size();
        assertEquals(0, post.getPostId());

        postDAO.addPost(post);

        assertEquals(numPosts + 1, postDAO.getAllPosts(true).size());
        assertNotEquals(0, post.getPostId());
    }
}

