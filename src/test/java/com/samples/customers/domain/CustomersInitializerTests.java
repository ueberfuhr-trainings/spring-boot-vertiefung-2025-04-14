package com.samples.customers.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomersInitializerTests {

  @Autowired
  CustomersService customersService;

  @Test
  void shouldContainAtLeastOneCustomer() {
    assertThat(customersService.count())
      .isGreaterThan(0);
  }

}
