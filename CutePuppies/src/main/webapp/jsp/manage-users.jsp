<%-- 
    Document   : tagcategory
    Created on : Nov 30, 2016, 7:24:34 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SWG Cute Puppy Adoption Center Dashboard</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/TagsCategoriesCSS.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=McLaren" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/navbar-fixed-side.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-lg-2">
                  <%@include file="includes/dashboard-nav.jsp" %>
                </div>
                <div class="col-sm-9 col-lg-10">
                    <div id="tableDiv" class="row">
                        <div class="col-md-4 col-lg-3 col-sm-6">
                            <div>
                                <table id="editTable" class="table table-striped table-responsive">
                                    <caption id="tagOrCategory" class="text-center">${categoryTag}</caption>
                                    <tbody id="tableBody">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-lg-3 col-sm-6">
                            <div class="input-group">
                                <input type="text" id="addDataInput" class="form-control" placeholder="Add">
                                <span class="input-group-btn">
                                    <button class="btn btn-primary" type="button" id="addButton">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                    </button>
                                </span>
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
        <script>var contextPath = "${pageContext.request.contextPath}"</script>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/TagsCategoriesJS.js"></script>
          
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
        <script>tinymce.init({selector: 'textarea'});</script>
    </body>
</html>
