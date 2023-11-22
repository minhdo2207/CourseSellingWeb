package neu.com.vo.dtomapper;


import neu.com.model.Course;
import neu.com.vo.response.course.MeetingCourseDetailResponseVO;
import neu.com.vo.response.course.VideoCourseDetailResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoCourseDetailResponseVOMapper extends CommonMapper<Course, VideoCourseDetailResponseVO>{
}
