package com.samples.consumer.apiclient;

import com.samples.consumer.domain.Customer;
import com.samples.consumer.domain.CustomersSink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ApiClientCustomersSink
  implements CustomersSink {

  private final CustomerApiClient client;
  private final CustomerDtoMapper mapper;

  @Override
  public Flux<Customer> getCustomers() {
    return client.getCustomers()
      .map(mapper::map);
  }

}
