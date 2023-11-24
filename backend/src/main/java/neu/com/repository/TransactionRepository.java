package neu.com.repository;

import neu.com.model.Enrollment;
import neu.com.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT count(t.course),count(t.transactionValue) FROM Transaction t WHERE t.course.courseType=?1")
    Long countMoney(Long userId);
}
