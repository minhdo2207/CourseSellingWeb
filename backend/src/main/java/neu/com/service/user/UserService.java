package neu.com.service.user;

import neu.com.vo.request.SortingAndPagingRequestVO;
import neu.com.vo.request.course.*;
import neu.com.vo.response.PagedResult;
import neu.com.vo.response.course.UserDetailResponseVO;
import neu.com.vo.response.course.UserResponseVO;

public interface UserService {
    PagedResult<UserResponseVO> getPagingUsers(FindUserRequestVo findUserRequestVo, SortingAndPagingRequestVO paging);

    UserDetailResponseVO getUserDetail(Long userId);

    UserResponseVO updateUser(UserCreateRequestVO userUpdateRequestVO, Long userId);

    UserResponseVO createUser(UserCreateRequestVO userCreateRequestVO,Long roleId);

    UserResponseVO deleteUser(Long userId);

    Object getPagingUserReport(FindUserRequestVo findUserRequestVo, SortingAndPagingRequestVO paging);

    Object getFreeTeacher();

    Object getPagingTeachers(FindTeacherRequestVo findTeacherRequestVo, SortingAndPagingRequestVO paging);

    Object deleteStudentFromClass(Long userId, Long classId);

    Object getWatingStudent(Long classId);

    Object addStudentFromClass(UserRequestVO userRequestVO, Long classId);
}
