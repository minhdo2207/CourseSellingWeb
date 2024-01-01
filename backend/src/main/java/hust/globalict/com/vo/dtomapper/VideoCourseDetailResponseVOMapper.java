package hust.globalict.com.vo.dtomapper;


import hust.globalict.com.vo.response.course.VideoCourseDetailResponseVO;
import hust.globalict.com.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoCourseDetailResponseVOMapper extends CommonMapper<Course, VideoCourseDetailResponseVO>{
}
