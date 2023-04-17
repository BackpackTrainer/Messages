package com.example.messages.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String email;
    @OneToMany
    private List<Message> mySentMessages = new ArrayList<Message>();

    public List<Message> getMySentMessages()  {
        return mySentMessages;
    }
    public void add(Message message) {
            mySentMessages.add(message);
        System.out.println("Add message function called for Person: " + name);
        }

    public Person()  {}

    public Person(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
