package neu.com.vo.dtomapper;

import neu.com.model.Lesson;
import neu.com.vo.request.course.LessonRequestVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface LessonRequestVOMapper extends CommonMapper<LessonRequestVO, Lesson> {
}
