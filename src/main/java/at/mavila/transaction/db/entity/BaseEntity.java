package at.mavila.transaction.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity<T extends Serializable> implements Persistable<T>, Serializable {


  @Serial
  private static final long serialVersionUID;

  static {
    serialVersionUID = 4869001190101856127L;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Getter(AccessLevel.NONE)
  protected T id;

  @CreatedBy
  protected String createdBy;

  @LastModifiedBy
  protected String lastModifiedBy;

  @CreatedDate
  protected LocalDateTime createdDate;

  @LastModifiedDate
  protected LocalDateTime lastModifiedDate;

  @Column(name = "default_comment", length = 1000)
  protected String defaultComment;

  @Version
  private Long version;

  @Transient
  private boolean isNew = true;

  @Override
  public boolean equals(Object thatObject) {
    if (this == thatObject) {
      return true;
    }

    if (thatObject == null || this.getClass() != thatObject.getClass()) {
      return false;
    }

    BaseEntity<?> that = (BaseEntity<?>) thatObject;

    return new EqualsBuilder().append(this.getId(), that.getId()).isEquals();
  }

  @Override
  public int hashCode() {
    return new
        HashCodeBuilder(17, 37)
        .append(this.getId())
        .toHashCode();
  }

  @Override
  public boolean isNew() {
    return this.isNew;
  }

  @PostLoad
  @PostPersist
  public void markNotNew() {
    this.isNew = false;
  }


}
