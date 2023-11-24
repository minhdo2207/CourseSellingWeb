package neu.com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import neu.com.utils.Constants;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE courses SET is_deleted= true WHERE course_id = ?")
@Where(clause = Constants.IS_NOT_DELETED)
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Course extends BaseEnt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_title", nullable = false)
    private String courseTitle;

    @Column(name = "course_subtitle")
    private String courseSubtitle;

    @Column(name = "course_description_text")
    private String courseDescriptionText;

    @Column(name = "course_price", nullable = false)
    private Long coursePrice;

    @Column(name = "course_image", length = 255, columnDefinition = "VARCHAR(255) DEFAULT 'default'")
    private String courseImage;

    @Column(name = "course_start")
    @Temporal(TemporalType.DATE)
    private Date courseStart;

    @Column(name = "course_end")
    @Temporal(TemporalType.DATE)
    private Date courseEnd;

    @Column(name = "course_hour", columnDefinition = "INT DEFAULT 0")
    private Long courseHour;

    @Column(name = "course_type", nullable = false)
    private Long courseType;

    @Column(name = "course_grade", nullable = false)
    private Long courseGrade;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Chapter> chapters;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ZoomClass> classes;

}
