package hust.globalict.com.vo.response.course;

import lombok.Data;

import java.util.List;

@Data
public class LessonDetailResponseVO {
    private Long lessonId;
    private String lessonTitle;

    private String lessonVideoLink;

    private Long lessonVideoView;

    private List<QuestionResponseVO> questions;

    private List<DocumentResponseVO> documents;
}
