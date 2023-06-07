package com.example.services;

import com.example.dataAccess.IMessageRepository;
import com.example.dataAccess.IPersonRepository;
import com.example.model.Message;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements IMessageService {

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

    public Message saveMessage(Message savedMessage) {
        Person sender = personRepository.findPersonByName(savedMessage.getSender().getName());
        if(sender == null) {
            sender= new Person(savedMessage.getSender().getName(), savedMessage.getSender().getEmail());
            sender = personRepository.save(sender);
        }

        savedMessage.setSender(sender);
        return messageRepo.save(savedMessage);
    }
}
