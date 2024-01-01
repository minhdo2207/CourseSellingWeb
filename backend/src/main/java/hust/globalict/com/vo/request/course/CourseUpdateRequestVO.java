package hust.globalict.com.vo.request.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateRequestVO {
    @NotBlank(message = "msg_error_invalid_course_title")
    private String courseTitle ;
    @NotNull(message = "msg_error_invalid_price")
    private  Long coursePrice;
}
