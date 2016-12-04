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
    sessionStorage.setItem('pageNumber', 1);
    if (pageType === 'StaticPage') {
        $('#sidebarColumn').hide();
        $('#revisionRow').hide();
        $('#mainEditColumn').removeClass('col-md-8').addClass('col-md-12');
        $('#titleText').text('Static Page Title (Required)');
        $('#postTitle').prop('required', true);
        $('#urlText').text('Static Page URL (Required)');
        $('#postURL').prop('required', true);
        $('#bodyText').text('Static Page Body');
        if (staticId !== "") {
            populateStatic();
        }
    } else {
        loadData();
    }
});
function loadData() {
    $('.chosenElement').chosen();
    populateCategories();
    populateTags();
    populateEdit();
}


$(document).on('click', '.revision', function () {

    var conId = $(this).attr("data-content-id");
    $.ajax({
        type: 'GET',
        url: contextPath + '/admin/ajax/edit/getContent/' + conId
    }).success(function (thisContent) {

        $('#contentStatusText').html('<h4><span class="' + thisContent.contentStatusCode + 'TEXT">' + thisContent.contentStatusCode + '</span></h4>');
        $('#postTitle').val(thisContent.title);
        $('#postURL').val(thisContent.urlPattern);
        $('#imageName').val(thisContent.contentImgAltTxt);
        $('#imageURL').val(thisContent.contentImgLink);
        tinyMCE.activeEditor.setContent(thisContent.body);
        highlightTags(thisContent.listOfTags);
        highlightCategories(thisContent.listOfCategories);
        $('#selectCategories').chosen().trigger('chosen:updated');
    });
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
            url: contextPath + '/admin/ajax/archiveContent/' + contentID + '/' + userID,
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
            url: contextPath + '/admin/ajax/archivePost/' + postID + '/' + userID,
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
    $('#selectTags option').removeAttr('selected');
    var tagList = $('#selectTags');
    $.each(listOfTags, function (arrayPosition, tag) {
        $(".tagChosen[data-tagid=" + tag.tagID + "]").prop('selected', true);
    });
    tagList.trigger('chosen:updated');
}

function highlightCategories(listOfCategories) {
    $('#selectCategories option').removeAttr('selected');
    var categoryList = $('#selectCategories');
    $.each(listOfCategories, function (arrayPosition, category) {
        $(".categoryChosen[data-categoryid=" + category.categoryID + "]").prop("selected", true);
    });
    categoryList.trigger('chosen:updated');
}

function populateEdit() {

    if ($('#post-id').length) {

        postID = $('#post-id').val();
        $.ajax({
            type: 'GET',
            url: contextPath + '/admin/ajax/edit/getPost/' + postID
        }).success(function (thisPost) {

            clearContentTable();
            var summaryTable = $('#revisionRows');
            var contentCount = 0;
            $.each(thisPost.allContentRevisions, function (arrayPosition, content) {
                contentCount++;
                var contentCreateDate = new Date(content.createdOnDate);
                var contentCreateDateString = contentCreateDate.toLocaleDateString() + " " + contentCreateDate.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});
                summaryTable.append($('<tr>')
                        .append($('<td>').text(contentCount))
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
                $("#revisionRows").each(function (row, index) {
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

function populateStaticEdit() {
    staticId = $('#static-id').val();

}

function clearContentTable() {
    $('#revisionRows').empty();
}

function populateCategories() {
    $.ajax({
        url: contextPath + '/ajax/getCategories'
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
        url: contextPath + '/ajax/getTags/false'
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
    validateTitleAndUrl(contentStatusCode);
});
$('#saveButton').click(function () {
    var contentStatusCode = 'DRAFT';
    validateTitleAndUrl(contentStatusCode);
});
function validateTitleAndUrl(contentStatusCode) {
    var regexPattern = /^[a-zA-Z0-9_-]*$/i;
    var title = $('#postTitle').val();
    var urlPattern = $('#postURL').val();
    $('#titleEmptyError').empty();
    $('#urlEmptyError').empty();
    if (pageType === 'StaticPage') {
        if (title === "" && urlPattern === "") {
            $('#titleEmptyError').text("Please enter a title.");
            $('#urlEmptyError').text("Please enter a valid URL (only letters, numbers, dashes, underscores)");
        } else if (title === "") {
            $('#titleEmptyError').text("Please enter a title.");
        } else if (urlPattern === "" || regexPattern.test(urlPattern) === false) {
            $('#urlEmptyError').text("Please enter a valid URL (only letters, numbers, dashes, underscores)");
        } else {
            $.ajax({
                type: 'GET',
                url: contextPath + '/admin/ajax/isUniqueUrl/' + urlPattern,
                contentType: 'application/json; charset=utf-8',
                headers: {
                    'Accept': 'application/json',
                    'Content-type': 'application/json'
                },
                dataType: 'json'
            }).success(function (data, status) {
                if (data === null) {
                    addStaticPage(contentStatusCode);
                } else {
                    $('#urlEmptyError').text("That URL Pattern already exists in our database. Please enter another valid URL (only letters, numbers, dashes, underscores.");
                }
            }).error(function (data, status) {
                
            });
        }
    } else {
        if (postID === null || postID === 0) {
            addPost(contentStatusCode);
        } else {
            addContent(contentStatusCode);
        }
    }
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
        url: contextPath + '/admin/ajax/addContent',
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
function addStaticPage(contentStatusCode) {
    var body = tinyMCE.activeEditor.getContent();
    var user = ({
        'userId': 1
    });
    $.ajax({
        type: 'POST',
        url: contextPath + '/admin/ajax/addStaticPage',
        data: JSON.stringify({
            'title': $('#postTitle').val(),
            'contentImgLink': $('#imageURL').val(),
            'contentImgAltTxt': $('#imageName').val(),
            'body': body,
            'contentStatusCode': contentStatusCode,
            'urlPattern': $('#postURL').val(),
            'contentTypeCode': 'STATIC PAGE',
            'createdByUser': user
        }),
        contentType: 'application/json; charset=utf-8',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        dataType: 'json'
    }).success(function (post, status) {
        window.location.assign('/CutePuppies/admin/manageStaticPages');
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
        url: contextPath + '/admin/ajax/addPost',
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
        url: contextPath + "/admin/ajax/addCategories",
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
            if ($(this).data('categoryid') === category.categoryID) {
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
        url: contextPath + "/admin/ajax/addTags",
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
            if ($(this).data('tagid') === tag.tagID) {
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