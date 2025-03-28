package com.samples.consumer.domain;

import reactor.core.publisher.Flux;

public interface CustomersSink {

  Flux<Customer> getCustomers();

}
