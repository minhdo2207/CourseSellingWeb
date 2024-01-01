package hust.globalict.com.vo.request.course;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreateRequestVO {
    @NotNull(message = "msg_error_invalid_course_type")
    private Long courseType;
    @NotBlank(message = "msg_error_invalid_course_title")
    private String courseTitle;
    @NotNull(message = "msg_error_invalid_price")
    private Long coursePrice;
    @NotNull(message = "msg_error_invalid_course_grade")
    private Long courseGrade;
}
