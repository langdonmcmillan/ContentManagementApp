<%-- 
    Document   : dashboard
    Created on : Nov 23, 2016, 12:37:47 PM
    Author     : apprentice
--%>

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SWG Cute Puppy Adoption Center</title>
    <link href="/CutePuppies/css/bootstrap.min.css" rel="stylesheet">
    <link href="/CutePuppies/css/DashboardCSS.css" rel="stylesheet">
    <link href="/CutePuppies/css/navbar-fixed-side.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=McLaren" rel="stylesheet">
  </head>
  <body>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-lg-2">
          <%@include file="includes/dashboard-nav.jsp" %>
        </div>
        <div id = "dashboardContent" class="col-sm-9 col-lg-10">
          <div class="row">
            <div class="col-sm-9 col-lg-10 text-center">
              <h3>List Of All Posts</h3>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-9 col-lg-10 text-left">
              <input id = "showArchivedPosts" type="checkbox" name="archivedPosts" value="showArchivedPosts"> Show Archived Posts<br>
            </div>
          </div>
          <div class="row" id = "listScroll">
            <div class="col-sm-9 col-lg-10">
              <table class="table table-responsive table-hover text-center">
                <thead>
                  <tr>
                    <th>Title</th>
                    <th>Original Author</th>
                    <th>Date Created</th>
                    <th>Contributing Author</th>
                    <th>Date Last Updated</th>
                  </tr>
                </thead>
                <tbody id ="populateTable"></tbody>
              </table>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-9 col-lg-10 text-center">
              <a id="createNewPost" href="${pageContext.request.contextPath}/admin/edit" class="btn btn-primary btn-lg active" role="button">Create A New Post</a>
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

    <script>var contextPath = "${pageContext.request.contextPath}";</script>
    <script>var pageType = "${PageType}";</script>
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.dotdotdot.js"></script>
    <script src="${pageContext.request.contextPath}/js/DashboardJS.js"></script>        
  </body>
</html>