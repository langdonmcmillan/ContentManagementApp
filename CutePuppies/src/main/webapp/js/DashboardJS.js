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

    if (this.checked) {
        loadAllPosts(archiveBoxChecked);
    } else {
        loadAllPosts(archiveBoxChecked);
    }

});

function loadAllPosts(archiveBoxChecked) {
    $.ajax({
        type: 'GET',
        url: 'getAllPosts/' + archiveBoxChecked
    }).success(function (data, status) {
        fillTableWithAllPosts(data);
    }).error(function (data, status) {
        // some error window pop-up?
    });
}

function fillTableWithAllPosts(listOfAllPosts) {
    var tbody = $('#populateTable');
    tbody.empty();

    $.each(listOfAllPosts, function (index, post) {
        var arrayOfContent = post.allContentRevisions;
        var contentToDisplay = arrayOfContent[arrayOfContent.length - 1];
        tbody.append($('<tr>')
                .append($('<td>').append($('<a>').attr('href', 'admin/edit/'+ post.postId)
                        .text(contentToDisplay.title)))
                .append($('<td>').text(post.createdByUser.userName))
                .append($('<td>').text(post.createdOnDate))
                .append($('<td>').text(contentToDisplay.createdByUser.userName))
                .append($('<td>').text(contentToDisplay.createdOnDate))
                .append($('<td>').text(contentToDisplay.contentStatusCode))
                );
    });
    $("tr").click(function () {
        window.location.href = $(this).find("a").attr("href");
    });
}
