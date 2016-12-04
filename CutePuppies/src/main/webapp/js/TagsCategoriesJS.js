/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var type;
$(document).ready(function () {
    type = $("#tagOrCategory").text();
    loadData();
    setTableHeight();
});

function loadData() {
    $("#tableBody").empty();
    if (type === "Tags") {
        populateTags();
    } else {
        populateCategories();
    }
}

function setTableHeight() { 
    var maxHeight = $(window).height() - 120;
    $('#editTable').css('max-height', maxHeight); //set max height
}

$(window).on('resize', function(){
    setTableHeight();
});

function populateCategories() {
    var row;
    $.ajax({
        url: contextPath + '/ajax/getCategories'
    }).success(function (data, status) {
        $.each(data, function (index, category) {
            row = $("<tr id='row" + category.categoryID + "'>");
            $("#tableBody").append(row);
            $(row).append($('<th>')
                    .html('<span id="cell' + category.categoryID + '">' + category.categoryDescription + '</span>' +
                            '<input id="input' + category.categoryID + '" type="text" class="edit editInput form-control" value="' + category.categoryDescription + '">'))
                    .append($('<td>')
                            .attr({
                                'data-description': category.categoryDescription,
                                'data-id': category.categoryID
                            })
                            .html('<a href="#" class="view editLink"><span class="glyphicon glyphicon-pencil"></span></a>\n\
                                   <a href="#" class="edit saveLink"><span class="glyphicon glyphicon-ok"></span></a>'))
                    .append($('<td>')
                            .attr({
                                'data-description': category.categoryDescription,
                                'data-id': category.categoryID
                            })
                            .html('<a href="#" class="view deleteLink"><span class="glyphicon glyphicon-trash"></span></a>\n\
                                   <a href="#" class="edit cancelLink"><span class="glyphicon glyphicon-remove"></span></a>'));
            showView(category.categoryID);
        });
    });
}

function populateTags() {
    var row;
    $.ajax({
        url: contextPath + '/ajax/getTags/false'
    }).success(function (data, status) {
        $.each(data, function (index, tag) {
            row = $("<tr id='row" + tag.tagID + "'>");
            $("#tableBody").append(row);
            $(row).append($('<th>')
                    .html('<span id="cell' + tag.tagID + '">' + tag.tagDescription + '</span>' +
                            '<input id="input' + tag.tagID + '" type="text" class="edit editInput form-control" value="' + tag.tagDescription + '">'))
                    .append($('<td>')
                            .attr({
                                'data-description': tag.tagDescription,
                                'data-id': tag.tagID
                            })
                            .html('<a href="#" class="view editLink"><span class="glyphicon glyphicon-pencil"></span></a>\n\
                                   <a href="#" class="edit saveLink"><span class="glyphicon glyphicon-ok"></span></a>'))
                    .append($('<td>')
                            .attr({
                                'data-description': tag.tagDescription,
                                'data-id': tag.tagID
                            })
                            .html('<a href="#" class="view deleteLink"><span class="glyphicon glyphicon-trash"></span></a>\n\
                                   <a href="#" class="edit cancelLink"><span class="glyphicon glyphicon-remove"></span></a>'));
            showView(tag.tagID);
        });
    });
}

$('#addButton').click(function () {
    var dataDescription = $('#addDataInput').val();
    $.ajax({
        url: contextPath + "/admin/add" + type,
        type: "POST",
        data: dataDescription,
        contentType: 'application/json; charset=utf-8',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        dataType: 'json'
    }).success(function (data, status) {
        loadData();
        $('#addDataInput').val('');
    }).error(function (data, status) {

    });
});

$(document).on('click', '.editLink', function () {
    var id = $(this).parent().data('id');
    $('#input' + id).val($('#cell' + id).text());
    showEdit(id);
});

$(document).on('click', '.deleteLink', function () {
    var id = $(this).parent().data('id');
    if (confirm('Are you sure you want to delete?')) {
        $.ajax({
            type: 'DELETE',
            url: contextPath + '/admin/delete' + type + '/' + id,
            contentType: 'application/json; charset=utf-8',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            dataType: 'json'
        }).success(function (data, status) {
            loadData();
        }).error(function (data, status) {

        });
    }
});

$(document).on('click', '.saveLink', function () {
    var id = $(this).parent().data('id');
    var dataDescription = $('#input' + id).val();
    $.ajax({
        url: contextPath + "/admin/edit" + type + '/' + id,
        type: "PUT",
        data: dataDescription,
        contentType: 'application/json; charset=utf-8',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        dataType: 'json'
    }).success(function (data, status) {
        loadData();
        $('#addDataInput').val('');
    }).error(function (data, status) {

    });
});

$(document).on('click', '.cancelLink', function () {
    var id = $(this).parent().data('id');
    showView(id);
});

function showEdit(id) {
    $('#cell' + id).hide();
    $('.view').hide();
    $('#row' + id + ' .edit').show();
}

function showView(id) {
    $('#cell' + id).show();
    $('.view').show();
    $('.edit').hide();
}