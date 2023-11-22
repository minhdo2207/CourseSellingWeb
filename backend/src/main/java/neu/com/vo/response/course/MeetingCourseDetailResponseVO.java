package neu.com.vo.response.course;

import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import neu.com.vo.response.ChapterResponseVO;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Data
@SuperBuilder
public class MeetingCourseDetailResponseVO extends CourseDetailResponseVO{
    //Add Classes
    private List<ClassResponseVO> classVos;
}
