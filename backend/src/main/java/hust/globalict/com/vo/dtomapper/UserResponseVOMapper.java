package hust.globalict.com.vo.dtomapper;

import hust.globalict.com.model.User;
import hust.globalict.com.vo.response.course.UserResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserResponseVOMapper extends CommonMapper<User, UserResponseVO> {
}
