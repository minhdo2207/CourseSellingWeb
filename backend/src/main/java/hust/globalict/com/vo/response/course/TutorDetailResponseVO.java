package hust.globalict.com.vo.response.course;

import hust.globalict.com.model.Tutor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class TutorDetailResponseVO extends UserDetailResponseVO {
    private List<ClassResponseVO> classVos;
    private Tutor tutor;
    private Long teachingStatus;
}
