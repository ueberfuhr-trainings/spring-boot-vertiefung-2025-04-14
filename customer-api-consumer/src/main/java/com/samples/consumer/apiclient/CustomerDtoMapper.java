package com.samples.consumer.apiclient;

import com.samples.consumer.domain.Customer;
import com.samples.consumer.domain.CustomerState;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapper {

  public Customer map(CustomerDto source) {
    var target = new Customer();
    target.setUuid(source.getUuid());
    target.setName(source.getName());
    target.setBirthdate(source.getBirthdate());
    target.setState(mapState(source.getState()));
    return target;
  }

  public CustomerState mapState(String state) {
    return null == state ? null : switch (state) {
      case "active" -> CustomerState.ACTIVE;
      case "locked" -> CustomerState.LOCKED;
      case "disabled" -> CustomerState.DISABLED;
      default -> throw new ValidationException("Invalid state: " + state);
    };
  }

  public CustomerDto map(Customer source) {
    var target = new CustomerDto();
    target.setUuid(source.getUuid());
    target.setName(source.getName());
    target.setBirthdate(source.getBirthdate());
    target.setState(mapState(source.getState()));
    return target;
  }

  public String mapState(CustomerState state) {
    return null == state ? null : switch (state) {
      case ACTIVE -> "active";
      case LOCKED -> "locked";
      case DISABLED -> "disabled";
    };
  }

}
