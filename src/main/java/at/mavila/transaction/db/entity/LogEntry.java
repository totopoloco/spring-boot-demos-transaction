package at.mavila.transaction.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "LogEntry")
@Table(name = "LogEntry")
@Getter
@Setter
public class LogEntry extends BaseEntity<UUID> {

  @Column(name = "message")
  private String message;

  @Override
  public UUID getId() {
    return this.id;
  }
}
