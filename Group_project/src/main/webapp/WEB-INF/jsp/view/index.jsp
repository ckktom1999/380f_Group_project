<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>Poll page</title>
    </head>
    <body>
        <h1>Poll Question:</h1>
        <c:choose>
            <c:when test="${fn:length(PollQues) == 0}">
                <i>There are no poll question.</i>
            </c:when>
            <c:otherwise>
                <table style="white-space:nowrap;width:70%;">
                    <tr align="left"> 
                        <th>QID</th> <th>Question</th> <th>Author</th> <th>Action</th>
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
