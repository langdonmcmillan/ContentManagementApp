
<div class="singlePost">
  <h1 class="title">${post.publishedContent.title}</h1>
  <p class = "lead userName">created by <a href="#">${post.createdByUser.userName}</a></p>
  <c:if test="${post.createdByUser.userName != post.publishedContent.createdByUser.userName}">
    <p class = "lead userName">updated by <a href="#">${data.publishedContent.createdByUser.userName}</a></p>
  </c:if>
  <hr>
  <p><span class="glyphicon glyphicon-time createdOnDate"></span><span>${post.createdOnDate}</span></p>
  <hr>
  <img class="img-responsive contentImgLink" src=${post.publishedContent.contentImgLink} alt=${post.publishedContent.contentImgAltTxt}>
  <hr>
  <div class = "body">${post.publishedContent.body}</div>
  <hr>
</div>