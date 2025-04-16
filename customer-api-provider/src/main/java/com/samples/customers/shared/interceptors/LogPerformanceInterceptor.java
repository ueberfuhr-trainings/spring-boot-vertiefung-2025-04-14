package com.samples.customers.shared.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogPerformanceInterceptor implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    final var timeStampBefore = System.currentTimeMillis();
    try {
      return invocation.proceed();
    } finally {
      final var timeStampAfter = System.currentTimeMillis();
      log.info(
        "Method invoked: {} ({}ms)",
        invocation.getMethod().getName(),
        timeStampAfter - timeStampBefore
      );
    }
  }

}
