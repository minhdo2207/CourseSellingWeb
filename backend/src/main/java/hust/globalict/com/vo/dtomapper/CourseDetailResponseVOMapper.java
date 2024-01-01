package hust.globalict.com.vo.dtomapper;


import hust.globalict.com.vo.response.course.CourseDetailResponseVO;
import hust.globalict.com.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseDetailResponseVOMapper extends CommonMapper<Course, CourseDetailResponseVO>{
}
