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
    }).succes(function (data, status) {
        fillTableWithAllPosts(data);
    }).error(function (data, status) {
        // some error window pop-up?
    });
}

function fillTaleWithAllPosts(listOfAllPosts) {
    var tBody = $('#populateTable');
}