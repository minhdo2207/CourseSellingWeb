package neu.com.service.courseservice.course;

import neu.com.vo.request.SortingAndPagingRequestVO;
import neu.com.vo.request.course.CourseCreateRequestVO;
import neu.com.vo.request.course.CourseDateRequestVO;
import neu.com.vo.request.course.CourseUpdateRequestVO;
import neu.com.vo.request.course.FindCourseRequestVo;
import neu.com.vo.response.PagedResult;
import neu.com.vo.response.course.CourseDetailResponseVO;
import neu.com.vo.response.course.CourseReportResponseVO;
import neu.com.vo.response.course.CourseResponseVO;

import java.text.ParseException;

public interface CourseService {
    PagedResult<CourseResponseVO> getPagingCourse(FindCourseRequestVo findCourseRequestVo, SortingAndPagingRequestVO paging);

    CourseDetailResponseVO getCourseDetail(Long courseId);

    CourseResponseVO updateCourse(CourseUpdateRequestVO courseUpdateRequestVO,Long courseId);

    CourseResponseVO createCourse(CourseCreateRequestVO courseCreateRequestVORequestVO);

    CourseResponseVO deleteCourse(Long courseId);

    PagedResult<CourseReportResponseVO> getPagingCourseReport(FindCourseRequestVo findCourseRequestVo, SortingAndPagingRequestVO paging);

    Object updateDateCourse(CourseDateRequestVO courseUpdateRequestVO, Long courseId) throws ParseException;
}
