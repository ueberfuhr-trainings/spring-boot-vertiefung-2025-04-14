package com.samples.customers.persistence;

import com.samples.customers.domain.CustomerState;
import com.samples.customers.shared.validation.Adult;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "Customer") // Name im JPQL
@Table(name = "CUSTOMERS") // DB-Schema
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID uuid;
  @NotNull
  @Size(min = 3, max = 100)
  private String name;
  @NotNull
  @Adult
  @Column(name = "BIRTH_DATE")
  private LocalDate birthdate;
  private CustomerState state = CustomerState.ACTIVE;

}
