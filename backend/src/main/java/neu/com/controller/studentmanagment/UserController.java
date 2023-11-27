package neu.com.controller.studentmanagment;

import jakarta.validation.Valid;
import neu.com.service.user.UserService;
import neu.com.utils.Constants;
import neu.com.vo.request.SortingAndPagingRequestVO;
import neu.com.vo.request.course.FindTeacherRequestVo;
import neu.com.vo.request.course.FindUserRequestVo;
import neu.com.vo.request.course.UserCreateRequestVO;
import neu.com.vo.request.course.UserRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_VERSION + "/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Get all user
     * Search by name or type
     */
    @GetMapping()
    public Object getPagingUsers(FindUserRequestVo findUserRequestVo, SortingAndPagingRequestVO paging) {
        return userService.getPagingUsers(findUserRequestVo, paging);

    }

    /**
     * Get all user
     * Search by name or type
     */
    @GetMapping("/teacher")
    public Object getPagingTeachers(FindTeacherRequestVo findTeacherRequestVo, SortingAndPagingRequestVO paging) {
        return userService.getPagingTeachers(findTeacherRequestVo, paging);

    }

    /**
     * Get user by ID
     */

    @GetMapping("/{userId}")
    public Object getUserDetail(@PathVariable("userId") Long userId) {
        return userService.getUserDetail(userId);
    }

    /**
     * Update user
     */

    @PutMapping("/{userId}")
    public Object updateUser(@Valid @RequestBody UserCreateRequestVO userUpdateRequestVO, @PathVariable("userId") Long userId) {
        return userService.updateUser(userUpdateRequestVO, userId);
    }


    /**
     * Create user
     */

    @PostMapping("role/{roleId}")
    public Object createUser(@Valid @RequestBody UserCreateRequestVO userCreateRequestVO, @PathVariable("roleId") Long roleId) {
        return userService.createUser(userCreateRequestVO,roleId);
    }

    /**
     * Delete user
     */

    @DeleteMapping("/{userId}")
    public Object deleteUser(@PathVariable("userId") Long userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/free-teacher")
    public Object getFreeTeacher() {
        return userService.getFreeTeacher();

    }

    /**
     * Delete student from class
     */

    @DeleteMapping("/class/{classId}/student/{userId}")
    public Object deleteStudentFromClass(@PathVariable("userId") Long userId, @PathVariable("classId") Long classId) {
        return userService.deleteStudentFromClass(userId,classId);
    }

    /**
     * Add student from class
     */
    @PutMapping("/class/{classId}")
    public Object addStudentFromClass(@Valid @RequestBody UserRequestVO userRequestVO, @PathVariable("classId") Long classId) {
        return userService.addStudentFromClass(userRequestVO,classId);
    }

    /**
     * Get waiting student from class
     */
    @GetMapping("/class/{classId}")
    public Object getWatingStudent(@PathVariable("classId") Long classId) {
        return userService.getWatingStudent(classId);
    }
}
