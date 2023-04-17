package com.example.messages.ServiceLayerTests;

import com.example.messages.dataAccess.IMessageRepository;
import com.example.messages.model.Message;
import com.example.messages.services.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class MessageServiceTests {

    MessageService uut;
    IMessageRepository messageRepository;
    long messageId;

    @BeforeEach
    public void setUp()  {
        uut = new MessageService();
        messageRepository = mock(IMessageRepository.class);
        uut.setRepos(messageRepository, null);
    }

    @Test
    public void TestGettingMessageWithValidId()  {
        messageId = 1;
        Message dummyMessage = new Message();
        when(messageRepository.getMessageById(messageId)).thenReturn(dummyMessage);

        Message actualMessage = uut.getMessageById(messageId);

        verify(messageRepository, times(1)).getMessageById(messageId);
        assertNotEquals("Message does not exist", actualMessage.getMessageContent());
    }

    @Test
    public void TestGettingMessageWithInvalidId()  {
        messageId = 20;
        String expectedContent = "Message does not exist";
        when(messageRepository.getMessageById(messageId)).thenReturn(null);

        Message actualMessage =uut.getMessageById(messageId);

        verify(messageRepository, times(1)).getMessageById(messageId);
        assert(expectedContent.equals(actualMessage.getMessageContent()));

    }


}
