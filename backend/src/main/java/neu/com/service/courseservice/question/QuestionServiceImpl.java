package neu.com.service.courseservice.question;

import com.naharoo.commons.mapstruct.MappingFacade;
import neu.com.configuration.exception.InvalidInputRequestException;
import neu.com.model.Lesson;
import neu.com.model.Question;
import neu.com.repository.LessonRepository;
import neu.com.repository.QuestionRepository;
import neu.com.vo.request.course.QuestionRequestVO;
import neu.com.vo.response.course.QuestionResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private MappingFacade mapper;

    @Override
    public QuestionResponseVO getQuestionDetail(Long questionId) {
        //Check if question exitst
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (!questionOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_question_notfound");
        }
        return mapper.map(questionOptional.get(), QuestionResponseVO.class);
    }

    @Override
    public QuestionResponseVO updateQuestion(QuestionRequestVO questionRequestVO, Long questionId) {
        //Check if question exitst
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (!questionOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_question_notfound");
        }
        //Check the question if exists
        if (questionRepository.findByQuestionAndQuestionIdNot(questionRequestVO.getQuestion(), questionId).isPresent()) {
            throw new InvalidInputRequestException("msg_error_question_already_exists");
        }

        Question question = questionOptional.get();
        question.setQuestion(questionRequestVO.getQuestion());
        question.setChoiceA(questionRequestVO.getChoiceA());
        question.setChoiceB(questionRequestVO.getChoiceB());
        question.setChoiceC(questionRequestVO.getChoiceC());
        question.setChoiceD(questionRequestVO.getChoiceD());
        question.setSolution(questionRequestVO.getSolution());
        questionRepository.save(question);

        return mapper.map(question, QuestionResponseVO.class);

    }

    @Override
    public QuestionResponseVO addQuesion(QuestionRequestVO questionRequestVO, Long lessonId) {
        //Check if lesson exitst
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
        if (!lessonOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_lesson_notfound");
        }

        //Check the question if exists
        if (questionRepository.findByQuestion(questionRequestVO.getQuestion()).isPresent()) {
            throw new InvalidInputRequestException("msg_error_question_already_exists");
        }

        Question question = mapper.map(questionRequestVO, Question.class);
        question.setLesson(lessonOptional.get());
        questionRepository.save(question);
        return mapper.map(question, QuestionResponseVO.class);
    }

    @Override
    public QuestionResponseVO deleteQuestion(Long questionId) {
        //Check if question exitst
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (!questionOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_question_notfound");
        }
        Question question = questionOptional.get();
        questionRepository.delete(question);
        return mapper.map(question, QuestionResponseVO.class);
    }
}
