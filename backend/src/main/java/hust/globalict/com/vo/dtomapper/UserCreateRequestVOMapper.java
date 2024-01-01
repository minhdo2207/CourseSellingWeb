package hust.globalict.com.vo.dtomapper;

import hust.globalict.com.vo.request.course.UserCreateRequestVO;
import hust.globalict.com.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserCreateRequestVOMapper extends CommonMapper<UserCreateRequestVO, User> {
}
