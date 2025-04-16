package com.samples.customers.shared.interceptors;

import org.springframework.boot.test.context.TestComponent;

@TestComponent
@LogPerformance
public class LogPerformanceTestAtClassLevelService {

  void doSth() {
  }

}
