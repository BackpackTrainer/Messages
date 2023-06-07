package com.example.controllers;

import com.example.model.Message;
import com.example.services.IMessageService;
import com.example.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
public class MessageController {

    IMessageService messageService;

    @GetMapping("/message")
    public String respond()  {
        return messageService.receivedMessage();
    }

    @GetMapping("/messages")
    public Iterable<Message> getAllMessages()  {
        return messageService.findAll();
    }

    @GetMapping("/messages/{id}")
    public Message getMessageById(@PathVariable Long id)  {
       Message m = messageService.getMessageById(id);
       return m;
    }

    @GetMapping("/bySender/{name}")
    public Iterable<Message> getMessagesBySender(@PathVariable String name) {
        return messageService.getMessageBySender(name);
    }

    @PostMapping("/addMessage")
    public ResponseEntity createMessage(@RequestBody Message message) throws URISyntaxException {
        Message savedMessage = messageService.saveMessage(message);
//        return ResponseEntity.created(new URI("/messages/" +savedMessage.getId())).body((savedMessage));
        return ResponseEntity.created(new URI("/addMessage/")).body((savedMessage));
    }

    @Autowired
    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }
}
