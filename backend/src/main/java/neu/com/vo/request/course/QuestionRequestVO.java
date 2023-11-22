package neu.com.vo.request.course;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuestionRequestVO {
    @NotBlank(message = "msg_error_invalid_question")
    private String question;
    @NotBlank(message = "msg_error_invalid_question_choice_a")
    private String choiceA;
    @NotBlank(message = "msg_error_invalid_question_choice_b")
    private String choiceB;
    @NotBlank(message = "msg_error_invalid_question_choice_c")
    private String choiceC;
    @NotBlank(message = "msg_error_invalid_question_choice_d")
    private String choiceD;
    @NotBlank(message = "msg_error_invalid_question_answer")
    private String solution;
}
