/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var type;
$(document).ready(function () {
    type = $("#tagOrCategory").text();
    if (type === "Tags") {
        populateTags();
    } else {
        populateCategories();
    }
});


function populateCategories() {
    var row;
    $.ajax({
        url: contextPath + '/categories'
    }).success(function (data, status) {
        $.each(data, function (index, category) {
            row =  $("<tr>");
            $("#tableBody").append(row);
            $(row).append($('<td>')
                    .attr({
                        'class': 'category',
                        'data-categoryDescription': category.categoryDescription,
                        'data-categoryID': category.categoryID
                    }).text(category.categoryDescription))
            .append($('<td>').html('<a href="#" class="editLink"><span class="glyphicon glyphicon-pencil"></span></a>'))
            .append($('<td>').html('<a href="#" class="deleteLink"><span class="glyphicon glyphicon-trash"></span></a>'));
        });
    });
}

function populateTags() {
    var row;
    $.ajax({
        url: contextPath + '/tags'
    }).success(function (data, status) {
        $.each(data, function (index, tag) {
            row =  $("<tr>");
            $("#tableBody").append(row);
            $(row).append($('<td>')
                    .attr({
                        'class': 'tag',
                        'data-tagDescription': tag.tagDescription,
                        'data-tagID': tag.tagID
                    }).text(tag.tagDescription))
            .append($('<td>').html('<a href="#" class="editLink"><span class="glyphicon glyphicon-pencil"></span></a>'))
            .append($('<td>').html('<a href="#" class="deleteLink"><span class="glyphicon glyphicon-trash"></span></a>'));
        });
    });
}