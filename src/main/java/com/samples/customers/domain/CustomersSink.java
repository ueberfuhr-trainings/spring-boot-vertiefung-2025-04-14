package com.samples.customers.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface CustomersSink {

  Stream<Customer> findAll();

  default Optional<Customer> findById(UUID id) {
    return findAll()
      .filter(c -> id.equals(c.getUuid()))
      .findFirst();
  }

  void create(Customer customer);

  default boolean existsById(UUID id) {
    return findById(id)
      .isPresent();
  }

  void delete(UUID id);

  default long count() {
    return findAll()
      .count();
  }

}
