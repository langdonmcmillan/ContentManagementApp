/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var type;
$(document).ready(function () {
    setTableHeight();
    $("#password, #password2").keyup(checkMatch);
});

function checkMatch() {
    var password = $("#password").val();
    var confirmPassword = $("#password2").val();

    if (password !== confirmPassword)
        $("#password2").css('background-color', 'salmon');
    else
        $("#password2").css('background-color', '');
}

function setTableHeight() {
    var maxHeight = $(window).height() - 120;
    $('#editTable').css('max-height', maxHeight); //set max height
}

$(window).on('resize', function () {
    setTableHeight();
});


$('#addButton').click(function () {
});
