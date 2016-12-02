/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var postID;
var contentID;
var userID;

$(document).ready(function () {
    postID = 0;
    contentID = 0;
    userID = 1;
    $('.chosenElement').chosen();
    populateCategories();
    populateTags();
    populateEdit();
    sessionStorage.setItem('pageNumber', 1);
});

function populateEdit() {

    if ($('#post-id').length) {

        postID = $('#post-id').val();
        $.ajax({
            type: 'GET',
            url: 'post/' + $('#post-id').val()
        }).success(function (thisPost) {

            clearContentTable();

            var summaryTable = $('#contentRows');

            $.each(thisPost.allContentRevisions, function (arrayPosition, content) {
                var contentCreateDate = new Date(content.createdOnDate);
                var contentCreateDateString =
                        contentCreateDate.getUTCFullYear() + "/" +
                        ("0" + (contentCreateDate.getUTCMonth() + 1)).slice(-2) + "/" +
                        ("0" + contentCreateDate.getUTCDate()).slice(-2) + " " +
                        ("0" + contentCreateDate.getUTCHours()).slice(-2) + ":" +
                        ("0" + contentCreateDate.getUTCMinutes()).slice(-2);
                summaryTable.append($('<tr>')
                        .append($('<td>')
                                .append($('<a>')
                                        .attr({
                                            'data-contact-id': content.contentId
                                        })
                                        .text(content.title)))
                        .append($('<td>').text(contentCreateDateString))
                        .append($('<td>').text(content.createdByUser.userName))
                        .append($('<td class="contentStatusCode">').text(content.contentStatusCode))
                        );
            });

            $('#contentStatusText').html('<h4><span class="' + thisPost.mostRecentContent.contentStatusCode + 'TEXT">' + thisPost.mostRecentContent.contentStatusCode + '</span></h4>');
            $('#postTitle').val(thisPost.mostRecentContent.title);
            $('#postURL').val(thisPost.mostRecentContent.urlPattern);
            $('#imageName').val(thisPost.mostRecentContent.contentImgAltTxt);
            $('#imageURL').val(thisPost.mostRecentContent.contentImgLink);
            tinyMCE.activeEditor.setContent(thisPost.mostRecentContent.body);
            contentID = thisPost.mostRecentContent.contentId;

            $.each(thisPost.mostRecentContent.listOfTags, function (arrayPosition, tag) {

                $(".tag").each(function () {
                    if ($(this).data('tagid') === tag.tagID) {

                        $(this).toggleClass('selected');
                    }
                });
            });

            $.each(thisPost.mostRecentContent.listOfCategories, function (arrayPosition, category) {

                $(".category").each(function () {
                    if ($(this).data('categoryid') === category.categoryID) {

                        $(this).toggleClass('selected');
                    }
                });
            });

        });
    }
}

function clearContentTable() {
    $('#contentRows').empty();
}

function populateCategories() {
    $.ajax({
        url: contextPath + '/categories'
    }).success(function (data, status) {
        var categoryList = $('#selectCategories');
        categoryList.empty();
        $.each(data, function (index, category) {
            categoryList.append($('<option>')
                    .attr({
                        'data-categoryDescription': category.categoryDescription,
                        'data-categoryid': category.categoryID,
                        'class': 'categoryChosen'
                    })
                    .text(category.categoryDescription));
        });
        categoryList.trigger('chosen:updated');
    });

}

function populateTags() {
    $.ajax({
        url: contextPath + '/tags/false'
    }).success(function (data, status) {
        var tagList = $('#selectTags');
        tagList.empty();
        $.each(data, function (index, tag) {
            tagList.append($('<option>')
                    .attr({
                        'data-tagDescription': tag.tagDescription,
                        'data-tagid': tag.tagID,
                        'class': 'tagChosen'
                    })
                    .text(tag.tagDescription));
        });
        tagList.trigger('chosen:updated');
    });
}

$('#publishButton').click(function () {
    var contentStatusCode = 'PUBLISHED';
    if (postID === null || postID === 0) {
        addPost(contentStatusCode);
    } else {

        addContent(contentStatusCode);

    }
});

$('#saveButton').click(function () {
    var contentStatusCode = 'DRAFT';
    if (postID === null || postID === 0) {
        addPost(contentStatusCode);
    } else {

        addContent(contentStatusCode);
    }
});

$('#deleteButton').click(function () {
    if (postID === null || postID === 0) {
        window.location.assign('/CutePuppies/admin/dashboard');
    } else if (checkIfAllArchived()) {
        if (confirm('This post will be archived if this content is archived. Continue?')) {
            $('#deletePostButton').click();
        }
    } else {
        $.ajax({
            type: 'PUT',
            url: 'content/' + contentID + '/' + userID,
            contentType: 'application/json; charset=utf-8',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            dataType: 'json'
        }).success(function (post, status) {
            window.location.assign('/CutePuppies/admin/edit/' + postID);
        }).error(function (post, status) {

        });
    }
});

function checkIfAllArchived() {
    var nonArchived = 0;
    $('.contentStatusCode').each(function () {
        if ($(this).text() !== "ARCHIVED") {
            nonArchived++;
        }
    });
    return (nonArchived <= 1);
}

$('#deletePostButton').click(function () {
    if (postID === null || postID === 0) {
        window.location.assign('/CutePuppies/admin/dashboard');
    } else {
        $.ajax({
            type: 'PUT',
            url: 'post/' + postID + '/' + userID,
            contentType: 'application/json; charset=utf-8',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            dataType: 'json'
        }).success(function (post, status) {
            window.location.assign('/CutePuppies/admin/dashboard');
        }).error(function (post, status) {

        });
    }
});

function addContent(contentStatusCode) {
    var categoryList = [];
    $('.categoryChosen:selected').each(function () {
        categoryList.push({
            'categoryID': $(this).data('categoryid'),
            'categoryDescription': $(this).data('categorydescription')
        });
    });
    var tagList = [];
    $('.tagChosen:selected').each(function () {
        tagList.push({
            'tagID': $(this).data('tagid'),
            'tagDescription': $(this).data('tagdescription')
        });
    });
    var body = tinyMCE.activeEditor.getContent();
    var user = ({
        'userId': 1
    });
    var newContent = ({
        'title': $('#postTitle').val(),
        'contentImgLink': $('#imageURL').val(),
        'contentImgAltTxt': $('#imageName').val(),
        'body': body,
        'contentStatusCode': contentStatusCode,
        'urlPattern': $('#postURL').val(),
        'contentTypeCode': 'POST',
        'createdByUser': user,
        'listOfCategories': categoryList,
        'listOfTags': tagList,
        'postId': $('#post-id').val()
    });
    $.ajax({
        type: 'POST',
        url: 'content',
        data: JSON.stringify({
            createdByUser: user,
            mostRecentContent: newContent
        }),
        contentType: 'application/json; charset=utf-8',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        dataType: 'json'
    }).success(function (post, status) {
        postID = post.postId;
        window.location.assign('/CutePuppies/admin/dashboard');
    }).error(function (post, status) {

    });
}

function addPost(contentStatusCode) {
    var categoryList = [];
    $('.categoryChosen:selected').each(function () {
        categoryList.push({
            'categoryID': $(this).data('categoryid'),
            'categoryDescription': $(this).data('categorydescription')
        });
    });
    var tagList = [];
    $('.tagChosen:selected').each(function () {
        tagList.push({
            'tagID': $(this).data('tagid'),
            'tagDescription': $(this).data('tagdescription')
        });
    });
    var body = tinyMCE.activeEditor.getContent();
    var user = ({
        'userId': 1
    });
    var newContent = ({
        'title': $('#postTitle').val(),
        'contentImgLink': $('#imageURL').val(),
        'contentImgAltTxt': $('#imageName').val(),
        'body': body,
        'contentStatusCode': contentStatusCode,
        'urlPattern': $('#postURL').val(),
        'contentTypeCode': 'POST',
        'createdByUser': user,
        'listOfCategories': categoryList,
        'listOfTags': tagList
    });
    $.ajax({
        type: 'POST',
        url: 'post',
        data: JSON.stringify({
            createdByUser: user,
            mostRecentContent: newContent
        }),
        contentType: 'application/json; charset=utf-8',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        dataType: 'json'
    }).success(function (post, status) {
        postID = post.postId;
        window.location.assign('/CutePuppies/admin/dashboard');
    }).error(function (post, status) {

    });
}

$('#addCategoryButton').click(function () {
    var categoryDescription = $('#addCategoryInput').val();
    $.ajax({
        url: contextPath + "/admin/Categories",
        type: "POST",
        data: categoryDescription,
        contentType: 'application/json; charset=utf-8',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        dataType: 'json'
    }).success(function (category, status) {
        var alreadyExists = false;
        var categoryList = $('#selectCategories');
        $('.categoryChosen').each(function () {
            if ($(this).data('categoryid') == category.categoryID) {
                $(this).attr('selected', true);
                alreadyExists = true;
            }
        });
        if (!alreadyExists) {
            categoryList.append($('<option selected>')
                    .attr({
                        'data-categoryDescription': category.categoryDescription,
                        'data-categoryid': category.categoryID,
                        'class': 'categoryChosen'
                    })
                    .text(category.categoryDescription));
            categoryList.trigger('chosen:updated');
        }
        categoryList.trigger('chosen:updated');
        $('#addCategoryInput').val('');
    }).error(function (data, status) {

    });
});

$('#addTagButton').click(function () {
    var tagDescription = $('#addTagInput').val();
    $.ajax({
        url: contextPath + "/admin/Tags",
        type: "POST",
        data: tagDescription,
        contentType: 'application/json; charset=utf-8',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        dataType: 'json'
    }).success(function (tag, status) {
        var alreadyExists = false;
        var tagList = $('#selectTags');
        $('.tagChosen').each(function () {
            if ($(this).data('tagid') == tag.tagID) {
                $(this).attr('selected', true);
                alreadyExists = true;
            }
        });
        if (!alreadyExists) {
            tagList.append($('<option selected>')
                    .attr({
                        'data-tagDescription': tag.tagDescription,
                        'data-tagid': tag.tagID,
                        'class': 'tagChosen'
                    })
                    .text(tag.tagDescription));
            tagList.trigger('chosen:updated');
        }
        tagList.trigger('chosen:updated');
        $('#addTagInput').val('');
    }).error(function (data, status) {

    });
});

