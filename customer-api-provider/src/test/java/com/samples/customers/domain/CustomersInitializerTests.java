package com.samples.customers.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
  properties = {
    "application.initialization.enabled=true"
  }
)
//@ActiveProfiles("dev")
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class CustomersInitializerTests {

  @Autowired
  CustomersService customersService;

  @Test
  void shouldContainAtLeastOneCustomer() {
    assertThat(customersService.count())
      .isGreaterThan(0);
  }

}
