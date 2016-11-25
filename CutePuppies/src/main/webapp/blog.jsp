<%-- 
    Document   : cutepuppies
    Created on : Nov 23, 2016, 12:38:03 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/CutePuppiesCSS.css" rel="stylesheet">
        <title>SWG Cute Puppy Adoption Center</title>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">

                <!-- this is for mobile -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a class="emphasis" href="${pageContext.request.contextPath}/blog.jsp">SWG Cute Puppies</a></li>

                        <li><a href="${pageContext.request.contextPath}/blog.jsp">Blog</a></li>
                        <li><a href="#">About</a></li>
                        <li><a href="#">Contact Us</a></li>
                    </ul>
                    <form class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>

                </div>
            </div>
        </nav> 
        <img class = "img-responsive" id = "blog-main-pic" src="http://4.bp.blogspot.com/-FHl93z8Tavw/U3A4mwjmwRI/AAAAAAAAJWI/uiuJ9wlhNE4/s1600/Nine+Brothers+and+Sisters.jpg" alt="logo?">

        <div id="allPosts">
            <!--this is where all the posts should go...-->
        </div>
        <nav class = "text-center" aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>

        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/CutePuppiesJS.js"></script>        
    </body>
</html>
