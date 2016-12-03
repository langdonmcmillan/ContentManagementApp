
<div class="staticPage">
    <h1 class="title">${staticPage.title}</h1>
    <p class = "lead userName">created by <a href="#">${staticPage.createdByUser.userName}</a></p>
    <c:if test="${(staticPage.createdByUser.userName != staticPage.updatedByUser.userName) && (staticPage.createdOnDate != staticPage.updatedOnDate)}">
        <p class = "lead userName">updated by <a href="#">${staticPage.updatedByUser.userName}</a></p>
    </c:if>
    <hr>
    <p>
        <span class="glyphicon glyphicon-time createdOnDate"></span>
        <span><fmt:formatDate value="${staticPage.createdOnDate}" pattern="MM/dd/yyyy hh:mm a"/></span>
    </p>
    <hr>
    <c:if test="${not empty staticPage.contentImgLink}">
        <img class="img-responsive contentImgLink" src=${staticPage.contentImgLink} alt=${staticPage.contentImgAltTxt}>
    </c:if>
    <hr>
    <div class = "body">${staticPage.body}</div>
    <hr>
</div>