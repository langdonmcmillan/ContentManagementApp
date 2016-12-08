/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    setSessionProperties();
    updatePageNav(sessionStorage.getItem('pageNumber'));
    if (!$.trim($("#allPosts").html())) {
        loadPagePosts("");

        $('#itemsPerPageSelect').val(sessionStorage.getItem('postsPerPage'));

        $('.pagination li').click(function () {
            var selectedPage = $(this).data('value');
            if (selectedPage != 0) {
                updatePageNav(selectedPage);
                loadPagePosts("");
            }
        });

        $('#itemsPerPageSelect').change(function () {
            sessionStorage.setItem('postsPerPage', $('#itemsPerPageSelect option:selected').val());
            loadPagePosts("");
        });
    }

    $('#tagList').height($('#tagWell').height());
    $('#tagList').width($('#tagWell').width());
    
    

    populateCategories();
    populateTags();
    showStaticPage();
    $('img').addClass('img-responsive');
});

$(document).ajaxComplete(function () {
    $(".ellipsis").dotdotdot({
        ellipsis: '... ',
        height: 150,
        watch: "window",
        after: "a.readmore"
    });
});

function showStaticPage() {
    if (staticPage !== "") {
        $('#allPosts').removeClass('col-md-8').addClass('col-md-12');
    }
}
function loadPagePosts() {
    $('#searchInput').val("");
    $.ajax({
        type: 'GET',
        url: contextPath + '/ajax/getPagePosts/',
        data: {
            pageNumber: sessionStorage.getItem('pageNumber'),
            postsPerPage: sessionStorage.getItem('postsPerPage'),
            tagId: sessionStorage.getItem('selectedTagId'),
            categoryId: sessionStorage.getItem('selectedCategoryId'),
            searchTerm: sessionStorage.getItem('searchTerm'),
            userId: sessionStorage.getItem('postCreatedByUserId')
        }
    }).success(function (data, status) {
        fillPostSnippetsContainer(data);
        $('img').addClass('img-responsive');
    }).error(function (data, status) {
// TODO: display message that data could not be loaded
    });
}
;
function fillPostSnippetsContainer(posts) {
    var postSnippetContainer = $('#allPosts');

    postSnippetContainer.empty();
    $.each(posts, function (index, post) {
        var appendInput = '';
        var postCreateDate = new Date(post.createdOnDate);

        var postCreateDateString = postCreateDate.toLocaleDateString() + " " + postCreateDate.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});
        if (!post.createdByUser.userName === post.publishedContent.createdByUser.userName) {
            appendInput = $('<p class = "lead userName">').html('updated by <a href="#">' + post.publishedContent.createdByUser.userName + '</a>');
        }
        postSnippetContainer.append($('<div class="row singlePost">')
                .append($('<a href="' + contextPath + '/post/' + post.postId + '">')
                        .append($('<h1 class="title readMoreLink">')
                                .text(post.publishedContent.title)
                                .attr({'data-postId': post.postId})))
                .append($('<p class = "lead userName">').html('created by <a href="#" class="authorId" data-userId="' + post.createdByUser.userId + '">' + post.createdByUser.userName + '</a>'))
                .append(appendInput)
                .append('<hr>')
                .append($('<p>')
                        .html('<span class="glyphicon glyphicon-time createdOnDate"></span><span>' + ' ' + postCreateDateString + '</span>'))
                .append($('<div>')
                        .attr('id', 'categories' + post.publishedContent.contentId)
                        .append($('<img>')
                                .attr({
                                    'src': contextPath + '/img/folder.png',
                                    'id': 'folderImg'
                                })))
                .append($('<div>')
                        .attr('id', 'tags' + post.publishedContent.contentId)
                        .append($('<img>').attr('src', contextPath + '/img/tag.png')))
                .append('<hr>')
//                .append($('<a href="post/' + post.postId + '">')
//                        .append($('<img class="img-responsive contentImgLink readMoreLink">')
//                                .attr({
//                                    'src': post.publishedContent.contentImgLink,
//                                    'alt': post.publishedContent.contentImgAltTxt,
//                                    'data-postId': post.postId
//                                })))
//                .append('<hr>')
                .append($('<p class = "body ellipsis">').html(post.publishedContent.body))
                .append($('<a title="read more" class="readmore readMoreLink" href="post/' + post.postId + '">')
                        .text('Read more »')
                        .attr({'data-postId': post.postId})
                        )
                .append('<hr>'));

        var delimiter = "";
        $.each(post.publishedContent.listOfCategories, function (index, category) {
            $('#categories' + post.publishedContent.contentId)
                    .append(delimiter)
                    .append($('<a href="#">' + category.categoryDescription + '</a>')
                            .attr({
                                'class': 'category',
                                'data-categoryDescription': category.categoryDescription,
                                'data-categoryID': category.categoryID
                            }));
            delimiter = ", \u00A0";
        });

        delimiter = "";
        $.each(post.publishedContent.listOfTags, function (index, tag) {
            $('#tags' + post.publishedContent.contentId)
                    .append(delimiter)
                    .append($('<a href="#">' + tag.tagDescription + '</a>')
                            .attr({
                                'class': 'tag',
                                'data-tagDescription': tag.tagDescription,
                                'data-tagID': tag.tagID
                            }).append(' '));
            delimiter = ", \u00A0";
        });
    });


}
;

function setSessionProperties() {
    if (sessionStorage.getItem('postsPerPage') === null) {
        sessionStorage.setItem('postsPerPage', 5);
    }
    if (sessionStorage.getItem('pageNumber') === null) {
        sessionStorage.setItem('pageNumber', 1);
    }
    if (sessionStorage.getItem('selectedTagId') === null) {
        sessionStorage.setItem('selectedTagId', 'null');
    }
    if (sessionStorage.getItem('selectedCategoryId') === null) {
        sessionStorage.setItem('selectedCategoryId', 'null');
    }
    if (sessionStorage.getItem('postCreatedByUserId') === null) {
        sessionStorage.setItem('postCreatedByUserId', 'null');
    }
    if (sessionStorage.getItem('searchTerm') === null) {
        sessionStorage.setItem('searchTerm', '');
    }
}

function updatePageNav(selectedPage) {
    sessionStorage.setItem('pageNumber', selectedPage);
    var prevPage = sessionStorage.getItem('pageNumber') - 1;
    var nextPage = parseInt(sessionStorage.getItem('pageNumber')) + 1;
    $('#prevPageButton').data('value', prevPage);
    $('#nextPageButton').data('value', nextPage);
    $('#prevPageButton').removeClass('disabled');
    if (prevPage == 0) {
        $('#prevPageButton').addClass('disabled');
    }
    $('.pagination').children('li').removeClass('active');
    $('.pgNum[data-value=' + selectedPage + ']').addClass('active');
//    loadPagePosts();
}

function populateCategories() {
    $.ajax({
        url: contextPath + '/ajax/getCategories'
    }).success(function (data, status) {
        $.each(data, function (index, category) {
            $("#categoryList").append($('<a href="#"' + category.categoryID + '>')
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
        url: contextPath + '/ajax/getTags/true'
    }).success(function (data, status) {
        $.each(data, function (index, tag) {
            $("#tagList").append($('<li data-weight="' + tag.numUsed + '"><a href="#">' + tag.tagDescription + '</a></li>')
                    .attr({
                        'class': 'tag',
                        'data-tagDescription': tag.tagDescription,
                        'data-tagID': tag.tagID
                    }).append());
        });
        $("#tagList").tx3TagCloud({
            multiplier: 1
        });
    });
}

$(document).on('click', '.category', function () {
    var categoryId = $(this).data('categoryid');
    sessionStorage.setItem('selectedTagId', 'null');
    sessionStorage.setItem('selectedCategoryId', $(this).data('categoryid'));
    sessionStorage.setItem('searchTerm', '');
    sessionStorage.setItem('postCreatedByUserId', 'null');
    updatePageNav(1);
    window.location.replace(contextPath);
});

$(document).on('click', '.tag', function () {
    var tagId = $(this).data('tagid');
    sessionStorage.setItem('selectedTagId', $(this).data('tagid'));
    sessionStorage.setItem('selectedCategoryId', 'null');
    sessionStorage.setItem('searchTerm', '');
    sessionStorage.setItem('postCreatedByUserId', 'null');
    updatePageNav(1);
    window.location.replace(contextPath);
});

$(document).on('click', '.authorId', function () {
    var userId = $(this).data('userid');
    sessionStorage.setItem('postCreatedByUserId', userId);
    sessionStorage.setItem('selectedTagId', 'null');
    sessionStorage.setItem('selectedCategoryId', 'null');
    sessionStorage.setItem('searchTerm', '');
    updatePageNav(1);
    window.location.replace(contextPath);
});

$('#searchButton').click(function () {
    sessionStorage.setItem('searchTerm', $('#searchInput').val());
    updatePageNav(1);
    window.location.replace(contextPath);
});

$('.homeLink').click(function () {
    updatePageNav(1);
    resetSessionProperties();
    window.location.replace(contextPath);
});

$('#commentModal').on('show.bs.modal', function () {
  resetCaptcha();
});

function checkCaptcha() {
    var answer = $('#captchaAnswer').val();
    if (answer == sessionStorage.getItem('captchaSum')) {
        saveUserComment();
        clearModalFields();
        $('#commentModal').modal('toggle');
    } else {
        resetCaptcha();
        alert('answer is wrong!');
    }
}

function resetCaptcha() {
    $('#captchaAnswer').val('');
    sessionStorage.setItem('captchaNum1', Math.floor((Math.random() * 10) + 1));
    sessionStorage.setItem('captchaNum2', Math.floor((Math.random() * 10) + 1));
    sessionStorage.setItem('captchaQuestion', 'Enter the sum of ' + sessionStorage.getItem('captchaNum1') + ' and ' + sessionStorage.getItem('captchaNum2') + ':');
    sessionStorage.setItem('captchaSum', parseInt(sessionStorage.getItem('captchaNum1'))+parseInt(sessionStorage.getItem('captchaNum2')));
    $('#captchaQuestion').text(sessionStorage.getItem('captchaQuestion'));
    
}

function saveUserComment() {
    $.ajax({
        type: 'POST',
        url: contextPath + '/comments/ajax/addComment',
        data: JSON.stringify({
            postId: postId,
            createdByUser: {userName: $('#commentorName').val()},
            body: tinyMCE.activeEditor.getContent()
        }),
        contentType: 'application/json; charset=utf-8',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        dataType: 'json'
    }).success(function () {
        window.location.replace(contextPath + '/post/' + postId);
    });
}

function clearModalFields() {
    $('#commentorName').val('');
    tinyMCE.activeEditor.setContent('');
}

function archiveComment(contentId) {
    window.location.replace(contextPath + '/comments/archiveComment/' + postId + '/' + contentId);
}