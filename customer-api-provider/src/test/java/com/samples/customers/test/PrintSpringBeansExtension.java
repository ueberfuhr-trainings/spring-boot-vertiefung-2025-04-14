package com.samples.customers.test;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

class PrintSpringBeansExtension
  implements BeforeEachCallback {

  @Override
  @SuppressWarnings("rawtypes")
  public void beforeEach(ExtensionContext context) {
    final var applicationContext = SpringExtension.getApplicationContext(context);
    final var beanNames = applicationContext.getBeanDefinitionNames();
    Arrays.sort(beanNames);
    for (String beanName : beanNames) {
      final var bean = applicationContext.getBean(beanName);
      final var beanType = Optional
        .ofNullable((Class) applicationContext.getType(beanName))
        .orElseGet(bean::getClass);
      System.out.printf("Bean name: %-50s Type: %s%n", beanName, beanType.getName());
    }
  }
}
