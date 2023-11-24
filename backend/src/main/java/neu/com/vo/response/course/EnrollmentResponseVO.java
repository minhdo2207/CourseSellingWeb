package neu.com.vo.response.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class EnrollmentResponseVO {
    protected Date created;
    @JsonIgnore
    private UserResponseVO user;
    @JsonIgnore
    private CourseResponseVO course;
    private String userName;
    private String courseName;
    private Long status;

}
