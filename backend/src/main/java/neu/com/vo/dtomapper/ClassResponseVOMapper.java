package neu.com.vo.dtomapper;

import neu.com.model.ZoomClass;
import neu.com.vo.response.course.ClassResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ClassResponseVOMapper extends CommonMapper<ZoomClass, ClassResponseVO> {
}
