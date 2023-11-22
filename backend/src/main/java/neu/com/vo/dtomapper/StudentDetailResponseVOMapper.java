package neu.com.vo.dtomapper;

import neu.com.model.User;
import neu.com.vo.response.course.StudentDetailResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface StudentDetailResponseVOMapper extends CommonMapper<User, StudentDetailResponseVO> {
}
