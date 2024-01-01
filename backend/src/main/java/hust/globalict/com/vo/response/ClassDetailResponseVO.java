package hust.globalict.com.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hust.globalict.com.model.ZoomEnrollment;
import lombok.Data;
import hust.globalict.com.model.Course;
import hust.globalict.com.model.Tutor;
import hust.globalict.com.vo.response.course.UserResponseVO;

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
