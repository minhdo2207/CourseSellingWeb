package neu.com.vo.dtomapper;

import neu.com.model.Enrollment;
import neu.com.vo.response.course.EnrollmentResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface EnrollmentResponseVOMapper extends CommonMapper<Enrollment, EnrollmentResponseVO> {
}
