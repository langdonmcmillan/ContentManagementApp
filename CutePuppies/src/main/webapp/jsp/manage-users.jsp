<%-- 
    Document   : tagcategory
    Created on : Nov 30, 2016, 7:24:34 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SWG Cute Puppy Adoption Center Dashboard</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=McLaren" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/navbar-fixed-side.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/ManageUsersCSS.css" rel="stylesheet">
  </head>
  <body>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-lg-2">
          <%@include file="includes/dashboard-nav.jsp" %>
        </div>
        <div class="col-sm-9 col-lg-10">
          <div id="tableDiv" class="row">
            <div class="col-md-6">
              <div>
                <table id="editTable" class="table table-striped table-responsive">
                  <caption id="userList" class="text-center">Site Users</caption>
                  <thead>
                    <th>Username</th>
                    <th>Edit</th>
                    <th>Delete</th>
                  </thead>
                  <tbody id="tableBody">
                    <c:forEach var="user" items="${users}">
                        <tr>
                          <td>
                            <c:out value="${user.userName}"/> 
                          </td>
                          <td>
                            <a onClick="editUser(${user.userId})"><span class="glyphicon glyphicon-pencil"></span></a>
                          </td>
                          <td>
                            <a onClick="deleteUser(${user.userId})"><span class="glyphicon glyphicon-trash"></span></a>
                          </td>
                        </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="col-sm-6">
              <sf:form class="form-horizontal" modelAttribute="newUser" 
                    action="manageusers/add" 
                    method="POST">
                <div class="form-group">
                  <label for="username" 
                         class="col-md-4 control-label">Username</label>
                  <div class="col-md-8">
                    <sf:input type="text" class="form-control" 
                              id="username" 
                              path="userName"
                              placeholder="username" />
                  </div>
                </div>
                  <div class="form-group">
                  <label for="email" 
                         class="col-md-4 control-label">Email Address</label>
                  <div class="col-md-8">
                    <sf:input type="email" class="form-control" 
                              id="email" 
                              path="userEmail"
                              placeholder="email address" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="password" 
                         class="col-md-4 control-label">Password</label>
                  <div class="col-md-8">
                    <sf:input type="password" class="form-control" 
                              id="password" 
                              path="userPassword"
                              placeholder="password"
                              onchange="checkMatch();" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="password2" 
                         class="col-md-4 control-label">Re-Type Password</label>
                  <div class="col-md-8">
                    <input type="password" class="form-control" 
                              id="password2" 
                              placeholder="re-type password"
                              onchange="checkMatch();" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="user-type" 
                         class="col-md-4 control-label">User Type</label>
                  <div class="col-md-8">
                    <sf:select path="roleCode" id="user-type">
                        <optgroup>
                          <option selected="true">Select One</option>
                          <option value="AUTHOR">Author</option>
                          <option value="ADMIN">Admin</option>
                          <option value="USER">User</option>
                      </optgroup>
                    </sf:select>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-md-offset-4 col-md-8">
                    <input hidden="true" id="edit_dvd_id">
                    <button type="submit" id="edit-dvd-button"
                            class="btn btn-primary">Add User</button>
                  </div>
                </div>
              </sf:form>
            </div>
          </div>

        </div>
        <%@include file="includes/footer.jsp" %>


      </div>
      <script>var contextPath = "${pageContext.request.contextPath}"</script>
      <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/ManageUsersJS.js"></script>

      <!--        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
              <script>tinymce.init({selector: 'textarea'});</script>-->
  </body>
</html>
