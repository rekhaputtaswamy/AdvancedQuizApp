<%@page import="fr.epita.quiz.model.Questions"%>
<%@ page import="java.sql.*"%>
<jsp:useBean id="questDao" type="fr.epita.quiz.dao.QuestionDao"
	scope="request" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quiz Web Application</title>
</head>
<body>
	<form method="post" action="quest.html">
		<% int count = 1;%>
		<ol type="1">
			<%
			for (Questions quest : questDao.getAllQuestions()) {
		%>
			
			<li><%=quest.getQuestion()%> <input type="hidden"
				name="questionId" value="<%=count%>">
				<ol type="a">
					<%
					for (int j=0; j < quest.getChoices().length; j++) {	
				%>
					<li><input type="radio" name="question_<%=count %>"
						value="<%=quest.getChoices()[j]%>"> <%=quest.getChoices()[j]%></li>
					<% } %>
				</ol></li>
			<% count++; %>
			<% } %>
		</ol>
		<br>
		<input type="hidden"
				name="score">
		
		Score: <%= request.getAttribute("score") %>
		
		<br><br><input type="submit" value="Submit">
	</form>
</body>
</html>
