package com.example.springIntegrationTests;

import com.example.controllers.MessageController;
import com.example.model.Message;
import com.example.services.IMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(MessageController.class)
@AutoConfigureMockMvc
public class MessageControllerIT {

    @MockBean
    IMessageService messageService;

   @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void testGetAllMessagesFromHttpRequest() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(messageService, times(1)).findAll();
    }

    @Test
    public void testGetMessageFromHttpRequestById() throws Exception {
        Long messageId = 2L;
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/messages/"+ messageId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(messageService, times(1)).getMessageById(messageId);
    }

    @Test
    public void testGetMessageFromHttpRequestById2() throws Exception {
        Long messageId = 2L;
        Message expectedMessage = createDummyMessage();

        when(messageService.getMessageById(messageId)).thenReturn(expectedMessage);

        ResultActions resultActions = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/messages/"+ messageId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Message actualMessage = mapper.readValue(contentAsString, Message.class);

        assertEquals(expectedMessage.getMessageContent(), actualMessage.getMessageContent());
    }

    private Message createDummyMessage()  {
        return new Message("Dummy Message");
    }
}
