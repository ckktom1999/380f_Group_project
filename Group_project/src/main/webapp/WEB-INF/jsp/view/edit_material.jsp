<!DOCTYPE html>
<html>
    <head>
        <title>ourse material page</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/cslogout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>${lecture.title}</h2>
        ${current_page = "edit" ; ""}
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="lectureForm">
            <form:label path="title">Title</form:label><br/>
            <form:input type="text" path="title" /><br/><br/>
            <c:if test="${fn:length(lecture.lecture_notes_attachments) > 0}">
                <b>Lecture note:</b><br/>
                <ul>
                    <c:forEach items="${lecture.lecture_notes_attachments}" var="attachment">
                        <li>
                            <c:out value="${attachment.name}" />
                            [<a href="<c:url
                                    value="/material/${lecture.id}/delete_lecture_note/${attachment.name}/${current_page}"
                                    />">Delete</a>]
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <b>Add lecture note</b><br />
            <input type="file" name="lecture_notes_attachments" multiple="multiple"/><br/><br/>
            
            <c:if test="${fn:length(lecture.tutorial_notes_attachments) > 0}">
                <b>Totorial note:</b><br/>
                <ul>
                    <c:forEach items="${lecture.tutorial_notes_attachments}" var="attachment">
                        <li>
                            <c:out value="${attachment.name}" />
                            [<a href="<c:url
                                    value="/material/${lecture.id}/delete_tutorial_note/${attachment.name}/${current_page}"
                                    />">Delete</a>]
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <b>Add tutorial note</b><br />
            <input type="file" name="tutorial_notes_attachments" multiple="multiple"/><br/><br/>
            <input type="submit" value="Update"/><br/><br/>
        </form:form>
        <a href="<c:url value="/home/list" />">Return to home page</a>
    </body>
</html> 