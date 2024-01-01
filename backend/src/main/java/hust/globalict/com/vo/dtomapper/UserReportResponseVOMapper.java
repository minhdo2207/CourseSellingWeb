package hust.globalict.com.vo.dtomapper;

import hust.globalict.com.vo.response.course.UserReportResponseVO;
import hust.globalict.com.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserReportResponseVOMapper extends CommonMapper<User, UserReportResponseVO> {
}
