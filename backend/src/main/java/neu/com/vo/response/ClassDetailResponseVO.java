package neu.com.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import neu.com.model.Course;
import neu.com.model.Tutor;
import neu.com.model.ZoomEnrollment;
import neu.com.vo.response.course.UserResponseVO;

import java.util.List;

@Data

public class ClassDetailResponseVO {
    private Long classId;
    @JsonIgnore
    private Tutor tutor;
    private String tutorName;
    private String className;
    private String classLink;
    private Long totalStudents;
    private Boolean classStatus;
    @JsonIgnore
    private List<ZoomEnrollment> zoomEnrollments;
    private List<UserResponseVO> students;
    @JsonIgnore
    private Course course;
    private Long courseId;

}
