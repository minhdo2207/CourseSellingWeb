package neu.com.service.courseservice.chapter;

import com.naharoo.commons.mapstruct.MappingFacade;
import neu.com.configuration.exception.InvalidInputRequestException;
import neu.com.model.Chapter;
import neu.com.model.Course;
import neu.com.repository.ChapterRepository;
import neu.com.repository.CourseRepository;
import neu.com.service.courseservice.chapter.ChapterService;
import neu.com.vo.request.ChapterRequestVO;
import neu.com.vo.response.ChapterDetailResponseVO;
import neu.com.vo.response.ChapterResponseVO;
import neu.com.vo.response.course.LessonResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MappingFacade mapper;

    @Override
    public ChapterDetailResponseVO getChapterDetail(Long chapterId) {

        //Check if chapter exitst
        Optional<Chapter> chapterOptional = chapterRepository.findById(chapterId);
        if (!chapterOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_chapter_notfound");
        }
        ChapterDetailResponseVO chapterDetailResponseVO = mapper.map(chapterOptional.get(), ChapterDetailResponseVO.class);
        chapterDetailResponseVO.setLessonVos(mapper.mapAsList(chapterOptional.get().getLessons(), LessonResponseVO.class));
        return chapterDetailResponseVO;
    }

    @Override
    public ChapterResponseVO updateChapter(ChapterRequestVO chapterRequestVO, Long chapterId) {
        //Check if chapter exitst
        Optional<Chapter> chapterOptional = chapterRepository.findById(chapterId);
        if (!chapterOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_chapter_notfound");
        }
        //Check the title if exists
        if (chapterRepository.findByChapterTitleAndChapterIdNot(chapterRequestVO.getChapterTitle(), chapterId).isPresent()) {
            throw new InvalidInputRequestException("msg_error_chapter_tile_already_exists");
        }
        Chapter chapter = chapterOptional.get();
        chapter.setChapterTitle(chapterRequestVO.getChapterTitle());
        chapterRepository.save(chapter);

        return mapper.map(chapter, ChapterResponseVO.class);
    }

    @Override
    public ChapterResponseVO addChapter(ChapterRequestVO chapterRequestVO, Long courseId) {
        //Check if course exitst
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_course_notfound");
        }

        //Check the title if exists
        if (chapterRepository.findByChapterTitle(chapterRequestVO.getChapterTitle()).isPresent()) {
            throw new InvalidInputRequestException("msg_error_course_tile_already_exists");
        }

        Chapter chapter = mapper.map(chapterRequestVO,Chapter.class);
        chapter.setCourse(courseOptional.get());
        chapterRepository.save(chapter);
        return mapper.map(chapter, ChapterResponseVO.class);
    }

    @Override
    public ChapterResponseVO deleteChapter(Long chapterId) {
        //Check if chapter exitst
        Optional<Chapter> chapterOptional = chapterRepository.findById(chapterId);
        if (!chapterOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_chapter_notfound");
        }
        Chapter chapter= chapterOptional.get();
        chapterRepository.delete(chapter);

        return mapper.map(chapter, ChapterResponseVO.class);
    }
}
