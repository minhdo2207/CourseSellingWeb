package hust.globalict.com.vo.request.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDateRequestVO {
    @NotNull(message = "msg_error_invalid_course_start")
    private String courseStart;
    @NotBlank(message = "msg_error_invalid_course_end")
    private String courseEnd;
}
