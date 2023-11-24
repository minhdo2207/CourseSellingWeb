package neu.com.repository;

import neu.com.model.Tutor;
import neu.com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    List<Object> findByUser(User user);
}
