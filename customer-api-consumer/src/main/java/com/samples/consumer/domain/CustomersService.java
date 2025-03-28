package com.samples.consumer.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CustomersService {

  private final CustomersSink sink;

  public Flux<Customer> getCustomers() {
    return sink.getCustomers();
  }
}
