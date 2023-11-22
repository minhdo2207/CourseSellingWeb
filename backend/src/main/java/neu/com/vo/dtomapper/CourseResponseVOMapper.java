package neu.com.vo.dtomapper;


import neu.com.model.Course;
import neu.com.vo.response.course.CourseResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseResponseVOMapper extends CommonMapper<Course, CourseResponseVO>{
}
