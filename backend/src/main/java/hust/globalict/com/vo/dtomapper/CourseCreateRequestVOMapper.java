package hust.globalict.com.vo.dtomapper;

import hust.globalict.com.model.Course;
import hust.globalict.com.vo.request.course.CourseCreateRequestVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseCreateRequestVOMapper extends CommonMapper<CourseCreateRequestVO, Course> {
}
