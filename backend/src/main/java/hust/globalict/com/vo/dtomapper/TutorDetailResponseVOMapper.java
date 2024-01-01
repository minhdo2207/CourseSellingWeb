package hust.globalict.com.vo.dtomapper;

import hust.globalict.com.model.User;
import hust.globalict.com.vo.response.course.TutorDetailResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface TutorDetailResponseVOMapper extends CommonMapper<User, TutorDetailResponseVO> {
}
