package neu.com.repository;

import neu.com.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findByQuestionAndQuestionIdNot(String question, Long questionId);

    Optional<Question> findByQuestion(String question);
}
