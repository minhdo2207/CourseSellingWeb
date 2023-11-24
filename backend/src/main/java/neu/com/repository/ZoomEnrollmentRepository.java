package neu.com.repository;

import neu.com.model.ZoomEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoomEnrollmentRepository extends JpaRepository<ZoomEnrollment, Long> {
}
