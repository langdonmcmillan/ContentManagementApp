<%-- 
    Document   : cutepuppies
    Created on : Nov 23, 2016, 12:38:03 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SWG Cute Puppy Adoption Center</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/CutePuppiesCSS.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=McLaren" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <link href="${pageContext.request.contextPath}/css/tx3-tag-cloud.css" rel="stylesheet">
    </head>
    <body>

        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="row">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand homeLink" href="${pageContext.request.contextPath}/">SWG Cute Puppies</a>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a class="homeLink" href="${pageContext.request.contextPath}/" >Blog</a></li>
                                <c:forEach items="${allStaticPages}" var="staticPage">
                                <li><a href="${pageContext.request.contextPath}/${staticPage.urlPattern}">${staticPage.title}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <%--<%@include file="includes/blogNavbar.jsp" %>--%>


        <div class="container" style="padding-top: 90px">
            <div class="row">
                <h1 id="blogTitle">CUTE PUPPIES++</h1>
            </div>
            <div class="row">
                <div id = "allPosts" class="col-md-8">
                    <c:if test="${not empty post}">
                        <%@include file="includes/post.jsp" %>
                        <%@include file="includes/commentsPanel.jsp" %>
                    </c:if>
                    <c:if test="${not empty staticPage}">
                        <%@include file="includes/staticPage.jsp" %>
                    </c:if>
                </div>
                <c:if test="${empty staticPage}">
                    <div id="sidebar" class="col-md-4">
                        <div id="searchBox" class ="row">
                            <div class="well">
                                <h4>Filter Posts</h4>
                                <div class="input-group">
                                    <input id="searchInput" type="text" class="form-control" placeholder="Filter posts">
                                    <span class="input-group-btn">
                                        <button id="searchButton" class="btn btn-default" type="button">
                                            <span class="glyphicon glyphicon-search"></span>
                                        </button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div id="categoryBox" class="row">
                            <div id="categoryDiv" class="well">
                                <h4>Categories</h4>
                                <div id="categoryList">

                                </div>
                            </div>
                        </div>
                        <div id="tagBox" class="row">
                            <div id="tagDiv" class="well">
                                <h4>Tags</h4>
                                <ul id="tagList">
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
            <hr>
            <c:if test="${(empty post) && (empty staticPage)}">
                <div id="pagination">
                    <nav class = "text-center" aria-label="Page navigation">
                        <ul class="pagination">
                            <li id="prevPageButton" class="disabled prevPage" data-value="1"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                            <li class="pgNum active" data-value="1"><a href="#">1</a></li>
                            <li class="pgNum" data-value="2"><a href="#">2</a></li>
                            <li class="pgNum" data-value="3"><a href="#">3</a></li>
                            <li class="pgNum" data-value="4"><a href="#">4</a></li>
                            <li class="pgNum" data-value="5"><a href="#">5</a></li>
                            <li id="nextPageButton" class="nextPage" data-value="2"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                        </ul>
                    </nav>
                    <div class="text-center">
                        <label>Posts Per Page:</label>
                        <select id="itemsPerPageSelect">
                            <option value="5">5 per page</option>
                            <option value="10">10 per page</option>
                            <option value="25">25 per page</option>
                        </select>
                    </div>
                </div>
            </c:if>


            <footer>
                <div class="row">
                    <div class="col-md-12 text-center">
                        <p>Powered by Java and Bootstrap</p>
                    </div>
                </div>
            </footer>

        </div>

        <script>
            var contextPath = "${pageContext.request.contextPath}";
            var staticPage = "${staticPage}";
            var post = "${post}";
            var postId = "${post.postId}";
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.dotdotdot.js"></script>
        <script src="${pageContext.request.contextPath}/js/CutePuppiesJS.js"></script> 
        <script src="${pageContext.request.contextPath}/js/AdminJS.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.tx3-tag-cloud.js"></script>
    </body>
</html>