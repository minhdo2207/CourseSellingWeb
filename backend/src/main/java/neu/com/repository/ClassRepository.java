package neu.com.repository;

import neu.com.model.ZoomClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClassRepository extends JpaRepository<ZoomClass, Long> {

    Optional<ZoomClass> findByClassName(String className);

    Optional<ZoomClass> findByClassLink(String classLink);

    Optional<ZoomClass> findByClassNameAndClassIdNot(String className, Long classId);

    Optional<ZoomClass> findByClassLinkAndClassIdNot(String classLink, Long classId);
}
