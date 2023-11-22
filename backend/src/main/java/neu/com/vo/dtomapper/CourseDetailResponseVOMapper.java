package neu.com.vo.dtomapper;


import neu.com.model.Course;
import neu.com.vo.response.course.CourseDetailResponseVO;
import neu.com.vo.response.course.CourseResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseDetailResponseVOMapper extends CommonMapper<Course, CourseDetailResponseVO>{
}
