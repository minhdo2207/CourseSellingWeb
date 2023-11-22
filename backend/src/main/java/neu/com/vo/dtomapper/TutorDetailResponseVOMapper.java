package neu.com.vo.dtomapper;

import neu.com.model.User;
import neu.com.vo.response.course.TutorDetailResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface TutorDetailResponseVOMapper extends CommonMapper<User, TutorDetailResponseVO> {
}
