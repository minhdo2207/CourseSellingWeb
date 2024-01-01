package hust.globalict.com.vo.request.course;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentRequestVO {
    @NotBlank(message = "msg_error_invalid_document_title")
    private String documentTitle;
    @NotBlank(message = "msg_error_invalid_document_link")
    private String documentLink;
}
