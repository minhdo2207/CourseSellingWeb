package hust.globalict.com.vo.response.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hust.globalict.com.model.Enrollment;
import hust.globalict.com.model.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class UserReportResponseVO {

    private Long userId;

    private String userName;

    private String userPhone;

    private String userEmail;

    private Long totalCourse;

    private Long totalPay;

    @JsonIgnore
    private List<Enrollment> enrollments;

    @JsonIgnore
    private List<Transaction> transactions;


}
