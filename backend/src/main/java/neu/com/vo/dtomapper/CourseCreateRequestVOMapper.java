package neu.com.vo.dtomapper;

import neu.com.model.Course;
import neu.com.vo.request.course.CourseCreateRequestVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseCreateRequestVOMapper extends CommonMapper<CourseCreateRequestVO, Course> {
}
