package neu.com.vo.dtomapper;

import neu.com.model.Document;
import neu.com.vo.response.course.DocumentResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface DocumentResponseVOMapper extends CommonMapper<Document, DocumentResponseVO> {
}
