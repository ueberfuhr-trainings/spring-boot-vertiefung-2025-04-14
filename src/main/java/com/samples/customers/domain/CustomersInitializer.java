package com.samples.customers.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CustomersInitializer {

  private final CustomersService customersService;

  @EventListener(ContextRefreshedEvent.class)
  public void init() {
    if (customersService.count() < 1) {
      var customer = new Customer();
      customer.setName("John Doe");
      customer.setBirthdate(LocalDate.now().minusYears(20));
      customersService.create(customer);
    }
  }

}
