package neu.com.service.user;

import com.naharoo.commons.mapstruct.MappingFacade;
import neu.com.configuration.exception.InvalidInputRequestException;
import neu.com.model.*;
import neu.com.repository.*;
import neu.com.utils.Constants;
import neu.com.utils.common.ResponseUtil;
import neu.com.vo.request.SortingAndPagingRequestVO;
import neu.com.vo.request.course.FindTeacherRequestVo;
import neu.com.vo.request.course.FindUserRequestVo;
import neu.com.vo.request.course.UserCreateRequestVO;
import neu.com.vo.request.course.UserRequestVO;
import neu.com.vo.response.PagedResult;
import neu.com.vo.response.course.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final String DEFAULT_SORT_KEY = "userId";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MappingFacade mapper;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private ZoomEnrollmentRepository zoomEnrollmentRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public PagedResult<UserResponseVO> getPagingUsers(FindUserRequestVo findUserRequestVo, SortingAndPagingRequestVO paging) {
        PagedResult<UserResponseVO> result = ResponseUtil.commonPaging(paging, DEFAULT_SORT_KEY,
                pageable -> userRepository.findUsersByUserName(findUserRequestVo, pageable),
                data -> mapper.mapAsList(data, UserResponseVO.class));
        return result;
    }

    @Override
    public UserDetailResponseVO getUserDetail(Long userId) {
        //Check if user exitst
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_user_notfound");
        }
        User user = userOptional.get();
        if (user.getRoles().stream().anyMatch(role -> role.getRoleId().equals(1L))) {
            //Student
            StudentDetailResponseVO studentDetailResponseVO = mapper.map(user, StudentDetailResponseVO.class);
            List<TransactionResponseVO> transactionResponseVOs = mapper.mapAsList(user.getTransactions(), TransactionResponseVO.class);
            transactionResponseVOs.forEach(transactionResponseVO -> transactionResponseVO.setCourses(mapper.map(transactionRepository.findById(transactionResponseVO.getTransactionId()).get().getCourse(), CourseResponseVO.class)));
            studentDetailResponseVO.setTransactionResponseVOS(transactionResponseVOs);
            return studentDetailResponseVO;
        } else if (user.getRoles().stream().anyMatch(role -> role.getRoleId().equals(2L))) {
            //Tutor
            TutorDetailResponseVO tutorDetailResponseVO = mapper.map(user, TutorDetailResponseVO.class);
            tutorDetailResponseVO.setClassVos(mapper.mapAsList(user.getTutor().getZoomClasses(), ClassResponseVO.class));
            tutorDetailResponseVO.setTeachingStatus(user.getTutor().getZoomClasses().size() == 0 ? 0L : 1L);
            return tutorDetailResponseVO;

        } else {
            return mapper.map(user, UserDetailResponseVO.class);
        }
    }

    @Override
    public UserResponseVO updateUser(UserCreateRequestVO userUpdateRequestVO, Long userId) {
        //Check if user exitst
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_user_notfound");
        }
        //Check the email if exists
        if (userRepository.findByUserEmailAndUserIdNot(userUpdateRequestVO.getUserEmail(), userId).isPresent()) {
            throw new InvalidInputRequestException("msg_error_email_already_exists");
        }
        User user = userOptional.get();
        user.setUserAddress(userUpdateRequestVO.getUserAddress());
        user.setUserEmail(userUpdateRequestVO.getUserEmail());
        user.setUserName(userUpdateRequestVO.getUserName());
//        user.setUserPassword(encoder.encode(Constants.DEFAULT_PASSWORD));
        user.setUserPhone(userUpdateRequestVO.getUserPhone());
        user.setUserPassword(encoder.encode(Constants.DEFAULT_PASSWORD));
        userRepository.save(user);
        return mapper.map(user, UserResponseVO.class);
    }

    @Override
    public UserResponseVO createUser(UserCreateRequestVO userCreateRequestVO, Long roleId) {
        //Check if role exitst
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (!roleOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_role_notfound");
        }

        //Check the email if exists
        if (userRepository.findByUserEmail(userCreateRequestVO.getUserEmail()).isPresent()) {
            throw new InvalidInputRequestException("msg_error_email_already_exists");
        }

        User user = mapper.map(userCreateRequestVO, User.class);
        List<Role> listRole = new ArrayList<>();
        listRole.add(roleOptional.get());
        user.setUserPassword(encoder.encode(Constants.DEFAULT_PASSWORD));
        user.setRoles(listRole);
        userRepository.save(user);
        if (roleId.equals(2L)) {
            Tutor tutor = new Tutor();
            tutor.setUser(user);
            tutorRepository.save(tutor);
        }

        return mapper.map(user, UserResponseVO.class);
    }


    @Override
    public UserResponseVO deleteUser(Long userId) {
        //Check if user exitst
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_user_notfound");
        }
        User user = userOptional.get();
        userRepository.delete(user);
        return mapper.map(user, UserResponseVO.class);
    }

    @Override
    public PagedResult<UserReportResponseVO> getPagingUserReport(FindUserRequestVo findUserRequestVo, SortingAndPagingRequestVO paging) {
        PagedResult<UserReportResponseVO> result = ResponseUtil.commonPaging(paging, DEFAULT_SORT_KEY,
                pageable -> userRepository.findTeacherByUserName(findUserRequestVo, pageable),
                data -> {
                    List<UserReportResponseVO> userReportResponseVOList = mapper.mapAsList(data, UserReportResponseVO.class);
                    userReportResponseVOList.forEach(
                            userReportResponseVO -> {
                                userReportResponseVO.setTotalCourse(Long.valueOf(userReportResponseVO.getEnrollments().size()));
                                userReportResponseVO.setTotalPay(userReportResponseVO.getTransactions().stream()
                                        .mapToLong(Transaction::getTransactionValue)
                                        .sum());
                            }
                    );
                    return userReportResponseVOList;
                });
        return result;
    }

    @Override
    public Object getFreeTeacher() {
        List<User> tutors = userRepository.findAll();
        List<User> freeTutors = new ArrayList<>();
        for (User tutor : tutors) {
            if (tutor.getTutor() != null) {
                if (tutor.getTutor().getZoomClasses().size() == 0) {
                    freeTutors.add(tutor);
                }
            }
        }
        return mapper.mapAsList(freeTutors, UserResponseVO.class);
    }

    @Override
    public Object getPagingTeachers(FindTeacherRequestVo findTeacherRequestVo, SortingAndPagingRequestVO paging) {
        PagedResult<TutorDetailResponseVO> result = ResponseUtil.commonPaging(paging, DEFAULT_SORT_KEY,
                pageable -> userRepository.findTutorByUserName(findTeacherRequestVo, pageable),
                data -> {
                    List<TutorDetailResponseVO> tutorDetailResponseVOS = mapper.mapAsList(data, TutorDetailResponseVO.class);
                    tutorDetailResponseVOS.forEach(tutorDetailResponseVO -> tutorDetailResponseVO.setTeachingStatus(findTeacherRequestVo.getStatus() == null ? (tutorDetailResponseVO.getTutor().getZoomClasses().size() > 0 ? 1L :
                            0L) : findTeacherRequestVo.getStatus()));


                    return tutorDetailResponseVOS;
                });
        return result;
    }

    @Override
    public Object deleteStudentFromClass(Long userId, Long classId) {
        //Check if user exitst
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_user_notfound");
        }
        Optional<ZoomClass> classOptional = classRepository.findById(classId);
        if (!classOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_class_notfound");
        }
        List<Enrollment> enrollmentOptional = enrollmentRepository.findAllByUser(userOptional.get());
        Enrollment enrollment = null;
        for (Enrollment e : enrollmentOptional
        ) {
            if (e.getCourse().equals(classOptional.get().getCourse())) {
                enrollment = e;
            }
        }
        Optional<ZoomEnrollment> zoomEnrollmentOptional = zoomEnrollmentRepository.findByEnrollmentAndZoomClass(enrollment, classOptional.get());
        if (!zoomEnrollmentOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_enrollementt_notfound");
        }

        zoomEnrollmentRepository.delete(zoomEnrollmentOptional.get());
//        //Enrollment enrollment = enrollmentOptional.get();
        enrollment.setStatus(0L);
        enrollmentRepository.save(enrollment);

        return mapper.map(classRepository.findById(classId).get(), ClassResponseVO.class);
    }

    @Override
    public Object getWatingStudent(Long classId) {
        Optional<ZoomClass> classOptional = classRepository.findById(classId);
        if (!classOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_class_notfound");
        }

        Set<User> users = new HashSet<>();
        Course course = classOptional.get().getCourse();
        List<Enrollment> enrollmentList = enrollmentRepository.findAllByCourse(course);
        for (Enrollment e : enrollmentList) {
            if (e.getStatus().equals(0L)) {
                users.add(e.getUser());
            }
        }
        return mapper.mapAsList(users, UserResponseVO.class);
    }

    @Override
    public Object addStudentFromClass(UserRequestVO userRequestVO, Long classId) {
        Optional<User> userOptional = userRepository.findById(userRequestVO.getUserId());
        if (!userOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_user_notfound");
        }
        Optional<ZoomClass> classOptional = classRepository.findById(classId);
        if (!classOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_class_notfound");
        }

        Set<User> users = new HashSet<>();
        Course course = classOptional.get().getCourse();
        List<Enrollment> enrollmentList = enrollmentRepository.findAllByCourse(course);
        for (Enrollment e : enrollmentList) {
            if (e.getStatus().equals(0L)) {
                users.add(e.getUser());
            }
        }
        if (!users.contains(userOptional.get())) {
            throw new InvalidInputRequestException("msg_error_user_notfound");
        } else {
            //Add to zoom enrollment
            ZoomEnrollment zoomEnrollment = new ZoomEnrollment();
            zoomEnrollment.setZoomClass(classOptional.get());
            Enrollment enrollment = enrollmentRepository.findByUserAndCourse(userOptional.get(), course).get();
            zoomEnrollment.setEnrollment(enrollment);
            zoomEnrollment.setZoomEnrollmentStatus(true);
            zoomEnrollmentRepository.save(zoomEnrollment);
            //Set status
            enrollment.setStatus(1L);
            enrollmentRepository.save(enrollment);
            //Set class
            ZoomClass zoomClass = classOptional.get();
            zoomClass.setClassStatus(((30 - zoomClass.getTotalStudents().intValue()) == 0) ? false : true);
            classRepository.save(zoomClass);
            return mapper.map(zoomClass, ClassResponseVO.class);
        }
    }

    //TODO Add student to class

}
