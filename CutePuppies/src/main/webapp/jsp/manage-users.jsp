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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
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
                            <a href="${pageContext.request.contextPath}/manageUsers/edit/${user.userId}"><span class="glyphicon glyphicon-pencil"></span></a>
                          </td>
                          <td>
                            <form id="removeForm${user.userId}" action="${pageContext.request.contextPath}/manageUsers/remove" method="POST">
                              <a href="#" onclick="remove(${user.userId})"><span class="glyphicon glyphicon-trash"></a>
                              <input type="text" class="hidden" value="${user.userId}" name="userId">
                              <c:if test="${isCurrentUser == true && userId == user.userId}"><div id="isCurrentUser" class="label label-danger text-center">You cannot delete yourself</div></c:if>
                              </form>
                            </td>
                          </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>

            <div class="col-sm-6">
              <sf:form class="form-horizontal" modelAttribute="formUser"
                       action="${pageContext.request.contextPath}/manageUsers/${addEdit}" 
                       method="POST">
                  <input class="hidden" value="${formUser.userId}" name="userId">
                  <div class="form-group">
                    <label for="username" 
                           class="col-md-4 control-label">Username</label>
                    <div class="col-md-8">
                      <sf:input type="text" class="form-control" 
                                id="username" 
                                path="userName"
                                placeholder="username" />
                      <c:if test="${userNameExists == true}"><div id="userNameExists" class="label label-danger">Username already exists</div></c:if>
                      <c:if test="${userNameEmpty == true}"><div id="userNameEmpty" class="label label-danger">You must enter a username</div></c:if>
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
                      <c:if test="${userEmailExists == true}"><div id="userEmailExists" class="label label-danger">Email already exists</div></c:if>
                      <c:if test="${userEmailEmpty == true}"><div id="userEmailEmpty" class="label label-danger">You must enter an email</div></c:if>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="password" 
                             class="col-md-4 control-label">Password</label>
                      <div class="col-md-8 passwordDiv">
                      <sf:input type="password" class="form-control" 
                                id="password" 
                                path="userPassword"
                                placeholder="password"
                                onchange="checkMatch();" />
                      <c:if test="${userPasswordEmpty == true}"><div id="userPasswordEmpty" class="label label-danger">You must enter a password</div></c:if>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="password2" 
                             class="col-md-4 control-label">Re-Type Password</label>
                      <div class="col-md-8 passwordDiv">
                        <input type="password" class="form-control" name="password2"
                               id="password2" 
                               placeholder="re-type password"
                               onchange="checkMatch();" />
                      <c:if test="${userPasswordsMismatch == true}"><div id="userPasswordsMismatch" class="label label-danger">Passwords must match</div></c:if>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="user-type" 
                             class="col-md-4 control-label">User Type</label>
                      <div class="col-md-8">
                      <sf:select path="roleCode" id="user-type" class="form-control">
                          <optgroup>
                            <option value="" ${formUser.roleCode == "" || roleCode == null ? 'selected' : ''}>Select One</option>
                            <option value="ROLE_AUTHOR" ${formUser.roleCode == "ROLE_AUTHOR" ? 'selected' : ''}>Author</option>
                            <option value="ROLE_ADMIN" ${formUser.roleCode == "ROLE_ADMIN" ? 'selected' : ''}>Admin</option>
                            <option value="ROLE_USER" ${formUser.roleCode == "ROLE_USER" ? 'selected' : ''}>User</option>
                          </optgroup>
                      </sf:select>
                      <c:if test="${userRoleCodeEmpty == true}"><div id="userRoleCodeEmpty" class="label label-danger">You must define the user's role</div></c:if>
                      </div>
                    </div>
                    <div class="form-group">
                    <c:if test="${addEdit == 'add'}">
                        <div class="col-md-offset-4 col-md-8">
                          <button type="submit" id="addButton"
                                  class="btn btn-primary">Add User</button>
                        </div>
                    </c:if>
                    <c:if test="${addEdit == 'edit'}">
                        <div class="col-md-offset-4 col-md-8">
                          <button type="submit" id="editButton"
                                  class="btn btn-primary">Save Edits</button>
                          <a id="edit-dvd-button" href="${pageContext.request.contextPath}/manageUsers/userList"
                             class="btn btn-primary">Cancel</a>
                        </div>
                    </c:if>
                  </div>
              </sf:form>
            </div>
          </div>

        </div>
      </div>
    </div>
        
      <%@include file="includes/footer.jsp" %>
      
      <script>var contextPath = "${pageContext.request.contextPath}"</script>
      <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/ManageUsersJS.js"></script>

  </body>
</html>
