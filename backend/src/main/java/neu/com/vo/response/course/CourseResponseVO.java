package neu.com.vo.response.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import neu.com.model.BaseEnt;
import neu.com.model.Chapter;
import neu.com.model.Enrollment;
import neu.com.model.Transaction;
import neu.com.utils.Constants;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
