package neu.com.vo.dtomapper;

import neu.com.model.Chapter;
import neu.com.vo.request.ChapterRequestVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ChapterRequestVOMapper extends CommonMapper<ChapterRequestVO, Chapter> {
}
