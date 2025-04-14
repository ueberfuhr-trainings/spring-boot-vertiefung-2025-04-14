package com.samples.customers.domain;

import com.samples.customers.test.PrintSpringBeans;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DomainTest(initializationEnabled = true)
class CustomersInitializerTests {

  @Autowired
  CustomersService customersService;

  @PrintSpringBeans
  @Test
  void shouldContainAtLeastOneCustomer() {
    assertThat(customersService.count())
      .isGreaterThan(0);
  }

}
