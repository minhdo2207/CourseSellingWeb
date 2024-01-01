package hust.globalict.com.vo.response.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CourseDetailResponseVO {

    private Long courseId;

    private String courseTitle;

    private String courseSubtitle;

    private String courseDescriptionText;

    private int coursePrice;

    private String courseImage;

    private Date courseStart;

    private Date courseEnd;

    private Long courseHour;

    private Long courseType;

    private Long courseGrade;

}
