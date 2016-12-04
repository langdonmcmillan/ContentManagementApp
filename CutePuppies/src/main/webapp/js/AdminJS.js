/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    resetSessionProperties();
});

function resetSessionProperties() {
    sessionStorage.setItem('pageNumber', 1);
    sessionStorage.setItem('selectedTagId', 'null');
    sessionStorage.setItem('selectedCategoryId', 'null');
    sessionStorage.setItem('searchTerm', '');
}
