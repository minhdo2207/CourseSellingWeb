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

@Entity
@Table(name = "zoomenrollment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE zoomenrollment SET is_deleted= true WHERE zoom_enrollment_id = ?")
@Where(clause = Constants.IS_NOT_DELETED)
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ZoomEnrollment extends BaseEnt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zoom_enrollment_id")
    private Long zoomEnrollmentId;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ZoomClass zoomClass;

    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @Column(name = "zoom_enrollment_status", columnDefinition = "BOOLEAN DEFAULT 0")
    private boolean zoomEnrollmentStatus;

}
