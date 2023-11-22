package neu.com.controller.coursemanagement;

import jakarta.validation.Valid;
import neu.com.service.courseservice.document.DocumentService;
import neu.com.service.courseservice.question.QuestionService;
import neu.com.utils.Constants;
import neu.com.vo.request.course.DocumentRequestVO;
import neu.com.vo.request.course.QuestionRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_VERSION + "/admin/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    /**
     * Get question detail by ID
     */

    @GetMapping("/{documentId}")
    public Object getDocumentDetail(@PathVariable("documentId") Long documentId) {
        return documentService.getDocumentDetail(documentId);

    }

    /**
     * Update question
     */

    @PutMapping("/{documentId}")
    public Object updateDocument(@Valid @RequestBody DocumentRequestVO documentRequestVO, @PathVariable("documentId") Long documentId) {
        return documentService.updateDocument(documentRequestVO, documentId);
    }

    /**
     * Create question
     */

    @PostMapping("lesson/{lessonId}")
    public Object addDocument(@Valid @RequestBody DocumentRequestVO documentRequestVO, @PathVariable("lessonId") Long lessonId) {
        return documentService.addDocument(documentRequestVO, lessonId);
    }

    /**
     * Delete question
     */

    @DeleteMapping("/{documentId}")
    public Object deleteDocument(@PathVariable("documentId") Long documentId) {
        return documentService.deleteDocument(documentId);
    }
}
