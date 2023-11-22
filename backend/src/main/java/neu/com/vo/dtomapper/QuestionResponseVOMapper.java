package neu.com.vo.dtomapper;

import neu.com.model.Question;
import neu.com.vo.response.course.QuestionResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface QuestionResponseVOMapper extends CommonMapper<Question, QuestionResponseVO> {
}
