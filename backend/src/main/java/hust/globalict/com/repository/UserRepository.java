package hust.globalict.com.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import hust.globalict.com.model.QUser;
import hust.globalict.com.model.User;
import hust.globalict.com.vo.request.course.FindTeacherRequestVo;
import hust.globalict.com.vo.request.course.FindUserRequestVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {

    @Override
    default void customize(QuerydslBindings bindings, QUser root) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::contains);
    }

    default Page<User> findUsersByUserName(FindUserRequestVo findUserRequestVo, Pageable pageable) {
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        if (ObjectUtils.isNotEmpty(findUserRequestVo.getUserName())) {
            BooleanExpression matchKeyword = QUser.user.userName.containsIgnoreCase(findUserRequestVo.getUserName()).or(QUser.user.userPhone.containsIgnoreCase(findUserRequestVo.getUserName()));
            expression = expression.and(matchKeyword);
        }
        if (ObjectUtils.isNotEmpty(findUserRequestVo.getRoleId())) {
            expression = expression.and(QUser.user.roles.any().roleId.eq(findUserRequestVo.getRoleId()));
        }
        return this.findAll(expression, pageable);
    }

    Optional<User> findByUserEmail(String userEmail);

    Optional<User> findByUserEmailAndUserIdNot(String userEmail, Long userId);

    Optional<User> findByUserName(String username);

    default Page<User> findTutorByUserName(FindTeacherRequestVo findTeacherRequestVo, Pageable pageable) {
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        if (ObjectUtils.isNotEmpty(findTeacherRequestVo.getUserName())) {
            BooleanExpression matchKeyword = QUser.user.userName.containsIgnoreCase(findTeacherRequestVo.getUserName()).or(QUser.user.userPhone.containsIgnoreCase(findTeacherRequestVo.getUserName()));
            ;
            expression = expression.and(matchKeyword);
        }
        if (ObjectUtils.isNotEmpty(findTeacherRequestVo.getStatus())) {
            if (findTeacherRequestVo.getStatus() == 0L) {
                expression = expression.and(QUser.user.tutor.zoomClasses.size().eq(0));
            } else expression = expression.and(QUser.user.tutor.zoomClasses.size().gt(0));
        }
        expression = expression.and(QUser.user.roles.any().roleId.eq(2L));
        return this.findAll(expression, pageable);
    }

    default Page<User> findTeacherByUserName(FindUserRequestVo findUserRequestVo, Pageable pageable) {
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        if (ObjectUtils.isNotEmpty(findUserRequestVo.getUserName())) {
            BooleanExpression matchKeyword = QUser.user.userName.containsIgnoreCase(findUserRequestVo.getUserName());
            expression = expression.and(matchKeyword);
        }

        expression = expression.and(QUser.user.roles.any().roleId.eq(1L));

        return this.findAll(expression, pageable);
    }
}
