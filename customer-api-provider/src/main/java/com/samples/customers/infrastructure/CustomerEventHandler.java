package com.samples.customers.infrastructure;

import com.samples.customers.domain.CustomerCreatedEvent;
import com.samples.customers.domain.CustomerDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerEventHandler {

  private final LogManager logManager;

  @EventListener
  void onCustomerCreated(CustomerCreatedEvent event) {
    logManager.logCustomerCreated(event.customer());
  }

  @EventListener
  void onCustomerDeleted(CustomerDeletedEvent event) {
    logManager.logCustomerDeleted(event.uuid());
  }

}
