package neu.com.controller.coursemanagement;

import neu.com.service.courseservice.course.CourseService;
import neu.com.service.user.UserService;
import neu.com.utils.Constants;
import neu.com.vo.request.SortingAndPagingRequestVO;
import neu.com.vo.request.course.FindCourseRequestVo;
import neu.com.vo.request.course.FindUserRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_VERSION + "/admin/report")
public class ReportController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    /**
     * Get all course
     * Search by name or type
     */
    @GetMapping("/course")
    public Object getPagingCourseReport(FindCourseRequestVo findCourseRequestVo, SortingAndPagingRequestVO paging) {
        return courseService.getPagingCourseReport(findCourseRequestVo, paging);

    }

    @GetMapping("/student")
    public Object getPagingUserReport(FindUserRequestVo findUserRequestVo, SortingAndPagingRequestVO paging) {
        return userService.getPagingUserReport(findUserRequestVo, paging);

    }
}
