package at.mavila.transaction.db.repository;

import at.mavila.transaction.db.entity.CustomerOrder;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, UUID> {
}
