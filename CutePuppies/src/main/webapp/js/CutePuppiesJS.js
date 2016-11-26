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
            tag: sessionStorage.getItem('selectedTag'),
            category: sessionStorage.getItem('selectedCategory')
        }
    }).success(function (data, status) {
        sessionStorage.setItem('firstPost', data[0].postId);
        sessionStorage.setItem('lastPost', data[data.length-1].postId);
        fillPostSnippetsContainer(data);
    }).error(function (data, status) {
        // display message that data could not be loaded
    });
};

function fillPostSnippetsContainer(posts) {
    clearPostSnippetContainer();
    var postSnippetContainer = $('#allPosts');
    $.each(posts, function(index, post) {
        postSnippetContainer.append($('<div class="singlePost">')
                .append($('<h1 class="title">').text(post.title))
                .append('<p class = "lead userName">').html('by <a href="#">' + post.author + '</a>')
                .append('<hr>')
                .append('<p>').html('<span class="glyphicon glyphicon-time createdOnDate"></span><span>' + post.createdOnDate + '</span>')
                .append('<hr>')
                .append('<img class="img-responsive contentImgLink">').attr({'src': post.imgUrl, 'alt': post.imgAlt})
                .append('<hr>')
                .append('<p class = "body">').html(post.content.body)
                .append('<hr>')
                );
    });
};

function setSessionProperties() {
    if (sessionStorage.getItem('postsPerPage') === undefined) {
        sessionStorage.setItem('postsPerPage', 5);
    }
    if (sessionStorage.getItem('firstPostId') === undefined) {
        sessionStorage.setItem('firstPostId', null);
        sessionStorage.setItem('lastPostId', null);
    }
    sessionStorage.setItem('searchDirection', 'forward');
    sessionStorage.setItem('selectedTag', null);
    sessionStorage.setItem('selectedCategory', null);
};