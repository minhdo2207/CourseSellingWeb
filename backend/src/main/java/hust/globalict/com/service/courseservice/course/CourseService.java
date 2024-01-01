package hust.globalict.com.service.courseservice.course;

import hust.globalict.com.vo.request.course.CourseCreateRequestVO;
import hust.globalict.com.vo.request.course.CourseDateRequestVO;
import hust.globalict.com.vo.response.PagedResult;
import hust.globalict.com.vo.response.course.CourseDetailResponseVO;
import hust.globalict.com.vo.response.course.CourseResponseVO;
import hust.globalict.com.vo.request.SortingAndPagingRequestVO;
import hust.globalict.com.vo.request.course.CourseUpdateRequestVO;
import hust.globalict.com.vo.request.course.FindCourseRequestVo;

import java.text.ParseException;

public interface CourseService {
    PagedResult<CourseResponseVO> getPagingCourse(FindCourseRequestVo findCourseRequestVo, SortingAndPagingRequestVO paging);

    CourseDetailResponseVO getCourseDetail(Long courseId);

    CourseResponseVO updateCourse(CourseUpdateRequestVO courseUpdateRequestVO,Long courseId);

    CourseResponseVO createCourse(CourseCreateRequestVO courseCreateRequestVORequestVO);

    CourseResponseVO deleteCourse(Long courseId);

    Object updateDateCourse(CourseDateRequestVO courseUpdateRequestVO, Long courseId) throws ParseException;
}
