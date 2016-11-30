/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    setSessionProperties();
    updatePageNav(sessionStorage.getItem('pageNumber'));
    if (!$.trim($("#allPosts").html())) {
        loadPagePosts();

        $('#itemsPerPageSelect').val(sessionStorage.getItem('postsPerPage'));

        $('.pagination li').click(function () {
            var selectedPage = $(this).data('value');
            if (selectedPage != 0) {
                updatePageNav(selectedPage);
                loadPagePosts();
            }
        });

        $('#itemsPerPageSelect').change(function () {
            sessionStorage.setItem('postsPerPage', $('#itemsPerPageSelect option:selected').val());
            loadPagePosts();
        });
    }

    $('#tagList').height($('#tagWell').height());
    $('#tagList').width($('#tagWell').width());

    populateCategories();
    populateTags();
});

$(document).ajaxComplete(function () {
    $(".ellipsis").dotdotdot({
        ellipsis: '... ',
        height: 150,
        watch: "window",
        after: "a.readmore"
    });
});

function loadPagePosts() {
    $.ajax({
        type: 'GET',
        url: 'getPagePosts/',
        data: {
            pageNumber: sessionStorage.getItem('pageNumber'),
            postsPerPage: sessionStorage.getItem('postsPerPage'),
            tagId: sessionStorage.getItem('selectedTagId'),
            categoryId: sessionStorage.getItem('selectedCategoryId')
        }
    }).success(function (data, status) {
        fillPostSnippetsContainer(data);
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
        if (!post.createdByUser.userName === post.publishedContent.createdByUser.userName) {
            appendInput = $('<p class = "lead userName">').html('updated by <a href="#">' + post.publishedContent.createdByUser.userName + '</a>');
        }
        postSnippetContainer.append($('<div class="singlePost">')
                .append($('<a href="post/' + post.postId + '">')
                        .append($('<h1 class="title readMoreLink">')
                                .text(post.publishedContent.title)
                                .attr({'data-postId': post.postId})))
                .append($('<p class = "lead userName">').html('created by <a href="#">' + post.createdByUser.userName + '</a>'))
                .append(appendInput)
                .append('<hr>')
                .append($('<p>')
                        .html('<span class="glyphicon glyphicon-time createdOnDate"></span><span>' + new Date(post.createdOnDate) + '</span>')))
                .append($('<div>')
                        .attr('id', 'categories' + post.publishedContent.contentId)
                        .append($('<img>')
                                .attr({
                                    'src': 'img/folder.png',
                                    'id': 'folderImg'
                                })))
                .append($('<div>')
                        .attr('id', 'tags' + post.publishedContent.contentId)
                        .append($('<img>').attr('src', 'img/tag.png')))
                .append('<hr>')
                .append($('<a href="post/' + post.postId + '">')
                        .append($('<img class="img-responsive contentImgLink readMoreLink">')
                                .attr({
                                    'src': post.publishedContent.contentImgLink,
                                    'alt': post.publishedContent.contentImgAltTxt,
                                    'data-postId': post.postId
                                })))
                .append('<hr>')
                .append($('<p class = "body ellipsis">').html(post.publishedContent.body))
                .append($('<a title="read more" class="readmore readMoreLink" href="post/' + post.postId + '">')
                        .text('Read more »')
                        .attr({'data-postId': post.postId})
                        )
                .append('<hr>');


        $.each(post.publishedContent.listOfCategories, function (index, category) {
            $('#categories' + post.publishedContent.contentId)
                    .append($('<a href="#">' + category.categoryDescription + '</a>')
                            .attr({
                                'class': 'category',
                                'data-categoryDescription': category.categoryDescription,
                                'data-categoryID': category.categoryID
                            }).append(' '));
        });

        $.each(post.publishedContent.listOfTags, function (index, tag) {
            $('#tags' + post.publishedContent.contentId)
                    .append($('<a href="#">' + tag.tagDescription + '</a>')
                            .attr({
                                'class': 'tag',
                                'data-tagDescription': tag.tagDescription,
                                'data-tagID': tag.tagID
                            }).append(' '));
        });
    });


}
;

function resetSessionProperties() {
    sessionStorage.setItem('postsPerPage', 5);
    sessionStorage.setItem('pageNumber', 1);
    sessionStorage.setItem('selectedTagId', 'null');
    sessionStorage.setItem('selectedCategoryId', 'null');
}

function setSessionProperties() {
    if (sessionStorage.getItem('postsPerPage') === null) {
        sessionStorage.setItem('postsPerPage', 5);
    }
    if (sessionStorage.getItem('pageNumber') === null) {
        sessionStorage.setItem('pageNumber', 1);
    }
    sessionStorage.setItem('selectedTagId', 'null');
    sessionStorage.setItem('selectedCategoryId', 'null');
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
        url: 'categories'
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
        url: 'tags'
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
    updatePageNav(1);
    loadPagePosts();
});

$(document).on('click', '.tag', function () {
    var tagId = $(this).data('tagid');
    sessionStorage.setItem('selectedTagId', $(this).data('tagid'));
    sessionStorage.setItem('selectedCategoryId', 'null');
    updatePageNav(1);
    loadPagePosts();
});