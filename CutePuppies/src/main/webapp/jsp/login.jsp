<%-- 
    Document   : login
    Created on : Nov 18, 2016, 10:40:35 AM
    Author     : softwareguild
--%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <div>
      <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">SWG Cute Puppies</a>
          </div>
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li><a href="${pageContext.request.contextPath}/">Blog</a></li>
              <li><a href="#">About</a></li>
              <li><a href="#">Contact Us</a></li>
            </ul>
          </div>
        </div>
      </nav>

      <div class="container">
        <div class="row">
          <img class = "img-responsive" id = "blog-main-pic" src="http://4.bp.blogspot.com/-FHl93z8Tavw/U3A4mwjmwRI/AAAAAAAAJWI/uiuJ9wlhNE4/s1600/Nine+Brothers+and+Sisters.jpg" alt="logo?">
        </div>
        <div class="row">
          <!-- #1 - If login_error == 1 then there was a failed login attempt -->
          <!-- so display an error message -->
          <c:if test="${param.login_error == 1}">
              <h3>Wrong id or password!</h3>
          </c:if>
          <!-- #2 - Post to Spring security to check our authentication -->
          <form method="post" class="signin" action="j_spring_security_check">
            <fieldset>
              <table cellspacing="0">
                <tr>
                  <th>
                    <label for="username">Username
                    </label>
                  </th>

                  <td><input id="username_or_email"
                             name="j_username"
                             type="text" />
                  </td>
                </tr>
                <tr>
                  <th><label for="password">Password</label></th>
                  <!-- #2b - must be j_password for Spring -->
                  <td><input id="password"
                             name="j_password"
                             type="password" />
                  </td>
                </tr>
                <tr>
                  <th></th>
                  <td><input name="commit" type="submit" value="Sign In" /></td>
                </tr>
              </table>
            </fieldset>
          </form>
        </div> 
        <footer>
          <div class="row">
            <div class="col-md-12 text-center">
              <p>Powered by Java and Bootstrap</p>
            </div>
          </div>
        </footer>

      </div>
      <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/jquery.dotdotdot.js"></script>
      <script src="${pageContext.request.contextPath}/js/CutePuppiesJS.js"></script> 
      <script src="${pageContext.request.contextPath}/js/jquery.tx3-tag-cloud.js"></script>
  </body>
</html>
