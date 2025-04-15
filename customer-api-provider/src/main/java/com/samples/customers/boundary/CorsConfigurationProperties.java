package com.samples.customers.boundary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.cors")
@Getter
@Setter
public class CorsConfigurationProperties {

  private boolean enabled;
  private String[] allowedOrigins = new String[0];

}
