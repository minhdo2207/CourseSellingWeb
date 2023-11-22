package neu.com.service.courseservice.lesson;

import neu.com.vo.request.course.LessonRequestVO;
import neu.com.vo.response.course.LessonDetailResponseVO;
import neu.com.vo.response.course.LessonResponseVO;

public interface LessonService {
    LessonDetailResponseVO getLessonDetail(Long lessonId);

    LessonResponseVO updateLesson(LessonRequestVO lessonRequestVO, Long lessonId);

    LessonResponseVO addLesson(LessonRequestVO lessonRequestVO, Long chapterId);

    LessonResponseVO deleteLesson(Long lessonId);
}
