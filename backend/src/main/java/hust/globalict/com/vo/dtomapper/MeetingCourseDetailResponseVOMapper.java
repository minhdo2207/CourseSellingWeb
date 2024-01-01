package hust.globalict.com.vo.dtomapper;


import hust.globalict.com.vo.response.course.MeetingCourseDetailResponseVO;
import hust.globalict.com.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeetingCourseDetailResponseVOMapper extends CommonMapper<Course, MeetingCourseDetailResponseVO>{
}
