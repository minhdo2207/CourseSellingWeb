package neu.com.service.transaction;

import com.naharoo.commons.mapstruct.MappingFacade;
import neu.com.configuration.exception.InvalidInputRequestException;
import neu.com.model.Course;
import neu.com.model.Enrollment;
import neu.com.model.Transaction;
import neu.com.model.User;
import neu.com.repository.CourseRepository;
import neu.com.repository.EnrollmentRepository;
import neu.com.repository.TransactionRepository;
import neu.com.repository.UserRepository;
import neu.com.utils.Constants;
import neu.com.vo.request.TransactionCreateRequestVO;
import neu.com.vo.request.TransactionRequestVO;
import neu.com.vo.response.course.TransactionResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MappingFacade mapper;

    @Override
    public Object getTransactionRevenue(Long courseType) {
        return null;
    }

    @Override
    public Object updateTransaction(TransactionRequestVO transactionRequestVO, Long transactionId) {
        //Check if question exitst
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if (!transactionOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_transaction_notfound");
        }
        Transaction transaction = transactionOptional.get();
        transaction.setStatus(transactionRequestVO.getStatus());
        if (transactionRequestVO.getStatus().equals(1L)) {
            //Check xem có trong enrollment ko => Xóa
            Optional<Enrollment> enrollmentOptional = enrollmentRepository.findByUserAndCourse(transaction.getUser(), transaction.getCourse());
            if (enrollmentOptional.isPresent()) {
                enrollmentRepository.delete(enrollmentOptional.get());
            }
            //TODO Add to enrollement
            Enrollment enrollment = new Enrollment();
            enrollment.setUser(transaction.getUser());
            enrollment.setCourse(transaction.getCourse());
            enrollment.setStatus(0L);
            enrollmentRepository.save(enrollment);
            //TODO If course is video => status = enrolled
            if (transaction.getCourse().getCourseType().equals(1L)) {
                enrollment.setStatus(1L);
                enrollmentRepository.save(enrollment);
            }
        } else {
            //Check xem có trong enrollment ko => Xóa
            Optional<Enrollment> enrollment = enrollmentRepository.findByUserAndCourse(transaction.getUser(), transaction.getCourse());
            if (enrollment.isPresent()) {
                enrollmentRepository.delete(enrollment.get());
            }
        }
        transactionRepository.save(transaction);
        return mapper.map(transaction, TransactionResponseVO.class);
    }

    @Override
    public Object createTransaction(TransactionCreateRequestVO transactionRequestVO, Long userID, Long courseId) throws ParseException {
        //Check if user exitst
        Optional<User> userOptional = userRepository.findById(userID);
        if (!userOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_user_notfound");
        }
        //Check if course exitst
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_course_notfound");
        }
        Transaction transaction = new Transaction();
        transaction.setUser(userOptional.get());
        transaction.setCourse(courseOptional.get());
        transaction.setTransactionValue(courseOptional.get().getCoursePrice());
        transaction.setStatus(transactionRequestVO.getStatus());
        if (transactionRequestVO.getStatus().equals(1L)) {
            //Check xem có trong enrollment ko => Xóa
            Optional<Enrollment> enrollmentOptional = enrollmentRepository.findByUserAndCourse(transaction.getUser(), transaction.getCourse());
            if (enrollmentOptional.isPresent()) {
                enrollmentRepository.delete(enrollmentOptional.get());
            }
            //TODO Add to enrollement
            Enrollment enrollment = new Enrollment();
            enrollment.setUser(transaction.getUser());
            enrollment.setCourse(transaction.getCourse());
            enrollment.setStatus(0L);
            enrollmentRepository.save(enrollment);
            //TODO If course is video => status = enrolled
            if (transaction.getCourse().getCourseType().equals(1L)) {
                enrollment.setStatus(1L);
                enrollmentRepository.save(enrollment);
            }
        } else {
            //Check xem có trong enrollment ko => Xóa
            Optional<Enrollment> enrollment = enrollmentRepository.findByUserAndCourse(transaction.getUser(), transaction.getCourse());
            if (enrollment.isPresent()) {
                enrollmentRepository.delete(enrollment.get());
            }
        }
        transaction.setTransactionDate(new SimpleDateFormat(Constants.ISO_DATE_SDF_PATTERN).parse(transactionRequestVO.getTransactionDate()));
        transactionRepository.save(transaction);
        return mapper.map(transaction, TransactionResponseVO.class);
    }

    @Override
    public Object deleteTransaction(Long transactionId) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if (!transactionOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_transaction_notfound");
        }
        Transaction transaction = transactionOptional.get();
        transactionRepository.delete(transaction);
        return mapper.map(transaction, TransactionResponseVO.class);
    }
}
