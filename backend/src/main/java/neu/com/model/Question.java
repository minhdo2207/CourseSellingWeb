package neu.com.model;

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

import java.io.Serializable;

@Entity
@Table(name = "questions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE questions SET is_deleted= true WHERE question_id = ?")
@Where(clause = Constants.IS_NOT_DELETED)
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Question extends BaseEnt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Column(name = "question", nullable = false,length = 100)
    private String question;

    @Column(name = "choice_a", nullable = false,length = 100)
    private String choiceA;

    @Column(name = "choice_b", nullable = false,length = 100)
    private String choiceB;

    @Column(name = "choice_c", nullable = false,length = 100)
    private String choiceC;

    @Column(name = "choice_d", nullable = false)
    private String choiceD;

    @Column(name = "solution", nullable = false)
    private String solution;
}
