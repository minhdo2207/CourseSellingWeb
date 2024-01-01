package hust.globalict.com.vo.dtomapper;

import hust.globalict.com.vo.response.course.StudentDetailResponseVO;
import hust.globalict.com.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface StudentDetailResponseVOMapper extends CommonMapper<User, StudentDetailResponseVO> {
}
