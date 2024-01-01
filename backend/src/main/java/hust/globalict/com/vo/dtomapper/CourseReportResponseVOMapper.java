package hust.globalict.com.vo.dtomapper;


import hust.globalict.com.vo.response.course.CourseReportResponseVO;
import hust.globalict.com.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseReportResponseVOMapper extends CommonMapper<Course, CourseReportResponseVO>{
}
