
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="singlePost">
    <h1 class="title">${post.publishedContent.title}</h1>
    <p class = "lead userName">created by <a href="#" class="authorId" data-userId="${post.createdByUser.userId}">${post.createdByUser.userName}</a></p>
        <c:if test="${post.createdByUser.userName != post.publishedContent.createdByUser.userName}">
        <p class = "lead userName">updated by <span>${post.publishedContent.createdByUser.userName}</span></p>
        </c:if>
    <hr>
    <p>
        <span class="glyphicon glyphicon-time createdOnDate"></span>
        <span><fmt:formatDate value="${post.publishedContent.createdOnDate}" pattern="MM/dd/yyyy hh:mm a"/></span>
    </p>
    <div id="categories${post.publishedContent.contentId}">
        <img src="/CutePuppies/img/folder.png" id="folderImg">
        <c:forEach items="${post.publishedContent.listOfCategories}" var="category" varStatus="index">
            <c:if test="${index.index > 0}">,&nbsp</c:if>
            <a href="#" class="category" data-categoryDescription="${category.categoryDescription}" data-categoryID="${category.categoryID}">
                ${category.categoryDescription}
            </a>
        </c:forEach>
    </div>
    <div id="tags${post.publishedContent.contentId}">
        <img src="/CutePuppies/img/tag.png">
        <c:forEach items="${post.publishedContent.listOfTags}" var="tag" varStatus="index">
            <c:if test="${index.index > 0}">,&nbsp</c:if>
            <a href="#" class="tag" data-tagDescription="${tag.tagDescription}" data-tagID="${tag.tagID}">
                ${tag.tagDescription}
            </a>
        </c:forEach>
    </div>



    <hr>
<!--    <c:if test="${not empty post.publishedContent.contentImgLink}">
        <img class="img-responsive contentImgLink" src=${post.publishedContent.contentImgLink} alt=${post.publishedContent.contentImgAltTxt}>
    </c:if>
    <hr>-->
    <div id="postBody" class = "body">${post.publishedContent.body}</div>
    <hr>
</div>