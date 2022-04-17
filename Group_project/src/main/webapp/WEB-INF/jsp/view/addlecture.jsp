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

<h2>Create new Lecture</h2>
    <form:form method="POST" enctype="multipart/form-data"
                             modelAttribute="lectureForm">
        <form:label path="title">Title</form:label><br /> 
        <form:input type="text" path="title" /><br /><br />
        <form:label path="comment">Comment</form:label><br />
        <form:textarea path="comment" rows="5" cols="30" /><br /><br />
        <b>Lecature Notes:</b><br />
        <input type="file" name="lecture_notes_attachments" multiple="multiple" /><br /><br />
        <b>Tutorial Notes:</b><br />
        <input type="file" name="tutorial_notes_attachments" multiple="multiple" /><br /><br />
        <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>
