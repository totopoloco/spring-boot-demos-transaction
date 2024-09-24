package at.mavila.transaction.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.io.Serial;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Inventory")
@Table(name = "Inventory", uniqueConstraints = {@UniqueConstraint(name = "unique_item", columnNames = {"item"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends BaseEntity<UUID> {

  @Serial
  private static final long serialVersionUID;

  static {
    serialVersionUID = 1869001190191856127L;
  }

  @Column(name = "item")
  private String item;
  @Column(name = "stock")
  private long stock;

  @Override
  public UUID getId() {
    return this.id;
  }
}
