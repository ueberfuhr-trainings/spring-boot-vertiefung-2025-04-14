package com.samples.customers.domain;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class InMemoryCustomersSink
  implements CustomersSink {

  final Map<UUID, Customer> customers = new ConcurrentHashMap<>();

  @Override
  public Stream<Customer> findAll() {
    return customers
      .values()
      .stream();
  }

  @Override
  public Optional<Customer> findById(UUID id) {
    return Optional
      .ofNullable(customers.get(id));
  }

  @Override
  public void create(Customer customer) {
    customer.setUuid(UUID.randomUUID());
    customers.put(customer.getUuid(), customer);
  }

  @Override
  public boolean existsById(UUID id) {
    return customers.containsKey(id);
  }

  @Override
  public void delete(UUID id) {
    customers.remove(id);
  }

  @Override
  public long count() {
    return customers.size();
  }

}
