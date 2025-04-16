package com.samples.customers.domain;

import com.samples.customers.shared.interceptors.LogPerformance;
import com.samples.customers.shared.interceptors.PublishEvent;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Validated
@Service
@RequiredArgsConstructor
@LogPerformance
public class CustomersService {

  private final CustomersSink sink;

  public Stream<Customer> findAll() {
    return sink.findAll();
  }

  public Optional<Customer> findById(UUID id) {
    return sink.findById(id);
  }

  @PublishEvent(CustomerCreatedEvent.class)
  public void create(@Valid Customer customer) {
    sink.create(customer);
  }

  public boolean existsById(UUID id) {
    return sink.existsById(id);
  }

  @PublishEvent(CustomerDeletedEvent.class)
  public void delete(UUID id) {
    if (!sink.existsById(id)) {
      throw new IllegalStateException("Customer with id " + id + " does not exist");
    }
    sink.delete(id);
  }

  public long count() {
    return sink.count();
  }
}
