package at.mavila.transaction.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerOrderServiceTest {

  @Autowired
  private CustomerOrderService customerOrderService;

  @Test
  void whenOrderServiceIsCalled_thenOrderIsCreated() {

    //It is void method, so we can only check if it is not null
    assertThat(this.customerOrderService).isNotNull();

    //Simulating setting a new order
    this.customerOrderService.processOrder(RandomStringUtils.secureStrong().nextAlphanumeric(10), "POO1", 100L);
    this.customerOrderService.processOrder(RandomStringUtils.secureStrong().nextAlphanumeric(10), "POO1", 100L);
    this.customerOrderService.processOrder(RandomStringUtils.secureStrong().nextAlphanumeric(10), "POO1", 100L);

    assertThatThrownBy(() -> {
      this.customerOrderService.processOrder(RandomStringUtils.secureStrong().nextAlphanumeric(10), "POO1", 800L);
    }).isInstanceOf(InventoryException.class);
  }
}