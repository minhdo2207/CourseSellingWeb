package hust.globalict.com.vo.response.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseVO {

        private Long courseId;

        private String courseTitle;

        private Long coursePrice;

        private Long courseType;

        private Long courseGrade;

}
