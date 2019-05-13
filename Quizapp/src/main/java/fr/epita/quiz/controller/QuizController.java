package fr.epita.quiz.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.epita.quiz.dao.QuestionDao;
import fr.epita.quiz.model.Questions;

@Controller
public class QuizController {

	@Autowired
	private QuestionDao questDao;

	@RequestMapping(value = "/adminquest")
	public ModelAndView getQuestions(HttpServletRequest request) {
		
		String question = request.getParameter("question");
		String choice1 = request.getParameter("choice1");
		String choice2 = request.getParameter("choice2");
		String choice3 = request.getParameter("choice3");
		String answer = request.getParameter("answer");

		Questions questions = new Questions();
		
		if (question != null && choice1 != null && choice2 != null && choice3 != null && answer != null) {
			String[] choiceList = new String[] { choice1, choice2, choice3};
			questions.setQuestion(question);
			questions.setChoices(choiceList);
			questions.setAnswer(answer);
			
			questDao.persist(questions);
			
		}

		return new ModelAndView("adminquest.jsp", "questDao", questDao);
	}
	
	@RequestMapping(value = "/quiz")
	public ModelAndView load(HttpServletRequest request) {
		return new ModelAndView("quest.jsp", "questDao", questDao);
		
	}
	
	@RequestMapping(value = "/quest")
	public ModelAndView submit(HttpServletRequest request) {
		int score = 0;
		String[] questionIds = request.getParameterValues("questionId");
		for(String questionId: questionIds) {
			System.out.println(questionId);
			String correctAnswer = questDao.findCorrectAnswerById(Integer.parseInt(questionId));
			if(correctAnswer != null && correctAnswer.equals(request.getParameter("question_" + questionId))) {
				score++;
			}
		}
		
		request.setAttribute("score", score);
		return new ModelAndView("quest.jsp", "questDao", questDao);
	}
}
