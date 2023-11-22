package neu.com.vo.dtomapper;

import neu.com.model.Question;
import neu.com.vo.request.ChapterRequestVO;
import neu.com.vo.request.course.QuestionRequestVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface QuestionRequestVOMapper extends CommonMapper<QuestionRequestVO, Question> {
}

