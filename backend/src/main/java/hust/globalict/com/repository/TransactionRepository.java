package hust.globalict.com.repository;

import hust.globalict.com.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT count(t.course),count(t.transactionValue) FROM Transaction t WHERE t.course.courseType=?1")
    Long countMoney(Long userId);

}
