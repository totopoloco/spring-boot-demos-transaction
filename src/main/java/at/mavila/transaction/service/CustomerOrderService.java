package at.mavila.transaction.service;

import at.mavila.transaction.db.entity.CustomerOrder;
import at.mavila.transaction.db.entity.Inventory;
import at.mavila.transaction.db.repository.CustomerOrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CustomerOrderService {

  private final InventoryService inventoryService;
  private final CustomerOrderRepository customerOrderRepository;

  @Transactional
  public void processOrder(final String clientId, String item, long quantity) {

    CustomerOrder customerOrder = new CustomerOrder(clientId, item, quantity);
    CustomerOrder orderedSaved = this.customerOrderRepository.save(customerOrder);
    log.info("CustomerOrder saved: {}", orderedSaved.getId());
    Inventory saved = this.inventoryService.save(item, quantity);
    log.info("Inventory saved: {}", saved.getId());
  }
}