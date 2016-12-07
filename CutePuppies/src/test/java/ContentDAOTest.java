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
import com.sg.cutepuppies.daos.UserDaoInterface;
import com.sg.cutepuppies.models.User;
import java.util.List;
import java.util.stream.Collectors;
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
    public void testUpdatePostContent() {
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

    @Test
    public void testAddStaticPage() {
        User user = new User();
        user.setUserId(1);
        user.setRoleCode("ROLE_ADMIN");

        Content newStatPgPublish = new Content();
        newStatPgPublish.setTitle("new static page");
        newStatPgPublish.setContentImgLink("http://placehold.it/900x300");
        newStatPgPublish.setContentImgAltTxt("alt text for img");
        newStatPgPublish.setBody("body for new static page");
        newStatPgPublish.setContentStatusCode("PUBLISHED");
        newStatPgPublish.setUrlPattern("static_pg_url_pattern");
        newStatPgPublish.setContentTypeCode("STATIC PAGE");
        newStatPgPublish.setCreatedByUser(user);
        newStatPgPublish.getCreatedByUser().setUserId(user.getUserId());

        contentDao.addStaticPage(newStatPgPublish);
        int actualContentId = newStatPgPublish.getContentId();
        Content publishedStatPgFromDB = contentDao.getContentById(actualContentId);

        assertNotEquals(0, actualContentId);
        assertEquals(0, publishedStatPgFromDB.getPostId());
        assertEquals("new static page", publishedStatPgFromDB.getTitle());
        assertEquals("STATIC PAGE", publishedStatPgFromDB.getContentTypeCode());
        assertEquals("PUBLISHED", publishedStatPgFromDB.getContentStatusCode());

        Content newStatPgDraft = new Content();
        newStatPgDraft.setTitle("new static page 2");
        newStatPgDraft.setContentImgLink("http://placehold.it/900x300");
        newStatPgDraft.setContentImgAltTxt("alt text for img for page 2");
        newStatPgDraft.setBody("body for new static page 2");
        newStatPgDraft.setContentStatusCode("DRAFT");
        newStatPgDraft.setUrlPattern("static_pg_url_pattern_1");
        newStatPgDraft.setContentTypeCode("STATIC PAGE");
        newStatPgDraft.setCreatedByUser(user);
        newStatPgDraft.getCreatedByUser().setUserId(user.getUserId());

        contentDao.addStaticPage(newStatPgDraft);
        int statPgContentId = newStatPgDraft.getContentId();
        Content savedStatPgFromDB = contentDao.getContentById(statPgContentId);

        assertNotEquals(0, statPgContentId);
        assertEquals(0, savedStatPgFromDB.getPostId());
        assertEquals("new static page 2", savedStatPgFromDB.getTitle());
        assertEquals("STATIC PAGE", savedStatPgFromDB.getContentTypeCode());
        assertEquals("DRAFT", savedStatPgFromDB.getContentStatusCode());

    }

    @Test
    public void testUpdateStaticPage() {
        String date = "2000-11-01";
        java.sql.Date adminCreateDate = java.sql.Date.valueOf(date);

        User admin = new User();
        admin.setUserId(1);
        admin.setRoleCode("ROLE_ADMIN");
        admin.setCreatedDate(adminCreateDate);
        admin.setUserName("sadukie");

        Content statPg = contentDao.getStaticPageByURL("statPgUrlPattern1");

        statPg.setBody("<p>Content24 test content body UPDATED</p>");
        statPg.setUpdatedByUser(admin);
        Content newContent = contentDao.updateStaticPage(statPg);

        assertEquals(statPg.getBody(), newContent.getBody());

        Content statPg2 = contentDao.getStaticPageByURL("statPgUrlPattern3");
        statPg2.setTitle("StaticPage3Content26 UPDATED");
        statPg2.setContentStatusCode("PUBLISHED");
        statPg2.setUpdatedByUser(admin);

        Content newContent2 = contentDao.updateStaticPage(statPg2);
        assertEquals("PUBLISHED", newContent2.getContentStatusCode());
    }

    @Test
    public void testGetPublishedStaticPages() {
        List<Content> allStaticPage = contentDao.getPublishedStaticPages();
        int expectedPublishedPages = 3;
        assertEquals(expectedPublishedPages, allStaticPage.size());
    }

    @Test
    public void testGetStaticPagesByStatus() {
        List<Content> publishedPgs = contentDao.getStaticPageByStatus("PUBLISHED");
        List<Content> archivedPgs = contentDao.getStaticPageByStatus("ARCHIVED");
        List<Content> savedPgs = contentDao.getStaticPageByStatus("DRAFT");

        int expectedPublishedPages = 3;
        int actualPublishedPages = publishedPgs.size();
        assertEquals(expectedPublishedPages, actualPublishedPages);

        String expectedFirstPublishedPageTitle = "StaticPage5Content28";
        String actualFirstPublishedPageTitle = publishedPgs.get(0).getTitle();
        assertEquals(expectedFirstPublishedPageTitle, actualFirstPublishedPageTitle);

        int expectedArchivedPages = 2;
        int actualArchivedPages = archivedPgs.size();
        assertEquals(expectedArchivedPages, actualArchivedPages);

        String expectedOldestArchivedPageUrlPattern = "statPgUrlPattern2";
        String actualOldestArchivedPageUrlPattern = archivedPgs.get(1).getUrlPattern();
        assertEquals(expectedOldestArchivedPageUrlPattern, actualOldestArchivedPageUrlPattern);

        int expectedSavedStaticPages = 1;
        int actualSavedStaticPages = savedPgs.size();
        assertEquals(expectedSavedStaticPages, actualSavedStaticPages);
    }

    @Test
    public void testGetStaticPageByURL() {
        Content content = contentDao.getStaticPageByURL("statPgUrlPattern1");

        String expectedTitle = "StaticPage1Content24";
        String actualTitle = content.getTitle();
        assertEquals(expectedTitle, actualTitle);

        int expectedContentId = 24;
        int actualContentId = content.getContentId();
        assertEquals(expectedContentId, actualContentId);

        String expectedContentStatus = "PUBLISHED";
        String actualContentStatus = content.getContentStatusCode();
        assertEquals(expectedContentStatus, actualContentStatus);

        Content emptyContent = contentDao.getStaticPageByURL("someUrlNotInDatabase");
        assertNull(emptyContent.getTitle());
        assertEquals(0, emptyContent.getContentId());
        assertEquals(0, emptyContent.getPostId());
        
    }
}
