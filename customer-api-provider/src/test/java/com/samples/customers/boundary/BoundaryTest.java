package com.samples.customers.boundary;

import com.samples.customers.domain.AutoConfigureDomainMocks;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
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
 *   <li>initializes the whole application, but mocks the domain</li>
 *   <li>configures CORS enabled to allow origins from <tt>*.swagger.io</tt></li>
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
// initialize only @Controller and other web components
@WebMvcTest
// ... and all other @Component from boundary package
@ComponentScan(basePackageClasses = BoundaryTest.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureCors(allowedOrigins = {"*.swagger.io", "test"})
// mock the domain
@AutoConfigureDomainMocks
/* *****************************************
 * N O T   C O N T E X T - R E L E V A N T *
 ***************************************** */
@Tag("boundary-test")
public @interface BoundaryTest {
}
