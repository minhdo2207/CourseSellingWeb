package neu.com.vo.dtomapper;

import neu.com.model.Course;
import neu.com.model.Lesson;
import neu.com.vo.response.course.CourseResponseVO;
import neu.com.vo.response.course.LessonResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface LessonResponseVOMapper extends CommonMapper<Lesson, LessonResponseVO>{
}
