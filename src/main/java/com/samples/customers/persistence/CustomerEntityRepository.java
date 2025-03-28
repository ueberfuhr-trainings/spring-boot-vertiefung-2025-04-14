package com.samples.customers.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerEntityRepository
  extends JpaRepository<CustomerEntity, UUID> {

//  List<CustomerEntity> findAllByBirthdateBefore(LocalDate birthdate);

//  @Query("select c from Customer c where c.state = :state")
//  List<CustomerEntity> findSomething(@Param("state") CustomerState state);

}
