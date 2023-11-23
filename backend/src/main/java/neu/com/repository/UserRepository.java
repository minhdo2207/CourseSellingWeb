package neu.com.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import neu.com.model.QUser;
import neu.com.model.User;
import neu.com.vo.request.course.FindUserRequestVo;
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
            BooleanExpression matchKeyword = QUser.user.userName.containsIgnoreCase(findUserRequestVo.getUserName());
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
}
