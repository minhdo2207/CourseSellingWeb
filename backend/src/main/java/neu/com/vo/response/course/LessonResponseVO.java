package neu.com.vo.response.course;

import lombok.Data;

@Data
public class LessonResponseVO {
    private Long lessonId;
    private String lessonTitle;

    private String lessonVideoLink;

    private Long lessonVideoView;


}
