package neu.com.vo.dtomapper;

import neu.com.model.User;
import neu.com.vo.response.course.UserReportResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserReportResponseVOMapper extends CommonMapper<User, UserReportResponseVO> {
}
