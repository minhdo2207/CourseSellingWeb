package hust.globalict.com.vo.dtomapper;


import hust.globalict.com.vo.response.course.CourseResponseVO;
import hust.globalict.com.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseResponseVOMapper extends CommonMapper<Course, CourseResponseVO>{
}
