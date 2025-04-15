package com.samples.customers.test;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.test.autoconfigure.properties.AnnotationsPropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

class PrintSpringBootPropertiesExtension
  implements BeforeEachCallback {

  @Override
  public void beforeEach(ExtensionContext context) {
    final var applicationContext = SpringExtension.getApplicationContext(context);
    if (applicationContext.getEnvironment() instanceof ConfigurableEnvironment env) {
      System.out.println("📦 Spring Boot Properties:");

      env
        .getPropertySources()
        .forEach(propertySource -> {
          System.out.println("🔹 Source: " + propertySource.getName());
          if (propertySource.getSource() instanceof java.util.Map<?, ?> map) {
            map.forEach((key, value) -> System.out.println("   " + key + " = " + value));
          } else if (propertySource instanceof AnnotationsPropertySource annotationsPropertySource) {
            for (String key : annotationsPropertySource.getPropertyNames()) {
              System.out.println("   " + key + " = " + annotationsPropertySource.getProperty(key));
            }
          } else {
            System.out.println("   (non-map source, skipping details)");
          }
        });

    } else {
      System.out.println("❌ not able to print Spring Boot Properties");
    }
  }

}
