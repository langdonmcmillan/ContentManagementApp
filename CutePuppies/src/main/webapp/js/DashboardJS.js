/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    var archiveBoxChecked = $('#showArchivedPosts').is(':checked');
    loadAllPosts(archiveBoxChecked);
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
        var postCreateName = post.createdByUser.userName;
        var postCreateDate = post.createdOnDate;
        var contentTitle = post.mostRecentContent.title;
        var contentCreateName = post.mostRecentContent.createdByUser.userName;
        var contentCreateDate = post.mostRecentContent.createdOnDate;
        
        if ((postCreateName === contentCreateName) && (postCreateDate === contentCreateDate)) {
            $("td.contentUser").text('-');
            $("td.contentDate").text('-');
        }
        tbody.append($('<tr>')
                .append($('<td>').append($('<a>').attr('href', 'admin/edit/' + post.postId)
                        .text(contentTitle)))
                .append($('<td>').addClass('postUser').text(postCreateName))
                .append($('<td>').addClass('postDate').text(postCreateDate))
                .append($('<td>').addClass('contentUser').text(contentCreateName))
                .append($('<td>').addClass('contentDate').text(contentCreateDate)));
    });
    $("tr").click(function () {
        window.location.href = $(this).find("a").attr("href");
    });
}
