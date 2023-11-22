package neu.com.repository;

import neu.com.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Optional<Lesson> findByLessonTitle(String lessonTitle);

    Optional<Lesson> findByLessonTitleAndLessonIdNot(String lessonTitle, Long lessonId);
}
