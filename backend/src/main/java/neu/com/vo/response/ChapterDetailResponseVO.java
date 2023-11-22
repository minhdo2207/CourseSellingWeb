package neu.com.vo.response;

import lombok.Data;
import neu.com.model.Lesson;
import neu.com.vo.response.course.LessonResponseVO;

import java.util.List;

@Data

public class ChapterDetailResponseVO {

    private Long chapterId;

    private String chapterTitle;

    private List<LessonResponseVO> lessonVos;

}
