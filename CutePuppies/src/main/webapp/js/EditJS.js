/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var postID;

$(document).ready(function () {
    populateCategories();
    populateTags();
    postID = 0;
});

function populateCategories() {
    $.ajax({
        url: 'categories'
    }).success(function (data, status) {
        $.each(data, function (index, category) {
            $("#categoryList").append($('<a href="#">')
                    .attr({
                        'class': 'category',
                        'data-categoryDescription': category.categoryDescription,
                        'data-categoryID': category.categoryID
                    }).append(category.categoryDescription));
        });
    });
}

function populateTags() {
    $.ajax({
        url: 'tags'
    }).success(function (data, status) {
        $.each(data, function (index, tag) {
            $("#tagList").append($('<a href="#">')
                    .attr({
                        'class': 'tag',
                        'data-tagDescription': tag.tagDescription,
                        'data-tagID': tag.tagID
                    }).append(tag.tagDescription));
        });
    });
}

$(document).on('click', '.category', function () {
    $(this).toggleClass('selected');
});

$(document).on('click', '.tag', function () {
    $(this).toggleClass('selected');
});

$('#publishButton').click(function () {
    var contentStatusCode = 'PUBLISHED';
    if (postID === null || postID === 0) {
        addPost(contentStatusCode);
    } else {
        // editPost
    }
});

$('#saveButton').click(function () {
    var contentStatusCode = 'DRAFT';
    if (postID === null || postID === 0) {
        addPost(contentStatusCode);
    } else {
        // editPost
    }
});

function addPost(contentStatusCode) {
    var categoryList = [];
    $('.category.selected').each(function () {
        categoryList.push({
            'categoryID': $(this).data('categoryid'),
            'categoryDescription': $(this).data('categorydescription')
        });
    });
    var tagList = [];
    $('.tag.selected').each(function () {
        tagList.push({
            'tagID': $(this).data('tagid'),
            'tagDescription': $(this).data('tagdescription')
        });
    });
    var body = tinyMCE.activeEditor.getContent();
    var contentRevisionsList = [];
    contentRevisionsList.push({
        'title': $('#postTitle').val(),
        'contentImgLink': $('#imageURL').val(),
        'contentImgAltTxt': $('#imageName').val(),
        'body': body,
        'contentStatusCode': contentStatusCode,
        'urlPattern': $('#postURL').val(),
        'contentTypeCode': 'POST',
        'createdByUserId': 1,
        'listOfCategories': categoryList,
        'listOfTags': tagList
    });
    $.ajax({
        type: 'POST',
        url: 'post',
        data: JSON.stringify({
            createdByUserId: 1,
            allContentRevisions: contentRevisionsList
        }),
        contentType: 'application/json; charset=utf-8',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        dataType: 'json'
    }).success(function (post, status) {
        postID = post.postId;
        window.location.assign('/CutePuppies/dashboard');
    }).error(function (post, status) {

    });
}