package com.example.messages.model;
import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private long id;

    private String messageContent;

    @ManyToOne
    private Person sender;

    public Message(String messageContent) {
        this.messageContent = messageContent;
    }

    public Message(String messageContent, Person sender) {
        this.messageContent = messageContent;
        this.sender = sender;
        sender.add(this);
    }

    public String getMessageContent() {
        return messageContent;
    }

    public Message()  {}

    public long getId() {
        return id;
    }
    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }
}
