package hust.globalict.com.model;

import hust.globalict.com.utils.Constants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "submissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE submissions SET is_deleted= true WHERE submission_id = ?")
@Where(clause = Constants.IS_NOT_DELETED)
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Submission extends BaseEnt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private Long submissionId;

    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Column(name = "submission_status", columnDefinition = "BOOLEAN DEFAULT 0")
    private boolean submissionStatus;

    @Column(name = "submission_mark", precision = 4, scale = 2)
    private BigDecimal submissionMark;

    @Column(name="status",nullable = false)
    private Long status;
}
