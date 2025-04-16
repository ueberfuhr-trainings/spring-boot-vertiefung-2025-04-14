package com.samples.customers.shared.interceptors;

import com.samples.customers.shared.utilities.AnnotationUtility;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogPerformanceInterceptor implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    final var logPerformanceAnnotation = AnnotationUtility
      .findAnnotation(
        invocation.getMethod(),
        LogPerformance.class
      );
    final var logLevel = logPerformanceAnnotation
      .map(LogPerformance::value)
      .orElse(Level.INFO);
    final var timeStampBefore = System.currentTimeMillis();
    try {
      return invocation.proceed();
    } finally {
      final var timeStampAfter = System.currentTimeMillis();
      log
        .atLevel(logLevel)
        .log(
          "Method invoked: {} ({}ms)",
          invocation.getMethod().getName(),
          timeStampAfter - timeStampBefore
        );
    }
  }

}
