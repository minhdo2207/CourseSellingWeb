package neu.com.controller.studentmanagment;

import jakarta.validation.Valid;
import neu.com.service.user.UserService;
import neu.com.utils.Constants;
import neu.com.vo.request.SortingAndPagingRequestVO;
import neu.com.vo.request.course.FindUserRequestVo;
import neu.com.vo.request.course.UserCreateRequestVO;
import neu.com.vo.request.course.UserUpdateRequestVO;
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
    public Object getPagingCourse(FindUserRequestVo findUserRequestVo, SortingAndPagingRequestVO paging) {
        return userService.getPagingCourse(findUserRequestVo, paging);

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
}
