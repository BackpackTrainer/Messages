package com.example.messages.controllers;

import com.example.messages.model.Message;
import com.example.messages.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    MessageService messageService;

    @GetMapping("/message")
    public String respond()  {

        return messageService.receivedMessage();
    }

    @GetMapping("/messages")
    public Iterable<Message> getAllMessages()  {

        return messageService.findAll();
    }

    @GetMapping("/messages/{id}")
    public Message getMessagebyId(@PathVariable Long id)  {
       Message m = messageService.getMessageById(id);
       return m;
    }

    @GetMapping("/bySender/{name}")
    public Iterable<Message> getMessagesBySender(@PathVariable String name) {
        return messageService.getMessageBySender(name);
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

}
