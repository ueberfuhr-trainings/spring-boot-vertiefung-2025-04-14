package com.samples.customers.test;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
  ElementType.TYPE,
  ElementType.METHOD,
  ElementType.ANNOTATION_TYPE
})
@Documented
@Inherited
@ExtendWith(PrintSpringBootPropertiesExtension.class)
public @interface PrintSpringBootProperties {
}
