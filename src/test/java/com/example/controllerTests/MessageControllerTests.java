package com.example.controllerTests;


import com.example.controllers.MessageController;
import com.example.services.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(MessageController.class)
@AutoConfigureMockMvc
public class MessageControllerTests {

   @MockBean
    private MessageService messageService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAllMessages()  throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(messageService, times(1)).findAll();
    }
}
