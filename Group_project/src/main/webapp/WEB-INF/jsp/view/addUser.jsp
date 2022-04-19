<!DOCTYPE html>
<html>
    <head><title>Customer Support</title></head>
    <body>
        <c:url var="logoutUrl" value="/cslogout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Create a User</h2>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="courseUser">
            <form:label path="username">Username</form:label><br/>
            <form:input type="text" path="username" required="required"/><br/><br/>
            <form:label path="password">Password</form:label><br/>
            <form:input type="text" path="password" required="required"/><br/><br/>
            <form:label path="full_name">Full Name</form:label><br/>
            <form:input type="text" path="full_name" required="required"/><br/><br/>
            <form:label path="phone_number">8 Digit Phone Number</form:label><br/>
            <form:input type="number" path="phone_number" min="10000000" max="99999999" required="required"/><br/><br/>
            <form:label path="address">Address</form:label><br />
            <form:textarea path="address" rows="5" cols="30" required="required"/><br /><br />
            <form:label path="roles">Roles</form:label><br/>
            <form:checkbox path="roles" value="ROLE_USER" checked="checked"/>ROLE_USER
            <form:checkbox path="roles" value="ROLE_ADMIN" />ROLE_ADMIN
            <br /><br />
            <input type="submit" value="Add User"/>
        </form:form>
        <br />
        <a href="<c:url value="/user/list" />">Return to user management page</a>
    </body>
</html>