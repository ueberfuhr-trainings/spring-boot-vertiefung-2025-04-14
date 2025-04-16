package com.samples.customers;

import com.samples.customers.shared.interceptors.AutoConfigureInterceptorTestComponents;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation providing a Spring context that
 * initializes the whole application.
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
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@AutoConfigureInterceptorTestComponents
/* *****************************************
 * N O T   C O N T E X T - R E L E V A N T *
 ***************************************** */
@Tag("blackbox-test")
public @interface CustomerManagementApiProviderApplicationTest {
}
