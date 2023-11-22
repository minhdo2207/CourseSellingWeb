package neu.com.controller.coursemanagement;

import jakarta.validation.Valid;
import neu.com.service.courseservice.question.QuestionService;
import neu.com.utils.Constants;
import neu.com.vo.request.course.QuestionRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_VERSION + "/admin/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /**
     * Get question detail by ID
     */

    @GetMapping("/{questionId}")
    public Object getQuestionDetail(@PathVariable("questionId") Long questionId) {
        return questionService.getQuestionDetail(questionId);

    }

    /**
     * Update question
     */

    @PutMapping("/{questionId}")
    public Object updateQuestion(@Valid @RequestBody QuestionRequestVO questionRequestVO, @PathVariable("questionId") Long questionId) {
        return questionService.updateQuestion(questionRequestVO, questionId);
    }

    /**
     * Create question
     */

    @PostMapping("lesson/{lessonId}")
    public Object addQuesion(@Valid @RequestBody QuestionRequestVO questionRequestVO, @PathVariable("lessonId") Long lessonId) {
        return questionService.addQuesion(questionRequestVO, lessonId);
    }

    /**
     * Delete question
     */

    @DeleteMapping("/{questionId}")
    public Object deleteQuestin(@PathVariable("questionId") Long questionId) {
        return questionService.deleteQuestion(questionId);
    }
}
