package neu.com.service.courseservice.lesson;

import com.naharoo.commons.mapstruct.MappingFacade;
import neu.com.configuration.exception.InvalidInputRequestException;
import neu.com.model.Chapter;
import neu.com.model.Lesson;
import neu.com.repository.ChapterRepository;
import neu.com.repository.LessonRepository;
import neu.com.vo.request.course.LessonRequestVO;
import neu.com.vo.response.course.DocumentResponseVO;
import neu.com.vo.response.course.LessonDetailResponseVO;
import neu.com.vo.response.course.LessonResponseVO;
import neu.com.vo.response.course.QuestionResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private MappingFacade mapper;

    @Override
    public LessonDetailResponseVO getLessonDetail(Long lessonId) {
        //Check if chapter exitst
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
        if (!lessonOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_lesson_notfound");
        }
        LessonDetailResponseVO lessonDetailResponseVO = mapper.map(lessonOptional.get(), LessonDetailResponseVO.class);
        lessonDetailResponseVO.setDocuments(mapper.mapAsList(lessonOptional.get().getDocuments(), DocumentResponseVO.class));
        lessonDetailResponseVO.setQuestions(mapper.mapAsList(lessonOptional.get().getQuestions(), QuestionResponseVO.class));
        return lessonDetailResponseVO;
    }

    @Override
    public LessonResponseVO updateLesson(LessonRequestVO lessonRequestVO, Long lessonId) {
        //Check if lesson exitst
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
        if (!lessonOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_lesson_notfound");
        }
        //Check the title if exists
        if (lessonRepository.findByLessonTitleAndLessonIdNot(lessonRequestVO.getLessonTitle(), lessonId).isPresent()) {
            throw new InvalidInputRequestException("msg_error_lesson_tile_already_exists");
        }
        Lesson lesson = lessonOptional.get();
        lesson.setLessonTitle(lessonRequestVO.getLessonTitle());
        lesson.setLessonVideoLink(lessonRequestVO.getLessonVideoLink());
        lessonRepository.save(lesson);

        return mapper.map(lesson, LessonResponseVO.class);
    }

    @Override
    public LessonResponseVO addLesson(LessonRequestVO lessonRequestVO, Long chapterId) {
        //Check if chapter exitst
        Optional<Chapter> chapterOptional = chapterRepository.findById(chapterId);
        if (!chapterOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_chapter_notfound");
        }

        if (lessonRepository.findByLessonTitle(lessonRequestVO.getLessonTitle()).isPresent()) {
            throw new InvalidInputRequestException("msg_error_lesson_tile_already_exists");
        }

        Lesson lesson = mapper.map(lessonRequestVO, Lesson.class);
        lesson.setChapter(chapterOptional.get());
        lessonRepository.save(lesson);
        return mapper.map(lesson, LessonResponseVO.class);
    }

    @Override
    public LessonResponseVO deleteLesson(Long lessonId) {
        //Check if lesson exitst
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
        if (!lessonOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_lesson_notfound");
        }
        Lesson lesson = lessonOptional.get();
        lessonRepository.delete(lesson);
        return mapper.map(lesson, LessonResponseVO.class);
    }
}
