package neu.com.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import neu.com.model.Course;
import neu.com.model.Enrollment;
import neu.com.model.QCourse;
import neu.com.model.QEnrollment;
import neu.com.utils.common.DateTimeUtils;
import neu.com.vo.request.FindEnrollmentRequestVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>, QuerydslPredicateExecutor<Enrollment>, QuerydslBinderCustomizer<QEnrollment> {
    @Override
    default void customize(QuerydslBindings bindings, QEnrollment root) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::contains);
    }

    default Page<Enrollment> findEnrollments(FindEnrollmentRequestVo findEnrollmentRequestVo, Pageable pageable) throws ParseException {
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        if (ObjectUtils.isNotEmpty(findEnrollmentRequestVo.getUserName())) {
            BooleanExpression matchKeyword = QEnrollment.enrollment.user.userName.containsIgnoreCase(findEnrollmentRequestVo.getUserName());
            expression = expression.and(matchKeyword);
        }
        if (ObjectUtils.isNotEmpty(findEnrollmentRequestVo.getCourseName())) {
            expression = expression.and(QEnrollment.enrollment.course.courseTitle.containsIgnoreCase(findEnrollmentRequestVo.getCourseName()));
        }
        if (ObjectUtils.isNotEmpty(findEnrollmentRequestVo.getStartDate())) {
            expression = expression.and(QEnrollment.enrollment.created.after(DateTimeUtils.getDateFromIsoDate(findEnrollmentRequestVo.getStartDate())));
        }
        if (ObjectUtils.isNotEmpty(findEnrollmentRequestVo.getEndDate())) {
            expression = expression.and(QEnrollment.enrollment.created.before(DateTimeUtils.getDateFromIsoDate(findEnrollmentRequestVo.getEndDate())));
        }
        return this.findAll(expression, pageable);
    }

    @Query(value = "SELECT count(*) FROM Enrollment e WHERE e.user.userId=?1")
    Long countEnrollment(Long userId);


    Enrollment findByCourse(Optional<Course> courseOptional);
}
