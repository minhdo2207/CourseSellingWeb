package neu.com.service.courseservice.chapter;

import neu.com.vo.request.ChapterRequestVO;
import neu.com.vo.response.ChapterDetailResponseVO;
import neu.com.vo.response.ChapterResponseVO;

public interface ChapterService {
    ChapterDetailResponseVO getChapterDetail(Long chapterId);

    ChapterResponseVO updateChapter(ChapterRequestVO chapterRequestVO, Long chapterId);

    ChapterResponseVO addChapter(ChapterRequestVO chapterRequestVO, Long courseId);

    ChapterResponseVO deleteChapter(Long chapterId);
}
