package com.samples.customers.boundary;

import com.samples.customers.domain.CustomersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiWithMockedDomainTests {

  @Autowired
  MockMvc mockMvc;
  @MockitoBean
  CustomersService customersService;

  // DELETE customers/{id} -> 204
  @Test
  void shouldReturn204OnDeleteExistingCustomer() throws Exception {
    var uuid = UUID.randomUUID();
    when(customersService.delete(uuid))
      .thenReturn(true);
    mockMvc.perform(
        delete("/customers/{uuid}", uuid)
      )
      .andExpect(status().isNoContent());
  }

  // DELETE customers/{id} -> 404
  @Test
  void shouldReturn404OnDeleteNonExistingCustomer() throws Exception {
    var uuid = UUID.randomUUID();
    when(customersService.delete(uuid))
      .thenReturn(false);
    mockMvc.perform(
        delete("/customers/{uuid}", uuid)
      )
      .andExpect(status().isNotFound());
  }

  @Test
  void shouldNotCreateInvalidCustomer() throws Exception {
    mockMvc
      .perform(
        post("/customers")
          .contentType(MediaType.APPLICATION_JSON)
          .content("""
              {
                "name": "T",
                "birthdate": "1985-07-03",
                "state": "active"
              }
            """)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest());
    verify(customersService, never()).create(any());
  }

}
