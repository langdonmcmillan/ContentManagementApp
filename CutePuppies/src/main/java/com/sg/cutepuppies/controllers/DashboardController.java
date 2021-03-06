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
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

/**
 *
 * @author apprentice
 */
@RequestMapping(value = "/admin")
@Controller
public class DashboardController {

    private PostDaoInterface postDao;
    private ContentDaoInterface contentDao;
    private CategoryDaoInterface categoryDao;
    private TagDaoInterface tagDao;
    private UserDaoInterface userDao;

//    @Autowired
    @Inject
    public DashboardController(PostDaoInterface postDao, ContentDaoInterface contentDao,
            CategoryDaoInterface categoryDao, TagDaoInterface tagDao, UserDaoInterface userDao) {
        this.postDao = postDao;
        this.contentDao = contentDao;
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = {"/dashboard", "/", "managePosts",""}, method = RequestMethod.GET)
    public String getDashBoardPage() {
        return "dashboard";
    }

    @RequestMapping(value = {"/manageStaticPages"}, method = RequestMethod.GET)
    public String getPagesDashboard(Model model) {
        model.addAttribute("PageType", "StaticPage");
        return "dashboard";
    }

    @RequestMapping(value = "/ajax/getStaticPages/{statusCode}", method = RequestMethod.GET)
    @ResponseBody
    public List<Content> getStaticPagesOfStatus(@PathVariable("statusCode") String statusCode) {
        List<Content> staticPagesOfStatus = contentDao.getStaticPageByStatus(statusCode);
        staticPagesOfStatus.forEach(content -> {
            int contentId = content.getContentId();
            content.setCreatedByUser(userDao.getUserWhoCreatedContent(contentId));
            content.setUpdatedByUser(userDao.getUserWhoUpdatedContent(contentId));
            content.setArchivedByUser(userDao.getUserWhoArchivedContent(contentId));
        });
        return staticPagesOfStatus;
    }

    @RequestMapping(value = "/ajax/getAllPosts/{statusCode}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts(@PathVariable("statusCode") String statusCode) {
        List<Post> listOfAllPosts = postDao.getAllPosts(statusCode);
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

    @RequestMapping(value = "/ajax/edit/getPost/{postId}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/ajax/edit/getContent/{contentId}", method = RequestMethod.GET)
    @ResponseBody
    public Content getContentById(@PathVariable("contentId") int contentId) {

        Content content = contentDao.getContentById(contentId);

        content.setListOfCategories(categoryDao.getCategoriesByContentId(content.getContentId()));
        content.setListOfTags(tagDao.getTagsByContentId(content.getContentId()));

        return content;
    }

    @RequestMapping(value = "/edit/post", method = RequestMethod.GET)
    public String displayAddPostPage(Model model) {
        model.addAttribute("PageType", "post");
        return "edit";
    }

    @RequestMapping(value = "/edit/post/{postId}", method = RequestMethod.GET)
    public String displayEditPostPage(@PathVariable("postId") int postId, Model model) {
        model.addAttribute("postId", postId);
        model.addAttribute("PageType", "post");
        return "edit";
    }

    @RequestMapping(value = "/edit/static", method = RequestMethod.GET)
    public String displayAddStaticPage(Model model) {
        model.addAttribute("PageType", "StaticPage");
        return "edit";
    }

    @RequestMapping(value = "/edit/static/{staticId}", method = RequestMethod.GET)
    public String displayEditStaticPage(@PathVariable("staticId") int staticId, Model model) {
        model.addAttribute("staticId", staticId);
        model.addAttribute("PageType", "StaticPage");
        return "edit";
    }

    @RequestMapping(value = "ajax/isUniqueUrl/{urlPattern}", method = RequestMethod.GET)
    @ResponseBody
    public Content isUniqueUrl(@PathVariable("urlPattern") String urlPattern) {
        // this returns null if there were no Content for given url (actual success)
        // this returns Content if there were Content for given url (fake success)
        return contentDao.getStaticPageByURL(urlPattern);
    }

    @RequestMapping(value = "ajax/addPost", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post addPost(@Valid @RequestBody Post post) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userDao.getUserIdByUsername(username);
        post.getCreatedByUser().setUserId(userId);
        post.getMostRecentContent().getCreatedByUser().setUserId(userId);
        post = postDao.addPost(post);
        post.getMostRecentContent().setPostId(post.getPostId());
        checkUrlPattern(post);
        contentDao.updatePostContent(post.getMostRecentContent());
        return post;
    }

    private void checkUrlPattern(Post post) {
        if (post.getMostRecentContent().getUrlPattern() == null) {
            post.getMostRecentContent().setUrlPattern(String.valueOf(post.getPostId()));
        }
    }

    @RequestMapping(value = "ajax/addContent", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post addContent(@Valid @RequestBody Post post) {
        checkUrlPattern(post);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userDao.getUserIdByUsername(username);
        post.setUpdatedByUser(new User());
        post.getUpdatedByUser().setUserId(userId);
        post.getMostRecentContent().getCreatedByUser().setUserId(userId);
        contentDao.updatePostContent(post.getMostRecentContent());
        post = postDao.updatePost(post);

        return post;
    }

    @RequestMapping(value = "ajax/publishAwaiting", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post publishAwaiting(@Valid @RequestBody Post post) {
        checkUrlPattern(post);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userDao.getUserIdByUsername(username);
        post.setUpdatedByUser(new User());
        post.getUpdatedByUser().setUserId(userId);
        post.getMostRecentContent().getCreatedByUser().setUserId(userId);
        
        contentDao.setAwaitingToArchived(post);
        contentDao.updatePostContent(post.getMostRecentContent());
        post = postDao.updatePost(post);

        return post;
    }

    @RequestMapping(value = "ajax/addStaticPage", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Content addStaticPage(@Valid @RequestBody Content content) {
        return contentDao.addStaticPage(content);
    }

    @RequestMapping(value = "ajax/updateStaticPage/{staticId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStaticPage(@Valid @RequestBody Content content) {
        contentDao.updateStaticPage(content);
    }

    @RequestMapping(value = "ajax/archivePost/{postId}/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void archivePost(@PathVariable("postId") int postID, @PathVariable("userId") int userId) {
        postDao.archivePost(postID, userId);
        contentDao.archivePost(postID, userId);
    }

    @RequestMapping(value = "ajax/archiveContent/{contentId}/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void archiveContent(@PathVariable("contentId") int contentID, @PathVariable("userId") int userId) {
        contentDao.archiveContent(contentID, userId);
    }

    @RequestMapping(value = "/manageTags", method = RequestMethod.GET)
    public String manageTags(Model model) {
        model.addAttribute("categoryTag", "Tags");
        return "tagcategory";
    }

    @RequestMapping(value = "/manageCategories", method = RequestMethod.GET)
    public String manageCategories(Model model) {
        model.addAttribute("categoryTag", "Categories");
        return "tagcategory";
    }

    @RequestMapping(value = "ajax/addTags", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Tag addTag(@Valid @RequestBody String tag) {
        return tagDao.addTag(tag);
    }

    @RequestMapping(value = "ajax/addCategories", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Category addCategory(@Valid @RequestBody String category) {
        return categoryDao.addCategory(category);
    }

    @RequestMapping(value = "ajax/editCategories/{categoryId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editCategory(@Valid @RequestBody String categoryDescription, @PathVariable("categoryId") int categoryId) {
        Category category = new Category();
        category.setCategoryID(categoryId);
        category.setCategoryDescription(categoryDescription);
        categoryDao.updateCategory(category);
    }

    @RequestMapping(value = "ajax/editTags/{tagId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTag(@Valid @RequestBody String tagDescription, @PathVariable("tagId") int tagId) {
        Tag tag = new Tag();
        tag.setTagID(tagId);
        tag.setTagDescription(tagDescription);
        tagDao.updateTag(tag);
    }

    @RequestMapping(value = "ajax/deleteCategories/{categoryId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("categoryId") int categoryId) {
        categoryDao.deleteCategory(categoryId);
    }

    @RequestMapping(value = "ajax/deleteTags/{tagId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTag(@PathVariable("tagId") int tagId) {
        tagDao.deleteTag(tagId);
    }

    @RequestMapping(value = "ajax/getTagsByAlpha/{alphaId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> populateTagsByAlpha(@PathVariable("alphaId") String alphaId) {
        List<Tag> listOfTags = tagDao.getAllTags(false);
        List<Tag> smallList = new ArrayList();

        if (alphaId.equals("all")) {

            smallList = listOfTags;

        } else if (alphaId.equals("num")) {

            for (int x = 0; x < listOfTags.size(); x++) {

                String first = listOfTags.get(x).getTagDescription().substring(0, 1);

                if (first.equals("0") || first.equals("1") || first.equals("2")
                        || first.equals("3") || first.equals("4") || first.equals("5")
                        || first.equals("6") || first.equals("7") || first.equals("8")
                        || first.equals("9")) {

                    smallList.add(listOfTags.get(x));

                }
            }
        } else {
            for (int x = 0; x < listOfTags.size(); x++) {

                String first = listOfTags.get(x).getTagDescription().substring(0, 1);

                if (first.equalsIgnoreCase(alphaId)) {

                    smallList.add(listOfTags.get(x));

                }

            }
        }
        return smallList;
    }

    @RequestMapping(value = "ajax/getCategoriesByAlpha/{alphaId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> populateCategoriesByAlpha(@PathVariable("alphaId") String alphaId) {
        List<Category> listOfCategories = categoryDao.getAllCategories();
        List<Category> smallList = new ArrayList();

        if (alphaId.equals("all")) {

            smallList = listOfCategories;

        } else if (alphaId.equals("num")) {

            for (int x = 0; x < listOfCategories.size(); x++) {

                String first = listOfCategories.get(x).getCategoryDescription().substring(0, 1);

                if (first.equals("0") || first.equals("1") || first.equals("2")
                        || first.equals("3") || first.equals("4") || first.equals("5")
                        || first.equals("6") || first.equals("7") || first.equals("8")
                        || first.equals("9")) {

                    smallList.add(listOfCategories.get(x));

                }
            }
        } else {
            for (int x = 0; x < listOfCategories.size(); x++) {

                String first = listOfCategories.get(x).getCategoryDescription().substring(0, 1);

                if (first.equalsIgnoreCase(alphaId)) {

                    smallList.add(listOfCategories.get(x));

                }

            }
        }
        return smallList;
    }
}
