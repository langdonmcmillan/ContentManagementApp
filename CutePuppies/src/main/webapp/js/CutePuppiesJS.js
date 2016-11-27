/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    setSessionProperties();
    loadAllPosts();
});


function loadAllPosts() {
    $.ajax({
        type: 'GET',
        url: 'getPagePosts/',
        data: {
            newestPostId: sessionStorage.getItem('firstPostId'),
            oldestPostId: sessionStorage.getItem('lastPostId'),
            postsPerPage: sessionStorage.getItem('postsPerPage'),
            direction: sessionStorage.getItem('searchDirection'),
            tagId: sessionStorage.getItem('selectedTagId'),
            categoryId: sessionStorage.getItem('selectedCategoryId')
        }
    }).success(function (data, status) {
        sessionStorage.setItem('firstPostId', data[0].postId);
        sessionStorage.setItem('lastPostId', data[data.length - 1].postId);
        fillPostSnippetsContainer(data);
    }).error(function (data, status) {
        // display message that data could not be loaded
    });
}
;

function fillPostSnippetsContainer(posts) {
//    clearPostSnippetContainer();
    var postSnippetContainer = $('#allPosts');
    postSnippetContainer.empty();
    $.each(posts, function (index, post) {
        var appendInput = '';
        if (!post.createdByUser.userName === post.publishedContent.createdByUser.userName) {
            appendInput = $('<p class = "lead userName">').html('updated by <a href="#">' + post.publishedContent.createdByUser.userName + '</a>');
        }
        postSnippetContainer.append($('<div class="singlePost">'))
                .append($('<h1 class="title">').text(post.publishedContent.title))
                .append($('<p class = "lead userName">').html('created by <a href="#">' + post.createdByUser.userName + '</a>'))
                .append(appendInput)
                .append('<hr>')
                .append($('<p>').html('<span class="glyphicon glyphicon-time createdOnDate"></span><span>' + post.createdOnDate + '</span>'))
                .append('<hr>')
                .append($('<img class="img-responsive contentImgLink">').attr({'src': post.publishedContent.contentImgLink, 'alt': post.publishedContent.contentImgAltTxt}))
                .append('<hr>')
                .append($('<p class = "body">').html(post.publishedContent.body))
                .append('<hr>');

    });
}
;

function setSessionProperties() {
    if (sessionStorage.getItem('postsPerPage') === null) {
        sessionStorage.setItem('postsPerPage', 5);
    }
    if (sessionStorage.getItem('firstPostId') === null) {
        sessionStorage.setItem('firstPostId', 'null');
        sessionStorage.setItem('lastPostId', 'null');
    }
    sessionStorage.setItem('searchDirection', 'forward');
    sessionStorage.setItem('selectedTagId', 'null');
    sessionStorage.setItem('selectedCategoryId', 'null');
}
;