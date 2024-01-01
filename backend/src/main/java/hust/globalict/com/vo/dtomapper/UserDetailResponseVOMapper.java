package hust.globalict.com.vo.dtomapper;

import hust.globalict.com.vo.response.course.UserDetailResponseVO;
import hust.globalict.com.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserDetailResponseVOMapper extends CommonMapper<User, UserDetailResponseVO> {
}
