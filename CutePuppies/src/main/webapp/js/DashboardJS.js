/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadAllPosts();
});

function loadAllPosts() {

    $.ajax({
        type: 'GET',
        url: 'getAllPostsNotArchived'
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
        $.each(post.allContentRevisions, function (index, content) {
            tbody.append($('<tr>')
                    .append($('<td>').text(content.title))
                    .append($('<td>').text(content.createdByUser.userName))
                    .append($('<td>').text(content.createdOnDate))
                    .append($('<td>').text(content.contentStatusCode))
                    .append($('<td>').text('some options...'))
                    );
        });

    });
}
