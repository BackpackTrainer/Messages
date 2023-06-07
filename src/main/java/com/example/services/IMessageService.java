package com.example.services;

import com.example.model.Message;

public interface IMessageService {
    Iterable<Message> findAll();

    Message getMessageById(Long messageId);

    Message saveMessage(Message message);

    Iterable<Message> getMessageBySender(String name);

    String receivedMessage();
}
