<%-- 
    Document   : edit
    Created on : Nov 23, 2016, 12:51:55 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SWG Cute Puppy Adoption Center Dashboard</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/DashboardCSS.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/chosen.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=McLaren" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/navbar-fixed-side.css" rel="stylesheet">
    </head>
    <body>
        <c:if test="${not empty postId}">
            <input type="hidden" id="post-id" value="${postId}"/>
        </c:if>
        <c:if test="${not empty staticId}">
            <input type="hidden" id="static-id" value="${staticId}"/>
        </c:if>
        <p id="testp"></p>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-lg-2">
                    <nav class="navbar navbar-default navbar-fixed-side">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="${pageContext.request.contextPath}/">SWG Cute Puppies</a>
                            </div>
                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav">
                                    <li><a href="${pageContext.request.contextPath}/admin/managePosts">Posts</a></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/manageStaticPages">Pages</a></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/manageUsers">Users</a></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/manageCategories">Categories</a></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/manageTags">Tags</a></li>
                                </ul>
                            </div>
                        </div>
                    </nav>
                </div>
                <input type="hidden" id="PageType" value="${PageType}"/>
                <div class="col-sm-9 col-lg-10">
                    <div id="mainEditColumn" class="col-md-8">
                        <form class="form-horizontal" role="form">
                            <div class="row">
                                <h4>Content Status</h4>
                                <div id="contentStatusText"></div>
                                <h4 id="titleText">Post Title</h4>
                                <input id="postTitle" class="form-control" placeholder="Title">
                                <p id="titleEmptyError"></p>
                                <h4 id="urlText">Post URL (optional)</h4>
                                <input id="postURL" class="form-control" placeholder="Custom Post URL">
                                <p id="urlEmptyError"></p>
                                <h4 id="imgNameText">Image Name</h4>
                                <input id="imageName" class="form-control" placeholder="Image Name">
                                <h4 id="imgUrlText">Image URL</h4>
                                <input id="imageURL" class="form-control" placeholder="Image URL">
                            </div>
                            <div class="row">
                                <h4 id="bodyText">Post Body</h4>
                                <textarea></textarea>
                            </div>
                        </form>
                        <div id="revisionRow">
                            <div class="row">
                                <div id="revisionsDiv" class="col-md-offset-2 col-md-8">
                                    <table id="revisionsTable" class="table table-striped">
                                        <caption class="text-center">Previous Revisions</caption>
                                        <tr>
                                            <th class="text-center">#</th>
                                            <th class="text-center">Title</th>
                                            <th class="text-center">Date Modified</th>
                                            <th class="text-center">Author</th>
                                            <th class="text-center">Status</th>
                                        </tr>
                                        <tbody id="revisionRows"></tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="text-center">
                                <button id="deletePostButton" class="btn btn-primary">Archive All Revisions</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-offset-1 col-md-3">
                        <div class="row">
                            <div id="publishDiv" class="text-center">
                                <hr>
                                <button id="publishButton" class="btn btn-primary">Publish</button>
                                <button id="saveButton" class="btn btn-primary">Save</button>
                                <button id="deleteButton" class="btn btn-primary">Delete</button>
                                <hr>
                            </div>
                        </div>
                        <div id="sidebarColumn">
                            <div class="row">
                                <div id="categoryDivChosen" class="well">
                                    Categories
                                    <div>
                                        <select class="form-control chosenElement" id = "selectCategories" data-placeholder="Choose categories..." multiple = "multiple">
                                        </select>
                                    </div>
                                    <div class="input-group addDiv">
                                        <input type="text" id="addCategoryInput" class="form-control" placeholder="Add New Category">
                                        <span class="input-group-btn">
                                            <button class="btn btn-primary" type="button" id="addCategoryButton">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                            </button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div id="tagDivChosen" class="well">
                                    Tags
                                    <div>
                                        <select class="form-control chosenElement" id = "selectTags" data-placeholder="Choose tags..." multiple = "multiple">
                                        </select>
                                    </div>
                                    <div class="input-group addDiv">
                                        <input type="text" id="addTagInput" class="form-control" placeholder="Add New Tag">
                                        <span class="input-group-btn">
                                            <button class="btn btn-primary" type="button" id="addTagButton">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                            </button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <footer>
                    <div class="row">
                        <div class="col-md-12 text-center">
                            <p>Powered by Java and Bootstrap</p>
                        </div>
                    </div>
                </footer>

            </div>
        </div>
        <script>var contextPath = "${pageContext.request.contextPath}";</script>
        <script>var pageType = "${PageType}";</script>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/chosen.jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/EditJS.js"></script>  
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
        <script>tinymce.init({selector: 'textarea'});</script>
    </body>
</html>
