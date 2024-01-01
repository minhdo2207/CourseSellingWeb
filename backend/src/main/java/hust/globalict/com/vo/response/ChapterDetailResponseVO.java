package hust.globalict.com.vo.response;

import lombok.Data;
import hust.globalict.com.vo.response.course.LessonResponseVO;

import java.util.List;

@Data

public class ChapterDetailResponseVO {

    private Long chapterId;

    private String chapterTitle;

    private List<LessonResponseVO> lessonVos;

}
