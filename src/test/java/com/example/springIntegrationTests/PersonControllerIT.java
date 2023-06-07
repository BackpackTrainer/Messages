package com.example.springIntegrationTests;

import com.example.controllers.PersonController;
import com.example.model.Person;
import com.example.services.IPersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc
public class PersonControllerIT {

    @MockBean
    IPersonService personService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void testHttpPostRequest() throws Exception {
        String name = "Richy";
        String email = "me@here";

        Person testPerson = new Person(name, email);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(testPerson);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/addperson")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(personService, times(1)).savePerson(any(Person.class));

    }

    @Test
    public void testHttpPostRequestResponse() throws Exception {
        String name = "Richy";
        String email = "me@here";

        Person testPerson = new Person(name, email);

        when(personService.savePerson(any(Person.class))).thenReturn(testPerson);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(testPerson);

        ResultActions resultActions = this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/addperson")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(personService, times(1)).savePerson(any(Person.class));

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Person createdPerson = mapper.readValue(contentAsString, Person.class);

        assertAll("Test that this is the right person",
                () -> assertEquals(testPerson.getName(), createdPerson.getName()),
                () -> assertEquals(testPerson.getEmail(), createdPerson.getEmail())
                );
    }
}
