package com.example.messages.dataAccess;

import com.example.messages.model.Message;
import com.example.messages.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepository  extends CrudRepository<Message, Long> {
    Message getMessageById(Long id);

    Iterable<Message> findMessagesBySender(Person sender);


}
