package neu.com.repository;

import neu.com.model.ZoomClass;
import neu.com.utils.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_VERSION + "/admin/chapter")
@Repository
public interface ClassRepository extends JpaRepository<ZoomClass, Long> {

}
