package hust.globalict.com.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import hust.globalict.com.model.Course;
import hust.globalict.com.utils.common.DateTimeUtils;
import hust.globalict.com.vo.request.course.FindCourseRequestVo;
import hust.globalict.com.model.QCourse;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.Optional;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, QuerydslPredicateExecutor<Course>, QuerydslBinderCustomizer<QCourse> {
    @Override
    default void customize(QuerydslBindings bindings, QCourse root) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::contains);
    }

    default Page<Course> findCourses(FindCourseRequestVo request, Pageable pageable) throws ParseException {
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        if (ObjectUtils.isNotEmpty(request.getName())) {
            BooleanExpression matchKeyword = QCourse.course.courseTitle.containsIgnoreCase(request.getName());
            expression = expression.and(matchKeyword);
        }
        if (ObjectUtils.isNotEmpty(request.getType())) {
            expression = expression.and(QCourse.course.courseType.eq(request.getType()));
        }
        if (ObjectUtils.isNotEmpty(request.getStartDate())) {
            expression = expression.and(QCourse.course.courseStart.after(DateTimeUtils.getDateFromIsoDate(request.getStartDate())));
        }
        if (ObjectUtils.isNotEmpty(request.getEndDate())) {
            expression = expression.and(QCourse.course.courseEnd.before(DateTimeUtils.getDateFromIsoDate(request.getEndDate())));
        }

        return this.findAll(expression, pageable);
    }


    Optional<Object> findByCourseTitle(String courseTitle);

    Optional<Object> findByCourseTitleAndCourseIdNot(String courseTitle, Long courseId);
}
