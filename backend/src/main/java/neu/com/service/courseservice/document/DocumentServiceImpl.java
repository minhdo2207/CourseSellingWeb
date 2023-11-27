package neu.com.service.courseservice.document;

import com.naharoo.commons.mapstruct.MappingFacade;
import neu.com.configuration.exception.InvalidInputRequestException;
import neu.com.model.Document;
import neu.com.model.Lesson;
import neu.com.repository.DocumentRepository;
import neu.com.repository.LessonRepository;
import neu.com.vo.request.course.DocumentRequestVO;
import neu.com.vo.response.course.DocumentResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private MappingFacade mapper;

    @Override
    public DocumentResponseVO getDocumentDetail(Long documentId) {
        //Check if document exitst
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (!documentOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_document_notfound");
        }
        return mapper.map(documentOptional.get(), DocumentResponseVO.class);
    }

    @Override
    public DocumentResponseVO updateDocument(DocumentRequestVO documentRequestVO, Long documentId) {
        //Check if doucument exitst
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (!documentOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_document_notfound");
        }
        //Check the document if exists
        if (documentRepository.findByDocumentTitleAndDocumentIdNot(documentRequestVO.getDocumentTitle(), documentId).isPresent()) {
            throw new InvalidInputRequestException("msg_error_question_already_exists");
        }
        Document document = documentOptional.get();
        document.setDocumentTitle(documentRequestVO.getDocumentTitle());
        document.setDocumentLink(documentRequestVO.getDocumentLink());
        documentRepository.save(document);
        return mapper.map(document, DocumentResponseVO.class);
    }

    @Override
    public DocumentResponseVO addDocument(DocumentRequestVO documentRequestVO, Long lessonId) {
        //Check if lesson exitst
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
        if (!lessonOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_lesson_notfound");
        }
        //Check the document if exists
        if (documentRepository.findByDocumentTitle(documentRequestVO.getDocumentTitle()).isPresent()) {
            throw new InvalidInputRequestException("msg_error_question_already_exists");
        }
        Document document = mapper.map(documentRequestVO, Document.class);
        document.setLesson(lessonOptional.get());
        documentRepository.save(document);

        return mapper.map(document, DocumentResponseVO.class);

    }

    @Override
    public DocumentResponseVO deleteDocument(Long documentId) {
        //Check if lesson exitst
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (!documentOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_document_notfound");
        }
        Document document = documentOptional.get();
        documentRepository.delete(document);
        return mapper.map(document, DocumentResponseVO.class);
    }
}
