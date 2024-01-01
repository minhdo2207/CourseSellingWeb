package hust.globalict.com.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public abstract class BaseEnt implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(name = "created", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

    @LastModifiedDate
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updated;


    @Column(name = "is_deleted")
    protected Boolean isDeleted;

    @PrePersist
    public void onPrePersist() {
        this.isDeleted = false;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.isDeleted = false;
    }
}
