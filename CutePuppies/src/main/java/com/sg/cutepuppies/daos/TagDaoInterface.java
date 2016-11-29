/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cutepuppies.daos;

import com.sg.cutepuppies.models.Tag;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface TagDaoInterface {
    public List<Tag> getAllTags(boolean onlyPublished);
    public List<Tag> getTagsByContentId(int contentId);
    public Tag getTagByName(String tag);
    public Tag addTag(String tag);
    public Tag updateTag(Tag tag);
    public void deleteTag(int tagID);
}
