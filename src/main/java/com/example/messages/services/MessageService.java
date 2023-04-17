package com.example.messages.services;

import com.example.messages.dataAccess.IMessageRepository;
import com.example.messages.dataAccess.IPersonRepository;
import com.example.messages.model.Message;
import com.example.messages.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    IMessageRepository messageRepo;
    IPersonRepository personRepository;

    public String songResponse()  {
        return "Happy Birthday to You!";
    }

    public String receivedMessage()  {
        return "Message Received";
    }

    public Iterable<Message> findAll() {
        return messageRepo.findAll();
    }

    @Autowired
    public void setRepos(IMessageRepository messageRepo, IPersonRepository personRepository) {
        this.messageRepo = messageRepo;
        this.personRepository = personRepository;
    }

    public Message getMessageById(Long id) {
        Message m = messageRepo.getMessageById(id);
        if(m == null) {
            m = new Message("Message does not exist");
        }
       return m;
    }

    public Iterable<Message> getMessageBySender(String name)  {
        Person sender = personRepository.findPersonByName(name);
        return messageRepo.findMessagesBySender(sender);
    }
}
