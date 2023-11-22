package neu.com.controller.coursemanagement;

import jakarta.validation.Valid;
import neu.com.service.courseservice.lesson.LessonService;
import neu.com.utils.Constants;
import neu.com.vo.request.course.LessonRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_VERSION + "/admin/lesson")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    /**
     * Get lesson detail by ID
     */

    @GetMapping("/{lessonId}")
    public Object getLessonDetail(@PathVariable("lessonId") Long lessonId) {
        return lessonService.getLessonDetail(lessonId);

    }

    /**
     * Update lesson
     */

    @PutMapping("/{lessonId}")
    public Object updateLesson(@Valid @RequestBody LessonRequestVO lessonRequestVO, @PathVariable("lessonId") Long lessonId) {
        return lessonService.updateLesson(lessonRequestVO, lessonId);
    }

    /**
     * Create lesson
     */

    @PostMapping("chapter/{chapterId}")
    public Object addLesson(@Valid @RequestBody LessonRequestVO lessonRequestVO, @PathVariable("chapterId") Long chapterId) {
        return lessonService.addLesson(lessonRequestVO, chapterId);
    }

    /**
     * Delete lesson
     */

    @DeleteMapping("/{lessonId}")
    public Object deleteLesson(@PathVariable("lessonId") Long lessonId) {
        return lessonService.deleteLesson(lessonId);
    }
}
