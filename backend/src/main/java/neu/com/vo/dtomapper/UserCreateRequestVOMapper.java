package neu.com.vo.dtomapper;

import neu.com.model.User;
import neu.com.vo.request.course.UserCreateRequestVO;
import neu.com.vo.response.course.UserResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserCreateRequestVOMapper extends CommonMapper<UserCreateRequestVO, User> {
}
