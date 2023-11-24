package neu.com.vo.response.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import neu.com.model.Enrollment;
import neu.com.model.Transaction;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseReportResponseVO {

        private Long courseId;

        private String courseTitle;

        private Long courseType;

        private Long courseGrade;

        private Long totalStudents;

        private Long totalMoney;
        @JsonIgnore
        private List<Enrollment> enrollments;
        @JsonIgnore
        private List<Transaction> transactions;

}
