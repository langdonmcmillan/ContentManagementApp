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
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
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
            <div class="col-md-7 col-lg-6 col-sm-9">
                <div id="alphaDiv" style="visibility: hidden">
                    <table class="table table-striped" id="alphaTable">
                    <caption class="text-center">Starts With</caption>
                    <tr>
                        <td class="text-center">
                            <a class="alpha" id="a">a</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="b">b</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="c">c</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="d">d</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="e">e</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="f">f</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="g">g</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="h">h</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="i">i</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="j">j</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="k">k</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="l">l</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="m">m</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="n">n</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="o">o</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="p">p</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="q">q</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="r">r</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="s">s</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="t">t</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="u">u</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="v">v</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="w">w</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="x">x</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="y">y</a>
                        </td>
                        <td class="text-center">
                            <a  class="alpha" id="z">z</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center">
                            <a class="alpha" id="all">All</a>
                        </td>
                        <td class="text-center">
                            <a class="alpha" id="num">Number</a>
                        </td>
                    </tr>
                  </table>
                </div>
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
      </div>
      <%@include file="includes/footer.jsp" %>

    </div>
        <script>var contextPath = "${pageContext.request.contextPath}"</script>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/TagsCategoriesJS.js"></script>
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
        <script>tinymce.init({selector: 'textarea'});</script>
        <script src="${pageContext.request.contextPath}/js/AdminJS.js"></script>
    </body>
</html>
