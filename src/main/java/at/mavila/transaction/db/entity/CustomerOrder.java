package at.mavila.transaction.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import java.io.Serial;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "CustomerOrder")
@Table(name = "CustomerOrder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder extends BaseEntity<UUID> {

  @Serial
  private static final long serialVersionUID;

  static {
    serialVersionUID = 1869001190191856000L;
  }

  @Column(name = "clientId", nullable = false)
  private String clientId;

  @Column(name = "item", nullable = false)
  private String item;

  @Min(value = 1L, message = "Quantity must be greater than 0")
  @Column(name = "quantity")
  private long quantity;

  @Override
  public UUID getId() {
    return this.id;
  }
}