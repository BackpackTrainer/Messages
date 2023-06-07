package FullIntegrationTests;


import com.example.MessagesApplication;
import com.example.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MessagesApplication.class)
@AutoConfigureMockMvc
public class ControllerToDBTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void testGetMessageFromHttpRequestById2() throws Exception {
        Long messageId = 2L;

        String expectedContent = "Hello World";

        ResultActions resultActions = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/messages/"+ messageId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Message actualMessage = mapper.readValue(contentAsString, Message.class);

        assertEquals(expectedContent, actualMessage.getMessageContent());
    }

    @ParameterizedTest
    @CsvSource({"2, Hello World", "3, GLHF!", "5, Kamila says hi"})
    public void testGetMessageFromHttpRequestById(Long messageId, String expectedContent) throws Exception {

        ResultActions resultActions = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/messages/"+ messageId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Message actualMessage = mapper.readValue(contentAsString, Message.class);

        assertEquals(expectedContent, actualMessage.getMessageContent());
    }

    @Test
    public void testGetAlMessageFromHttpRequest() throws Exception {

        ResultActions resultActions = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/messages/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Message[] messages = mapper.readValue(contentAsString, Message[].class);

        assertEquals(3, messages.length);
    }
    
    @Test
    public void testAddingMessageFromPostRequest() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/addMessage/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"messageContent\" : \"message from test Post\", \"sender\" : {"+
                                    "\"email\" : \"you@there\","+
                                    "\"name\" : \"Churina\" }}")
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Message createdMessage = mapper.readValue(contentAsString, Message.class);

        assertAll("Testing that a message was created",
                () -> assertTrue(createdMessage.getSender().getName().equals("Churina")),
                () -> assertTrue(createdMessage.getSender().getEmail().equals("you@there")),
                () -> assertTrue(createdMessage.getMessageContent().equals("message from test Post"))
                );
    }
}
