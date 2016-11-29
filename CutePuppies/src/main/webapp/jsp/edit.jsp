<%-- 
    Document   : edit
    Created on : Nov 23, 2016, 12:51:55 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SWG Cute Puppy Adoption Center Dashboard</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/DashboardCSS.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=McLaren" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/navbar-fixed-side.css" rel="stylesheet">
    </head>
    <body>
        <c:if test="${not empty postId}">
              
            <input type="hidden" id="post-id" value=${postId}>
              
        </c:if>
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
                                <a class="navbar-brand" href="${pageContext.request.contextPath}/blog.jsp">SWG Cute Puppies</a>
                            </div>
                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav">
                                    <li><a href="#">Posts</a></li>
                                    <li><a href="#">Pages</a></li>
                                    <li><a href="#">Users</a></li>
                                </ul>
                            </div>
                        </div>
                    </nav>
                </div>
                <div class="col-sm-9 col-lg-10">
                    <div class="col-md-8">
                        <div class="row">
                            <div id="revisionsDiv" class="col-md-offset-2 col-md-8">
                                <table id="revisionsTable" class="table table-striped">
                                    <caption class="text-center">Previous Revisions</caption>
                                    <tr>
                                        <th class="text-center">Title</th>
                                        <th class="text-center">Date Modified</th>
                                        <th class="text-center">Author</th>
                                    </tr>
                                    <tbody id="contentRows"></tbody>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <h4>Post Title</h4>
                            <input id="postTitle" class="form-control" placeholder="Title">
                            <h4>Post URL (optional)</h4>
                            <input id="postURL" class="form-control" placeholder="Custom Post URL">
                            <h4>Image Name</h4>
                            <input id="imageName" class="form-control" placeholder="Image Name">
                            <h4>Image URL</h4>
                            <input id="imageURL" class="form-control" placeholder="Image URL">
                        </div>
                        <div class="row">
                            <h4>Post Body</h4>
                            <contentarea></contentarea>
                        </div>
                    </div>
                    <div class="col-md-offset-1 col-md-3">
                        <div class="row">
                            <div id="publishDiv">
                                <button id="publishButton" class="btn btn-primary">Publish</button>
                                <button id="saveButton" class="btn btn-primary">Save</button>
                                <button id="deleteButton" class="btn btn-primary">Delete</button>
                            </div>
                        </div>
                        <div class="row">
                            <div id="categoryDiv" class="well">
                                Categories
                                <div id="categoryList">
                                    
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div id="tagDiv" class="well">
                                Tags
                                <div id="tagList">
                                    
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
            <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/EditJS.js"></script>  
            <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
            <script>tinymce.init({selector: 'contentarea'});</script>
    </body>
</html>
