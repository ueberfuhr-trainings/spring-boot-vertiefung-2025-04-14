package com.samples.customers.domain;

import com.samples.customers.test.PrintSpringBeans;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.ApplicationEvents;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DomainTest
class CustomersServiceEventsTests {

  @Autowired
  CustomersService customersService;
  @Autowired
  ApplicationEvents events;

  @PrintSpringBeans
  @Test
  void shouldPublishEventOnCreateValidCustomer() {
    var customer = new Customer();
    customer.setName("John");
    customer.setBirthdate(LocalDate.now().minusYears(20));

    // create and check
    customersService.create(customer);
    assertThat(events.stream(CustomerCreatedEvent.class))
      .hasSize(1)
      .first()
      .extracting(CustomerCreatedEvent::customer)
      .isSameAs(customer);
  }

  @Test
  void shouldNotPublishEventOnCreateInvalidCustomer() {
    var customer = new Customer();
    customer.setName("J");
    customer.setBirthdate(LocalDate.now().minusYears(20));
    assertThatThrownBy(() -> customersService.create(customer))
      .isInstanceOf(ValidationException.class);
    assertThat(events.stream(CustomerCreatedEvent.class))
      .isEmpty();
  }

  @Test
  void shouldPublishEventOnDeleteCustomer() {
    var customer = new Customer();
    customer.setName("John");
    customer.setBirthdate(LocalDate.now().minusYears(20));
    customersService.create(customer);
    events.clear(); // reset events

    // delete and check
    customersService.delete(customer.getUuid());
    assertThat(events.stream(CustomerDeletedEvent.class))
      .hasSize(1)
      .first()
      .extracting(CustomerDeletedEvent::uuid)
      .isEqualTo(customer.getUuid());
  }

  @Test
  void shouldNotPublishEventOnDeleteNonExistingCustomer() {
    var customer = new Customer();
    customer.setName("John");
    customer.setBirthdate(LocalDate.now().minusYears(20));
    customersService.create(customer);
    customersService.delete(customer.getUuid());
    events.clear(); // reset events

    // delete and check
    assertThatThrownBy(() -> customersService.delete(customer.getUuid()))
      .isNotNull();
    assertThat(events.stream(CustomerCreatedEvent.class))
      .isEmpty();

  }


}
