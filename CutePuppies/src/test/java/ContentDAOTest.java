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
import com.sg.cutepuppies.daos.ContentDaoInterface;
import com.sg.cutepuppies.models.User;
import java.util.List;
import org.junit.After;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 *
 * @author apprentice
 */
public class ContentDAOTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private ContentDaoInterface contentDao;

    private JdbcTemplate jdbcTemplate;
    SimpleJdbcCall simpleJdbcCall;

    public ContentDAOTest() {

    }

    @Before
    public void setUp() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();

        contentDao = ctx.getBean("ContentDBImplTest", ContentDaoInterface.class);
        JdbcTemplate template = (JdbcTemplate) ctx.getBean("jdbcTemplate");

    }

    @After
    public void teardown() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();
    }

    @Test
    public void testAddPostContent() {
        String date = "2000-11-01";
        java.sql.Date adminCreateDate = java.sql.Date.valueOf(date);

        String date2 = "2016-11-01";
        java.sql.Date contentCreateDate = java.sql.Date.valueOf(date2);

        User admin = new User();
        admin.setUserId(1);
        admin.setRoleCode("ADMIN");
        admin.setCreatedDate(adminCreateDate);
        admin.setUserName("sadukie");

        Content content = new Content();
        content.setPostId(1);
        content.setTitle("Title");
        content.setContentImgLink("Image Link");
        content.setContentImgAltTxt("Image Text");
        content.setBody("Body");
        content.setSnippet("Snippet");
        content.setContentTypeCode("POST");
        content.setCreatedByUser(admin);
        content.setCreatedOnDate(contentCreateDate);
        content.setUrlPattern("URL Pattern");
        content.setContentStatusCode("PUBLISHED");

        // int numRevisions = contentDAO.getContentByPostID(0).size();
        assertEquals(0, content.getContentId());

        contentDao.updatePostContent(content);

        // assertEquals(numRevisions + 1, contentDAO.getContentByPostID(0).size());
        assertNotEquals(0, content.getContentId());
    }

    @Test
    public void testGetAllContentsByPostId() {
        String date = "2000-11-01";
        java.sql.Date adminCreateDate = java.sql.Date.valueOf(date);

        String date2 = "2016-11-01";
        java.sql.Date contentCreateDate = java.sql.Date.valueOf(date2);

        User admin = new User();
        admin.setUserId(1);
        admin.setRoleCode("ADMIN");
        admin.setCreatedDate(adminCreateDate);
        admin.setUserName("sadukie");

        Post post = new Post();
        post.setPostId(1);
        post.setCreatedByUser(admin);

        Content content1 = new Content();
        content1.setContentId(1);
        content1.setPostId(1);
        content1.setTitle("Content1");
        content1.setContentStatusCode("PUBLISHED");
        content1.setUrlPattern("someUrlForContent1");
        content1.setContentTypeCode("POST");
        content1.setCreatedByUser(admin);
        content1.setCreatedOnDate(contentCreateDate);

        Content content2 = new Content();
        content2.setContentId(2);
        content2.setPostId(1);
        content2.setTitle("Content1");
        content2.setContentStatusCode("ARCHIVED");
        content2.setUrlPattern("someUrlForContent2");
        content2.setContentTypeCode("POST");
        content2.setCreatedByUser(admin);
        content2.setCreatedOnDate(contentCreateDate);

    }

    @Test
    public void testArchivePost() {
        int userId = 1;
        int postId = 5;

        contentDao.archivePost(postId, userId);

        for (Content content : contentDao.getAllContentsByPostId(postId)) {
            assertEquals("ARCHIVED", content.getContentStatusCode());
        }
    }

    @Test
    public void testArchiveContent() {
        int userId = 1;
        Content contentToArchive = contentDao.getPublishedPostContent(5);
        int contentId = contentToArchive.getContentId();

        contentDao.archiveContent(contentId, userId);

        for (Content content : contentDao.getAllContentsByPostId(5)) {
            if (content.getContentId() == contentId) {
                assertEquals("ARCHIVED", content.getContentStatusCode());
            }
        }
    }
    
    

}
