<!DOCTYPE html>
<html>
    <head>
        <title>Course material page</title>
    </head>
    <body>
        <h2> COMP S380F Web Applications </h2>
        <c:choose>
            <c:when test="${empty principal.name}">
                <c:url var="loginUrl" value="/cslogin"/>
                <form action="${loginUrl}" method="get">
                    <input type="submit" value="Login" />
                </form>
                <c:url var="registerUrl" value="/home/register"/>
                <form action="${registerUrl}" method="get">
                    <input type="submit" value="Register" />
                </form>
            </c:when>
            <c:otherwise>
                <c:url var="logoutUrl" value="/cslogout"/>
                <form action="${logoutUrl}" method="post">
                    <input type="submit" value="Log out" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </c:otherwise>
        </c:choose>
        <security:authorize access="hasRole('ADMIN')">
            <br /><a href="<c:url value="/user" />">Manage User Accounts</a><br />
        </security:authorize>
        <h3>Lectures:</h3>
        <security:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/material/create" />">Create Lecture</a><br /><br />
        </security:authorize>
        <c:choose>
            <c:when test="${fn:length(lecturesDatabase) == 0}">
                <i>There are no lectures in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${lecturesDatabase}" var="lectures">
                    <a href="<c:url value="/material/view/${lectures.id}" />">
                        <c:out value="${lectures.title}" /></a>
                        <security:authorize access="hasRole('ADMIN')">
                        [<a href="<c:url value="/material/edit/${lectures.id}" />">Edit</a>]
                    </security:authorize>
                    <security:authorize access="hasRole('ADMIN')">
                        [<a href="<c:url value="/material/delete/${lectures.id}" />">Delete</a>]
                    </security:authorize>
                    <br />
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <br /><br />
        <a href="<c:url value="/home/comment/history" />">Comment History</a><br /><br />
        <h2>Poll Question:</h2>
        <c:choose>
            <c:when test="${fn:length(PollQues) == 0}">
                <i>There are no poll question.</i>
            </c:when>
            <c:otherwise>
                <table style="white-space:nowrap;width:70%;">
                    <tr align="left"> 
                        <th>QID</th><th>Question</th><th>Author</th><th>Action</th>
                    </tr>
                    <tr> 
                        <c:forEach items="${PollQues}" var="pollQ">
                            <td>${pollQ.pollId}</td>
                            <td>${pollQ.question}</td>
                            <td>${pollQ.username}</td>
                            <td> [<a href="<c:url value="/poll/view/${pollQ.pollId}" />">View</a>]</td>
                        <br />
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
