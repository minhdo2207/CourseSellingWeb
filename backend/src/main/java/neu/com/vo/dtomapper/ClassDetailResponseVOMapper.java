package neu.com.vo.dtomapper;

import neu.com.model.ZoomClass;
import neu.com.vo.response.ClassDetailResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ClassDetailResponseVOMapper extends CommonMapper<ZoomClass, ClassDetailResponseVO> {
}
