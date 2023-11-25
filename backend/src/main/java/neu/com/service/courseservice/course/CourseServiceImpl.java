package neu.com.service.courseservice.course;

import com.naharoo.commons.mapstruct.MappingFacade;
import neu.com.configuration.exception.InvalidInputRequestException;
import neu.com.model.*;
import neu.com.repository.CourseRepository;
import neu.com.utils.common.ResponseUtil;
import neu.com.vo.enumData.CourseType;
import neu.com.vo.request.SortingAndPagingRequestVO;
import neu.com.vo.request.course.CourseCreateRequestVO;
import neu.com.vo.request.course.CourseUpdateRequestVO;
import neu.com.vo.request.course.FindCourseRequestVo;
import neu.com.vo.response.ChapterResponseVO;
import neu.com.vo.response.PagedResult;
import neu.com.vo.response.course.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final String DEFAULT_SORT_KEY = "courseId";
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MappingFacade mapper;

    @Override
    public PagedResult<CourseResponseVO> getPagingCourse(FindCourseRequestVo findCourseRequestVo, SortingAndPagingRequestVO paging) {
        PagedResult<CourseResponseVO> result = ResponseUtil.commonPaging(paging, DEFAULT_SORT_KEY,
                pageable -> courseRepository.findCourses(findCourseRequestVo, pageable),
                data -> mapper.mapAsList(data, CourseResponseVO.class));
        return result;
    }

    @Override
    public CourseDetailResponseVO getCourseDetail(Long courseId) {
        //Check if course exitst
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_course_notfound");
        }
        Course course = courseOptional.get();
        if (CourseType.VIDEO_COURSE.getValue().equals(course.getCourseType())) {
            VideoCourseDetailResponseVO videoCourseDetailResponseVO = mapper.map(course, VideoCourseDetailResponseVO.class);
            videoCourseDetailResponseVO.setChaptersVo(mapper.mapAsList(course.getChapters(), ChapterResponseVO.class));
            return videoCourseDetailResponseVO;
        } else if (CourseType.MEETING_COURSE.getValue().equals(course.getCourseType())) {
            MeetingCourseDetailResponseVO meetingCourseDetailResponseVO = mapper.map(course, MeetingCourseDetailResponseVO.class);
            List<ClassResponseVO> classResponseVOS = mapper.mapAsList(course.getClasses(), ClassResponseVO.class);

            for (ClassResponseVO classResponseVO : classResponseVOS) {
                classResponseVO.setTutorName(classResponseVO.getTutor().getUser().getUserName());
            }

            meetingCourseDetailResponseVO.setClassVos(classResponseVOS);
            return meetingCourseDetailResponseVO;
        }
        return null;
    }

    @Override
    public CourseResponseVO updateCourse(CourseUpdateRequestVO courseUpdateRequestVO, Long courseId) {
        //Check if course exitst
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_course_notfound");
        }
        //Check the title if exists
        if (courseRepository.findByCourseTitleAndCourseIdNot(courseUpdateRequestVO.getCourseTitle(), courseId).isPresent()) {
            throw new InvalidInputRequestException("msg_error_course_tile_already_exists");
        }
        //Check the title exists
        Course course = courseOptional.get();
        course.setCourseTitle(courseUpdateRequestVO.getCourseTitle());
        course.setCoursePrice(courseUpdateRequestVO.getCoursePrice());
        courseRepository.save(course);


        return mapper.map(course, CourseResponseVO.class);
    }

    @Override
    public CourseResponseVO createCourse(CourseCreateRequestVO courseCreateRequestVO) {
        //Check the title if exists
        if (courseRepository.findByCourseTitle(courseCreateRequestVO.getCourseTitle()).isPresent()) {
            throw new InvalidInputRequestException("msg_error_course_tile_already_exists");
        }
        Course course = mapper.map(courseCreateRequestVO, Course.class);
        courseRepository.save(course);
        return mapper.map(course, CourseResponseVO.class);
    }

    @Override
    public CourseResponseVO deleteCourse(Long courseId) {
        //Check if course exitst
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_course_notfound");
        }
        Course course = courseOptional.get();
        courseRepository.delete(course);
        return mapper.map(course, CourseResponseVO.class);
    }

    @Override
    public PagedResult<CourseReportResponseVO> getPagingCourseReport(FindCourseRequestVo findCourseRequestVo, SortingAndPagingRequestVO paging) {
        PagedResult<CourseReportResponseVO> result = ResponseUtil.commonPaging(paging, DEFAULT_SORT_KEY,
                pageable -> courseRepository.findCourses(findCourseRequestVo, pageable),
                data -> {
                    List<CourseReportResponseVO> courseReportResponseVOS = mapper.mapAsList(data, CourseReportResponseVO.class);
                    courseReportResponseVOS.forEach(courseReportResponseVO -> {
                        courseReportResponseVO.setTotalStudents(Long.valueOf(courseReportResponseVO.getEnrollments().size()));
                        courseReportResponseVO.setTotalMoney(courseReportResponseVO.getTransactions().stream()
                                .mapToLong(Transaction::getTransactionValue)
                                .sum());
                    });
                    return courseReportResponseVOS;
                });
        return result;
    }


    public List<ZoomClass> getZoomClassesFromCourse(Course course) {
        List<Enrollment> enrollments = course.getEnrollments();

        // Assuming you have a flat list of ZoomEnrollments across all enrollments
        List<ZoomEnrollment> zoomEnrollments = enrollments.stream()
                .flatMap(enrollment -> enrollment.getZoomEnrollments().stream())
                .collect(Collectors.toList());

        // Extracting ZoomClasses from ZoomEnrollments
        List<ZoomClass> zoomClasses = zoomEnrollments.stream()
                .map(ZoomEnrollment::getZoomClass)
                .collect(Collectors.toList());

        return zoomClasses;


    }
}
