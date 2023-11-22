package neu.com.vo.request.course;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LessonRequestVO {
    @NotBlank(message = "msg_error_invalid_lesson_title")
    private String lessonTitle;
    @NotBlank(message = "msg_error_invalid_lesson_video_link")
    private String lessonVideoLink;
}
