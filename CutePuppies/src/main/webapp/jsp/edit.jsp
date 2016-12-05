<%-- 
    Document   : edit
    Created on : Nov 23, 2016, 12:51:55 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <p id="testp"></p>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-lg-2">
          <%@include file="includes/dashboard-nav.jsp" %>
        </div>
        <div class="col-sm-9 col-lg-10">
          <div class="col-md-8">

            <div class="row">
              <h4>Content Status</h4>
              <div id="contentStatusText"></div>
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
              <textarea></textarea>
            </div>

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
          <div id="sidebarColumn" class="col-md-offset-1 col-md-3">
            <div class="row">
              <div id="publishDiv">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <button id="publishButton" class="btn btn-primary">Publish</button>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_AUTHOR')">
                    <button id="requestPublishButton" class="btn btn-primary">Submit</button>
                </sec:authorize>
                <button id="saveButton" class="btn btn-primary">Save</button>
                <button id="deleteButton" class="btn btn-primary">Delete</button>
              </div>
            </div>
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

      <%@include file="includes/footer.jsp" %>


      <script>var contextPath = "${pageContext.request.contextPath}";</script>
      <script>var pageType = "${PageType}";</script>
      <script>var staticId = "${staticId}";</script>
      <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/chosen.jquery.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/EditJS.js"></script>  
      <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
      <script>tinymce.init({selector: 'textarea'});</script>
      <script src="${pageContext.request.contextPath}/js/AdminJS.js"></script>
  </body>
</html>
