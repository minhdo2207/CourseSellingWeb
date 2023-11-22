package neu.com.vo.response.course;

import lombok.Data;

@Data
public class QuestionResponseVO {
    private Long questionId;

    private String question;

    private String choiceA;

    private String choiceB;

    private String choiceC;

    private String choiceD;

    private String solution;
}
