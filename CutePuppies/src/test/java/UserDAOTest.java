/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.cutepuppies.daos.PostDaoInterface;
import com.sg.cutepuppies.daos.PostDbImpl;
import com.sg.cutepuppies.daos.UserDaoInterface;
import com.sg.cutepuppies.models.Post;
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
public class UserDAOTest {
    
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private UserDaoInterface userDao;
    private PostDaoInterface postDao;
    private JdbcTemplate jdbcTemplate;
    SimpleJdbcCall simpleJdbcCall;
    
    public UserDAOTest() {
    }

    @Before
    public void setUp() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();

        postDao = ctx.getBean("PostDBImplTest", PostDaoInterface.class);
        userDao = ctx.getBean("UserDBImplTest", UserDaoInterface.class);
    }

    @After
    public void teardown() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();
    }
    
    @Test
    public void testGetAllUsersAndAddUsers() {
        
        int numUsers = userDao.getAllUsers().size();
        
        
        User john = new User();
        john.setUserName("John");
        john.setUserPassword("pw");
        john.setUserEmail("12@gmail.com");
        john.setRoleCode("ROLE_ADMIN");
        
        john = userDao.addUser(john);
        
        
        assertEquals(numUsers + 1, userDao.getAllUsers().size());
        
    }
    
    @Test
    public void testUpdateUserAndGetUserById() {
        User james = userDao.getUserById(1);

        james.setUserName("JAMES");
        james.setUserPassword("1222");
        james.setUserEmail("xrx@aol.com");
        james.setRoleCode("ROLE_ADMIN");
        
        int numUsers = userDao.getAllUsers().size();
        
        userDao.updateUser(james);
        
        assertEquals(numUsers, userDao.getAllUsers().size());
        assertEquals("JAMES", userDao.getUserById(1).getUserName());
    }
    
    @Test
    public void testDeleteCategory() {
        List<User> users = userDao.getAllUsers();
        int numUsers = users.size();
        int firstUserId = users.get(0).getUserId();
        
        userDao.deleteUser(firstUserId);
        
        assertEquals(numUsers - 1, userDao.getAllUsers().size());
        assertNotEquals(firstUserId, userDao.getAllUsers().get(0).getUserId());
    }
    
    @Test
    public void testGetUserIdByEmail(){
        
        String email = userDao.getUserById(1).getUserEmail();
        
        assertEquals(1, userDao.getUserIdByEmail(email));
        
    }
    
    @Test
    public void testGetUserIdByUsername(){
        
        String name = userDao.getUserById(1).getUserName();
        
        assertEquals(1, userDao.getUserIdByUsername(name));
        
    }
    
    @Test
    public void testGetUserWhoCreatedPost(){
        
        String admin = "admin";
        
       assertEquals(admin, userDao.getUserWhoCreatedPost(1).getUserName());
    }
    
}
