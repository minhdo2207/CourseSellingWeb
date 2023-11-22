package neu.com.controller.coursemanagement;

import jakarta.validation.Valid;
import neu.com.service.courseservice.chapter.ChapterService;
import neu.com.utils.Constants;
import neu.com.vo.request.ChapterRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_VERSION + "/admin/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    /**
     * Get chapter detail by ID
     */

    @GetMapping("/{chapterId}")
    public Object getChapterDetail(@PathVariable("chapterId") Long chapterId) {
        return chapterService.getChapterDetail(chapterId);

    }

    /**
     * Update chapter
     */

    @PutMapping("/{chapterId}")
    public Object updateChapter(@Valid @RequestBody ChapterRequestVO chapterRequestVO, @PathVariable("chapterId") Long chapterId) {
        return chapterService.updateChapter(chapterRequestVO, chapterId);
    }

    /**
     * Create chapter
     */

    @PostMapping("course/{courseId}")
    public Object addChapter(@Valid @RequestBody ChapterRequestVO chapterRequestVO, @PathVariable("courseId") Long courseId) {
        return chapterService.addChapter(chapterRequestVO, courseId);
    }

    /**
     * Delete chapter
     */

    @DeleteMapping("/{chapterId}")
    public Object deleteChapter(@PathVariable("chapterId") Long chapterId) {
        return chapterService.deleteChapter(chapterId);
    }


}
