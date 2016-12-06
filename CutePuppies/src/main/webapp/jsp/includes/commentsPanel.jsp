<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/CommentsPanelCSS.css" rel="stylesheet">

<div id="commentsBlock">
  <div class="row">
    <button id="addCommentButton" type="button" class="pull-right btn btn-primary" data-toggle="modal" data-target="#commentModal" >Add Comment</button>
  </div>

  <c:forEach items="${post.postComments}" var="comment" >
    <div class="well">
      <div class="row">
        <div class="col-sm-3">By: ${comment.createdByUser.userName}</div>
        <div class="col-sm-3">On: ${comment.createdByUser.createdDate}</div>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button onclick="archiveComment(${comment.contentId})" type="button" class="btn btn-danger pull-right archiveComment">Remove</button>
        </sec:authorize>
      </div>
      <hr>
      <div class="row">
        <div class="commentBody">
          ${comment.body}
        </div>
      </div>
    </div>
  </c:forEach>

</div>


<div class="modal fade" id="commentModal" tabindex="-1" role="dialog" aria-labelledby="commentModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="contentModalLabel">Add Comment to Discussion</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="commentorName" class="control-label">Name:</label>
            <input type="text" class="form-control" id="commentorName">
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">Comment:</label>
            <textarea></textarea>
          </div>
          <div class="form-group">
            <label id="captchaQuestion" for="captchaAnswer" class="control-label"></label>
            <input type="text" class="form-control" id="captchaAnswer">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button id="saveComment" onclick="checkCaptcha()" type="button" class="btn btn-primary">Save comment</button>
      </div>
    </div>
  </div>
</div>

<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script>tinymce.init({selector: 'textarea'});</script>