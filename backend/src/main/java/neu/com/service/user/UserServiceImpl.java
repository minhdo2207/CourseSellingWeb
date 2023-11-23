package neu.com.service.user;

import com.naharoo.commons.mapstruct.MappingFacade;
import neu.com.configuration.exception.InvalidInputRequestException;
import neu.com.model.Role;
import neu.com.model.Transaction;
import neu.com.model.Tutor;
import neu.com.model.User;
import neu.com.repository.RoleRepository;
import neu.com.repository.TutorRepository;
import neu.com.repository.UserRepository;
import neu.com.utils.Constants;
import neu.com.utils.common.ResponseUtil;
import neu.com.vo.request.SortingAndPagingRequestVO;
import neu.com.vo.request.course.FindUserRequestVo;
import neu.com.vo.request.course.UserCreateRequestVO;
import neu.com.vo.response.PagedResult;
import neu.com.vo.response.course.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    PasswordEncoder encoder;

    @Override
    public PagedResult<UserResponseVO> getPagingCourse(FindUserRequestVo findUserRequestVo, SortingAndPagingRequestVO paging) {
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
            studentDetailResponseVO.setTransactionResponseVOS(mapper.mapAsList(user.getTransactions(), TransactionResponseVO.class));
            return studentDetailResponseVO;
        } else if (user.getRoles().stream().anyMatch(role -> role.getRoleId().equals(2L))) {
            //Tutor
            TutorDetailResponseVO tutorDetailResponseVO = mapper.map(user, TutorDetailResponseVO.class);
            tutorDetailResponseVO.setClassVos(mapper.mapAsList(user.getTutor().getZoomClasses(), ClassResponseVO.class));
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
                pageable -> userRepository.findUsersByUserName(findUserRequestVo, pageable),
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


}
