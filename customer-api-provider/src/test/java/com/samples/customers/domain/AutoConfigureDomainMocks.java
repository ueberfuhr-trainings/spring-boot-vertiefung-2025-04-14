package com.samples.customers.domain;

import org.junit.jupiter.api.Tag;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation at your test slices to get
 * the domain classes mocked. It is not intended
 * to be used to directly annotate a test class.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
// context-relevant
@MockitoBean(types = {
  CustomersService.class
})
// nicht context-relevant
@Tag("mocked-domain")
public @interface AutoConfigureDomainMocks {
}
