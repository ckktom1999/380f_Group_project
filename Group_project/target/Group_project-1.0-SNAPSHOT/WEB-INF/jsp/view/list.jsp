<!DOCTYPE html>
<html>
    <head>
        <title>Guest Book</title>
    </head>
    <body>
        <h2> COMP S380F Web Applications </h2>
        <h3>Lectures:</h3>
        <c:choose>
            <c:when test="${fn:length(lecturesDatabase) == 0}">
                <i>There are no lectures in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${lecturesDatabase}" var="lectures">
                    <a href="<c:url value="/material/view/${lectures.id}" />">
                        <c:out value="${lectures.title}" /></a>
                    <br />
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </body>
</html>
