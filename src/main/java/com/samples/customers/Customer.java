package com.samples.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Customer {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private UUID uuid;
  private String name;
  // @JsonProperty("birth_date")
  private LocalDate birthdate;
  private String state;

}
