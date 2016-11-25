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
        <title>SWG Cute Puppy Adoption Center</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/CutePuppiesCSS.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=McLaren" rel="stylesheet">
    </head>
    <body>
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
                        <li><a href="${pageContext.request.contextPath}/blog.jsp">Blog</a></li>
                        <li><a href="#">About</a></li>
                        <li><a href="#">Contact Us</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <img class = "img-responsive" id = "blog-main-pic" src="http://4.bp.blogspot.com/-FHl93z8Tavw/U3A4mwjmwRI/AAAAAAAAJWI/uiuJ9wlhNE4/s1600/Nine+Brothers+and+Sisters.jpg" alt="logo?">

        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h1 id = "title" >Blog Post Title</h1>
                    <p id = "userName" class="lead">by <a href="#">Admin</a></p>
                    <hr>
                    <p><span class="glyphicon glyphicon-time" id = "createdOnDate" ></span></p>
                    <hr>
                    <img id = "contentImgLink" class="img-responsive" src="http://placehold.it/900x300" alt="">
                    <hr>
                    <p id = "body" >Authentic gluten-free cray, four loko butcher fam ennui offal mumblecore fingerstache direct trade fanny pack scenester. 
                        Austin waistcoat pabst 90's iPhone, snackwave migas selfies 8-bit hashtag unicorn distillery. Post-ironic selvage migas, 
                        shabby chic art party kickstarter brooklyn next level ethical 8-bit kale chips actually tote bag. Plaid thundercats small batch, 
                        jianbing humblebrag williamsburg meh. Beard hammock irony, cardigan bushwick fashion axe godard shabby chic kombucha asymmetrical 
                        post-ironic banjo chia locavore. Salvia wayfarers taxidermy, try-hard flannel food truck DIY irony chia. 
                        Taxidermy fanny pack vegan, vape gluten-free literally polaroid activated charcoal tattooed PBR&B.</p>
                    <hr>
                </div>

                <div class="col-md-4">
                    <div class="well">
                        <h4>Blog Search</h4>
                        <div class="input-group">
                            <input type="text" class="form-control">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </span>
                        </div>
                    </div>

                    <div class="well">
                        <h4>Categories</h4>
                        <div class="row">
                            <!--categories populated here-->
                        </div>
                    </div>
                    <div class="well">
                        <h4>Tags</h4>
                        <div class="row">
                            <!--tags populated here-->
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <nav class = "text-center" aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                </ul>
            </nav>


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
        <script src="${pageContext.request.contextPath}/js/CutePuppiesJS.js"></script>        
    </body>
</html>