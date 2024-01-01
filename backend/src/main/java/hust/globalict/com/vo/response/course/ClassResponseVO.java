package hust.globalict.com.vo.response.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hust.globalict.com.model.Tutor;
import lombok.Data;

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
