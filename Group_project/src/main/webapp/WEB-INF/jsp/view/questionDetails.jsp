
<html>
    <head>
        <title>Question Details</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/cslogout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h3>QID:${pollQues.pollId}, ${pollQues.question} </h3> 
        <i>(Question created at ${pollQues.pqDate})</i> 

        <br></br>

        <form:form method="POST" enctype="multipart/form-data" modelAttribute="addReply">
            <form:label path="option"></form:label>
            A<form:radiobutton path="option" value="A"/> &nbsp; ${pollQues.optionA} <i> (votes count: ${optionACount})</i><br/>
            <c:if test= "${fn:length(pollQues.optionB) > 0}">
                B<form:radiobutton path="option" value="B"/> &nbsp; ${pollQues.optionB} <i> (votes count: ${optionBCount})</i><br/>
            </c:if>
            <c:if test="${fn:length(pollQues.optionC) > 0}">
                C<form:radiobutton path="option" value="C"/> &nbsp; ${pollQues.optionC} <i> (votes count: ${optionCCount})</i><br/>
            </c:if>
            <c:if test="${fn:length(pollQues.optionD) > 0}">
                D<form:radiobutton path="option" value="D"/> &nbsp; ${pollQues.optionD} <i> (votes count: ${optionDCount})</i><br/><br/>
            </c:if>
            <br/>
            <input type="submit" value="Add Reply"/>
            <input type="hidden" name="pollId" value="${pollQues.pollId}">  
        </form:form>
        <p style="color:green">current number of votes: ${numberOfVotes} </p>
        <p style="color:blue"> ${message} </p>



        <h3>Comment:</h3>
        <c:if test="${fn:length(pollQues.pComment) > 0}">
            <c:forEach items="${pollQues.pComment}" var="comment">
                ${comment.name}: <c:out value="${comment.body}" /></a>
                <security:authorize access="hasRole('ADMIN')">
                [<a href="<c:url value="/poll/deleteComment/${comment.id}/${pollQues.pollId}" />">Delete</a>]
            </security:authorize>
            <br />
        </c:forEach>
    </c:if>
    <c:if test="${fn:length(pollQues.pComment) == 0}">
        No comment!<br />
    </c:if>
        
    <h3>Add comment</h3>
    <form method="POST" action="/Group_project/poll/createPollComment" enctype="multipart/form-data" id="createCommentForm">
        <label for="cpComment">Comment</label> <br/>
        <textarea id="cpComment" name="cpComment" rows="5" cols="30" required="required" form="createCommentForm"/></textarea><br /><br />
        <input type="submit" value="Add Comment"/>    
        <input type="hidden" name="pollId" value="${pollQues.pollId}">  
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    

    <br/><a href="<c:url value="/home/list" />">Return to home page</a>
</body>
</html>
