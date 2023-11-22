package neu.com.vo.dtomapper;

import neu.com.model.Chapter;
import neu.com.model.Lesson;
import neu.com.vo.response.ChapterDetailResponseVO;
import neu.com.vo.response.course.LessonDetailResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface LessonDetailResponseVOMapper extends CommonMapper<Lesson, LessonDetailResponseVO>{
}
