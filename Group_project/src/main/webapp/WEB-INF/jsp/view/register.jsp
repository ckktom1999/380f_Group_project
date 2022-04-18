<!DOCTYPE html>
<html>
    <head><title>Course User Register</title></head>
    <body>
        <h2>Register</h2>
        Pleass fill in the information to register the account! <br /><br />
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="courseUser">
            <form:label path="username">Username</form:label><br/>
            <form:input type="text" path="username" /><br/><br/>
            <form:label path="password">Password</form:label><br/>
            <form:input type="text" path="password" /><br/><br/>
            <form:label path="full_name">Full Name</form:label><br/>
            <form:input type="text" path="full_name" /><br/><br/>
            <form:label path="phone_number">Phone Number</form:label><br/>
            <form:input type="number" path="phone_number" /><br/><br/>
            <form:label path="address">Address</form:label><br />
            <form:textarea path="address" rows="5" cols="30" /><br /><br />
            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>