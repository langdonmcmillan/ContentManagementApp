/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.cutepuppies.daos.CategoryDaoInterface;
import com.sg.cutepuppies.daos.ContentDaoInterface;
import com.sg.cutepuppies.daos.PostDaoInterface;
import com.sg.cutepuppies.daos.TagDaoInterface;
import com.sg.cutepuppies.daos.UserDaoInterface;
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
public class DashboardControllerTest {
    
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private ContentDaoInterface contentDao;
    private PostDaoInterface postDao;
    private CategoryDaoInterface categoryDao;
    private TagDaoInterface tagDao;
    private UserDaoInterface userDao;
    
    private JdbcTemplate jdbcTemplate;
    SimpleJdbcCall simpleJdbcCall;
    
    public DashboardControllerTest() {
    }
    
    @Before
    public void setUp() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();

        contentDao = ctx.getBean("ContentDBImplTest", ContentDaoInterface.class);
        postDao = ctx.getBean("PostDBImplTest", PostDaoInterface.class);
        categoryDao = ctx.getBean("CategoryDBImplTest", CategoryDaoInterface.class);
        tagDao = ctx.getBean("TagDBImplTest", TagDaoInterface.class);
    }

    @After
    public void teardown() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();
    }
}
