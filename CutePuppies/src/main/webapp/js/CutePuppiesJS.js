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
        url: 'getAllPosts',
        type: 'GET'
    }).success(function (data, status) {
        // fill posts here
    });
}