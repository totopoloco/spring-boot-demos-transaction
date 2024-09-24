package at.mavila.transaction.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import at.mavila.transaction.db.entity.Inventory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InventoryServiceTest {

  @Autowired
  private InventoryService inventoryService;

  @Test
  void saveInventory() {
    final String product = "P001";
    final Inventory savedInventory0 = this.inventoryService.save(product, 102L);
    assertThat(savedInventory0).isNotNull();
    assertThat(savedInventory0.getItem()).isEqualTo(product);
    assertThat(savedInventory0.getStock()).isEqualTo(898L);

    final Inventory savedInventory1 = this.inventoryService.save(product, 102L);
    assertThat(savedInventory1).isNotNull();
    assertThat(savedInventory1.getItem()).isEqualTo(product);
    assertThat(savedInventory1.getStock()).isEqualTo(796L);

    //Assert the exception is thrown
    assertThatThrownBy(() -> this.inventoryService.save(product, 800L))
        .isInstanceOf(InventoryException.class)
        .hasMessageContaining("Not enough stock");
  }

}