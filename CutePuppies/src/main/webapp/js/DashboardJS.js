/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var statusCode = $("option:selected", this).val();
    loadContent(statusCode);
});

$('#contentStatusSelect').on('change', function (e) {
    var statusCode = $("option:selected", this).val();
    loadContent(statusCode);
});

function loadContent(statusCode) {
    if (pageType === 'StaticPage') {
        loadStaticPages(statusCode);
    } else {
        loadAllPosts(statusCode);
    }
}

function loadStaticPages(statusCode) {
    $('#listTitle').text('List Of All Static Pages');
    $('#createNewPost').attr("href", contextPath + "/admin/edit/static");
    $('#createNewPost').text('Create New Static Page');
    $.ajax({
        type: 'GET',
        url: contextPath + '/admin/ajax/getStaticPages/' + statusCode
    }).success(function (data, status) {
        fillTableWithStaticPages(data);
    }).error(function (data, status) {

    });

}


function fillTableWithStaticPages(listOfAllStaticPageContent) {

    var tbody = $('#populateTable');
    tbody.empty();
    $.each(listOfAllStaticPageContent, function (index, content) {
        var contentCreateName = content.createdByUser.userName;
        var contentUpdateName = (content.updatedByUser === null) ? '-' : content.updatedByUser.userName;
        var contentCreateDate = new Date(content.createdOnDate);
        var contentUpdateDate = (content.updatedOnDate === null) ? '-' : new Date(content.updatedOnDate);

        var contentCreateDateString = contentCreateDate.toLocaleDateString() + " " + contentCreateDate.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});
        var contentUpdateDateString = (contentUpdateDate === '-') ? '-' : contentUpdateDate.toLocaleDateString() + " " + contentUpdateDate.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});

        if ((contentCreateName === contentUpdateName) && (contentUpdateDateString === contentCreateDateString)) {
            contentUpdateName = '-';
            contentUpdateDateString = '-';
        }

        var staticPgUrl = contextPath + '/admin/edit/static/' + content.contentId;
        tbody.append($('<tr>')

                .append($('<td>').append($('<a>').attr('href', staticPgUrl)
                        .text(content.title)))
                .append($('<td>').addClass('contentCreateName').text(contentCreateName))
                .append($('<td>').addClass('contentCreateDate').text(contentCreateDateString))
                .append($('<td>').addClass('contentUpdateName').text(contentUpdateName))
                .append($('<td>').addClass('contentUpdateUser').text(contentUpdateDateString)));
    });
}

function loadAllPosts(statusCode) {
    $.ajax({
        type: 'GET',
        url: contextPath + '/admin/ajax/getAllPosts/' + statusCode
    }).success(function (data, status) {
        fillTableWithAllPosts(data);
    }).error(function (data, status) {
        // some error window pop-up, or perhaps a redirect to custom error page...
    });
}

function fillTableWithAllPosts(listOfAllPosts) {
    var tbody = $('#populateTable');
    tbody.empty();

    $.each(listOfAllPosts, function (index, post) {
        var contentTitle = post.mostRecentContent.title;
        var postCreateName = post.createdByUser.userName;
        var contentCreateName = post.mostRecentContent.createdByUser.userName;

        var postCreateDate = new Date(post.createdOnDate);
        var contentCreateDate = new Date(post.mostRecentContent.createdOnDate);

        var contentCreateDateString = contentCreateDate.toLocaleDateString() + " " + contentCreateDate.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});
        var postCreateDateString = postCreateDate.toLocaleDateString() + " " + postCreateDate.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});

        if ((postCreateName === contentCreateName) && (postCreateDateString === contentCreateDateString)) {
            contentCreateName = '-';
            contentCreateDateString = '-';
        }
        var url = contextPath + '/admin/edit/post/' + post.postId;
        tbody.append($('<tr>')

                .append($('<td>').append($('<a>').attr('href', url)
                        .text(contentTitle)))
                .append($('<td>').addClass('postUser').text(postCreateName))
                .append($('<td>').addClass('postDate').text(postCreateDateString))
                .append($('<td>').addClass('contentUser').text(contentCreateName))
                .append($('<td>').addClass('contentDate').text(contentCreateDateString)));


    });


    $("tr").click(function () {
        window.location.href = $(this).find("a").attr("href");
    });

}
