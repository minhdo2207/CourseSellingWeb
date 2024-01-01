package hust.globalict.com.vo.request.course;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClassRequestVO {
    private Long tutorId;
    @NotBlank
    private String className;
    private String classLink;
}
