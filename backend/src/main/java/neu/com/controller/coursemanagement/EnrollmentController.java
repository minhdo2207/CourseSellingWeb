package neu.com.controller.coursemanagement;

import neu.com.service.courseservice.enrollment.EnrollmentService;
import neu.com.utils.Constants;
import neu.com.vo.request.FindEnrollmentRequestVo;
import neu.com.vo.request.SortingAndPagingRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_VERSION + "/admin/enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    /**
     * Get all Enrollemt
     * Search by user_name or course_name
     */
    @GetMapping()
    public Object getPagingEnrollment(FindEnrollmentRequestVo findEnrollmentRequestVo, SortingAndPagingRequestVO paging) {
        return enrollmentService.getPagingEnrollment(findEnrollmentRequestVo, paging);

    }
}
