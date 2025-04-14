package com.samples.customers.domain;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation providing a Spring context that
 * <ul>
 *   <li>initializes the domain</li>
 *   <li>uses an in-memory implementation of {@link CustomersSink}</li>
 * </ul>
 */
@Target({
  ElementType.ANNOTATION_TYPE,
  ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
/* *********************************
 * C O N T E X T - R E L E V A N T *
 ********************************* */
// do not search for the @SpringBootApplication package-upwards
@SpringBootTest(classes = DomainTest.DomainTestConfiguration.class)
// disable all auto-configurations for database or boundary
@OverrideAutoConfiguration(enabled = false)
// just auto-configure bean validation
@ImportAutoConfiguration({
  ValidationAutoConfiguration.class
})
@ActiveProfiles("test")
/* *****************************************
 * N O T   C O N T E X T - R E L E V A N T *
 ***************************************** */
@Tag("domain-test")
public @interface DomainTest {

  // This will only scan classes within the domain package.
  @Configuration
  @ComponentScan(basePackageClasses = DomainTest.class)
  class DomainTestConfiguration {

    // choose in-memory as primary implementation for sink
    // (no matter which one is configured elsewhere)
    @Primary
    @Bean
    CustomersSink testCustomersSink() {
      return new InMemoryCustomersSink();
    }

  }

  /**
   * Whether the initialization of sample customer data should be
   * enabled or not.
   *
   * @return <tt>true</tt>, if the initialization of sample customer data should be
   * enabled, otherwise <tt>false</tt>
   */
  // This would allow a maximum of 2 contexts.
  @PropertyMapping("application.initialization.enabled")
  boolean initializationEnabled() default false;

}
