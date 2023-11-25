package neu.com.service.courseservice.enrollment;

import com.naharoo.commons.mapstruct.MappingFacade;
import neu.com.repository.EnrollmentRepository;
import neu.com.repository.TransactionRepository;
import neu.com.utils.common.ResponseUtil;
import neu.com.vo.request.FindEnrollmentRequestVo;
import neu.com.vo.request.SortingAndPagingRequestVO;
import neu.com.vo.response.PagedResult;
import neu.com.vo.response.course.EnrollmentResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class EnrollementServiceImpl implements EnrollmentService {
    private final String DEFAULT_SORT_KEY = "enrollmentId";
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MappingFacade mapper;

    @Override
    public PagedResult<EnrollmentResponseVO> getPagingEnrollment(FindEnrollmentRequestVo findEnrollmentRequestVo, SortingAndPagingRequestVO paging) {
        PagedResult<EnrollmentResponseVO> result = ResponseUtil.commonPaging(paging, DEFAULT_SORT_KEY,
                pageable -> {
                    try {
                        return enrollmentRepository.findEnrollments(findEnrollmentRequestVo, pageable);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                },
                data -> {
                    List<EnrollmentResponseVO> enrollmentResponseVOList = mapper.mapAsList(data, EnrollmentResponseVO.class);
                    enrollmentResponseVOList.forEach(enrollmentResponseVO -> {
                        enrollmentResponseVO.setUserName(enrollmentResponseVO.getUser().getUserName());
                        enrollmentResponseVO.setCourseName(enrollmentResponseVO.getCourse().getCourseTitle());
                    });
                    return enrollmentResponseVOList;
                });
        return result;
    }


}
