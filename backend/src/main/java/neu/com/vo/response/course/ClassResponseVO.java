package neu.com.vo.response.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import neu.com.model.Tutor;

@Data
public class ClassResponseVO {
    private Long classId;
    @JsonIgnore
    private Tutor tutor;
    private String tutorName;
    private String className;
    private String classLink;
    private Long totalStudents;
    private Boolean classStatus;

}
