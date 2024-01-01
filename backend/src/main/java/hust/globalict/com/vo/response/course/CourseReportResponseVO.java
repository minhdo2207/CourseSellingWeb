package hust.globalict.com.vo.response.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hust.globalict.com.model.Enrollment;
import hust.globalict.com.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
