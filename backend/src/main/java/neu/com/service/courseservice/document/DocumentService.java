package neu.com.service.courseservice.document;

import neu.com.vo.request.course.DocumentRequestVO;
import neu.com.vo.response.course.DocumentResponseVO;

public interface DocumentService {
    DocumentResponseVO getDocumentDetail(Long documentId);

    DocumentResponseVO updateDocument(DocumentRequestVO documentRequestVO, Long documentId);

    DocumentResponseVO addDocument(DocumentRequestVO documentRequestVO, Long lessonId);

    DocumentResponseVO deleteDocument(Long documentId);
}
