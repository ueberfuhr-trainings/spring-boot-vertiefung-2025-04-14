package com.samples.customers.persistence;

import com.samples.customers.domain.Customer;
import com.samples.customers.domain.CustomersSink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JpaCustomersSink implements CustomersSink {

  private final CustomerEntityMapper mapper;
  private final CustomerEntityRepository repo;

  @Override
  public Stream<Customer> findAll() {
    return repo
      .findAll()
      .stream()
      .map(mapper::map);
  }

  @Override
  public Optional<Customer> findById(UUID id) {
    return repo
      .findById(id)
      .map(mapper::map);
  }

  @Override
  public void create(Customer customer) {
    var entity = mapper.map(customer);
    repo.save(entity);
    // customer.setUuid(entity.getUuid());
    mapper.copy(entity, customer);
  }

  @Override
  public boolean existsById(UUID id) {
    return repo.existsById(id);
  }

  @Override
  public void delete(UUID id) {
    repo.deleteById(id);
  }

  @Override
  public long count() {
    return repo.count();
  }
}
