package com.samples.customers.domain;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DomainTest
class CustomersServiceTests {

  @Autowired
  CustomersService customersService;

  @Test
  void shouldValidateCustomerWhenCreated() {
    var customer = new Customer();
    customer.setName("J");
    customer.setBirthdate(LocalDate.now().minusYears(20));
    assertThatThrownBy(() -> customersService.create(customer))
      .isInstanceOf(ValidationException.class);
  }

}
