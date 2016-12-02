/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    var archiveBoxChecked = $('#showArchivedPosts').is(':checked');
    loadAllPosts(archiveBoxChecked);
    sessionStorage.setItem('pageNumber', 1);
});

$('#pagesDashboard').click(function () {
    window.location.href = $(this).find("a").attr("href");
});

$("#showArchivedPosts").change(function () {
    var archiveBoxChecked = $('#showArchivedPosts').is(':checked');
    loadAllPosts(archiveBoxChecked);
});

function loadAllPosts(archiveBoxChecked) {
    $.ajax({
        type: 'GET',
        url: 'getAllPosts/' + archiveBoxChecked
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

        var contentCreateDateString =
                contentCreateDate.getUTCFullYear() + "/" +
                ("0" + (contentCreateDate.getUTCMonth() + 1)).slice(-2) + "/" +
                ("0" + contentCreateDate.getUTCDate()).slice(-2) + " " +
                ("0" + contentCreateDate.getUTCHours()).slice(-2) + ":" +
                ("0" + contentCreateDate.getUTCMinutes()).slice(-2);

        var postCreateDateString =
                postCreateDate.getUTCFullYear() + "/" +
                ("0" + (postCreateDate.getUTCMonth() + 1)).slice(-2) + "/" +
                ("0" + postCreateDate.getUTCDate()).slice(-2) + " " +
                ("0" + postCreateDate.getUTCHours()).slice(-2) + ":" +
                ("0" + postCreateDate.getUTCMinutes()).slice(-2);

        if ((postCreateName === contentCreateName) && (postCreateDateString) === contentCreateDateString) {
            contentCreateName = '-';
            contentCreateDateString = '-';
        }
        tbody.append($('<tr>')

                .append($('<td>').append($('<a>').attr('href', 'edit/' + post.postId)
                        .text(contentTitle)))
                .append($('<td>').addClass('postUser').text(postCreateName))
                .append($('<td>').addClass('postDate').text(postCreateDateString))
                .append($('<td>').addClass('contentUser').text(contentCreateName))
                .append($('<td>').addClass('contentDate').text(contentCreateDateString)));


    });


    $("tr").click(function () {
        window.location.href = $(this).find("a").attr("href");
    });
    
    $('#')
}
