package com.samples.customers.persistence;

import com.samples.customers.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityMapper {

  public CustomerEntity map(Customer source) {
    if (null == source) {
      return null;
    }
    var target = new CustomerEntity();
    target.setUuid(source.getUuid());
    target.setName(source.getName());
    target.setBirthdate(source.getBirthdate());
    target.setState(source.getState());
    return target;
  }

  public Customer map(CustomerEntity source) {
    if (null == source) {
      return null;
    }
    var target = new Customer();
    target.setUuid(source.getUuid());
    target.setName(source.getName());
    target.setBirthdate(source.getBirthdate());
    target.setState(source.getState());
    return target;
  }

  public void copy(CustomerEntity source, Customer target) {
    target.setUuid(source.getUuid());
    target.setName(source.getName());
    target.setBirthdate(source.getBirthdate());
    target.setState(source.getState());
  }


}
