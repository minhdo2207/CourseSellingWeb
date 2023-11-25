package neu.com.service.classservice;

import com.naharoo.commons.mapstruct.MappingFacade;
import neu.com.configuration.exception.InvalidInputRequestException;
import neu.com.model.Course;
import neu.com.model.User;
import neu.com.model.ZoomClass;
import neu.com.repository.*;
import neu.com.vo.request.course.ClassRequestVO;
import neu.com.vo.response.ClassDetailResponseVO;
import neu.com.vo.response.course.ClassResponseVO;
import neu.com.vo.response.course.UserResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private ZoomEnrollmentRepository zoomEnrollmentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MappingFacade mapper;

    @Override
    public Object updateClass(ClassRequestVO classRequestVO, Long classId) {
        //Check if class exitst
        Optional<ZoomClass> classOptional = classRepository.findById(classId);
        if (!classOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_class_notfound");
        }
        if (classRepository.findByClassNameAndClassIdNot(classRequestVO.getClassName(), classId).isPresent()) {
            throw new InvalidInputRequestException("msg_error_class_name_already_exists");
        }
        if (classRepository.findByClassLinkAndClassIdNot(classRequestVO.getClassLink(), classId).isPresent()) {
            throw new InvalidInputRequestException("msg_error_class_link_already_exists");
        }

        ZoomClass zoomClass = classOptional.get();
        zoomClass.setClassName(classRequestVO.getClassName());
        zoomClass.setClassLink(classRequestVO.getClassLink());
        if (classRequestVO.getTutorId() != null) {
            User user = userRepository.findById(classRequestVO.getTutorId()).get();
            zoomClass.setTutor(tutorRepository.findById(user.getTutor().getTutorId()).get());
        }

        classRepository.save(zoomClass);
        ClassResponseVO classResponseVO = mapper.map(zoomClass, ClassResponseVO.class);
        classResponseVO.setTutorName(zoomClass.getTutor().getUser().getUserName());
        return classResponseVO;

    }

    @Override
    public Object deleteClass(Long classId) {
        //Check if class exitst
        Optional<ZoomClass> classOptional = classRepository.findById(classId);
        if (!classOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_class_notfound");
        }
        ZoomClass zoomClass = classOptional.get();
        classRepository.delete(zoomClass);
        return mapper.map(zoomClass, ClassResponseVO.class);
    }

    @Override
    public Object addClass(ClassRequestVO classRequestVO, Long courseId) {
        //Check if course exitst
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_course_notfound");
        }
        //Check the name if exists
        if (classRepository.findByClassName(classRequestVO.getClassName()).isPresent()) {
            throw new InvalidInputRequestException("msg_error_class_name_already_exists");
        }
        if (classRepository.findByClassLink(classRequestVO.getClassLink()).isPresent()) {
            throw new InvalidInputRequestException("msg_error_class_link_already_exists");
        }
        ZoomClass zoomClass = mapper.map(classRequestVO, ZoomClass.class);
        zoomClass.setClassStatus(true);
        zoomClass.setTotalStudents(0L);

        if (classRequestVO.getTutorId() != null) {
            User user = userRepository.findById(classRequestVO.getTutorId()).get();
            zoomClass.setTutor(tutorRepository.findById(user.getTutor().getTutorId()).get());
        }
        zoomClass.setCourse(courseOptional.get());
        classRepository.save(zoomClass);
        ClassResponseVO classResponseVO = mapper.map(zoomClass, ClassResponseVO.class);
        classResponseVO.setTutorName(zoomClass.getTutor().getUser().getUserName());
        return classResponseVO;
    }

    @Override
    public Object getClass(Long classId) {
        //Check if class exitst
        Optional<ZoomClass> classOptional = classRepository.findById(classId);
        if (!classOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_class_notfound");
        }
        ZoomClass zoomClass = classOptional.get();
        ClassDetailResponseVO classDetailResponseVO = mapper.map(zoomClass, ClassDetailResponseVO.class);
        // Map ZoomEnrollment to UserResponseVO for students
        List<UserResponseVO> students = classDetailResponseVO.getZoomEnrollments().stream()
                .map(enrollment -> mapper.map(enrollment.getEnrollment().getUser(), UserResponseVO.class))
                .collect(Collectors.toList());

        classDetailResponseVO.setStudents(students);

        return classDetailResponseVO;
    }
}
