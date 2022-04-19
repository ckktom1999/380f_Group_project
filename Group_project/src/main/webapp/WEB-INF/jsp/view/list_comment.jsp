<!DOCTYPE html>
<html>
    <head>
        <title>Comment History Page</title>
    </head>
    <body>
        <h2> Comment History Page </h2>

        <c:url var="logoutUrl" value="/cslogout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <br />
        <c:choose>
            <c:when test="${fn:length(commentdb) == 0}">
                <i>There are no comment in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${commentdb}" var="comment">
                    ${comment.date_time}: User(${comment.userName}) has create a comment "${comment.body}" in page(${comment.lectures.title})<br />
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <br />
        <a href="<c:url value="/home/list" />">Return to home page</a>
    </body>
</html>
