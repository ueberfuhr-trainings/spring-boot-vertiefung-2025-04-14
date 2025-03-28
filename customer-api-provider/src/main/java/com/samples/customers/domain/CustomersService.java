package com.samples.customers.domain;

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
public class CustomersService {

  private final CustomersSink sink;

  public Stream<Customer> findAll() {
    return sink.findAll();
  }

  public Optional<Customer> findById(UUID id) {
    return sink.findById(id);
  }

  public void create(@Valid Customer customer) {
    sink.create(customer);
  }

  public boolean delete(UUID id) {
    if (!sink.existsById(id)) {
      return false;
    }
    sink.delete(id);
    return true;
  }

  public long count() {
    return sink.count();
  }
}
