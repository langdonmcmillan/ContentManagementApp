<nav class="navbar navbar-default navbar-fixed-side">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/dashboard">SWG Cute Puppies Dashboard</a>
  </div>
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
      <li><a href="#" class="disableClick"><strong>Hello <sec:authentication property="principal.username" />!</strong></a></li>
      <li><a href="${pageContext.request.contextPath}/admin/dashboard">Posts</a></li>
      <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li><a href="${pageContext.request.contextPath}/admin/manageStaticPages">Pages</a></li>
        <li><a href="${pageContext.request.contextPath}/manageUsers/userList">Users</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/manageCategories">Categories</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/manageTags">Tags</a></li>
      </sec:authorize>
      <li role="presentation">
        <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</a>
      </li> 
    </ul>
  </div>
</nav>