<%@page import="fr.epita.quiz.model.Choices"%>

<%@page import="fr.epita.quiz.model.Questions"%>

<%@page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="fr.epita.quiz.*"%>

<jsp:useBean id="questDao" type="fr.epita.quiz.dao.QuestionDao"
	scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"

    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Quiz Web Application</title>
</head>

<body>

	<form method="POST" action="quest.html">

		Question: <input type="text" name="question" /><br /> Choice1: <input
			type="text" name="choice1" /><br /> Choice2: <input type="text"
			name="choice2" /><br /> Choice3: <input type="text" name="choice3" /><br />

		Correct Answer: <input type="text" name="answer" /><br /> <input
			type="submit" value="Add" />

		<ol>

			<%
				for (Questions quest : questDao.getAllQuestions()) {
			%>

			<br />

			<li><%=quest.getQuestion()%></li>

			<%
				}
			%>

		</ol>

		<hr>

	</form>

</body>

</html>