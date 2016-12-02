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
    populateCategories();
    populateTags();
    populateEdit();
    sessionStorage.setItem('pageNumber', 1);
});

$(document).on('click', '.category', function () {
    $(this).toggleClass('selected');
});

$(document).on('click', '.tag', function () {
    $(this).toggleClass('selected');
});

$(document).on('click', '.revision', function () {

    var conId = $(this).attr("data-content-id");

    $.ajax({
        type: 'GET',
        url: 'post/' + $('#post-id').val() + "/" + conId
    }).success(function (thisContent) {

        $('#contentStatusText').html('<h4><span class="' + thisContent.contentStatusCode + 'TEXT">' + thisContent.contentStatusCode + '</span></h4>');
        $('#postTitle').val(thisContent.title);
        $('#postURL').val(thisContent.urlPattern);
        $('#imageName').val(thisContent.contentImgAltTxt);
        $('#imageURL').val(thisContent.contentImgLink);
        tinyMCE.activeEditor.setContent(thisContent.body);

        highlightTags(thisContent.listOfTags);
        highlightCategories(thisContent.listOfCategories);

    });
});

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

function highlightTags(listOfTags) {
    $("a.tag").removeClass("selected");
    $.each(listOfTags, function (arrayPosition, tag) {

        $("a.tag[data-tagid=" + tag.tagID + "]").addClass("selected");

    });
}

function highlightCategories(listOfCategories) {
    $("a.category").removeClass("selected");
    $.each(listOfCategories, function (arrayPosition, category) {

        $("a.category[data-categoryid=" + category.categoryID + "]").addClass("selected");

    });
}

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
                                            'class': 'revision',
                                            'data-content-id': content.contentId
                                        })
                                        .text(content.title)))
                        .append($('<td>').text(contentCreateDateString))
                        .append($('<td>').text(content.createdByUser.userName))
                        .append($('<td class="contentStatusCode">').text(content.contentStatusCode))
                        );
            });

            $(function () {
                $("#contentRows").each(function (row, index) {
                    var arr = $.makeArray($("tr", this).detach());
                    arr.reverse();
                    $(this).append(arr);
                });
            });

            $('#contentStatusText').html('<h4><span class="' + thisPost.mostRecentContent.contentStatusCode + 'TEXT">' + thisPost.mostRecentContent.contentStatusCode + '</span></h4>');
            $('#postTitle').val(thisPost.mostRecentContent.title);
            $('#postURL').val(thisPost.mostRecentContent.urlPattern);
            $('#imageName').val(thisPost.mostRecentContent.contentImgAltTxt);
            $('#imageURL').val(thisPost.mostRecentContent.contentImgLink);
            tinyMCE.activeEditor.setContent(thisPost.mostRecentContent.body);
            contentID = thisPost.mostRecentContent.contentId;

            highlightTags(thisPost.mostRecentContent.listOfTags);
            highlightCategories(thisPost.mostRecentContent.listOfCategories);

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
        $("#categoryList").empty();
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
        url: contextPath + '/tags'
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

function checkIfAllArchived() {
    var nonArchived = 0;
    $('.contentStatusCode').each(function () {
        if ($(this).text() !== "ARCHIVED") {
            nonArchived++;
        }
    });
    return (nonArchived <= 1);
}

function addContent(contentStatusCode) {
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

