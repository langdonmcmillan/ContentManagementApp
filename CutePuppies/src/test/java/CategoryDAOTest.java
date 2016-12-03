/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.cutepuppies.daos.CategoryDaoInterface;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import com.sg.cutepuppies.models.Category;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 *
 * @author apprentice
 */
public class CategoryDAOTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private CategoryDaoInterface categoryDao;

    private JdbcTemplate jdbcTemplate;
    SimpleJdbcCall simpleJdbcCall;

    public CategoryDAOTest() {

    }

    @Before
    public void setUp() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();

        categoryDao = ctx.getBean("CategoryDBImplTest", CategoryDaoInterface.class);
        JdbcTemplate template = (JdbcTemplate) ctx.getBean("jdbcTemplate");

    }

    @After
    public void teardown() {
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("reset_CutePuppiesTest");
        simpleJdbcCall.execute();
    }

    @Test
    public void testAddCategory() {
        int numCategories = categoryDao.getAllCategories().size();
        
        categoryDao.addCategory("New Category");
        
        assertEquals(numCategories + 1, categoryDao.getAllCategories().size());
    }

    @Test
    public void testUpdateCategory() {
        Category category = categoryDao.getAllCategories().get(0);
        
        category.setCategoryDescription("ZZUPDATED CATEGORY");
        
        int numCategories = categoryDao.getAllCategories().size();
        
        categoryDao.updateCategory(category);
        
        assertEquals(numCategories, categoryDao.getAllCategories().size());
        assertEquals("ZZUPDATED CATEGORY", categoryDao.getAllCategories().get(numCategories - 1).getCategoryDescription());
    }

    @Test
    public void testDeleteCategory() {
        List<Category> categories = new ArrayList<>();
        categories = categoryDao.getAllCategories();
        int numCategories = categories.size();
        int firstCategoryID = categories.get(0).getCategoryID();
        
        categoryDao.deleteCategory(firstCategoryID);
        
        assertEquals(numCategories - 1, categoryDao.getAllCategories().size());
        assertNotEquals(firstCategoryID, categoryDao.getAllCategories().get(0).getCategoryID());
    }
}
