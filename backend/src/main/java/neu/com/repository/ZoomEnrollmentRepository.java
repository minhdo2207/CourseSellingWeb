package neu.com.repository;

import neu.com.model.Enrollment;
import neu.com.model.ZoomClass;
import neu.com.model.ZoomEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZoomEnrollmentRepository extends JpaRepository<ZoomEnrollment, Long> {

    List<ZoomEnrollment> findByZoomClass(Optional<ZoomClass> byId);

    Optional<ZoomEnrollment> findByEnrollmentAndZoomClass(Enrollment enrollment, ZoomClass zoomClass);
}
