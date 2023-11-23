package neu.com.vo.dtomapper;


import neu.com.model.Course;
import neu.com.vo.response.course.CourseDetailResponseVO;
import neu.com.vo.response.course.CourseReportResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseReportResponseVOMapper extends CommonMapper<Course, CourseReportResponseVO>{
}
