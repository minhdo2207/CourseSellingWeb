package neu.com.vo.dtomapper;

import neu.com.model.Chapter;
import neu.com.model.Course;
import neu.com.vo.response.ChapterResponseVO;
import neu.com.vo.response.course.CourseDetailResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ChapterResponseVOMapper extends CommonMapper<Chapter, ChapterResponseVO>{
}
