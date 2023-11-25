package neu.com.vo.response.course;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class TutorDetailResponseVO extends UserDetailResponseVO {
    private List<ClassResponseVO> classVos;
    private Long teachingStatus;
}
