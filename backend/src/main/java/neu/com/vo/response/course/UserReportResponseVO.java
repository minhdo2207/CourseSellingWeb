package neu.com.vo.response.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import neu.com.model.Enrollment;
import neu.com.model.Transaction;

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
