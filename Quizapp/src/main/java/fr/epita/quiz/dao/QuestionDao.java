package fr.epita.quiz.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.quiz.model.Questions;

@Component
public class QuestionDao {
	// Injected database connection:
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void persist(Questions question) {
		if (question != null) {
			em.persist(question);
		}
	}

	public List<Questions> getAllQuestions() {
		TypedQuery<Questions> query = em.createQuery("SELECT g FROM Questions g ORDER BY g.id", Questions.class);
		return query.getResultList();
	}
	
	public String findCorrectAnswerById(int questionId) {
		String queryString = "SELECT g FROM Questions g where id=" + questionId;
		TypedQuery<Questions> query = em.createQuery(queryString, Questions.class);
		List<Questions> questions = query.getResultList();
		
		return questions.get(0).getAnswer();
	}

	public void removeData(Questions questions) {
		em.getTransaction().begin();
		Query query = em.createQuery("DELETE FROM Questions where id=" + questions.getId(), Questions.class);
		query.executeUpdate();

		em.getTransaction().commit();

	}
}