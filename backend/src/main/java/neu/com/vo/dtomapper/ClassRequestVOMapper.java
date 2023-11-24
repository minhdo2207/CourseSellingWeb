package neu.com.vo.dtomapper;

import neu.com.model.ZoomClass;
import neu.com.vo.request.course.ClassRequestVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassRequestVOMapper extends CommonMapper<ClassRequestVO, ZoomClass> {
}
