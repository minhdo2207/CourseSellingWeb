package neu.com.vo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChapterRequestVO {
    @NotBlank(message = "msg_error_invalid_chapter_title")
    private String chapterTitle;
}
