<!DOCTYPE html>
<html>
    <head>
        <title>Poll Create</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/cslogout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Create new Poll</h2>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="createPoll">
            <form:label path="question">Question</form:label><br /> 
            <form:textarea path="question" rows="1" cols="20" required="required"/><br /><br />

            <form:label path="optionA">Option A</form:label><br /> 
            <form:textarea path="optionA" rows="2" cols="30" required="required"/><br /><br />  
            <form:label path="optionB">Option B</form:label><br /> 
            <form:textarea path="optionB" rows="2" cols="30" required="required"/><br /><br />        
            <form:label path="optionC">Option C</form:label><br /> 
            <form:textarea path="optionC" rows="2" cols="30" /><br /><br />  
            <form:label path="optionD">Option D</form:label><br /> 
            <form:textarea path="optionD" rows="2" cols="30" /><br /><br />  
            <input type="submit" value="Submit"/>
        </form:form>
            <br/>
        <a href="<c:url value="/home/list" />">Return to home page</a>
    </body>
</html>
