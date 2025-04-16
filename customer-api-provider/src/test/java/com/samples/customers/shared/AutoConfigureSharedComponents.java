package com.samples.customers.shared;

import com.samples.customers.shared.interceptors.AutoConfigureInterceptorTestComponents;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@Documented
@AutoConfigureInterceptorTestComponents
@ComponentScan(basePackageClasses = AutoConfigureInterceptorTestComponents.class)
public @interface AutoConfigureSharedComponents {
}
