package neu.com.service.courseservice.enrollment;

import neu.com.vo.request.FindEnrollmentRequestVo;
import neu.com.vo.request.SortingAndPagingRequestVO;

public interface EnrollmentService {
    Object getPagingEnrollment(FindEnrollmentRequestVo findEnrollmentRequestVo, SortingAndPagingRequestVO paging);
}
