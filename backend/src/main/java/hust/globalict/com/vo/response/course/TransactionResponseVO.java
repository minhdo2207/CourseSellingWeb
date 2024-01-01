package hust.globalict.com.vo.response.course;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionResponseVO {
    private Long transactionId;
    private CourseResponseVO courses;

    private Date transactionDate;

    private BigDecimal transactionValue;

    private Long status;
}
