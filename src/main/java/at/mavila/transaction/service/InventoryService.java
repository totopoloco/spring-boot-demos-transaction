package at.mavila.transaction.service;

import at.mavila.transaction.db.entity.Inventory;
import at.mavila.transaction.db.entity.LogEntry;
import at.mavila.transaction.db.repository.InventoryRepository;
import at.mavila.transaction.db.repository.LogEntryRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class InventoryService {
  private final InventoryRepository inventoryRepository;
  private final LogEntryRepository logEntryRepository;
  private final SmsService smsService;
  private final static long DEFAULT_STOCK = 1000L;

  @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
  public Inventory save(final String item, final long quantity) {
    logStockSetting(item, quantity);
    Inventory inventory = findInventoryByItem(item);
    checkStockAvailability(inventory, item, quantity);
    inventory.setStock(inventory.getStock() - quantity);
    log.info("Saving inventory with item {} and stock {}", item, inventory.getStock());
    return this.inventoryRepository.save(inventory);
  }

  private void logStockSetting(String item, long quantity) {
    LogEntry logEntry = new LogEntry();
    logEntry.setMessage("Setting stock for item " + item + " to " + quantity);
    LogEntry saved = this.logEntryRepository.save(logEntry);
    log.info("Saved log entry with id {}", saved.getId());
  }

  private Inventory findInventoryByItem(String item) {
    Optional<Inventory> byItem = this.inventoryRepository.findByItem(item);

    if (byItem.isPresent()) {
      return byItem.get();
    }

    log.warn("Item {} not found", item);
    this.smsService.sendSms(RandomStringUtils.secureStrong().nextAlphanumeric(10),
        "Item " + item + " not found, saving new item with stock " + DEFAULT_STOCK);
    //When creating a new item, the stock is set to 1000
    return this.inventoryRepository.save(new Inventory(item, DEFAULT_STOCK));
  }

  private void checkStockAvailability(Inventory inventory, String item, long quantity) {

    if (inventory.getStock() >= quantity) {
      return;
    }

    this.smsService.sendSms(RandomStringUtils.secureStrong().nextAlphanumeric(10),
        "Low stock for item " + item + " with stock " + quantity);
    throw new InventoryException("Not enough stock for item " + item);

  }

}
