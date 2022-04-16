<!DOCTYPE html>
<html>
    <head>
        <title>Course material page</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/cslogout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2><c:out value="${lecture.title}" /></h2>
        <security:authorize access="hasRole('ADMIN') or principal.username=='${ticket.customerName}'">
            [<a href="<c:url value="/material/edit/${lecture.id}" />">Edit</a>]
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            [<a href="<c:url value="/material/delete/${lecture.id}" />">Delete</a>]
        </security:authorize>
        <br /><br />
        <h3>Lecture Note:</h3>
        <c:if test="${fn:length(lecture.lecture_notes_attachments) > 0}">
            <c:forEach items="${lecture.lecture_notes_attachments}" var="attachment" varStatus="status">
                <c:if test="${!status.first}">, </c:if>
                <a href="<c:url value="/material/${lecture.id}/lecture_notes_attachments/${attachment.name}" />">
                    <c:out value="${attachment.name}" /></a>
            </c:forEach><br /><br />
        </c:if>
        <c:if test="${fn:length(lecture.lecture_notes_attachments) == 0}">
            No lecture note!
        </c:if>
        <br />
        <h3>Lecture Note:</h3>
        <c:if test="${fn:length(lecture.tutorial_notes_attachments) > 0}">
            <c:forEach items="${lecture.tutorial_notes_attachments}" var="attachment" varStatus="status">
                <c:if test="${!status.first}">, </c:if>
                <a href="<c:url value="/material/${lecture.id}/tutorial_notes_attachments/${attachment.name}" />">
                    <c:out value="${attachment.name}" /></a>
            </c:forEach><br /><br />
        </c:if>
        <c:if test="${fn:length(lecture.tutorial_notes_attachments) == 0}">
            No Tutorial note!
        </c:if>
        <h3>Comment:</h3>
        <c:if test="${fn:length(lecture.lecture_comments) > 0}">
            <c:forEach items="${lecture.lecture_comments}" var="comment">
                    <c:out value="${comment.body}" /></a>
            </c:forEach>
        </c:if>
        <c:if test="${fn:length(lecture.lecture_comments) == 0}">
            No comment!
        </c:if>
        <br />
        <a href="<c:url value="/home/list" />">Return to home page</a>
    </body>
</html>