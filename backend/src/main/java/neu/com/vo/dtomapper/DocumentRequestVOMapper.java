package neu.com.vo.dtomapper;

import neu.com.model.Document;
import neu.com.vo.request.course.DocumentRequestVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface DocumentRequestVOMapper extends CommonMapper<DocumentRequestVO, Document> {
}

