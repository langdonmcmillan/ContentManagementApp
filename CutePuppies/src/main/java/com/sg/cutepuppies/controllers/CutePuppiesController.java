/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.controllers;

import com.sg.cutepuppies.models.Content;
import com.sg.cutepuppies.models.Post;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sg.cutepuppies.daos.CategoryDaoInterface;
import com.sg.cutepuppies.daos.ContentDaoInterface;
import com.sg.cutepuppies.daos.PostDaoInterface;
import com.sg.cutepuppies.daos.TagDaoInterface;
import com.sg.cutepuppies.daos.UserDaoInterface;
import com.sg.cutepuppies.models.Category;
import com.sg.cutepuppies.models.Tag;
import org.springframework.ui.Model;

/**
 *
 * @author apprentice
 */
@Controller
public class CutePuppiesController {

    private CategoryDaoInterface categoryDao;
    private TagDaoInterface tagDao;
    private ContentDaoInterface contentDao;
    private PostDaoInterface postDao;
    private UserDaoInterface userDao;

    @Inject
    public CutePuppiesController(CategoryDaoInterface categoryDao, TagDaoInterface tagDao, ContentDaoInterface contentDao, PostDaoInterface postDao, UserDaoInterface userDao) {
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
        this.contentDao = contentDao;
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String getLandingPage(Model model) {
        List<Content> allStaticPages = contentDao.getPublishedStaticPages();
        model.addAttribute("allStaticPages", allStaticPages);
        return "blog";
    }

    @RequestMapping(value = "/ajax/getPagePosts/", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getPagePosts(String pageNumber, String postsPerPage, String tagId, String categoryId, String searchTerm) {
        // parse to int: newestPostId, oldestPostId, postsPerPage, tagId, categoryId
        int pageNumberInt = 0;
        int postsPerPageInt = 0;
        int tagIdInt = 0;
        int categoryIdInt = 0;

        if (!pageNumber.equals("null")) {
            pageNumberInt = Integer.parseInt(pageNumber);
        }
        if (!postsPerPage.equals("null")) {
            postsPerPageInt = Integer.parseInt(postsPerPage);
        }
        if (!tagId.equals("null")) {
            tagIdInt = Integer.parseInt(tagId);
        }
        if (!categoryId.equals("null")) {
            categoryIdInt = Integer.parseInt(categoryId);
        }

        // get me a list of Post objects.
        List<Post> listOfPosts = postDao.getPostsByAllCriteria(pageNumberInt, postsPerPageInt, tagIdInt, categoryIdInt, searchTerm);

        listOfPosts.forEach((post) -> {
            int postId = post.getPostId();
            Content postContent = contentDao.getPublishedPostContent(postId);
            postContent.setListOfTags(tagDao.getTagsByContentId(postContent.getContentId()));
            postContent.setListOfCategories(categoryDao.getCategoriesByContentId(postContent.getContentId()));
            postContent.setCreatedByUser(userDao.getUserWhoCreatedContent(postContent.getContentId()));
            post.setCreatedByUser(userDao.getUserWhoCreatedPost(postId));
            post.setPublishedContent(postContent);
        });
        return listOfPosts;
    }

    @RequestMapping(value = "/{urlPattern}", method = RequestMethod.GET)
    public String displayStaticPage(@PathVariable("urlPattern") String urlPattern, Model model) {
        List<Content> allStaticPages = contentDao.getPublishedStaticPages();
        model.addAttribute("allStaticPages", allStaticPages);

        Content staticPage = contentDao.getStaticPageByURL(urlPattern);
        int staticPageId = staticPage.getContentId();
        staticPage.setCreatedByUser(userDao.getUserWhoCreatedContent(staticPageId));
        staticPage.setUpdatedByUser(userDao.getUserWhoUpdatedContent(staticPageId));
        model.addAttribute("staticPage", staticPage);
        return "/blog";
    }

    @RequestMapping(value = "post/{postId}", method = RequestMethod.GET)
    public String displayPost(@PathVariable("postId") int postId, Model model) {
        List<Content> allStaticPages = contentDao.getPublishedStaticPages();
        model.addAttribute("allStaticPages", allStaticPages);
        Post post = postDao.getPostByID(postId);
        Content postContent = contentDao.getPublishedPostContent(postId);
        postContent.setListOfTags(tagDao.getTagsByContentId(postContent.getContentId()));
        postContent.setListOfCategories(categoryDao.getCategoriesByContentId(postContent.getContentId()));
        postContent.setCreatedByUser(userDao.getUserWhoCreatedContent(postContent.getContentId()));
        post.setCreatedByUser(userDao.getUserWhoCreatedPost(postId));

        post.setPublishedContent(postContent);
        model.addAttribute("post", post);
        return "/blog";
    }

    @RequestMapping(value = "ajax/getCategories", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> populateCategories() {
        List<Category> listOfCategories = categoryDao.getAllCategories();
        return listOfCategories;
    }

    @RequestMapping(value = "ajax/getTags/{onlyPublished}", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> populateTags(@PathVariable("onlyPublished") boolean onlyPublished) {
        List<Tag> listOfTags = tagDao.getAllTags(onlyPublished);
        return listOfTags;
    }

}
