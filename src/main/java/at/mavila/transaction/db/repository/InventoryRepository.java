package at.mavila.transaction.db.repository;

import at.mavila.transaction.db.entity.Inventory;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

  @Query(" from Inventory i where i.item = :item")
  Optional<Inventory> findByItem(@Param("item") final String item);
}
