package neu.com.service.courseservice.question;

import neu.com.vo.request.course.QuestionRequestVO;
import neu.com.vo.response.course.QuestionResponseVO;

public interface QuestionService {
    QuestionResponseVO getQuestionDetail(Long questionId);

    QuestionResponseVO updateQuestion(QuestionRequestVO questionRequestVO, Long questionId);

    QuestionResponseVO addQuesion(QuestionRequestVO questionRequestVO, Long lessonId);

    QuestionResponseVO deleteQuestion(Long questionId);
}
