package com.samples.customers.infrastructure;

import com.samples.customers.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class LogManager {

  public void logCustomerCreated(Customer customer) {
    log.info("Customer created: {}", customer.getUuid());
  }

  public void logCustomerDeleted(UUID uuid) {
    log.info("Customer deleted: {}", uuid);
  }

}
