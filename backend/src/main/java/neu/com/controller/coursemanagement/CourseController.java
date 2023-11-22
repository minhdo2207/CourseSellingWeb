package neu.com.controller.coursemanagement;

import jakarta.validation.Valid;
import neu.com.service.courseservice.course.CourseService;
import neu.com.utils.Constants;
import neu.com.vo.request.course.CourseCreateRequestVO;
import neu.com.vo.request.course.CourseUpdateRequestVO;
import neu.com.vo.request.course.FindCourseRequestVo;
import neu.com.vo.request.SortingAndPagingRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_VERSION + "/admin/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * Get all course
     * Search by name or type
     */
    @GetMapping()
    public Object getPagingCourse(FindCourseRequestVo findCourseRequestVo, SortingAndPagingRequestVO paging) {
        return courseService.getPagingCourse(findCourseRequestVo, paging);

    }

    /**
     * Get course detail by ID
     */

    @GetMapping("/{courseId}")
    public Object getCourseDetail(@PathVariable("courseId") Long courseId) {
        return courseService.getCourseDetail(courseId);
    }

    /**
     * Update course
     */

    @PutMapping("/{courseId}")
    public Object updateCourse(@Valid @RequestBody CourseUpdateRequestVO courseUpdateRequestVO, @PathVariable("courseId") Long courseId) {
        return courseService.updateCourse(courseUpdateRequestVO, courseId);
    }


    /**
     * Create course
     */

    @PostMapping()
    public Object createCourse(@Valid @RequestBody CourseCreateRequestVO courseCreateRequestVORequestVO) {
        return courseService.createCourse(courseCreateRequestVORequestVO);
    }

    /**
     * Delete course
     */

    @DeleteMapping ("/{courseId}")
    public Object deleteCourse(@PathVariable("courseId") Long courseId) {
        return courseService.deleteCourse(courseId);
    }
}
