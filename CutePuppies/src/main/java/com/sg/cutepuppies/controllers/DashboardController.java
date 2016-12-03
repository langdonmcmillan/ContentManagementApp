/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.controllers;

import com.sg.cutepuppies.models.Content;
import com.sg.cutepuppies.models.Category;
import com.sg.cutepuppies.models.Post;
import com.sg.cutepuppies.models.Tag;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.sg.cutepuppies.daos.CategoryDaoInterface;
import com.sg.cutepuppies.daos.ContentDaoInterface;
import com.sg.cutepuppies.daos.PostDaoInterface;
import com.sg.cutepuppies.daos.TagDaoInterface;
import com.sg.cutepuppies.daos.UserDaoInterface;
import com.sg.cutepuppies.models.User;
import org.springframework.ui.Model;

/**
 *
 * @author apprentice
 */
@Controller
public class DashboardController {

    private PostDaoInterface postDao;
    private ContentDaoInterface contentDao;
    private CategoryDaoInterface categoryDao;
    private TagDaoInterface tagDao;
    private UserDaoInterface userDao;

    @Autowired
    @Inject
    public DashboardController(PostDaoInterface postDao, ContentDaoInterface contentDao,
            CategoryDaoInterface categoryDao, TagDaoInterface tagDao, UserDaoInterface userDao) {
        this.postDao = postDao;
        this.contentDao = contentDao;
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = {"/admin/dashboard", "/admin"}, method = RequestMethod.GET)
    public String getDashBoardPage() {
        return "dashboard";
    }
    
    @RequestMapping(value = {"/admin/dashboard/pages"}, method = RequestMethod.GET)
    public String getPagesDashboard(Model model) {
        List<Content> allStaticPages = contentDao.getAllStaticPages();
        model.addAttribute("allStaticPages", allStaticPages);
        
        return "dashboard";
    }

    @RequestMapping(value = "admin/getAllPosts/{archiveBoxChecked}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts(@PathVariable("archiveBoxChecked") boolean showArchived) {
        List<Post> listOfAllPosts = postDao.getAllPosts(showArchived);
        listOfAllPosts.forEach(post -> {
            int postId = post.getPostId();
            post.setCreatedByUser(userDao.getUserWhoCreatedPost(postId));
            post.setUpdatedByUser(userDao.getUserWhoUpdatedPost(postId));
            post.setArchivedByUser(userDao.getUserWhoArchivedPost(postId));

            Content mostRecentPostContent = contentDao.getMostRecentPostContent(postId);
            int contentId = mostRecentPostContent.getContentId();
            mostRecentPostContent.setCreatedByUser(userDao.getUserWhoCreatedContent(contentId));
            mostRecentPostContent.setListOfCategories(categoryDao.getCategoriesByContentId(contentId));
            mostRecentPostContent.setListOfTags(tagDao.getTagsByContentId(contentId));
            post.setMostRecentContent(mostRecentPostContent);
        });
        return listOfAllPosts;
    }

    @RequestMapping(value = "admin/edit/post/{postId}", method = RequestMethod.GET)
    @ResponseBody
    public Post getPostById(@PathVariable("postId") int postId) {

        Post post = postDao.getPostByID(postId);
        post.setCreatedByUser(userDao.getUserWhoCreatedPost(postId));
        post.setUpdatedByUser(userDao.getUserWhoUpdatedPost(postId));
        post.setArchivedByUser(userDao.getUserWhoArchivedPost(postId));

        Content mostRecentPostContent = contentDao.getMostRecentPostContent(postId);
        int contentId = mostRecentPostContent.getContentId();
        mostRecentPostContent.setCreatedByUser(userDao.getUserWhoCreatedContent(contentId));
        mostRecentPostContent.setListOfCategories(categoryDao.getCategoriesByContentId(contentId));
        mostRecentPostContent.setListOfTags(tagDao.getTagsByContentId(contentId));
        post.setMostRecentContent(mostRecentPostContent);

        List<Content> allContent = contentDao.getAllContentsByPostId(postId);

        allContent.forEach(content -> {
            int conId = content.getContentId();
            content.setCreatedByUser(userDao.getUserWhoCreatedContent(conId));

            //content.setListOfCategories(categoryDao.getCategoriesByContentId(conId));
            //content.setListOfTags(tagDao.getTagsByContentId(conId));
        });

        post.setAllContentRevisions(allContent);

        return post;
    }

    @RequestMapping(value = "admin/edit/post/{postId}/{contentId}", method = RequestMethod.GET)
    @ResponseBody
    public Content getContentById(@PathVariable("postId") int postId, @PathVariable("contentId") int contentId) {

        List<Content> allContent = contentDao.getAllContentsByPostId(postId);

        Content content = new Content();

        for (int x = 0; x < allContent.size(); x++) {

            if (contentId == allContent.get(x).getContentId()) {
                content = allContent.get(x);
            }
        }
        
        content.setListOfCategories(categoryDao.getCategoriesByContentId(content.getContentId()));
        content.setListOfTags(tagDao.getTagsByContentId(content.getContentId()));
        
        return content;
    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.GET)
    public String displayAddPostPage() {
        return "edit";
    }

    @RequestMapping(value = "admin/edit/{postId}", method = RequestMethod.GET)
    public String displayEditPostPage(@PathVariable("postId") int postId, Model model) {

        model.addAttribute(postId);

        return "edit";
    }

    @RequestMapping(value = "admin/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post addPost(@Valid @RequestBody Post post) {
        post = postDao.addPost(post);
        post.getMostRecentContent().setPostId(post.getPostId());
        contentDao.updatePostContent(post.getMostRecentContent());
        return post;
    }

    @RequestMapping(value = "admin/edit/content", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post addContent(@Valid @RequestBody Post post) {

        contentDao.updatePostContent(post.getMostRecentContent());
        post = postDao.updatePost(post);

        return post;
    }

    @RequestMapping(value = "admin/edit/post/{postId}/{userId}", method = RequestMethod.PUT)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void archivePost(@PathVariable("postId") int postID, @PathVariable("userId") int userId) {
        postDao.archivePost(postID, userId);
        contentDao.archivePost(postID, userId);
    }

    @RequestMapping(value = "admin/edit/content/{contentId}/{userId}", method = RequestMethod.PUT)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void archiveContent(@PathVariable("contentId") int contentID, @PathVariable("userId") int userId) {
        contentDao.archiveContent(contentID, userId);
    }
    
    @RequestMapping(value = "admin/manageTags", method = RequestMethod.GET)
    public String manageTags(Model model) {
        model.addAttribute("categoryTag", "Tags");
        return "tagcategory";
    }
    
    @RequestMapping(value = "admin/manageCategories", method = RequestMethod.GET)
    public String manageCategories(Model model) {
        model.addAttribute("categoryTag", "Categories");
        return "tagcategory";
    }
    
    @RequestMapping(value = "admin/Tags", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Tag addTag(@Valid @RequestBody String tag) {
        return tagDao.addTag(tag);
    }
    
    @RequestMapping(value = "admin/Categories", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Category addCategory(@Valid @RequestBody String category) {
        return categoryDao.addCategory(category);
    }
    
    @RequestMapping(value = "admin/Categories/{categoryId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editCategory(@Valid @RequestBody String categoryDescription, @PathVariable("categoryId") int categoryId) {
        Category category = new Category();
        category.setCategoryID(categoryId);
        category.setCategoryDescription(categoryDescription);
        categoryDao.updateCategory(category);
    }
    
    @RequestMapping(value = "admin/Tags/{tagId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTag(@Valid @RequestBody String tagDescription, @PathVariable("tagId") int tagId) {
        Tag tag = new Tag();
        tag.setTagID(tagId);
        tag.setTagDescription(tagDescription);
        tagDao.updateTag(tag);
    }
    
    @RequestMapping(value = "admin/Categories/{categoryId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("categoryId") int categoryId) {
        categoryDao.deleteCategory(categoryId);
    }
    
    @RequestMapping(value = "admin/Tags/{tagId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTag(@PathVariable("tagId") int tagId) {
        tagDao.deleteTag(tagId);
    }
}
