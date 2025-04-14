package com.samples.customers.boundary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@BoundaryTest
class StaticResourcesTests {

  @Autowired
  MockMvc mockMvc;

  @Test
  void whenGetIndexHtml_thenReturn200() throws Exception {
    mockMvc
      .perform(
        get("/index.html")
      )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.TEXT_HTML));
  }

  @Test
  void whenGetOpenApi_thenReturn200() throws Exception {
    mockMvc
      .perform(
        get("/openapi.yml")
      )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_YAML));
  }

}
