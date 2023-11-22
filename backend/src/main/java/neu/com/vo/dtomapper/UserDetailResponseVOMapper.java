package neu.com.vo.dtomapper;

import neu.com.model.User;
import neu.com.vo.response.course.UserDetailResponseVO;
import neu.com.vo.response.course.UserResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserDetailResponseVOMapper extends CommonMapper<User, UserDetailResponseVO> {
}
