
<html>
    <head>
        <title>Question Details</title>
    </head>
    <body>

        <h3>QID:${pollQues.pollId}, ${pollQues.question} </h3> 
        <i>(Question created at ${pollQues.pqDate})</i> 

        <br></br>

        <form:form method="POST" enctype="multipart/form-data" modelAttribute="addReply">
            <form:label path="option"></form:label>
            A<form:radiobutton path="option" value="A"/> &nbsp; ${pollQues.optionA} <br/>
            B<form:radiobutton path="option" value="B"/> &nbsp; ${pollQues.optionB} <br/>
            C<form:radiobutton path="option" value="C"/> &nbsp; ${pollQues.optionC} <br/>
            D<form:radiobutton path="option" value="D"/> &nbsp; ${pollQues.optionD} <br/><br/>
            <input type="submit" value="Add Reply"/>

            <input type="hidden" name="pollId" value="${pollQues.pollId}">  
        </form:form>
        <p style="color:green">current number of votes: ${numberOfVotes} </p>
        <p style="color:blue"> ${message} </p>
        <a href="<c:url value="/home/list" />">Return to home page</a>
    </body>
</html>
