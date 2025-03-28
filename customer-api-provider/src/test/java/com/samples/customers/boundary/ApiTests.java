package com.samples.customers.boundary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class ApiTests {

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldReturnCustomers() throws Exception {
    mockMvc
      .perform(
        get("/customers")
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(content().string(startsWith("[")))
      .andExpect(content().string(endsWith("]")));
  }

  /*
   * Testfall:
   *  - POST /customers mit {name, birthdate,state}
   *    -> 201 Statuscode
   */
  @Test
  void shouldCreateAndReturnCustomer() throws Exception {
    var location = mockMvc
      .perform(
        post("/customers")
          .contentType(MediaType.APPLICATION_JSON)
          .content("""
                        {
                          "name": "Tom Mayer",
                          "birthdate": "1985-07-03",
                          "state": "active"
                        }
            """)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isCreated())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.name").value("Tom Mayer"))
      .andExpect(jsonPath("$.birthdate").value("1985-07-03"))
      .andExpect(jsonPath("$.state").value("active"))
      .andExpect(jsonPath("$.uuid").exists())
      .andExpect(header().exists("Location"))
      .andReturn()
      .getResponse()
      .getHeader("Location");

    assertThat(location).isNotBlank();

    mockMvc
      .perform(
        get(location)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.name").value("Tom Mayer"))
      .andExpect(jsonPath("$.birthdate").value("1985-07-03"))
      .andExpect(jsonPath("$.state").value("active"));

  }

  @Test
  void shouldNotCreateXmlCustomer() throws Exception {
    mockMvc
      .perform(
        post("/customers")
          .contentType(MediaType.APPLICATION_XML)
          .content("<customer />")
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isUnsupportedMediaType());
  }

  @Test
  void shouldDeleteCustomer() throws Exception {
    var location = mockMvc
      .perform(
        post("/customers")
          .contentType(MediaType.APPLICATION_JSON)
          .content("""
                        {
                          "name": "Tom Mayer",
                          "birthdate": "1985-07-03",
                          "state": "active"
                        }
            """)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isCreated())
      .andExpect(header().exists("Location"))
      .andReturn()
      .getResponse()
      .getHeader("Location");

    assertThat(location).isNotBlank();

    // DELETE {location} -> 204
    mockMvc
      .perform(
        delete(location)
      )
      .andExpect(status().isNoContent());

    // DELETE {location} -> 404
    mockMvc
      .perform(
        delete(location)
      )
      .andExpect(status().isNotFound());

    // GET {location} -> 404
    mockMvc
      .perform(
        get(location)
      )
      .andExpect(status().isNotFound());

  }

  @ParameterizedTest
  @ValueSource(strings = {
    // no name
    """
        {
          "birthdate": "1985-07-03",
          "state": "active"
        }
      """,
    // short name
    """
        {
          "name": "T",
          "birthdate": "1985-07-03",
          "state": "active"
        }
      """,
    // long name
    """
        {
          "name": "T0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789",
          "birthdate": "1985-07-03",
          "state": "active"
        }
      """,
    // no birthdate
    """
        {
          "name": "Tom Mayer",
          "state": "active"
        }
      """,
    // invalid birthdate
    """
        {
          "name": "Tom Mayer",
          "birthdate": "gelbekatze",
          "state": "active"
        }
      """,
    // invalid state
    """
        {
          "name": "Tom Mayer",
          "birthdate": "1985-07-03",
          "state": "gelbekatze"
        }
      """,
    // unknown property
    """
        {
          "gender": "male",
          "name": "Tom Mayer",
          "birthdate": "1985-07-03",
          "state": "active"
        }
      """,
  })
  void shouldNotCreateInvalidCustomer(String body) throws Exception {
    mockMvc
      .perform(
        post("/customers")
          .contentType(MediaType.APPLICATION_JSON)
          .content(body)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest());
  }

  @Test
  void shouldNotCreateCustomerWithBirthdateInTheFuture() throws Exception {
    shouldNotCreateInvalidCustomer(
      String.format("""
          {
            "name": "Tom Mayer",
            "birthdate": "%s",
            "state": "gelbekatze"
          }
          """,
        LocalDate
          .now()
          .plusDays(1)
          .format(DateTimeFormatter.ISO_DATE)
      )
    );
  }

}
