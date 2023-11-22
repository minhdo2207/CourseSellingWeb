package neu.com.vo.response;

import jakarta.persistence.*;
import lombok.Data;
import neu.com.model.Course;

@Data

public class ChapterResponseVO {

    private Long chapterId;

    private String chapterTitle;
}
