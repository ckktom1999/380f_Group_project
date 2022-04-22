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
            <c:when test="${fn:length(personal_comment) == 0}">
                <i>You did not write any comment.</i>
                <br />
            </c:when>
            <c:otherwise>
                <h3>User(${principal.name}) comment history</h3>
                <table style="white-space:nowrap;width:60%;">
                    <tr align="left"> 
                        <th>Date Time</th><th>Comment content</th><th>Page</th>
                    </tr>
                    <c:forEach items="${personal_comment}" var="comment">
                        <tr>
                            <td>${comment.date_time}</td><td>${comment.body}</td><td>${comment.lectures.title}</td>
                        </tr>
                    </c:forEach>
                </table>
                <br />
            </c:otherwise>
        </c:choose>
        <security:authorize access="hasRole('ADMIN')">
            <c:choose>
                <c:when test="${fn:length(commentdb) == 0}">
                    <i>There are no comment in the system.</i>
                    <br />
                </c:when>
                <c:otherwise>
                    <h3>All Comment history in system</h3>
                    <table style="white-space:nowrap;width:60%;">
                        <tr align="left"> 
                            <th>Date Time</th><th>User</th><th>Comment content</th><th>Page</th>
                        </tr>
                        <c:forEach items="${commentdb}" var="comment">
                            <tr>
                                <td>${comment.date_time}</td><td>${comment.userName}</td><td>${comment.body}</td><td>${comment.lectures.title}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
            <br />
        </security:authorize>
        <a href="<c:url value="/home/list" />">Return to home page</a>
    </body>
</html>
