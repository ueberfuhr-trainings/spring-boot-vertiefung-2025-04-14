package com.samples.consumer.boundary;

import com.samples.consumer.domain.CustomersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

  private final CustomersService customerService;

  @GetMapping(
    produces = MediaType.TEXT_PLAIN_VALUE
  )
  @ResponseBody
  public Flux<String> getCustomerNames() {
    return customerService
      .getCustomers()
      .map(customer -> customer.getName() + "\n");
  }

}
