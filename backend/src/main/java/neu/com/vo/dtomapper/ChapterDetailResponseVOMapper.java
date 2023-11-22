package neu.com.vo.dtomapper;

import neu.com.model.Chapter;
import neu.com.vo.response.ChapterDetailResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ChapterDetailResponseVOMapper extends CommonMapper<Chapter, ChapterDetailResponseVO>{
}
